package it.wip.viewModel

import android.app.Application
import androidx.lifecycle.*
import it.wip.database.WIPDatabase
import kotlinx.coroutines.launch

class StartStoryViewModel(application: Application) : AndroidViewModel(application) {

    //private val db = WIPDatabase.getDatabase(application, viewModelScope)



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

    /*init {
        viewModelScope.launch {
            val user = db.userDao().getAll()[0]
            storyName = user.favouriteAvatar
        }
    }*/

    fun setStoryName(storyName: String) {
        this._storyName.value = storyName
    }

    fun setStudyBreakTime(studyTime: Float) {
        this._studyTime.value = studyTime
        this._breakTime.value = this._maxStudyTime.value?.minus(this._studyTime.value!!)
    }

}