package it.wip.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import it.wip.database.dao.*
import it.wip.database.model.*
import it.wip.utils.Seeder
import kotlinx.coroutines.*

@Database(entities = [
    Chapter::class,
    Preference::class,
    Setting::class,
    ShopElement::class,
    Shopped::class,
    Story::class,
    User::class],
    version = 1)
abstract class WIPDatabase : RoomDatabase() {

    abstract fun chapterDao(): ChapterDao
    abstract fun preferenceDao(): PreferenceDao
    abstract fun settingDao(): SettingDao
    abstract fun shopElementDao(): ShopElementDao
    abstract fun shoppedDao(): ShoppedDao
    abstract fun storyDao(): StoryDao
    abstract fun userDao(): UserDao

    private class WIPDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    Seeder.seed(database)
                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: WIPDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope): WIPDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WIPDatabase::class.java, "wip_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WIPDatabaseCallback(coroutineScope))
                    .build()
                    .also { INSTANCE = it }
                INSTANCE = instance
                return instance
            }
        }
    }
}