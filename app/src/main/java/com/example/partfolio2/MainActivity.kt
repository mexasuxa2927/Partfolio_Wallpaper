package com.example.partfolio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.partfolio2.app.App
import com.example.partfolio2.viewmodel.MyViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponents.inject(this)
        setContentView(R.layout.activity_main)


    }
}