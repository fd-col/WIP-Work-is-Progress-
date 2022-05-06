package it.wip.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.wip.R

class MenuFragment : Fragment(R.layout.fragment_menu){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottom_menu = view.findViewById<BottomNavigationView>(R.id.bottom_menu)
        bottom_menu.itemIconTintList = null
        bottom_menu.itemIconSize = 130
    }

}