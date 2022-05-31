package it.wip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.wip.ui.FrameFragment
import it.wip.ui.HeaderFragment
import it.wip.ui.MenuFragment
import it.wip.ui.SettingsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.header_layout, HeaderFragment())

        val configuration = intent.getIntExtra("configuration", 0)

        if(configuration == 0)
            transaction.add(R.id.frame_layout, FrameFragment())
        else //configuration = 1
            transaction.add(R.id.frame_layout, SettingsFragment())

        transaction.add(R.id.menu_layout, MenuFragment())

        transaction.commit()

    }
}