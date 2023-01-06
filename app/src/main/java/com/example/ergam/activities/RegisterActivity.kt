package com.example.ergam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.ergam.R
import com.example.ergam.databinding.ActivityRegisterBinding
import com.example.ergam.ui.fragments.EnterPhoneNumberFragment
import com.example.ergam.utilits.initFirebase
import com.example.ergam.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolBar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        mToolBar = mBinding.registerToolbar
        setSupportActionBar(mToolBar)
        title = getString(R.string.register_title_your_phone)

        replaceFragment(EnterPhoneNumberFragment(), false)
    }
}