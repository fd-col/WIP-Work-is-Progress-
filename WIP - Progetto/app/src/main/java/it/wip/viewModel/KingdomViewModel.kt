package it.wip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.dao.StoryDao
import it.wip.database.dao.UserDao
import kotlinx.coroutines.launch

class KingdomViewModel(application: Application) : AndroidViewModel(application) {

    var storyDao: StoryDao = WIPDatabase.getInstance(application.applicationContext).storyDao()

    private val _storyName = MutableLiveData("")
    val storyName : LiveData<String>
        get() = _storyName
/*
    init {
        viewModelScope.launch {
            val story = storyDao.getAll()[0]
            _storyName.value = story.storyName

        }
    }

 */
}