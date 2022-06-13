package it.wip.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.MotionEvent
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.wip.MainActivity
import it.wip.R
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import it.wip.DialogActivityStoryStarted
import it.wip.databinding.ActivityStartStoryBinding
import it.wip.viewModel.StartStoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StartStoryActivity : AppCompatActivity() {

    private lateinit var viewModel: StartStoryViewModel

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityStartStoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_start_story)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(StartStoryViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.seekBarStoryTime.value = viewModel.studyTime.value!!

        val seekBarStoryTime = binding.seekBarStoryTime

        binding.storyTitleSetByTheUser.doOnTextChanged { text, _, _, _ ->
            viewModel.setStoryName(text.toString())
        }

        seekBarStoryTime.addOnChangeListener { _, value, _ ->
            viewModel.setStudyBreakTime(value)
        }

        seekBarStoryTime.setLabelFormatter {

            "${viewModel.studyTime.value!!.toInt()} min study/${viewModel.breakTime.value?.toInt()} min pause"

        }

        binding.switchSilentMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)
        binding.switchHardcoreMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)

        // button backwards
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.backButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.backButton.setImageResource(R.drawable.back_arrow_pressed)
                MotionEvent.ACTION_UP -> binding.backButton.setImageResource(R.drawable.back_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        binding.infoButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.infoButton.setImageResource(R.drawable.info_button_pressed)
                MotionEvent.ACTION_UP -> binding.infoButton.setImageResource(R.drawable.info_button)
            }
            v?.onTouchEvent(event) ?: true

        }

        binding.avatarSxButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.avatarSxButton.setImageResource(R.drawable.avatar_sx_arrow_pressed)
                MotionEvent.ACTION_UP -> binding.avatarSxButton.setImageResource(R.drawable.avatar_sx_arrow)
            }
            v?.onTouchEvent(event) ?: true

        }

        binding.avatarDxButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.avatarDxButton.setImageResource(R.drawable.avatar_dx_arrow_pressed)
                MotionEvent.ACTION_UP -> binding.avatarDxButton.setImageResource(R.drawable.avatar_dx_arrow)
            }
            v?.onTouchEvent(event) ?: true

        }

        binding.startButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.startButton.setImageResource(R.drawable.start_story_button_pressed)
                MotionEvent.ACTION_UP -> binding.startButton.setImageResource(R.drawable.start_story_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        binding.startButton.setOnClickListener {

            val intent = Intent(this, StoryStartedActivity::class.java)

            //intent.putExtra(viewModel.studyTime)

           // startActivity()
        }

        //              SWITCH AVATAR
        val avatar = binding.avatar
        var avatarTag: String = avatar.tag.toString()

        binding.avatarDxButton.setOnClickListener {
            when (avatarTag) {
                "1" -> {
                    avatar.setBackgroundResource(R.drawable.magritte)
                    avatar.tag = "2"
                    avatarTag = avatar.tag.toString()
                }
                "2" -> {
                    avatar.setBackgroundResource(R.drawable.ragazza_col_turbante)
                    avatar.tag = "3"
                    avatarTag = avatar.tag.toString()
                }
                "3" -> {
                    avatar.setBackgroundResource(R.drawable.venere)
                    avatar.tag = "1"
                    avatarTag = avatar.tag.toString()
                }
            }
        }

        binding.avatarSxButton.setOnClickListener {
            when (avatarTag) {
                "1" -> {
                    avatar.setBackgroundResource(R.drawable.ragazza_col_turbante)
                    avatar.tag = "3"
                    avatarTag = avatar.tag.toString()
                }
                "2" -> {
                    avatar.setBackgroundResource(R.drawable.venere)
                    avatar.tag = "1"
                    avatarTag = avatar.tag.toString()
                }
                "3" -> {
                    avatar.setBackgroundResource(R.drawable.magritte)
                    avatar.tag = "2"
                    avatarTag = avatar.tag.toString()
                }
            }
        }

        binding.infoButton.setOnClickListener {
            val dialogInfo = DialogActivityStoryStarted()
            dialogInfo.show(supportFragmentManager, "info")
        }
    }
}