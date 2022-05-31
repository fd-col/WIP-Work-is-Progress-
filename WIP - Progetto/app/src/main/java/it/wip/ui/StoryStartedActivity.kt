package it.wip.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.wip.MainActivity
import it.wip.R
import it.wip.adapter.AdapterStoryStarted

class StoryStartedActivity : AppCompatActivity(){

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<AdapterStoryStarted.SSViewHolder>? = null

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_started)

        val storyPieces = mutableListOf(getString(R.string.venere_dali_step_1),
            getString(R.string.venere_dali_step_2), getString(R.string.venere_dali_break_1),
            getString(R.string.venere_dali_step_3), getString(R.string.venere_dali_step_4),
            getString(R.string.venere_dali_ending))

        /*
                val string: String = getString(R.string.venere_dali_step_1)
        * */

        /*
        val progresso = findViewById<ProgressBar>(R.id.progressBar)
        progresso.max = 100
        progresso.progress = 50
        */

        val cronometro = findViewById<Chronometer>(R.id.simpleChronometer)
        val stopButton = findViewById<ImageButton>(R.id.stop_button)

        cronometro.start()
        cronometro.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))

        stopButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> stopButton.setImageResource(R.drawable.stop_button_pressed)
                MotionEvent.ACTION_UP -> stopButton.setImageResource(R.drawable.stop_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        stopButton?.setOnClickListener {
            cronometro.stop()
            startActivity(Intent(this, MainActivity::class.java))
        }

        val recycler = findViewById<RecyclerView>(R.id.s_s_recycler)
        layoutManager = LinearLayoutManager(this)
       recycler.layoutManager = layoutManager

        adapter = AdapterStoryStarted(storyPieces)
        recycler.adapter = adapter
    }
}