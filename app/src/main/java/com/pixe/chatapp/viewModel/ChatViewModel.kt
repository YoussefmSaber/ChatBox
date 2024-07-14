package com.pixe.chatapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixe.chatapp.data.entity.Message
import com.pixe.chatapp.data.network.SupabaseClient.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ChatViewModel : ViewModel() {

    private val _userMessage = MutableStateFlow<List<Message>>(emptyList())
    val userMessageLive: StateFlow<List<Message>> = _userMessage.asStateFlow()

    fun sendMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                supabase.from("messages").insert(message)
                _userMessage.value += message
                // Optionally update _userMessages here if needed for immediate UI refresh
            } catch (e: Exception) {
                // Handle error, e.g., log or display error message
                Log.e("ChatViewModel", "Error sending message", e)
            }
        }
    }

    fun getCurrentChat() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = supabase.from("messages").select().decodeList<Message>()
                _userMessage.value = response
            } catch (e: Exception) {
                // Handle error
                Log.e("ChatViewModel", "Error fetching chat", e)
            }
        }
    }

    fun getUserLastMessages(userId: String) {
        viewModelScope.launch {
            val lastMessages = getLastMessagesForUser(userId)
            Log.d("Last Messages", "getUserLastMessages: $lastMessages")
            // Consider updating UI or state with lastMessages if needed
        }
    }

    private suspend fun getLastMessagesForUser(userId: String): List<Message> {
        return withContext(Dispatchers.IO) {
            try {
                supabase
                    .from("messages")
                    .select {
                        order("created_at", order = Order.DESCENDING)
                        filter {
                            eq("receiver", userId)
                        }
                    }
                    .decodeList<Message>()
                    .groupBy { it.sender }
                    .map { (_, messages) -> messages.first() }
            } catch (e: Exception) {
                // Handle error
                Log.e("ChatViewModel", "Error fetching last messages", e)
                emptyList() // Return an empty list on error
            }
        }
    }

    fun getUserChatMessages(myId: String, otherPersonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = supabase.from("messages").select {
                    filter {
                        or {
                            eq("sender", myId)
                            eq("receiver", myId)
                            eq("sender", otherPersonId)
                            eq("receiver", otherPersonId)
                        }

                    }
                }.decodeList<Message>()
                _userMessage.value = response
                // Use the response as needed, e.g., update UI or state
            } catch (e: Exception) {
                // Handle error
                Log.e("ChatViewModel", "Error fetching chat messages", e)
            }
        }
    }

    fun realtimeDb(scope: CoroutineScope) {
        viewModelScope.launch {
            try {
                val channel = supabase.channel("chat_messages")
                val dataFlow = channel.postgresChangeFlow<PostgresAction>(schema = "public")

                dataFlow.onEach {
                    when (it) {
                        is PostgresAction.Insert -> {
                            Log.d("INSERT", "realtimeDb: Inserted the data")
                        }

                        is PostgresAction.Delete -> {
                            Log.d("DELETE", "realtimeDb: Deleted the data")
                        }

                        is PostgresAction.Select -> {
                            Log.d("SELECT", "realtimeDb: Retrieved the data")
                            val NewMessages =
                                Json.decodeFromString<List<Message>>(it.record.toString())
                            _userMessage.value = NewMessages
                        }

                        is PostgresAction.Update -> {
                            _userMessage.value = _userMessage.value.map { message ->
                                val newMessage =
                                    Json.decodeFromString<Message>(it.record.toString())
                                if (message.id == newMessage.id)
                                    newMessage
                                else
                                    message
                            }
                            it.record
                            Log.d("UPDATE", "realtimeDb: Data Updated ${it.record}")
                        }
                    }
                }.launchIn(scope)
                channel.subscribe()
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error fetching chat messages", e)
            }
        }
    }
}