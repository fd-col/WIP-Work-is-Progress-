package it.wip.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import it.wip.MainActivity
import it.wip.R
import android.widget.Switch
import androidx.core.content.res.ResourcesCompat

class StartStoryActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_story)

        val backButton = findViewById<ImageButton>(R.id.back_button)

        val infoButton = findViewById<ImageButton>(R.id.info_button)

        val avatarSxButton = findViewById<ImageButton>(R.id.avatar_sx_button)

        val avatarDxButton = findViewById<ImageButton>(R.id.avatar_dx_button)

        val startButton = findViewById<ImageButton>(R.id.start_button)


        val slider = findViewById<com.google.android.material.slider.Slider>(R.id.seekBar_story_time)
        slider.setLabelFormatter { value: Float ->
            "${value.toInt()} min study/${60-value.toInt()} min pause"
        }

        val switchSilentMode = findViewById<Switch>(R.id.switch_silent_mode)
        val switchHardcoreMode = findViewById<Switch>(R.id.switch_hardcore_mode)
        switchSilentMode.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))
        switchHardcoreMode.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))

        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        backButton.setOnTouchListener { v, event ->

            when (event.action) {

                MotionEvent.ACTION_DOWN -> backButton.setImageResource(R.drawable.back_arrow_pressed)

                MotionEvent.ACTION_UP -> backButton.setImageResource(R.drawable.back_arrow)

            }

            v?.onTouchEvent(event) ?: true

        }

        infoButton.setOnTouchListener { v, event ->

            when (event.action) {

                MotionEvent.ACTION_DOWN -> infoButton.setImageResource(R.drawable.info_button_pressed)

                MotionEvent.ACTION_UP -> infoButton.setImageResource(R.drawable.info_button)

            }

            v?.onTouchEvent(event) ?: true

        }

        avatarSxButton.setOnTouchListener { v, event ->

            when (event.action) {

                MotionEvent.ACTION_DOWN -> avatarSxButton.setImageResource(R.drawable.avatar_sx_arrow_pressed)

                MotionEvent.ACTION_UP -> avatarSxButton.setImageResource(R.drawable.avatar_sx_arrow)

            }

            v?.onTouchEvent(event) ?: true

        }

        avatarDxButton.setOnTouchListener { v, event ->

            when (event.action) {

                MotionEvent.ACTION_DOWN -> avatarDxButton.setImageResource(R.drawable.avatar_dx_arrow_pressed)

                MotionEvent.ACTION_UP -> avatarDxButton.setImageResource(R.drawable.avatar_dx_arrow)

            }

            v?.onTouchEvent(event) ?: true

        }

        startButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> startButton.setImageResource(R.drawable.start_story_button_pressed)
                MotionEvent.ACTION_UP -> startButton.setImageResource(R.drawable.start_story_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        startButton?.setOnClickListener {
            startActivity(Intent(this, StoryStartedActivity::class.java))
        }



        //              SWITCH AVATAR
        val avatar = findViewById<ImageView>(R.id.avatar)
        var avatarTag = avatar.tag.toString()

        avatarDxButton?.setOnClickListener {
            if(avatarTag.equals("1")){
                avatar.setBackgroundResource(R.drawable.magritte)
                avatar.setTag("2")
                avatarTag = avatar.tag.toString()
            }else if(avatarTag.equals("2")){
                avatar.setBackgroundResource(R.drawable.ragazza_col_turbante)
                avatar.setTag("3")
                avatarTag = avatar.tag.toString()
            }else if(avatarTag.equals("3")){
                avatar.setBackgroundResource(R.drawable.venere)
                avatar.setTag("1")
                avatarTag = avatar.tag.toString()
            }
        }

    }
}