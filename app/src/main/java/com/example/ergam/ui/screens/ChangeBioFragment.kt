package com.example.ergam.ui.screens

import com.example.ergam.R
import com.example.ergam.database.USER
import com.example.ergam.database.setBioToDatabase
import kotlinx.android.synthetic.main.fragment_change_bio.*


class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio:String = settings_input_bio.text.toString()

        setBioToDatabase(newBio)
    }

}