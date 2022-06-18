package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.model.Shopped
import it.wip.database.model.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

class DialogOrderDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val app = application

    private val wipDb = WIPDatabase.getInstance(app.applicationContext)

    private lateinit var user: User

    lateinit var shopElementName: String

    private val _userId = MutableLiveData(0)
    val userId : LiveData<Int>
        get() = _userId

    init {

        val userIdPreference = app.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)

        _userId.value = userIdPreference.getInt("userId", Context.MODE_PRIVATE)

        viewModelScope.launch {
            user = wipDb.userDao().getUserById(userId.value!!)
        }

    }

    fun buyShopElement(price: Int): Boolean {

        if(price > user.coins)
            return false

        viewModelScope.launch {

            runBlocking {
                updateUserCoins(price)
                insertNewShopped()
            }

        }

        return true

    }

    private suspend fun updateUserCoins(price: Int) {

        user.coins = user.coins - price

        wipDb.userDao().update(user)

    }

    private suspend fun insertNewShopped() {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ITALY)

        wipDb.shoppedDao().insert(
            Shopped(userId.value!!, shopElementName, dateFormat.format(Date()).toString())
        )

    }

}