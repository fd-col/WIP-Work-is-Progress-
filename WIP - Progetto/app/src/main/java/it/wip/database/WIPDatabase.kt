package it.wip.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import it.wip.database.dao.*
import it.wip.database.model.*
import it.wip.utils.seed
import kotlinx.coroutines.*
import java.util.concurrent.Executors

@Database(entities = [
    Chapter::class,
    Preference::class,
    Setting::class,
    ShopElement::class,
    Shopped::class,
    Story::class,
    User::class],
    version = 4)
abstract class WIPDatabase : RoomDatabase() {

    abstract fun chapterDao(): ChapterDao
    abstract fun preferenceDao(): PreferenceDao
    abstract fun settingDao(): SettingDao
    abstract fun shopElementDao(): ShopElementDao
    abstract fun shoppedDao(): ShoppedDao
    abstract fun storyDao(): StoryDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: WIPDatabase? = null

        fun getInstance(context: Context): WIPDatabase {

            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    WIPDatabase::class.java, "wip_database"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            Executors.newSingleThreadScheduledExecutor().execute(Runnable {
                                kotlin.run {

                                    val wipDb = getInstance(context)

                                    seed(wipDb)

                                }
                            })

                        }
                    })
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}