package it.wip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import it.wip.database.model.Chapter

class ChapterInfoViewModel(application: Application) : AndroidViewModel(application) {

    var chapterDao: ChapterDao = WIPDatabase.getInstance(application.applicationContext).chapterDao()

    //function to return a single chapter in the DB, by chapterId
    fun getChapter(chapterId: Int): Chapter {
        return chapterDao.getAllById(chapterId)[0]
    }

}