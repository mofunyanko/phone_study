package com.example.todoapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.ArrayList


class ToDoActivity : AppCompatActivity(), OnItemLongClickListener {
    var user: FirebaseUser? = null
    var uid: String? = null
    var mAuth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    var mCustomAdapter: CustomAdapter? = null
    var mListView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        // ログイン情報を取得
        user = FirebaseAuth.getInstance().currentUser

        // user id = Uid を取得する
        uid = user!!.uid
        database = FirebaseDatabase.getInstance()
        reference = database!!.getReference("users").child(uid!!)
        mListView = findViewById(R.id.list_view)

        // CustomAdapterをセット
        mCustomAdapter = CustomAdapter(applicationContext, R.layout.card_view, ArrayList())
        mListView!!.adapter = mCustomAdapter

        // LongListenerを設定
        mListView!!.onItemLongClickListener = this

        // firebaseと同期するリスナー
        reference!!.addChildEventListener(object : ChildEventListener {
            // データを読み込むときはイベントリスナーを登録して行う
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            // アイテムのリストを取得するか、アイテムのリストへの追加がないかリッスンする
                val toDoData = dataSnapshot.getValue(ToDoData::class.java)
                mCustomAdapter!!.add(toDoData)
                mCustomAdapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                // リスト内のアイテムに対する変更がないかリッスンする
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                // リストから削除されるアイテムがないかリッスンする
                Log.d("ToDoActivity", "onChildRemoved:" + dataSnapshot.key)
                val result = dataSnapshot.getValue(ToDoData::class.java) ?: return
                val item = mCustomAdapter!!.getToDoDataKey(result.firebaseKey!!)
                mCustomAdapter!!.remove(item)
                mCustomAdapter!!.notifyDataSetChanged()
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                // 並べ替えリストの項目順に変更がないかリッスンする
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // ログを記録するなどError時の処理を記載
            }
        })
    }

    fun addButton(v: View?) {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    override fun onItemLongClick(parent: AdapterView<*>?, view: View, position: Int, id: Long): Boolean {
        val toDoData = mCustomAdapter!!.getItem(position)
        uid = user!!.uid
        AlertDialog.Builder(this)
            .setTitle("Done?")
            .setMessage("この項目を完了しましたか？")
            .setPositiveButton("Yes") { dialog, which ->
                // OKボタンが押されたら...
                reference!!.child(toDoData.firebaseKey!!).removeValue()
            }
            .setNegativeButton("No", null)
            .show()
        return false
    }

    fun logout(v: View?) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.signOut()
        val intent = Intent(this@ToDoActivity, LoginActivity::class.java)
        intent.putExtra("check", true)
        startActivity(intent)
        finish()
    }
}