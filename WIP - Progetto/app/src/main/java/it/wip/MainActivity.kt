package it.wip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import it.wip.database.WIPDatabase
import it.wip.databinding.ActivityMainBinding
import it.wip.ui.fragments.FrameFragment
import it.wip.ui.fragments.HeaderFragment
import it.wip.ui.fragments.MenuFragment
import it.wip.utils.seed

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

/*ROVA
        val storyDao = WIPDatabase.getInstance(applicationContext).storyDao()

        val storyId = try {
            storyDao.getAllByUserWithoutCoroutines(userId)[0].id
        } catch (ex: ArrayIndexOutOfBoundsException) {
            seed(WIPDatabase.getInstance(applicationContext))
            storyDao.getAllByUserWithoutCoroutines(userId)[0].id
        }

        val storyIdPreference = applicationContext.getSharedPreferences("storyId", Context.MODE_PRIVATE)
        val editor2 = storyIdPreference.edit()
        editor2.putInt("storyId", userId)
        editor2.apply()

 */

    }
}