package com.example.navigationpractice2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ThirdFragment : Fragment() {
    private val args: ThirdFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.contentTextView).text = args.content
        view.findViewById<TextView>(R.id.valueTextView).text = args.value.toString()

        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            val myData = MyData(args.content, args.value, "MEOW!!")

            val action = ThirdFragmentDirections.actionThirdToFourth(myData)
            findNavController().navigate(action)
        }
    }
}