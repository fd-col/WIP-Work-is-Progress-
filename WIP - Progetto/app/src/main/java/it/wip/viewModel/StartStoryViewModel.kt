package it.wip.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartStoryViewModel : ViewModel() {

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