package it.wip.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import it.wip.R
import it.wip.ui.activities.StartStoryActivity

class FrameFragment: Fragment(R.layout.fragment_frame) {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val playButton = view?.findViewById<ImageButton>(R.id.play_button)

        val shopButton = view?.findViewById<ImageButton>(R.id.shop_button)

        //action when play button on the layout is pressed
        playButton?.setOnClickListener {
            startActivity(Intent(activity, StartStoryActivity::class.java))
        }

        playButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> playButton.setImageResource(R.drawable.play_button_pressed)
                MotionEvent.ACTION_UP -> playButton.setImageResource(R.drawable.play_button)
            }
            v?.onTouchEvent(event) ?: true

        }
        //action when shop button on the layout is pressed
        shopButton?.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, ShopFragment())
                ?.commit()
        }

        shopButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> shopButton.setImageResource(R.drawable.shop_button_pressed)
                MotionEvent.ACTION_UP -> shopButton.setImageResource(R.drawable.shop_button)
            }
            v?.onTouchEvent(event) ?: true

        }

        return view
    }

}