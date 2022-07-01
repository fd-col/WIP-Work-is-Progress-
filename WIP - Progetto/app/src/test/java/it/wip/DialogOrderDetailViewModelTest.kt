package it.wip

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.wip.viewModel.DialogOrderDetailViewModel
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DialogOrderDetailViewModelTest {
    @Test
    fun buyShopElement(){
        val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val orderDetail = DialogOrderDetailViewModel(appContext as Application)
        val price = 1000
        val canBuy: Boolean = orderDetail.buyShopElement(price)
        assertEquals(canBuy, false)
    }
}