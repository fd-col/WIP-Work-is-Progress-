package it.wip.viewModel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.utils.ScreenOffDetector
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StoryStartedViewModel(application: Application) : AndroidViewModel(application){

    private val shoppedImgs = mutableListOf<String>()
    private val imgsInShop = mutableListOf<String>()
    val backgroundShoppedElements = mutableListOf<String>()
    val db: WIPDatabase
    val id: Int




    //              GUARDS
    /*
    * in riferimento al listener "setOnChronometerTickListener", che esegue il contenuto del
    * blocco {} ogni volta che trascorre un secondo, le variabili first, second, third e
    * fourthAlreadyExecuted garantiscono che la funzione "backgroundEvolution" venga eseguita
    * una sola volta per slot temporale
    * */
    var firstAlreadyExecuted = true
    var secondAlreadyExecuted = true
    var thirdAlreadyExecuted = true
    var fourthAlreadyExecuted = true




    var flag1: Boolean = false
    var flag2: Boolean = false

    val pauseIntentFilter = IntentFilter(Intent.ACTION_SCREEN_OFF)
    val screenOffDetector = ScreenOffDetector(this)

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




    fun coinCalculator(studyTime: Long, breakTime: Long, actualTime: Long): String{
        var meritCoefficient = studyTime/breakTime
        if(meritCoefficient<1){
            meritCoefficient = 1
        }
        val studyPlusPause = studyTime+breakTime
        val earnedCoins: Int

        if(actualTime>studyPlusPause){
            earnedCoins = (meritCoefficient*(actualTime/studyPlusPause)).toInt()
        }else{
            earnedCoins = (meritCoefficient*(actualTime/studyTime)).toInt()
        }

        viewModelScope.launch {
            val user = db.userDao().getUserById(id)
            user.coins = user.coins+earnedCoins
            db.userDao().update(user)
        }

        return earnedCoins.toString()
    }
}