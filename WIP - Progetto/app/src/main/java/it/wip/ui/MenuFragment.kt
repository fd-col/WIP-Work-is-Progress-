package it.wip.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import it.wip.MainActivity
import it.wip.R
import java.util.*

class MenuFragment : Fragment(R.layout.fragment_menu) {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val homeButton = view?.findViewById<ImageButton>(R.id.button1)
        val kingdomButton = view?.findViewById<ImageButton>(R.id.button2)
        val settingsButton = view?.findViewById<ImageButton>(R.id.button3)

        val parentActivityName = activity?.javaClass?.simpleName

        homeButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> homeButton.setImageResource(R.drawable.paint_button_pressed)
                MotionEvent.ACTION_UP -> homeButton.setImageResource(R.drawable.paint_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        kingdomButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> kingdomButton.setImageResource(R.drawable.crown_button_pressed)
                MotionEvent.ACTION_UP -> kingdomButton.setImageResource(R.drawable.crown_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        settingsButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> settingsButton.setImageResource(R.drawable.gear_button_pressed)
                MotionEvent.ACTION_UP -> settingsButton.setImageResource(R.drawable.gear_button)
            }
            v?.onTouchEvent(event) ?: true
        }



        homeButton?.setOnClickListener {
            if(parentActivityName == "MainActivity")
                mainActivityTransaction(FrameFragment())
            else //KingdomActivity
                explicitIntent("MainActivity")
        }

        kingdomButton?.setOnClickListener {
            if(parentActivityName == "MainActivity")
                explicitIntent("KingdomActivity")
            else //KingdomActivity
                Toast.makeText(activity?.applicationContext,"Kingdom transaction",Toast.LENGTH_SHORT).show()
        }

        settingsButton?.setOnClickListener {
            if(parentActivityName == "MainActivity")
                mainActivityTransaction(SettingsFragment())
            else //KingdomActivity
                explicitIntent("MainActivity", 1)
        }

        return view
    }

    private fun mainActivityTransaction(fragment: Fragment){
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frame_layout, fragment)
            ?.commit()
    }

    private fun explicitIntent(targetActivity: String, configuration: Int = 0){

        val intent = if(targetActivity == "MainActivity")
            Intent(activity, MainActivity::class.java)
        else //KingdomActivity
            Intent(activity, KingdomActivity::class.java)

        intent.putExtra("configuration", configuration)

        startActivity(intent)
    }

}