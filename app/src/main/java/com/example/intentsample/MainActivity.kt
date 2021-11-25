package com.example.intentsample

import android.app.LauncherActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // mutableListは変更可能なList, mutableListOf()で空配列の生成
        // menuListは<String, String>の辞書型を格納した配列
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        var menu1 = mutableMapOf("name" to "唐揚げ定食", "price" to "800円")
        var menu2 = mutableMapOf("name" to "ハンバーグ定食", "price" to "850円")
        var menu3 = mutableMapOf("name" to "生姜焼き定食", "price" to "850円")
        var menu4 = mutableMapOf("name" to "ステーキ定食", "price" to "1000円")
        menuList.add(menu1)
        menuList.add(menu2)
        menuList.add(menu3)
        menuList.add(menu4)

        /*
        adapterの引数：
        １）コンテキスト(thisでActivityを渡す)、２）data(リストに渡すデータ)、
        ３）resource: Int(リストビュー各行のレイアウトを表すR値 ※今回はAndroidSDKデフォルトの2行表示セットを使用)、
        ４）from: Array<String>...各画面部品に割り当てるデータを表すMutableMapのキー名配列
        ５）to: IntArray...from記載のMutableMapのキー名に対応してデータを割り当てられる画面部品のR値配列
         */
        val from = arrayOf("name", "price") // text1, text2にそれぞれ対応
        val to = intArrayOf(android.R.id.text1, android.R.id.text2) // SDKが用意しているデフォルトのUI部品(simple_list_item_2が所持)
        val adapter = SimpleAdapter(this@MainActivity, menuList, android.R.layout.simple_list_item_2, from, to)
        lvMenu.adapter = adapter

        // リストがタップされたときの処理
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMutableMap型
            val item = parent?.getItemAtPosition(position) as MutableMap<String, String>
            // 定食名と金額を取得
            val menuName = item["name"]
            val menuPrice = item["price"]
            // インテントオブジェクトを生成
            val intent2MenuThanks = Intent(this@MainActivity, MenuThanksActivity::class.java)
            // 第2画面へ送るデータを格納 ※上記に.apply{}で書くこともできる
            intent2MenuThanks.putExtra("menuName", menuName)
            intent2MenuThanks.putExtra("menuPrice", menuPrice)
            // 第2画面の起動
            startActivity(intent2MenuThanks)
        }

    }
}