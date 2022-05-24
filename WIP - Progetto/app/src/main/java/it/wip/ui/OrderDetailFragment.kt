package it.wip.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import it.wip.R

class OrderDetailFragment: Fragment(R.layout.fragment_order_detail){
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val buyButton = view?.findViewById<ImageButton>(R.id.buy_button)

        buyButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> buyButton.setImageResource(R.drawable.buy_button_pressed)
                MotionEvent.ACTION_UP -> buyButton.setImageResource(R.drawable.buy_button)
            }
            v?.onTouchEvent(event) ?: true
        }
        return view
    }
}