package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import it.wip.database.model.Story
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class StoryDetailViewModel(application: Application) : AndroidViewModel(application) {


    val chaptersId = mutableListOf<Int>()

    val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
    val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)


    var chapterDao: ChapterDao = WIPDatabase.getInstance(application.applicationContext).chapterDao()
    val chapter = chapterDao.getAllByUserWithoutCoroutines(userId)

    var sortedList = listOf<Story>()

    init {

        val story = WIPDatabase.getInstance(application.applicationContext).storyDao().getAllByUserWithoutCoroutines(userId)

        viewModelScope.launch {
            val format: DateFormat = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.ITALY)
            sortedList = story.sortedByDescending { format.parse(it.createdOn) }
/*
            for(singleChapter in chapter){
                chaptersId.add(singleChapter.id)
            }
            manca ordinare gli id
 */

        }

    }

}