package it.wip.viewModel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import it.wip.database.dao.StoryDao
import it.wip.database.model.Chapter
import it.wip.database.model.Story
import it.wip.utils.ScreenOffDetector
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

class StoryStartedViewModel(application: Application) : AndroidViewModel(application){

    private val shoppedImgs = mutableListOf<String>()
    private val imgsInShop = mutableListOf<String>()
    val backgroundShoppedElements = mutableListOf<String>()
    val db: WIPDatabase
    val id: Int

    private val storyDao: StoryDao
    val story: Array<Story>

    private val chapterDao: ChapterDao
    val chapter: Array<Chapter>
    private var increment: Int = 1


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

        storyDao = wipDb_.storyDao()
        story = storyDao.getAllByUserWithoutCoroutines(userId_)

        chapterDao = wipDb_.chapterDao()
        chapter = chapterDao.getAllByUserWithoutCoroutines(userId_)

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

        val earnedCoins: Int =
            if(actualTime>studyPlusPause){
                (meritCoefficient*(actualTime/studyPlusPause)).toInt()
            } else{
                (meritCoefficient*(actualTime/studyTime)).toInt()
            }

        viewModelScope.launch {
            val user = db.userDao().getUserById(id)
            user.coins = user.coins+earnedCoins
            db.userDao().update(user)
        }

        return earnedCoins.toString()
    }

    //function to add a new story
    suspend fun addNewStory(newStoryName: String, myTime: Long){
        //take all stories to add the new one at the end of the DB
        val allStories = storyDao.getAll()
        val lastStoryIndex = allStories.lastIndex+1
        Log.e("error", lastStoryIndex.toString())

        val lastChapterIndex = chapter.lastIndex+1
        Log.e("error", lastChapterIndex.toString())

        val dateFormat = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.ITALY)

        viewModelScope.launch {
            runBlocking() {
                //take user's stories and check if the new Story name is already in the DB
                for (singleStory in story) {
                    Log.e("error", singleStory.toString())

                    Log.e("newStoryName", newStoryName)
                    Log.e("storyNameOfTheStory",singleStory.storyName)
                    //if the story is already in the DB, then add another chapter to the story
                    if (singleStory.storyName == newStoryName) {
                        Log.e("in DB", newStoryName)
                        chapterDao.insert(
                            Chapter(
                                chapter[lastChapterIndex].id + 1, "Chapter $increment",
                                myTime.toString(), dateFormat.format(Date()).toString(),
                                singleStory.id, id
                            )
                        )
                        increment++ //increment index for chapter's names into a story already created
                    }
                    //in case there isn't another story with the same name of that one we want to crate,
                    // add a new one with a chapter inside into the DB
                    else {
                        storyDao.insert(
                            Story(
                                allStories[lastStoryIndex].id + 1, newStoryName,
                                dateFormat.format(Date()).toString(), id
                            )
                        )
                        chapterDao.insert(
                            Chapter(
                                chapter[lastChapterIndex].id + 1, "Chapter 1",
                                myTime.toString(), dateFormat.format(Date()).toString(),
                                allStories[lastStoryIndex].id + 1, id
                            )
                        )

                    }


                }
            }//runBlocking

        }//viewModelScope

    }
}