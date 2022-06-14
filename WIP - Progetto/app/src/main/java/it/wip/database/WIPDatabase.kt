package it.wip.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import it.wip.database.dao.*
import it.wip.database.model.*
import kotlinx.coroutines.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

@Database(entities = [
    Chapter::class,
    Preference::class,
    Setting::class,
    ShopElement::class,
    Shopped::class,
    Story::class,
    User::class],
    version = 2)
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

                                    wipDb.userDao().insertWithoutCoroutines(
                                        User(1, "Venere", 30F, 120F, 30)
                                    )

                                    wipDb.shopElementDao().insertWithoutCoroutines(
                                        ShopElement("Venere", "", 0),
                                        ShopElement("Magritte", "", 0),
                                        ShopElement("Ragazza col turbante", "", 0)
                                    )

                                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ITALY)

                                    wipDb.shoppedDao().insertWithoutCoroutines(
                                        Shopped(1, "Venere", dateFormat.format(Date())),
                                        Shopped(1, "Magritte", dateFormat.format(Date())),
                                        Shopped(1, "Ragazza col turbante", dateFormat.format(Date()))
                                    )

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