package com.example.ceibaapp.framework

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ceibaapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //const
    private val REQUESTPERMISSION = 123

    lateinit var mainActivityBinding: ActivityMainBinding

    private val mainActivitySharedViewModel: MainActivitySharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainActivityBinding.root
        setContentView(view)

        subscribeObservers()
        checkPermissions()
    }

    private fun subscribeObservers() {
        mainActivitySharedViewModel.isShowProgressBar.observe(this, { isShow ->
            if (isShow)
                mainActivityBinding.pgMain.visibility = View.VISIBLE
            else
                mainActivityBinding.pgMain.visibility = View.GONE
        })
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(application, Manifest.permission.ACCESS_NETWORK_STATE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            this.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.READ_PHONE_STATE),
                    REQUESTPERMISSION
                )
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUESTPERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(
                        this,
                        "Se deben dar permisos para poder continuar",
                        Toast.LENGTH_LONG
                    ).show()
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }
}
