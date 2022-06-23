package it.wip.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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
import it.wip.viewModel.ChapterInfoViewModel
import it.wip.viewModel.StoryDetailViewModel

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


        val datetimeStoryStarted = findViewById<TextView>(R.id.datetime_story_started)
        val datetimeStoryEnded = findViewById<TextView>(R.id.datetime_story_ended)
        //seekBar create problems
        //val seekBar = findViewById<SeekBar>(R.id.seekBar_chapter_info)
        val switchSilentMode = findViewById<Switch>(R.id.switch_silent_mode_chapter_info)
        val switchHardcoreMode = findViewById<Switch>(R.id.switch_hardcore_mode_chapter_info)
        val avatar = findViewById<ImageView>(R.id.avatar_chapter_info)
        val backButton = findViewById<ImageButton>(R.id.back_button_story_detail)

        switchSilentMode.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))
        switchHardcoreMode.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))

        //get the chapter ID related with the chapter clicked in the StoryDetail
        val chapterID = intent.getIntExtra("chapterID", 0)

        for(i in 0..viewModel.chapter.lastIndex){
            if(chapterID == viewModel.chapter[i].id) {
                datetimeStoryStarted.text = viewModel.chapter[i].createdOn
                //datetimeStoryEnded
                //seekBar
                //switchSilentMode
                //switchHardcoreMode
                //avatar.setImageResource(viewModel.chapter[i].avatar)
            }

        }



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