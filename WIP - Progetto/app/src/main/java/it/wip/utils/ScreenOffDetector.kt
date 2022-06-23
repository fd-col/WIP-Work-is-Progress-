package it.wip.utils

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.util.Log
import it.wip.viewModel.StoryStartedViewModel


class ScreenOffDetector(private val viewModel: StoryStartedViewModel) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action
        if (Intent.ACTION_SCREEN_OFF == action) {
            //viewModel.flag1 = true

            val am = context!!.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val tasks = am.getRunningTasks(1)
            val task = tasks[0].toString() // current task
            val correctTask = "topActivity=ComponentInfo{it.wip/it.wip.ui.activities.StoryStartedActivity}"

            if(correctTask in task){
                viewModel.flag3 = true
            }

            Log.e("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ", "$task")
            Log.e("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ", "${viewModel.flag3}")
            //TaskInfo{userId=0 stackId=1264 taskId=1936 displayId=0 isRunning=true baseIntent=Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] flg=0x10000000 cmp=it.wip/.MainActivity } baseActivity=ComponentInfo{it.wip/it.wip.MainActivity} topActivity=ComponentInfo{it.wip/it.wip.ui.activities.StoryStartedActivity} origActivity=null realActivity=ComponentInfo{it.wip/it.wip.MainActivity} numActivities=3 lastActiveTime=340369603 supportsSplitScreenMultiWindow=true resizeMode=1
        }
    }
}