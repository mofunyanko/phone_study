package com.example.navigationpractice2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class FirstFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            // EditTextの中身を取り出す
            val content = view.findViewById<TextView>(R.id.contentEditText).text.toString()

            // 生成されたクラスに引数を渡して遷移
            val action = FirstFragmentDirections.actionFirstToSecond(content)
            findNavController().navigate(action)
        }
    }
}