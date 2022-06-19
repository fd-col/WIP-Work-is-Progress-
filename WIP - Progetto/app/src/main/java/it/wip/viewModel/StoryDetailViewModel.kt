package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import kotlinx.coroutines.launch

class StoryDetailViewModel(application: Application) : AndroidViewModel(application) {

    var chapterDao: ChapterDao = WIPDatabase.getInstance(application.applicationContext).chapterDao()

    val chaptersName = mutableListOf<String>()
    val chapterStoryId = mutableListOf<Int>()
    val chapterDates = mutableListOf<String>()
    val chapterTimes = mutableListOf<String>()

    init {

        val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)
/*PROVA
        val storyIdPreference = application.applicationContext.getSharedPreferences("storyId", Context.MODE_PRIVATE)
        val storyId = storyIdPreference.getInt("storyId", Context.MODE_PRIVATE)

 */
        val chapter = chapterDao.getAllByUserWithoutCoroutines(userId)
        viewModelScope.launch {
            for(singleChapter in chapter) {
                chaptersName.add(singleChapter.chapterName)
                chapterStoryId.add(singleChapter.story)
                chapterDates.add(singleChapter.createdOn)
                chapterTimes.add(singleChapter.time)
            }
        }

    }

}