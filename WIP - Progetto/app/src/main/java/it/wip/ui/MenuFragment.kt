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

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val home = 1

    private val kingdom = 2

    private val settings = 3

    private lateinit var button1: ImageButton

    private lateinit var button2: ImageButton

    private lateinit var button3: ImageButton

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        button1 = view!!.findViewById(R.id.button1)
        button1.tag = home

        button2 = view.findViewById(R.id.button2)
        button2.tag = kingdom

        button3 = view.findViewById(R.id.button3)
        button3.tag = settings


        button1.setOnClickListener {
            buttonMenuClick(button1)
        }

        button2.setOnClickListener {
            buttonMenuClick(button2)
        }

        button3.setOnClickListener {
            buttonMenuClick(button3)
        }



        button1.setOnTouchListener { v, event ->

            buttonMenuLongClick(event, button1)

            v?.onTouchEvent(event) ?: true
        }

        button2.setOnTouchListener { v, event ->

            buttonMenuLongClick(event, button2)

            v?.onTouchEvent(event) ?: true
        }

        button3.setOnTouchListener { v, event ->

            buttonMenuLongClick(event, button3)

            v?.onTouchEvent(event) ?: true
        }

        return view

    }

    private fun buttonMenuClick(button: ImageButton) {

        when (button.tag) {

            home -> {

                button1.tag = home
                button1.setImageResource(R.drawable.paint_button)

                button2.tag = kingdom
                button2.setImageResource((R.drawable.crown_button))

                button3.tag = settings
                button3.setImageResource(R.drawable.gear_button)

                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.frame_layout, FrameFragment())
                    ?.commit()

            }

            kingdom -> {

                button1.tag = kingdom
                button1.setImageResource((R.drawable.crown_button))

                button2.tag = home
                button2.setImageResource(R.drawable.paint_button)

                button3.tag = settings
                button3.setImageResource(R.drawable.gear_button)

                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.frame_layout, KingdomFragment())
                    ?.commit()

            }

            settings -> {

                button1.tag = settings
                button1.setImageResource(R.drawable.gear_button)

                button2.tag = home
                button2.setImageResource(R.drawable.paint_button)

                button3.tag = kingdom
                button3.setImageResource((R.drawable.crown_button))

                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.frame_layout, SettingsFragment())
                    ?.commit()

            }

        }

    }

    private fun buttonMenuLongClick(event: MotionEvent, button: ImageButton) {

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                when (button.tag) {
                    home -> button.setImageResource(R.drawable.paint_button_pressed)
                    kingdom -> button.setImageResource(R.drawable.crown_button_pressed)
                    settings -> button.setImageResource(R.drawable.gear_button_pressed)
                }

            }

            MotionEvent.ACTION_UP -> {
                when (button.tag) {
                    home -> button.setImageResource(R.drawable.paint_button)
                    kingdom -> button.setImageResource(R.drawable.crown_button)
                    settings -> button.setImageResource(R.drawable.gear_button)
                }
            }
        }

    }

}