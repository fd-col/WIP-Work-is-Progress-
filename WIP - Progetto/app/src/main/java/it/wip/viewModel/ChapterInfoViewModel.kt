package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import kotlinx.coroutines.launch

class ChapterInfoViewModel(application: Application) : AndroidViewModel(application) {
    val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
    val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)


    var chapterDao: ChapterDao = WIPDatabase.getInstance(application.applicationContext).chapterDao()
    val chapter = chapterDao.getAllByUserWithoutCoroutines(userId)

}