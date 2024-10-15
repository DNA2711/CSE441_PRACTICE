package com.example.bai_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnKetQua : Button
    private var soA: Int = 0
    private var soB: Int = 0
    private var ketQua: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Lấy giá trị từ EditText và chuyển đổi sang kiểu Int
        btnKetQua = findViewById(R.id.btn_ket_qua)

        // Hiển thị kết quả sang txt_ket_qua trong ResultActivity
        btnKetQua.setOnClickListener {
            // try catch để bắt lỗi khi người dùng nhập vào không phải số
            soA = try {
                findViewById<EditText>(R.id.txt_a).text.toString().toInt()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Số a vui lòng nhập số", Toast.LENGTH_SHORT).show()
                0
            }

            soB = try {
                findViewById<EditText>(R.id.txt_b).text.toString().toInt()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Số b vui lòng nhập số", Toast.LENGTH_SHORT).show()
                0
            }

            // Giải phương trình bậc nhất
            ketQua = if (soA == 0) {
                if (soB == 0) {
                    Float.POSITIVE_INFINITY
                } else {
                    Float.NEGATIVE_INFINITY
                }
            } else {
                -soB.toFloat() / soA
            }
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("KET_QUA", ketQua)
            startActivity(intent)
        }
    }
}