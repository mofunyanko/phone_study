package com.example.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // リストビューに表示するリストデータ
    private var _menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
    // SimpleAdapterの第4引数fromに使用するプロパティ
    private val _from = arrayOf("name", "price")
    // SimpleAdapterの第5引数toに使用するプロパティ
    private val _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 定食メニューListオブジェクトをprivateメソッドを利用して用意し、プロパティに格納
        _menuList = createTeishokuList()
        // 画面部品ListViewを取得
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // SimpleAdapterを生成
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        // アダプタの登録
        lvMenu.adapter = adapter
        // リストタップのリストクラス登録
        lvMenu.onItemClickListener = ListItemClickListener()

        registerForContextMenu(lvMenu)
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

    private fun createCurryList(): MutableList<MutableMap<String, Any>> {
        // カレーメニューリスト用のオブジェクトを用意
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        // 「ビーフカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        var menu = mutableMapOf<String, Any>("name" to "ビーフカレー", "price" to 520,
        "desc" to "特選スパイスをきかせた国産ポーク100%のカレーです。")
        menuList.add(menu)
        // 「ポークカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ポークカレー", "price" to 420,
            "desc" to "特選スパイスをきかせた国産ポーク100%のカレーです。")
        menuList.add(menu)
        // 「ハンバーグカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ハンバーグカレー", "price" to 620,
            "desc" to "特選スパイスをきかせたカレーにハンバーグをトッピングしたカレーです。")
        menuList.add(menu)
        // 「チーズカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "チーズカレー", "price" to 560,
            "desc" to "特選スパイスをきかせたカレーにチーズをトッピングしたカレーです。")
        menuList.add(menu)
        // 「カツカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "カツカレー", "price" to 760,
            "desc" to "特選スパイスをきかせたカレーにカツをトッピングしたカレーです。")
        menuList.add(menu)
        // 「ビーフカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ビーフカツカレー", "price" to 880,
            "desc" to "特選スパイスをきかせたカレーに国産ビーフカツをトッピングしたカレーです。")
        menuList.add(menu)
        // 「から揚げカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "からあげカレー", "price" to 540,
            "desc" to "特選スパイスをきかせたカレーにから揚げをトッピングしたカレーです。")
        menuList.add(menu)

        return menuList
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // タップされた行のデータを取得
            val item = parent?.getItemAtPosition(position) as MutableMap<String, Any>
            // 注文処理
            order(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // オプションメニュー用xmlファイルをインフレント
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 戻り値用の変数を初期値trueで用意
        var returnVal = true
        // 選択されたメニューのIDのR値による処理の分岐
        when(item.itemId) {
            // 定食メニューが選択された場合の処理
            R.id.menuListOptionTeishoku ->
                // 定食メニューリストデータの生成
            _menuList = createTeishokuList()
            // カレーメニューが選択された場合の処理
            R.id.menuListOptionCurry ->
                // カレーメニューリストデータの生成
                _menuList = createCurryList()
            // それ以外...
            else ->
                // 親クラスの同名メソッドを呼び出し、その戻り値をreturnValとする
                returnVal = super.onOptionsItemSelected(item)
        }
        // 画面部品ListViewを取得
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // SimpleAdapterを選択されたメニューデータで生成
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        // アダプタの登録
        lvMenu.adapter = adapter
        return returnVal
    }

    override fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenu.ContextMenuInfo) {
        // 親クラスの同名メソッドの呼び出し
        super.onCreateContextMenu(menu, view, menuInfo)
        // コンテキストメニュー用xmlファイルをインフレイト
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        // コンテキストメニューのヘッダタイトルを設定
        menu.setHeaderTitle(R.string.menu_list_context_header)
    }

    private fun order(menu: MutableMap<String, Any>) {
        // 定食名と金額を取得。Mapの値部分がAny型なのでキャストが必要
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int

        // インテントオブジェクトを生成
        val intent2MenuThanks = Intent(this@MainActivity, MenuThanksActivity::class.java)
        // 第2画面に送るデータを格納
        intent2MenuThanks.putExtra("menuName", menuName)
        // MenuThanksActivityでのデータ受け取りと合わせるために、金額にここで「円」を追加する
        intent2MenuThanks.putExtra("menuPrice", "${menuPrice}円")
        // 第2画面の起動
        startActivity(intent2MenuThanks)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        // 戻り値用の変数を初期値trueで用意
        var returnVal = true
        // 長押しされたビューに関する情報が格納されたオブジェクトの取得
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        // 長押しされたリストのポジションを取得
        val listPosition = info.position
        // ポジションから長押しされたメニュー情報Mapオブジェクトを取得
        val menu = _menuList[listPosition]

        // 選択された目メニューのIDのR値による処理分岐
        when(item.itemId) {
            // [説明を表示]メニューが選択されたときの処理
            R.id.menuListContextDesc -> {
                // メニューの説明文字列を取得
                val desc = menu["desc"] as String
                // トーストを表示
                Toast.makeText(this@MainActivity, desc, Toast.LENGTH_LONG).show()
            }
            // ［ご注文］メニューが選択されたときの処理
            R.id.menuListContextOrder ->
                // 注文処理
                order(menu)
            // それ以外...
            else ->
                // 親クラスの同名メソッドを呼び出し、その戻り値をreturnValとする
                returnVal = super.onContextItemSelected(item)
        }
        return returnVal
    }
}