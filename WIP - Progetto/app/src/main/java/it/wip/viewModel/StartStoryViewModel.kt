package it.wip.viewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import it.wip.database.WIPDatabase
import it.wip.database.model.User
import kotlinx.coroutines.launch

class StartStoryViewModel(application: Application) : AndroidViewModel(application) {

    val userDao = WIPDatabase.getDatabase(application, viewModelScope).userDao()

    lateinit var user: User

    private val _storyName = MutableLiveData("")
    val storyName : LiveData<String>
        get() = _storyName

    private val _studyTime = MutableLiveData(10F)
    val studyTime : LiveData<Float>
        get() = _studyTime

    private val _maxStudyTime = MutableLiveData(120F) //dal database
    val maxStudyTime : LiveData<Float>
        get() = _maxStudyTime

    private val _maxStudyTimeGraphic = MutableLiveData(maxStudyTime.value?.minus(10F) ?: 50F)
    val maxStudyTimeGraphic : LiveData<Float>
        get() = _maxStudyTimeGraphic

    private val _breakTime = MutableLiveData(maxStudyTime.value?.minus(10F) ?: 50F)
    val breakTime : LiveData<Float>
        get() = _breakTime

    init {
        viewModelScope.launch {
            user = userDao.getAll()[0]
        }
    }

    fun setStoryName(storyName: String) {
        this._storyName.value = storyName
    }

    fun setStudyBreakTime(studyTime: Float) {
        this._studyTime.value = studyTime
        this._breakTime.value = this._maxStudyTime.value?.minus(this._studyTime.value!!)
    }

}