package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import it.wip.database.model.Chapter
import it.wip.database.model.Story
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class StoryDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
    val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)

    
    var chapterDao: ChapterDao = WIPDatabase.getInstance(application.applicationContext).chapterDao()

    fun getChapters(storyId: Int): Array<Chapter> {
        return chapterDao.getAllByStory(storyId)
    }

}