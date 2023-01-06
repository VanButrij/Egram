package com.example.ergam.ui.fragments

import androidx.fragment.app.Fragment
import com.example.ergam.MainActivity
import com.example.ergam.R
import com.example.ergam.activities.RegisterActivity
import com.example.ergam.utilits.*
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(private val phoneNumber: String,val id: String) : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher{

                val string = register_input_code.text.toString()
                if (string.length == 6) {
                    enterCode()
                }
            })
        }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val uid:String = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String,Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = phoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT
                    .child(NODE_USERS)
                    .child(uid)
                    .updateChildren(dateMap)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful) {
                            showToast("Добро пожаловать")
                            (activity as RegisterActivity).replaceActivity(MainActivity())
                        } else showToast(task.exception?.message.toString())
                    }

                showToast(getString(R.string.register_greetings))
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else showToast(it.exception?.message.toString())
        }
    }
}