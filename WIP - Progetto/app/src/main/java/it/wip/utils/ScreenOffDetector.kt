package it.wip.utils

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import it.wip.viewModel.StoryStartedViewModel


class ScreenOffDetector(private val viewModel: StoryStartedViewModel) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action
        if (Intent.ACTION_SCREEN_OFF == action) {

            val am = context!!.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val tasks = am.getRunningTasks(1)
            val task = tasks[0].toString() // current task
            val correctTask = "topActivity=ComponentInfo{it.wip/it.wip.ui.activities.StoryStartedActivity}"

            if(correctTask in task){
                viewModel.flag1 = true
            }

            //Log.e("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ", "$task")
            //Log.e("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ", "${viewModel.flag1}")
        }
    }
}