package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import it.wip.database.WIPDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StartStoryViewModel(application: Application) : AndroidViewModel(application) {

    private val _storyName = MutableLiveData("")
    val storyName : LiveData<String>
        get() = _storyName

    private val _studyTime = MutableLiveData(10F)
    val studyTime : LiveData<Float>
        get() = _studyTime

    private val _maxStudyTime = MutableLiveData(60F)
    val maxStudyTime : LiveData<Float>
        get() = _maxStudyTime

    private val _maxStudyTimeGraphic = MutableLiveData(50F)
    val maxStudyTimeGraphic : LiveData<Float>
        get() = _maxStudyTimeGraphic

    private val _breakTime = MutableLiveData(50F)
    val breakTime : LiveData<Float>
        get() = _breakTime

    //val avatarsName = mutableListOf<String>()
    val allShoppedElements = mutableListOf<String>()
    val shopElements = mutableListOf<String>()


    val avatarShoppedElements = mutableListOf<String>()

    init {

        val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)
        val wipDb = WIPDatabase.getInstance(application.applicationContext)

        viewModelScope.launch {
            runBlocking {
                val currentShoppedElements = wipDb.shoppedDao().getAllByUser(userId)
                val allElements = wipDb.shopElementDao().getAll()

                for(i in currentShoppedElements){
                    //avatarsName.add(avatar.shopElement)
                    allShoppedElements.add(i.shopElement)
                }
                for (i in allElements){
                    if(i.type == "avatar"){
                        shopElements.add(i.elementName)
                    }
                }

                for(i in allShoppedElements){
                    for(j in shopElements){
                        if(i==j){
                            avatarShoppedElements.add(i)
                        }
                    }
                }

            }

            val user = wipDb.userDao().getUserById(userId)
            _studyTime.value = user.studyTime
            _maxStudyTime.value = user.maxStudyTime
            _maxStudyTimeGraphic.value = _maxStudyTime.value!! - 10F
            _breakTime.value = _maxStudyTime.value!! - 10F

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