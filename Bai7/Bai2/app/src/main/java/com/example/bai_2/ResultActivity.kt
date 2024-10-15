package com.example.bai_2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    private var ketQua : Float = 0.0f
    private lateinit var btnBack : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ketQua = intent.getFloatExtra("KET_QUA", 0.0f)
        // Gán giá trị kết quả vào txt_ket_qua
        val txtKetQua = findViewById<TextView>(R.id.txt_ket_qua)
        txtKetQua.text = ketQua.toString()
        btnBack = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
    }
}