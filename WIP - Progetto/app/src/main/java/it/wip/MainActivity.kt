package it.wip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.wip.ui.HeaderFragment
import it.wip.ui.MenuFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.main_layout, HeaderFragment())

        transaction.add(R.id.main_layout, MenuFragment())

        transaction.commit()

    }
}