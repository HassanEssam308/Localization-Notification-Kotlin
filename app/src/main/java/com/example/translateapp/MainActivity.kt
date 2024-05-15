package com.example.translateapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private  lateinit var  btn_en : Button
    private  lateinit var  btn_ar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn_en =findViewById(R.id.Main_Btn_EN)
        btn_ar =findViewById(R.id.Main_Btn_AR)

        btn_en.setOnClickListener {
            val intent=Intent(this,ParagraphActivity() ::class.java)
            MyLocale(this,"en").setNewLocale()
//            intent.putExtra("language_key","en")
            startActivity(intent)
        }

        btn_ar.setOnClickListener {
            val intent=Intent(this,ParagraphActivity() ::class.java)
            MyLocale(this,"ar").setNewLocale()

//            intent.putExtra("language_key","ar")
            startActivity(intent)
        }
    }
}