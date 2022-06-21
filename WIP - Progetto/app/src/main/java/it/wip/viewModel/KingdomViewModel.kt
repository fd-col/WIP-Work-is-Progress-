package it.wip.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import it.wip.database.dao.StoryDao
import it.wip.database.dao.UserDao
import it.wip.database.model.Story
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class KingdomViewModel(application: Application) : AndroidViewModel(application) {

    var storyDao: StoryDao = WIPDatabase.getInstance(application.applicationContext).storyDao()


    var sortedList = listOf<Story>()

    init {
        //get user from SharedPreferences
        val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)
        //get all Story by userId detection
        val story = storyDao.getAllByUserWithoutCoroutines(userId)

        viewModelScope.launch {
                val format: DateFormat = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.ITALY)
                sortedList = story.sortedByDescending { format.parse(it.createdOn) }
        }

    }
}