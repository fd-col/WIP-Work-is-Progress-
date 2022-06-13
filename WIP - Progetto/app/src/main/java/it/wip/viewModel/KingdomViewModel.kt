package it.wip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import it.wip.database.WIPDatabase

class KingdomViewModel(application: Application) : AndroidViewModel(application) {
    private val db = WIPDatabase.getInstance(application)

    val story = db.storyDao().getAll()

}