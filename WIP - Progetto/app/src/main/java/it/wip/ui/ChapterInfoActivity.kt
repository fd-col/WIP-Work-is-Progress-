package it.wip.ui

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import it.wip.R

class ChapterInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_info)

        val switchSilentMode = findViewById<Switch>(R.id.switch_silent_mode_chapter_info)
        val switchHardcoreMode = findViewById<Switch>(R.id.switch_hardcore_mode_chapter_info)
        switchSilentMode.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))
        switchHardcoreMode.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))
    }
}