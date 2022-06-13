package it.wip.utils

import android.content.Context
import it.wip.database.WIPDatabase
import it.wip.database.model.User

class Seeder {

    companion object {
        suspend fun seed(wipDatabase: WIPDatabase) {
            val userDao = wipDatabase.userDao()
            userDao.insert(User(1, "Venere", 30, 60, 0))
        }
    }

}