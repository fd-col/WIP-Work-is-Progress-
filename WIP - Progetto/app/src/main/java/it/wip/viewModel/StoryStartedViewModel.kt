package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StoryStartedViewModel(application: Application) : AndroidViewModel(application){

    val shoppedImgs = mutableListOf<String>()
    val imgsInShop = mutableListOf<String>()
    val backgroundShoppedElements = mutableListOf<String>()

    init {

        val userIdPreference_ = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        val userId_ = userIdPreference_.getInt("userId", Context.MODE_PRIVATE)
        val wipDb_ = WIPDatabase.getInstance(application.applicationContext)

        viewModelScope.launch {
            runBlocking {
                val currentShoppedElements = wipDb_.shoppedDao().getAllByUser(userId_)
                val allElements = wipDb_.shopElementDao().getAll()

                for(i in currentShoppedElements){
                    shoppedImgs.add(i.shopElement)
                }
                for (i in allElements){
                    if(i.type == "background"){
                        imgsInShop.add(i.elementName)
                    }
                }

                for(i in shoppedImgs){
                    for(j in imgsInShop){
                        if(i==j){
                            backgroundShoppedElements.add(i)
                        }
                    }
                }
            }
        }
    }
}

/*
package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import it.wip.database.WIPDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StartStoryViewModel(application: Application) : AndroidViewModel(application) {


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
* */
