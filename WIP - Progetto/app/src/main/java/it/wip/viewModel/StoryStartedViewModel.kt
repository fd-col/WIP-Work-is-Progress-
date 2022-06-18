package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StoryStartedViewModel(application: Application) : AndroidViewModel(application){

    private val shoppedImgs = mutableListOf<String>()
    private val imgsInShop = mutableListOf<String>()
    val backgroundShoppedElements = mutableListOf<String>()
    val db: WIPDatabase
    val id: Int

    init {

        val userIdPreference_ = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        val userId_ = userIdPreference_.getInt("userId", Context.MODE_PRIVATE)
        val wipDb_ = WIPDatabase.getInstance(application.applicationContext)
        db = wipDb_
        id = userId_

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




    fun coinCalculator(studyTime: Long, breakTime: Long, actualTime: Long){
        val meritCoefficient = studyTime/breakTime
        val studyPlusPause = studyTime+breakTime
        val earnedCoins = (meritCoefficient*(actualTime/studyPlusPause)).toInt()

        viewModelScope.launch {
            val user = db.userDao().getUserById(id)
            user.coins = user.coins+earnedCoins
            db.userDao().update(user)
        }
    }
}