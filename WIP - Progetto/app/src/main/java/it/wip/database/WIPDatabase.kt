package it.wip.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import it.wip.database.dao.*
import it.wip.database.model.*
import java.util.concurrent.Executors

@Database(entities = [
    Chapter::class,
    Preference::class,
    Setting::class,
    ShopElement::class,
    Shopped::class,
    Story::class,
    User::class],
    version = 1)
abstract class WIPDatabase : RoomDatabase(){

    abstract fun chapterDao() : ChapterDao
    abstract fun preferenceDao() : PreferenceDao
    abstract fun settingDao() : SettingDao
    abstract fun shopElementDao() : ShopElementDao
    abstract fun shoppedDao() : ShoppedDao
    abstract fun storyDao() : StoryDao
    abstract fun userDao() : UserDao

    companion object {
        @Volatile
        private var INSTANCE: WIPDatabase? = null

        fun getInstance(context: Context): WIPDatabase {

            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    WIPDatabase::class.java, "wip_database"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            Executors.newSingleThreadScheduledExecutor().execute(Runnable {
                                kotlin.run {
                                    val userDao = getInstance(context).userDao()
                                    userDao.insert(User(1, "Venere", "Boh", 30))
                                }
                            })

                        }
                    })
                    .build()
                    .also { INSTANCE = it }

            }
        }

    }

}