package it.wip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.model.User
import it.wip.databinding.ActivityMainBinding
import it.wip.ui.fragments.FrameFragment
import it.wip.ui.fragments.HeaderFragment
import it.wip.ui.fragments.MenuFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.header_layout, HeaderFragment())

        transaction.add(R.id.frame_layout, FrameFragment())

        transaction.add(R.id.menu_layout, MenuFragment())

        transaction.commit()

        val userDao = WIPDatabase.getInstance(applicationContext).userDao()
        lifecycleScope.launch {
            try {
                userDao.getAll()[0]
            } catch (ex: ArrayIndexOutOfBoundsException) {
                userDao.insert(User(1, "Venere", 30F, 120F, 30))
                userDao.getAll()[0]
            }
        }
    }
}