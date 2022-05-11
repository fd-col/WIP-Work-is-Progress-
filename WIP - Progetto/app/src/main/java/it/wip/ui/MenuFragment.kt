package it.wip.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import it.wip.R

class MenuFragment : Fragment(R.layout.fragment_menu) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val menuHome = view?.findViewById<ImageButton>(R.id.menu_home)
        val menuWorld = view?.findViewById<ImageButton>(R.id.menu_world)
        val menuSettings = view?.findViewById<ImageButton>(R.id.menu_settings)

        // Listener per il pulsante HOME
        menuHome?.setOnClickListener {

        }

        menuHome?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> menuHome.setImageResource(R.drawable.paint_button_pressed)
                MotionEvent.ACTION_UP -> menuHome.setImageResource(R.drawable.paint_button)
            }

            v?.onTouchEvent(event) ?: true
        }

        // Listener per il pulsante WORLD
        menuWorld?.setOnClickListener {

        }

        menuWorld?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> menuHome?.setImageResource(R.drawable.crown_button_pressed)
                MotionEvent.ACTION_UP -> menuHome?.setImageResource(R.drawable.crown_button)
            }

            v?.onTouchEvent(event) ?: true
        }

        // Listener per il pulsante SETTINGS
        menuSettings?.setOnClickListener {

        }

        menuSettings?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> menuHome?.setImageResource(R.drawable.gear_button_pressed)
                MotionEvent.ACTION_UP -> menuHome?.setImageResource(R.drawable.gear_button)
            }

            v?.onTouchEvent(event) ?: true
        }


        return view
    }

}