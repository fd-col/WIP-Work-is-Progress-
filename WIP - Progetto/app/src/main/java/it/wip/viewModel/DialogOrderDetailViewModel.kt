package it.wip.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.model.Shopped
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DialogOrderDetailViewModel(application: Application) : AndroidViewModel(application) {

    val app = application

    private val _userId = MutableLiveData<Int>(0)
    val userId : LiveData<Int>
        get() = _userId

    lateinit var shopElementName: String

    init {

        val userIdPreference = app.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)

        _userId.value = userIdPreference.getInt("userId", Context.MODE_PRIVATE)

    }

    fun buyShopElement(price: Int) {

        val wipDb = WIPDatabase.getInstance(app.applicationContext)

        viewModelScope.launch {

            updateUserCoins(price, wipDb)

            insertNewShopped(wipDb)

        }

    }

    suspend fun updateUserCoins(price: Int, wipDb: WIPDatabase) {

        val user = wipDb.userDao().getUserById(userId.value!!)

        user.coins = user.coins - price

        wipDb.userDao().update(user)

    }

    suspend fun insertNewShopped(wipDb: WIPDatabase) {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ITALY)

        wipDb.shoppedDao().insert(
            Shopped(userId.value!!, shopElementName, dateFormat.format(Date()).toString())
        )

    }

}