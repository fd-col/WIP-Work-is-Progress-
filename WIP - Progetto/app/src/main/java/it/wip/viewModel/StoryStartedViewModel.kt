package it.wip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class StoryStartedViewModel(application: Application) : AndroidViewModel(application){
    
}

/*
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
* */