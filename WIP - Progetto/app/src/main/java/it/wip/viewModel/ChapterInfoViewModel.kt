package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import it.wip.database.model.Chapter
import kotlinx.coroutines.launch

class ChapterInfoViewModel(application: Application) : AndroidViewModel(application) {

    var chapterDao: ChapterDao = WIPDatabase.getInstance(application.applicationContext).chapterDao()

    fun getChapter(chapterId: Int): Chapter {

        return chapterDao.getAllById(chapterId)[0]

    }

}