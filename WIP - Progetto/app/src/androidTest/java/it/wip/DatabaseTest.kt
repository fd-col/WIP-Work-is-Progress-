package it.wip

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.wip.database.WIPDatabase
import it.wip.database.dao.ChapterDao
import it.wip.database.dao.StoryDao
import it.wip.database.model.Chapter
import it.wip.database.model.Story
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var storyDao: StoryDao
    private lateinit var chapterDao: ChapterDao
    private lateinit var db: WIPDatabase

    private lateinit var chapter1: Chapter
    private lateinit var chapter2: Chapter

    private val storyToInsert = Story(8,"Nuova Story",
                "30-06-22 11:00:12", 1)
    private val chapterToInsert = Chapter(10, "Capitol1", "00:30",
                "30/06/22 10.20.20", 50,10,1,"venere",8)

    private lateinit var storyInserted: Story
    private lateinit var chapterInserted: Chapter


    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = WIPDatabase.getInstance(context)

        storyDao = db.storyDao()
        chapterDao = db.chapterDao()
    }

    private fun getChaptersByIds(){
        //get a Chapter filtered by "storyId" and create an instance of it
        chapter1 = chapterDao.getAllByStory(2)[0] // chapter1 in the 2° story
        chapter2 = chapterDao.getAllByStory(2)[1] // chapter2 in the 2° story
    }

    private fun insertStoryWithChapter() {
        storyDao.insertWithoutCoroutines(storyToInsert)
        chapterDao.insertWithoutCoroutines(chapterToInsert)

        storyInserted = storyDao.getAllByIdStoryWithoutCoroutines(8)[0]
        chapterInserted = chapterDao.getAllById(10)[0]
    }

    @After
    @Throws(IOException::class)
    fun closeDb() = db.close()

    @Test //test the method "getAllByStory(storyId)" to receive back the right name of the Chapter inside the DB
    @Throws(Exception::class)
    fun getFirstChapter() {
        getChaptersByIds()
        assertEquals(chapter1.chapterName, "Capitol1") // CORRECT
    }

    @Test //test the method "getAllByStory(storyId)" to receive back the right name of the Chapter inside the DB
    @Throws(Exception::class)
    fun getSecondChapter() {
        getChaptersByIds()
        assertEquals(chapter2.chapterName, "Capitol1") //FAILURE
    }

    @Test // test if methods "insertWithoutCoroutines(<Story>)" & "insertWithoutCoroutines(<Chapter>)"
          // are correctly inserted in DB
    @Throws(Exception::class)
    fun insertAndRetrive() {
        insertStoryWithChapter()
        assertEquals(storyToInsert.storyName, storyInserted.storyName)  // CORRECT
        assertEquals(chapterToInsert.chapterName, chapterInserted.chapterName) // CORRECT
    }

}