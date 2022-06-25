package it.wip.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.media.AudioManager
import android.widget.Switch
import androidx.lifecycle.*
import it.wip.database.WIPDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StartStoryViewModel(application: Application) : AndroidViewModel(application) {

    private val app = application

    private val _storyName = MutableLiveData("")
    val storyName : LiveData<String>
        get() = _storyName

    private val _studyTime = MutableLiveData(10F)
    val studyTime : LiveData<Float>
        get() = _studyTime

    private var maxStudyTime = 60F

    private val _maxStudyTimeGraphic = MutableLiveData(50F)
    val maxStudyTimeGraphic : LiveData<Float>
        get() = _maxStudyTimeGraphic

    private val _breakTime = MutableLiveData(50F)
    val breakTime : LiveData<Float>
        get() = _breakTime

    private val _silenceMode = MutableLiveData(true)

    private val _hardcoreMode = MutableLiveData(true)

    //val avatarsName = mutableListOf<String>()
    private val allShoppedElements = mutableListOf<String>()
    private val shopElements = mutableListOf<String>()


    val avatarShoppedElements = mutableListOf<String>()

    //list of story's names to fill AutoCompleteTextView
    var storyNamesList = mutableListOf<String>()

    init {

        val userIdPreference = app.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)
        val wipDb = WIPDatabase.getInstance(app.applicationContext)

        val storyDao = wipDb.storyDao()
        val story = storyDao.getAllByUserWithoutCoroutines(userId)


        viewModelScope.launch {
            //put all story's name in a List to pass inside the AutoCompleteTextView
            for(singleStory in story) {
                storyNamesList.add(singleStory.storyName)
            }

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

            _studyTime.value = user.studyTime.toFloat()
            maxStudyTime = user.maxStudyTime.toFloat()
            _maxStudyTimeGraphic.value = maxStudyTime - 10F
            _breakTime.value = maxStudyTime - 10F

        }
    }

    fun setStoryName(storyName: String) {
        this._storyName.value = storyName
    }

    fun setStudyBreakTime(studyTime: Float) {
        this._studyTime.value = studyTime
        this._breakTime.value = maxStudyTime.minus(this._studyTime.value!!)
    }




    //              SWITCH SILENT MODE - NORMAL MODE
    fun silenceNormal(){
        val audioManager = app.applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if(_silenceMode.value!!){
            audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
            _silenceMode.value = false
        }else{
            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
            _silenceMode.value = true
        }
    }




    //              SWITCH SILENT MODE - HARDCORE MODE
    fun hardcoreMode(@SuppressLint("UseSwitchCompatOrMaterialCode") switchSilentMode: Switch){

        if (_silenceMode.value!! && _hardcoreMode.value!!) {
            switchSilentMode.isChecked = true
            silenceNormal()
            _silenceMode.value = false
            _hardcoreMode.value = false
            switchSilentMode.isClickable = false
        } else if (!_silenceMode.value!! && !_hardcoreMode.value!!) {
            switchSilentMode.isChecked = false
            silenceNormal()
            _silenceMode.value = true
            _hardcoreMode.value = true
            switchSilentMode.isClickable = true
        } else if (!_silenceMode.value!! && _hardcoreMode.value!!) {
            _silenceMode.value = false
            _hardcoreMode.value = false
            switchSilentMode.isClickable = false
        }
    }


    //              RETURN SELECTED MODE
    fun selectedMode(): Int {
        return if (_hardcoreMode.value!!) { 0 } else { 1 }
    }
}