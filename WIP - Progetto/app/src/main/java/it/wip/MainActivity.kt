package it.wip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import it.wip.database.WIPDatabase
import it.wip.databinding.ActivityMainBinding
import it.wip.ui.fragments.FrameFragment
import it.wip.ui.fragments.HeaderFragment
import it.wip.ui.fragments.MenuFragment
import it.wip.utils.seed
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

        val userId = try {
            userDao.getAllWithoutCoroutines()[0].id
        } catch (ex: ArrayIndexOutOfBoundsException) {
            seed(WIPDatabase.getInstance(applicationContext))
            userDao.getAllWithoutCoroutines()[0].id
        }
        val userIdPreference = applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        val editor = userIdPreference.edit()
        editor.putInt("userId", userId)
        editor.apply()

        /*
        val preferenceDao = WIPDatabase.getInstance(applicationContext).preferenceDao()
        var lefthand: Int = 0
        lifecycleScope.launch {
            lefthand = try {
                //AGGIUNGERE PRORIETA' "lefthand" di tipo Int/Boolean nel DB
                preferenceDao.getAllByUser(userId)[0].lefthand
            } catch (ex: ArrayIndexOutOfBoundsException) {
                seed(WIPDatabase.getInstance(applicationContext))
                preferenceDao.getAllByUser(userId)[0].lefthand
            }
        }
        val preferencePreference = application.getSharedPreferences("lefthand", Context.MODE_PRIVATE)
        val editor2 = preferencePreference.edit()
        editor2.putInt("preference", lefthand)
        editor2.apply()

         */

    }
}