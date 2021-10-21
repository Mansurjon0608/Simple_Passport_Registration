package com.example.pasportregister


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }


    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()

        findNavController(R.id.my_navigation_host).navigateUp()
    }
}