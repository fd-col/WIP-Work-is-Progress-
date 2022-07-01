package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import it.wip.database.model.Chapter

class StoryDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
    val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)

    
    var chapterDao: ChapterDao = WIPDatabase.getInstance(application.applicationContext).chapterDao()

    fun getChapters(storyId: Int): Array<Chapter> {
        return chapterDao.getAllByStory(storyId)
    }

}