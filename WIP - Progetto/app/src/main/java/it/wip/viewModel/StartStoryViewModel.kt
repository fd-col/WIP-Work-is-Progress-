package it.wip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.wip.database.WIPDatabase

class StartStoryViewModel(application: Application) : AndroidViewModel(application) {

    private val db = WIPDatabase.getInstance(application)

    val user = db.userDao().getAll()[0]

    var storyName : String = ""
    private val _studyTime = MutableLiveData<Float>(0F)
    val studyTime : LiveData<Float>
        get() = _studyTime
    var breakTime : Float = 60F

    fun setStudyBreakTime(studyTime : Float) {
        this._studyTime.value = studyTime
        this.breakTime = 60 - studyTime
    }

}