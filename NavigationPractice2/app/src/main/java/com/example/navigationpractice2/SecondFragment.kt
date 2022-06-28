package com.example.navigationpractice2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class SecondFragment : Fragment() {
    private  val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.resultTextView).text = args.content
        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            val content = args.content
            val value = view.findViewById<TextView>(R.id.valueEditText).text.toString().toInt()

            // argumentを増やすと、このメソッドの引数も対応して増える
            val action = SecondFragmentDirections.actionSecondToThird(content, value)
            findNavController().navigate(action)
        }
    }
}