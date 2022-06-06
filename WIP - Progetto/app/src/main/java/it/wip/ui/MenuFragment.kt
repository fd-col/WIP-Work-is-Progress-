package it.wip.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import it.wip.MainActivity
import it.wip.R
import it.wip.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null

    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        val view = binding.root

        val parentActivityName = activity?.javaClass?.simpleName

        binding.homeButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> _binding!!.homeButton.setImageResource(R.drawable.paint_button_pressed)
                MotionEvent.ACTION_UP -> _binding!!.homeButton.setImageResource(R.drawable.paint_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        binding.kingdomButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> binding.kingdomButton.setImageResource(R.drawable.crown_button_pressed)
                MotionEvent.ACTION_UP -> binding.kingdomButton.setImageResource(R.drawable.crown_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        binding.settingsButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> binding.settingsButton.setImageResource(R.drawable.gear_button_pressed)
                MotionEvent.ACTION_UP -> binding.settingsButton.setImageResource(R.drawable.gear_button)
            }
            v?.onTouchEvent(event) ?: true
        }



        binding.homeButton.setOnClickListener {
            if(parentActivityName == "MainActivity")
                mainActivityTransaction(FrameFragment())
            else //KingdomActivity
                explicitIntent("MainActivity")
        }

        binding.kingdomButton.setOnClickListener {
            if(parentActivityName == "MainActivity")
                explicitIntent("KingdomActivity")
            else //KingdomActivity
                Toast.makeText(activity?.applicationContext,"Kingdom transaction",Toast.LENGTH_SHORT).show()
        }

        binding.settingsButton.setOnClickListener {
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