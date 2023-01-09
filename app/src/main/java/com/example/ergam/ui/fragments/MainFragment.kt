package com.example.ergam.ui.fragments

import androidx.fragment.app.Fragment
import com.example.ergam.R
import com.example.ergam.utilits.APP_ACTIVITY
import com.example.ergam.utilits.hideKeyboard


class MainFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Egram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }
}