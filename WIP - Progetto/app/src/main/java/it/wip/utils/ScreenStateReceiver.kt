package it.wip.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView

class ScreenStateReceiver(private val quote: TextView, private val state: String) : BroadcastReceiver() {

    private val encouragement = encouragementQuotes()
    private val pause = pauseQuotes()

    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action
        if (Intent.ACTION_SCREEN_ON == action) {
            if(state=="study"){
                val actualEncouragement: Int = encouragement.random()
                quote.setText(actualEncouragement)
            }else if(state=="pause"){
                val actualPause: Int = pause.random()
                quote.setText(actualPause)
            }
        }
    }
}