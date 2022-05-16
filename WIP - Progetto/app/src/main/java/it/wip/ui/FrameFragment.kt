package it.wip.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import it.wip.MainActivity
import it.wip.R

class FrameFragment: Fragment(R.layout.fragment_frame) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val playButton = view?.findViewById<ImageButton>(R.id.play_button)

        playButton?.setOnClickListener {

        }

        playButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> playButton.setImageResource(R.drawable.play_button_pressed)
                MotionEvent.ACTION_UP -> playButton.setImageResource(R.drawable.play_button)
            }
            v?.onTouchEvent(event) ?: true

        }

        return view
    }

}