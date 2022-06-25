package it.wip.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.model.ShopElement
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {

    private val _coins = MutableLiveData(0)
    val coins: LiveData<Int>
        get() = _coins

    val avatars = mutableListOf<ShopElement>()

    val backgrounds = mutableListOf<ShopElement>()

    val shoppedElementNames = mutableListOf<String>()

    init {

        val userIdPreference = application.applicationContext.getSharedPreferences("userId", Context.MODE_PRIVATE)
        val userId = userIdPreference.getInt("userId", Context.MODE_PRIVATE)

        val wipDb = WIPDatabase.getInstance(application.applicationContext)

        viewModelScope.launch {

            val user = wipDb.userDao().getUserById(userId)
            _coins.value = user.coins

            val shopElements = wipDb.shopElementDao().getAll()

            for(shopElement in shopElements) {

                if(shopElement.type == "avatar")
                    avatars.add(shopElement)
                else
                    backgrounds.add(shopElement)
            }

            val shoppedElements = wipDb.shoppedDao().getAllByUser(userId)

            for(shoppedElement in shoppedElements)
                shoppedElementNames.add(shoppedElement.shopElement)

        }

    }

}