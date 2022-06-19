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

class KingdomViewModel(application: Application) : AndroidViewModel(application) {

    var storyDao: StoryDao = WIPDatabase.getInstance(application.applicationContext).storyDao()
    var chapterDao: ChapterDao = WIPDatabase.getInstance(application.applicationContext).chapterDao()
/*
    private val _storyName = MutableLiveData("")
    val storyName : LiveData<String>
        get() = _storyName
*/
    val storiesName = mutableListOf<String>()
    val chaptersName = mutableListOf<String>()

    init {

        val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)

        val story = storyDao.getAllByUserWithoutCoroutines(userId)
        val chapter = chapterDao.getAllByUserWithoutCoroutines(userId)

        viewModelScope.launch {
                for (singleStory in story) {
                    storiesName.add(singleStory.storyName)
                }
                for(singleChapter in chapter) {
                    chaptersName.add(singleChapter.chapterName)
                }
        }

    }
}