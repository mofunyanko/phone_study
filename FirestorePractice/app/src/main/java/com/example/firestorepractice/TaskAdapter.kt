package com.example.firestorepractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorepractice.databinding.TaskListItemBinding
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter: ListAdapter<Task, TaskViewHolder>(diffUtilItemCallback) {
    // Viewを生成し、生成したViewをViewHolderni格納し、戻り値として返す
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = TaskListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(view)
    }

    // その位置のTaskを取得し、ViewHolderからViewにTask情報をセット
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

// 生成したViewを保持し、bindでTaskをViewに反映
class TaskViewHolder(private val binding: TaskListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.titleTextView.text = task.title
        binding.dataTextView.text = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE).format(task.createdAt)
    }
}

// Taskの差分確認
private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Task>() {
    // 渡されたデータが同じ値か
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

    // 渡されたデータが同じ項目か
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }
}