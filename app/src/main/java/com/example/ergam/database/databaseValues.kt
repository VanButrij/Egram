package com.example.ergam.database

import com.example.ergam.models.UserModel
import com.example.ergam.utilits.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ServerValue
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_UID:String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var USER: UserModel
const val NODE_USERS = "users"
const val NODE_MESSAGES = "messages"
const val NODE_USERNAMES = "usernames"
const val NODE_PHONES = "phones"
const val NODE_PHONES_CONTACTS = "phones_contacts"
const val FOLDER_PROFILE_IMAGE = "profile_image"
const val FOLDER_FILES = "messages_files"
const val TYPE_TEXT = "text"
const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullname"
const val CHILD_BIO = "bio"
const val CHILD_PHOTO_URL = "photoUrl"
const val CHILD_STATE = "state"
const val CHILD_TEXT = "text"
const val CHILD_TYPE = "type"
const val CHILD_FROM = "from"
const val CHILD_TIMESTAMP = "timeStamp"
const val CHILD_FILE_URL = "fileUrl"
fun sendMessageAsFile(receivingUserID: String, fileUrl: String, messageKey: String, typeMessage: String, filename: String) {

   val refDialogUser = "$NODE_MESSAGES/$CURRENT_UID/$receivingUserID"
   val refDialogReceivingUser = "$NODE_MESSAGES/$receivingUserID/$CURRENT_UID"

   val mapMessage = hashMapOf<String, Any>()
   mapMessage[CHILD_FROM] = CURRENT_UID
   mapMessage[CHILD_TYPE] = typeMessage
   mapMessage[CHILD_ID] = messageKey
   mapMessage[CHILD_TIMESTAMP] = ServerValue.TIMESTAMP
   mapMessage[CHILD_FILE_URL] = fileUrl
   mapMessage[CHILD_TEXT] = filename

   val mapDialog = hashMapOf<String, Any>()
   mapDialog["$refDialogUser/$messageKey"] = mapMessage
   mapDialog["$refDialogReceivingUser/$messageKey"] = mapMessage

   REF_DATABASE_ROOT
       .updateChildren(mapDialog)
       .addOnFailureListener { showToast(it.message.toString()) }

}