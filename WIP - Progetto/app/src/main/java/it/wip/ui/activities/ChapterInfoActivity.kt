package it.wip.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import it.wip.R
import it.wip.databinding.ActivityChapterInfoBinding
import it.wip.utils.fromShopElementNameToLocalizedName
import it.wip.utils.fromShopElementNameToResource
import it.wip.viewModel.ChapterInfoViewModel

class ChapterInfoActivity : AppCompatActivity() {

    lateinit var viewModel: ChapterInfoViewModel

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        //LeftHand mode activation
        val lefthandPreference = applicationContext.getSharedPreferences("lefthandPreference", Context.MODE_PRIVATE)
        val lefthand = lefthandPreference.getInt("lefthand", Context.MODE_PRIVATE)
        if(lefthand==1)  setTheme(R.style.RightToLefTheme) else setTheme(R.style.LeftToRighTheme)

        super.onCreate(savedInstanceState)

        val binding: ActivityChapterInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_chapter_info)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ChapterInfoViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        val datetimeStoryDate= binding.datetimeStoryDate
        val datetimeStoryTime = binding.datetimeStoryTime
        val studyTime = binding.studyTime
        val breakTime = binding.breakTime
        val switchSilentMode = binding.switchSilentModeChapterInfo
        val switchHardcoreMode = binding.switchHardcoreModeChapterInfo
        val avatarChoosed = binding.avatarChapterInfo
        val backButton = binding.backButtonStoryDetail


        //get the chapter ID related with the chapter clicked in the StoryDetail
        val chapterID = intent.getIntExtra("chapterID", 0)

        //return the Chapter with chapterID taken by the intent in StoryDetail
        val chapter = viewModel.getChapter(chapterID)

        //set "date" on the Chapter Info related to chapterID
        datetimeStoryDate.text = chapter.createdOn
        datetimeStoryDate.movementMethod = ScrollingMovementMethod()

        //set "time" on the Chapter Info created for chapterID
        datetimeStoryTime.text = chapter.time

        //set "studyTime" on the Chapter Info created for chapterID
        studyTime.text = chapter.studyTime.toString()+" min"
        breakTime.text = chapter.breakTime.toString()+" min"

        //set "mode" on the Chapter Info related to chapterID's variable
        switchSilentMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)
        switchHardcoreMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)
        //check study mode (Silence or Hardcore) in the single Chapter Info related to chapterID's variable
        when (chapter.mode) {
            1 -> { //silent mode
                switchSilentMode.isChecked = true
                switchHardcoreMode.isChecked = false
            }
            2 -> { //harcore mode
                switchSilentMode.isChecked = true
                switchHardcoreMode.isChecked = true
            }
            else -> { //no mode selected
                switchSilentMode.isChecked = false
                switchHardcoreMode.isChecked = false
            }
        }
        switchHardcoreMode.isEnabled = false
        switchSilentMode.isEnabled = false


        //set "avatar" on the Chapter Info created for chapterID
        avatarChoosed.setBackgroundResource(fromShopElementNameToResource(chapter.avatar))
        avatarChoosed.contentDescription = getString(fromShopElementNameToLocalizedName(chapter.avatar))


        //button to go back in the previous StoryDetailActivity
        backButton.setOnClickListener {
            val bundle = Bundle()

            val storyTitle = intent.getStringExtra("storyName")
            bundle.putString("storyName", storyTitle)

            val storyID = intent.getIntExtra("storyID", 0)
            bundle.putInt("storyID", storyID)

            val intent = Intent(this, StoryDetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
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