package it.wip.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import it.wip.DialogFragmentOrderDetail
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
        val backButton = view?.findViewById<ImageButton>(R.id.order_detail_back_button)

        buyButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> buyButton.setImageResource(R.drawable.buy_button_pressed)
                MotionEvent.ACTION_UP -> buyButton.setImageResource(R.drawable.buy_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        backButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> backButton.setImageResource(R.drawable.back_arrow_pressed)
                MotionEvent.ACTION_UP -> backButton.setImageResource(R.drawable.back_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        buyButton?.setOnClickListener {
            val dialogShop = DialogFragmentOrderDetail()
            dialogShop.show(parentFragmentManager, "shop")
        }

        backButton?.setOnClickListener {

        }

        return view
    }
}

/*
MaterialAlertDialogBuilder(context)
        .setTitle(resources.getString(R.string.title))
        .setMessage(resources.getString(R.string.supporting_text))
        .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
            // Respond to neutral button press
        }
        .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
            // Respond to negative button press
        }
        .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
            // Respond to positive button press
        }
        .show()
* */