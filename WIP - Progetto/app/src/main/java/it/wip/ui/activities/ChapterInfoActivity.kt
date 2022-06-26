package it.wip.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MotionEvent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import it.wip.R
import it.wip.databinding.ActivityChapterInfoBinding
import it.wip.databinding.ActivityStoryDetailBinding
import it.wip.utils.fromShopElementNameToResource
import it.wip.viewModel.ChapterInfoViewModel
import it.wip.viewModel.StoryDetailViewModel
import kotlin.math.absoluteValue

class ChapterInfoActivity : AppCompatActivity() {

    lateinit var viewModel: ChapterInfoViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityChapterInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_chapter_info)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ChapterInfoViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        val datetimeStoryDate= binding.datetimeStoryDate
        val datetimeStoryTime = binding.datetimeStoryTime
        //seekBar create problems
        //val seekBar = findViewById<SeekBar>(R.id.seekBar_chapter_info)
        val switchSilentMode = binding.switchSilentModeChapterInfo
        val switchHardcoreMode = binding.switchHardcoreModeChapterInfo
        val avatarChoosed = binding.avatarChapterInfo
        val backButton = binding.backButtonStoryDetail


        //get the switchers' mode from the intent
        switchSilentMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)
        switchSilentMode.isChecked=true
        switchHardcoreMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)
        switchHardcoreMode.isChecked = true

        //get the chapter ID related with the chapter clicked in the StoryDetail
        val chapterID = intent.getIntExtra("chapterID", 0)

        //return the Chapter with chapterID taken by the intent in StoryDetail
        val chapter = viewModel.getChapter(chapterID)

        //set "date" on the Chapter Info created for chapterID
        datetimeStoryDate.text = chapter.createdOn
        datetimeStoryDate.movementMethod = ScrollingMovementMethod()

        //set "time" on the Chapter Info created for chapterID
        datetimeStoryTime.text = chapter.time

        //set "avatar" on the Chapter Info created for chapterID
        avatarChoosed.setBackgroundResource(fromShopElementNameToResource(chapter.avatar))



        //button to go back in the previous view
        backButton.setOnClickListener {
            startActivity(Intent(this, StoryDetailActivity::class.java))
        }
        backButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> backButton.setImageResource(R.drawable.back_arrow_pressed)
                MotionEvent.ACTION_UP -> backButton.setImageResource(R.drawable.back_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }
    }
}