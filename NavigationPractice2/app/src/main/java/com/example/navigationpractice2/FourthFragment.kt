package com.example.navigationpractice2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs

class FourthFragment : Fragment() {
    private val args: FourthFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fourth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myData = args.myData

        view.findViewById<TextView>(R.id.contentTextView).text = myData.content
        view.findViewById<TextView>(R.id.valueTextView).text = myData.value.toString()
        view.findViewById<TextView>(R.id.messageTextView).text = myData.message
    }
}