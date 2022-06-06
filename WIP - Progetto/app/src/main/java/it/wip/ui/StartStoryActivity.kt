package it.wip.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import it.wip.MainActivity
import it.wip.R
import android.widget.Switch
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import it.wip.databinding.ActivityStartStoryBinding

class StartStoryActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityStartStoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_start_story)


        val slider = findViewById<com.google.android.material.slider.Slider>(R.id.seekBar_story_time)
        slider.setLabelFormatter { value: Float ->
            "${value.toInt()} min study/${60-value.toInt()} min pause"
        }

        val switchSilentMode = findViewById<Switch>(R.id.switch_silent_mode)
        val switchHardcoreMode = findViewById<Switch>(R.id.switch_hardcore_mode)
        switchSilentMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)
        switchHardcoreMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)

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
            startActivity(Intent(this, StoryStartedActivity::class.java))
        }



        //              SWITCH AVATAR
        val avatar = findViewById<ImageView>(R.id.avatar)
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
    }
}