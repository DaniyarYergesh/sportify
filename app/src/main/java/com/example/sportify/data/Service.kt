package com.example.sportify.data

import com.example.sportify.entity.SportEvent
import com.example.sportify.entity.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

object Service {

    private val auth: FirebaseAuth = Firebase.auth
    private val usersRef = FirebaseDatabase.getInstance().getReference("users")
    private val eventsRef = FirebaseDatabase.getInstance().getReference("events")

    private fun getCurrentUser() = auth.currentUser

    fun getEventsDataRef() = eventsRef

    fun getUsersDataRef() = usersRef

    fun createUser(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun createNewUserToDB(user: User) {
        usersRef.child(getCurrentUser()?.uid ?: "").setValue(user)
    }

    fun createNewEventToDB(event: SportEvent): Task<Void> {
        val key = eventsRef.push().key ?: ""
        return eventsRef.child(key).setValue(event)
    }

    fun subscribeToEvent(eventId: String) {
        usersRef.child(getCurrentUser()?.uid ?: "").child("events").child(eventId).setValue(true)
        eventsRef.child("participants").child(getCurrentUser()?.uid.orEmpty()).setValue(true);
    }

}
