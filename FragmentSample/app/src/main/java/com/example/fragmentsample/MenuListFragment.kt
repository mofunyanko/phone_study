package com.example.fragmentsample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment

class MenuListFragment : Fragment() {
    private var _isLayoutXLarge = true

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        val menuThanksFrame = activity?.findViewById<View>(R.id.menuThanksFrame)

        if(menuThanksFrame == null) {
            _isLayoutXLarge = false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)

        val lvMenu = view.findViewById<ListView>(R.id.lvMenu)

        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()

        var menu = mutableMapOf("name" to "から揚げ定食", "price" to "800円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to "850円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "生姜焼き定食", "price" to "850円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "ステーキ定食", "price" to "1000円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "野菜炒め定食", "price" to "750円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "とんかつ定食", "price" to "900円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to "850円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "チキンカツ定食", "price" to "900円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "コロッケ定食", "price" to "850円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "回鍋肉定食", "price" to "750円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "麻婆豆腐定食", "price" to "800円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "青椒肉絲定食", "price" to "850円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "焼き魚定食", "price" to "700円")
        menuList.add(menu)

        menu = mutableMapOf("name" to "焼肉定食", "price" to "950円")
        menuList.add(menu)


        val from = arrayOf("name", "price")

        val to = intArrayOf(android.R.id.text1, android.R.id.text2)

        val adapter = SimpleAdapter(activity, menuList, android.R.layout.simple_list_item_2,
            from, to)

        lvMenu.adapter = adapter

        lvMenu.onItemClickListener = ListItemClickListener()

        return view
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent?.getItemAtPosition(position) as MutableMap<String, String>

            val menuName = item["name"]
            val menuPrice = item["price"]

            val bundle = Bundle()

            bundle.putString("menuName", menuName)
            bundle.putString("menuPrice", menuPrice)

            if(_isLayoutXLarge) {
                val transaction = parentFragmentManager.beginTransaction()

                val menuThanksFragment = MenuThanksFragment()

                menuThanksFragment.arguments = bundle

                transaction.replace(R.id.menuThanksFrame, menuThanksFragment)

                transaction.commit()
            }
            else {
                val intent2MenuThanks = Intent(activity, MenuThanksActivity::class.java)

                intent2MenuThanks.putExtras(bundle)

                startActivity(intent2MenuThanks)
            }
        }
    }
}