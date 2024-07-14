package com.pixe.chatapp.data.network

import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
import kotlin.time.Duration.Companion.milliseconds

object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9ldmVnb3ViYWZoYmJ1bGZnYWtjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjA2MzE5OTAsImV4cCI6MjAzNjIwNzk5MH0.yShR-dtGLLZPzWBPEu6sYULALZ16NzSTOiiA1KBr2Dg",
        supabaseUrl = "https://oevegoubafhbbulfgakc.supabase.co"
    ) {
        install(Auth)
        install(ComposeAuth) {
            googleNativeLogin(
                serverClientId = "295490986484-266g1rt0j75313345osmodajfdgg1e7u.apps.googleusercontent.com"
            )
        }
        install(Storage)
        install(Postgrest)
        install(Realtime)
    }
}