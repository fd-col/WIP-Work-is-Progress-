package it.wip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.model.User
import it.wip.databinding.ActivityMainBinding
import it.wip.ui.fragments.FrameFragment
import it.wip.ui.fragments.HeaderFragment
import it.wip.ui.fragments.MenuFragment
import it.wip.utils.seed
import it.wip.viewModel.ShopViewModel
import it.wip.viewModel.StartStoryViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //private lateinit var viewModel: ShopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /*viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ShopViewModel::class.java]*/

        //binding.lifecycleOwner = this

        //binding.viewModel = viewModel

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
                seed(WIPDatabase.getInstance(applicationContext))
                userDao.getAll()[0]
            }
        }

    }
}