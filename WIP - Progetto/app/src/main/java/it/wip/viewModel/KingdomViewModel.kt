package it.wip.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.dao.StoryDao
import it.wip.database.dao.UserDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class KingdomViewModel(application: Application) : AndroidViewModel(application) {

    var storyDao: StoryDao = WIPDatabase.getInstance(application.applicationContext).storyDao()

    private val _storyName = MutableLiveData("")
    val storyName : LiveData<String>
        get() = _storyName

    init {

        val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)

        val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)

        Log.e("userId", userId.toString())

        val story = storyDao.getAllByUserWithoutCoroutines(userId)[0]
        Log.e("story", story.toString())
        _storyName.value = story.storyName
    }


}