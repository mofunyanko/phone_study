package com.example.firestorepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestorepractice.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    companion object {
        private const val ADD_TAG = "add_task"
        private const val READ_TAG = "read_task"
        private const val TASKS_PATH = "tasks"
    }

    // bindingクラスの変数
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // レイアウトを読み込む
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firestoreをインスタンス化
        val db = Firebase.firestore

        // 追加ボタンを押したら
        binding.addButton.setOnClickListener {
            // Taskをインスタンス化
            val task = Task(title = binding.titleEditText.text.toString())

            // Firestoreに格納
            db.collection("tasks")
                .add(task)
                .addOnSuccessListener { documentReference -> Log.d(ADD_TAG,"DocumentSnapshot added with ID: ${documentReference.id}") }
                .addOnFailureListener { e -> Log.w(ADD_TAG, "Error adding document", e) }
        }

        // RecycleViewの設定
        val taskAdapter = TaskAdapter()
        binding.recycleView.adapter = taskAdapter
        binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // アプリ起動時に保存されているデータを取得
        db.collection("tasks")
            .orderBy("createdAt") // 日付・時間に沿って昇順に並べる
            .get()
            .addOnSuccessListener { tasks -> val taskList = ArrayList<Task>()
                tasks.forEach {taskList.add(it.toObject(Task::class.java)) }
                taskAdapter.submitList(taskList)
            }
            .addOnFailureListener { exception -> Log.d(READ_TAG, "Error getting documents: ", exception) }

        // データの変更をリアルタイムでアプリに反映
        db.collection("tasks").addSnapshotListener { tasks, e ->
            if (e != null ) {
                Log.w(READ_TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (tasks != null) {
                val taskList = ArrayList<Task>()
                tasks.forEach { taskList.add(it.toObject(Task::class.java)) }
                taskAdapter.submitList(taskList)
            }
            else {
                Log.d(READ_TAG, "Current data: null")
            }
        }
    }
}