package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.model.Preference
import it.wip.database.model.User
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val wipDb = WIPDatabase.getInstance(application.applicationContext)

    private var userId = 0

    private lateinit var user: User

    private val settingsUser = mutableListOf<String>()

    var breakTime = 10

    private val _studyTime = MutableLiveData(10F)
    val studyTime : LiveData<Float>
        get() = _studyTime

    private val _maxStudyTime = MutableLiveData(60)
    val maxStudyTime: LiveData<Int>
        get() = _maxStudyTime

    private val _maxStudyTimeGraphic = MutableLiveData(50F)
    val maxStudyTimeGraphic : LiveData<Float>
        get() = _maxStudyTimeGraphic

    private val _lefthandMode = MutableLiveData(false)
    val lefthandMode: LiveData<Boolean>
        get() = _lefthandMode

    init {
        val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)

        viewModelScope.launch {

            user = wipDb.userDao().getUserById(userId)

            _studyTime.value = user.studyTime.toFloat()

            _maxStudyTime.value = user.maxStudyTime

            _maxStudyTimeGraphic.value = user.maxStudyTime.toFloat() - 10

            breakTime = user.maxStudyTime - 10

            val preferences = wipDb.preferenceDao().getAllByUser(userId)

            for (preference in preferences) {
                settingsUser.add(preference.setting)
            }

            if(settingsUser.contains("lefthand_mode"))
                _lefthandMode.value = true

        }
    }

    fun setStudyBreakTime(studyTime: Float) {
        this._studyTime.value = studyTime
        this.breakTime = maxStudyTime.value!!.minus(this._studyTime.value!!).toInt()

        viewModelScope.launch {
            user.studyTime = studyTime.toInt()
            wipDb.userDao().update(user)
        }
    }

    fun setMaxStudyTime(maxStudyTime: Int) {

        _maxStudyTime.value = maxStudyTime

        _maxStudyTimeGraphic.value = maxStudyTime.toFloat() - 10

        breakTime = maxStudyTime - _studyTime.value!!.toInt()

        viewModelScope.launch {
            user.maxStudyTime = maxStudyTime
            wipDb.userDao().update(user)
        }

    }

    fun setLefthandMode(checked: Boolean) {
        _lefthandMode.value = checked

        viewModelScope.launch {

            val preferenceDao = wipDb.preferenceDao()

            if(checked) {
                if(!settingsUser.contains("lefthand_mode")) {
                    preferenceDao.insert(Preference(userId, "lefthand_mode"))
                    settingsUser.add("lefthand_mode")
                }
            } else {
                preferenceDao.delete(userId, "lefthand_mode")
                settingsUser.remove("lefthand_mode")
            }
        }

    }

}