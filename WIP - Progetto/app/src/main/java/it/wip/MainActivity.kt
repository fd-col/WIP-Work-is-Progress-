package it.wip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import it.wip.database.WIPDatabase
import it.wip.databinding.ActivityMainBinding
import it.wip.ui.fragments.FrameFragment
import it.wip.ui.fragments.HeaderFragment
import it.wip.ui.fragments.MenuFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.header_layout, HeaderFragment())

        transaction.add(R.id.frame_layout, FrameFragment())

        transaction.add(R.id.menu_layout, MenuFragment())

        transaction.commit()

        WIPDatabase.getInstance(applicationContext)

    }
}