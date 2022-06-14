package it.wip.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import it.wip.database.WIPDatabase
import it.wip.database.model.ShopElement
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {

    val avatars = mutableListOf<ShopElement>()

    val backgrounds = mutableListOf<ShopElement>()

    init {
        val wipDb = WIPDatabase.getInstance(application.applicationContext)

        viewModelScope.launch {
            val shopElements = wipDb.shopElementDao().getAll()

            for(shopElement in shopElements) {
                if(shopElement.type == "avatar")
                    avatars.add(shopElement)
                else
                    backgrounds.add(shopElement)
            }

        }

    }

}