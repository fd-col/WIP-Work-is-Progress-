package it.wip.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.wip.MainActivity
import it.wip.R
import it.wip.databinding.FragmentMenuBinding
import it.wip.ui.KingdomActivity
import it.wip.ui.SettingsActivity

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


        //action when home central button on the menu is pressed
        binding.homeButton.setOnClickListener {

            val fragments = activity?.supportFragmentManager?.fragments

            var isShop = false

            if (fragments != null) {
                for(fragment in fragments) {

                    val fragmentName = fragment.javaClass.simpleName.toString()

                    if(fragmentName == "ShopFragment" || fragmentName == "OrderDetailFragment") {
                        isShop = true
                        break
                    }
                }
            }

            if(parentActivityName != "MainActivity" || isShop)
                startActivity(Intent(activity, MainActivity::class.java))
        }
        //action when kingdom left button on the menu is pressed
        binding.kingdomButton.setOnClickListener {
            if(parentActivityName != "KingdomActivity")
                startActivity(Intent(activity, KingdomActivity::class.java))
        }
        //action when settings right button on the menu is pressed
        binding.settingsButton.setOnClickListener {
            if(parentActivityName != "SettingsActivity")
                startActivity(Intent(activity, SettingsActivity::class.java))
        }

        return view
    }

}