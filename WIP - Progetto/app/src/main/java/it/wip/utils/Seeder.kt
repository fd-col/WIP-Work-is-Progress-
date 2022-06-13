package it.wip.utils

import android.content.Context
import it.wip.database.WIPDatabase
import it.wip.database.model.Story
import it.wip.database.model.User

class Seeder {

    companion object {
        suspend fun seed(wipDatabase: WIPDatabase) {

            val userDao = wipDatabase.userDao()
            userDao.insert(User(1, "Venere", 30F, 100F, 0))

            val storyDao = wipDatabase.storyDao()
            storyDao.insert(Story(1, "Coding","11/06/2022", 1))

        }
    }

}