package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class PruebaFragment : Fragment() {
    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prueba, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        val textView = view.findViewById<TextView>(R.id.tv1)
        val button = view.findViewById<Button>(R.id.btn1)

        button.setOnClickListener {
            textView.text = "Boton clickeado"
        }
         */

    }*/
    companion object{
        private const val ARG_PAGE_NUMBER = "page_number"

        fun newInstance(pageNumber: Int): PruebaFragment{
            val fragment = PruebaFragment()
            val args = Bundle()
            args.putInt(ARG_PAGE_NUMBER, pageNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_prueba, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView:TextView
        val pageNumber = arguments?.getInt(ARG_PAGE_NUMBER)
        textView = view.findViewById(R.id.tv1)
        textView.text = "Página $pageNumber"
    }
}