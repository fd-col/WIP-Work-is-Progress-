package it.wip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Chronometer
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import it.wip.ui.FrameFragment
import it.wip.ui.HeaderFragment
import it.wip.ui.MenuFragment
import it.wip.ui.StartStoryActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start_story)
        val cronometro = findViewById<Chronometer>(R.id.simpleChronometer)
        cronometro.start()
        cronometro.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))

        val slider = findViewById<com.google.android.material.slider.Slider>(R.id.seekBar_story_time)
        slider.setLabelFormatter { value: Float ->
            "${value.toInt()}min/${60-value.toInt()}min"
        }


        //setContentView(R.layout.activity_main)

        //val transaction = supportFragmentManager.beginTransaction()

        //transaction.add(R.id.header_layout, HeaderFragment())

        //transaction.add(R.id.frame_layout, FrameFragment())

        //transaction.add(R.id.menu_layout, MenuFragment())

        //transaction.commit()
    }
}