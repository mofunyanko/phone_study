package com.example.recyclerviewsample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)
        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        toolbarLayout.title = getString(R.string.toolbar_title)
        toolbarLayout.setExpandedTitleColor(Color.WHITE)
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY)

        // RecyclerViewを取得
        val lvMenu = findViewById<RecyclerView>(R.id.lvMenu)
        // LinearLayoutManagerオブジェクトを生成
        val layout = LinearLayoutManager(this@MainActivity)
        // RecyclerViewにレイアウトマネージャーとしてLinearLayoutManagerを設定
        lvMenu.layoutManager = layout
        // 定食メニューリストデータを生成
        val menuList = createTeishokuList()
        // アダプタオブジェクトを生成
        val adapter = RecyclerListAdapter(menuList)
        // RecyclerViewにアダプタオブジェクトを生成
        lvMenu.adapter = adapter

        // 区切り線専用のオブジェクトを生成
        val decorator = DividerItemDecoration(this@MainActivity, layout.orientation)
        // RecyclerViewに区切り線オブジェクトを設定
        lvMenu.addItemDecoration(decorator)
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        // 定食メニューリスト用のListオブジェクトを用意
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        // 「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        var menu = mutableMapOf<String, Any>("name" to "から揚げ定食", "price" to 800,
            "desc" to "から揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to 850,
            "desc" to "ハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「生姜焼き定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to 850,
            "desc" to "生姜焼きにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「ステーキ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ステーキ定食", "price" to 1000,
            "desc" to "ステーキにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「野菜炒め定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to 750,
            "desc" to "野菜炒めにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「とんかつ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "とんかつ定食", "price" to 900,
            "desc" to "とんかつにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「ミンチかつ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to 850,
            "desc" to "ミンチかつにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「チキンカツ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "チキンカツ定食", "price" to 900,
            "desc" to "チキンカツにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「コロッケ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "コロッケ定食", "price" to 850,
            "desc" to "コロッケにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「回鍋肉定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "回鍋肉定食", "price" to 750,
            "desc" to "回鍋肉にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「麻婆豆腐定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "麻婆豆腐定食", "price" to 800,
            "desc" to "麻婆豆腐にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「青椒肉絲定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "青椒肉絲定食", "price" to 850,
            "desc" to "青椒肉絲にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「焼き魚定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "焼き魚定食", "price" to 700,
            "desc" to "焼き魚にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // 「焼肉定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "焼肉定食", "price" to 950,
            "desc" to "焼肉にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        return menuList
    }

    private inner class RecyclerListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // リスト1行文中でメニュー名を表示する画面部品
        var _tvMenuNameRow: TextView
        // リスト1行文中でメニュー金額を表示する画面部品
        var _tvMenuPriceRow: TextView

        init {
            // 引数で渡されたリスト1行分の画面部品中から表示に使われるTextViewを取得
            _tvMenuNameRow = itemView.findViewById(R.id.tvMenuNameRow)
            _tvMenuPriceRow = itemView.findViewById(R.id.tvMenuPriceRow)
        }
    }

    private inner class RecyclerListAdapter(private val _listData: MutableList<MutableMap<String, Any>>): RecyclerView.Adapter<RecyclerListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
            // レイアウトインフレータを取得
            val inflater = LayoutInflater.from(this@MainActivity)
            // row.xmlをインフレートし、1行分の画面部品とする
            val view = inflater.inflate(R.layout.row, parent, false)
            // インフレートされた1行分の画面部品にリスナを設定
            view.setOnClickListener(ItemClickListener())
            // ビューホルダオブジェクトの生成
            val holder = RecyclerListViewHolder(view)
            // 生成したビューホルダをリターン
            return holder
        }

        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            // リストデータから該当1行分のデータを取得
            val item = _listData[position]
            // メニュー名文字列を取得
            val menuName = item["name"] as String
            // メニュー金額を取得
            val menuPrice = item["price"] as Int
            // 表示用に金額を文字列に変換
            val menuPriceStr = menuPrice.toString()
            // メニュー名と金額をビューホルダ中のTextViewに設定
            holder._tvMenuNameRow.text = menuName
            holder._tvMenuPriceRow.text = menuPriceStr
        }

        override fun getItemCount(): Int {
            // リストデータ中の件数をリターン
            return _listData.size
        }
    }

    private inner class ItemClickListener: View.OnClickListener {
        override fun onClick(view: View) {
            // タップされたLinearLayout内にあるメニュー名表示TextViewを取得
            val tvMenuName = view.findViewById<TextView>(R.id.tvMenuNameRow)
            // メニュー名表示TextViewから表示されているメニュー名文字列を取得
            val menuName = tvMenuName.text.toString()
            // トーストに表示する文字列を生成
            val msg = getString(R.string.msg_header) + menuName
            // トースト表示
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}