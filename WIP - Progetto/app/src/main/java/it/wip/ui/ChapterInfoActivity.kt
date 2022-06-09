package it.wip.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import it.wip.R

class ChapterInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_info)

        val backButton = findViewById<ImageButton>(R.id.back_button_story_detail)
        val switchSilentMode = findViewById<Switch>(R.id.switch_silent_mode_chapter_info)
        val switchHardcoreMode = findViewById<Switch>(R.id.switch_hardcore_mode_chapter_info)
        switchSilentMode.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))
        switchHardcoreMode.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))



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