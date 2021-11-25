package com.example.intentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MenuThanksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)

        // リストから渡されたデータを取得
        val menuName = intent.getStringExtra("menuName")
        val menuPrice = intent.getStringExtra("menuPrice")

        // 定食名と金額を表示させるTextViewを取得
        val tvMenuName: TextView = findViewById(R.id.tvMenuName)
        val tvMenuPrice: TextView = findViewById(R.id.tvMenuPrice)

        // TextViewに定食名と金額を表示
        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice
    }

    fun onBackButtonClick(view: View) {
        finish()
    }
}