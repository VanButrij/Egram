package com.example.ergam.ui.screens

import androidx.fragment.app.Fragment
import com.example.ergam.utilits.APP_ACTIVITY


open class BaseFragment(layout:Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }

}