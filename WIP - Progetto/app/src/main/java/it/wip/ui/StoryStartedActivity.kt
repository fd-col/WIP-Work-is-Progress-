package it.wip.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import it.wip.MainActivity
import it.wip.R

class StoryStartedActivity : AppCompatActivity(){

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_started)

        /*
        val progresso = findViewById<ProgressBar>(R.id.progressBar)
        progresso.max = 100
        progresso.progress = 50
        */
        val stopButton = findViewById<ImageButton>(R.id.stop_button)
        val artwork = findViewById<ImageView>(R.id.canva)
        val cronometro = findViewById<Chronometer>(R.id.simpleChronometer)
        cronometro.setOnChronometerTickListener {
            var timeElapsed = (SystemClock.elapsedRealtime() - cronometro.base).toInt()
            if(timeElapsed>3000 && timeElapsed<4000){
                Toast.makeText(this, "ciao", Toast.LENGTH_SHORT).show()
                artwork.setBackgroundResource(R.drawable.hopper_1)
            }

            //Toast.makeText(this, "ciao", Toast.LENGTH_SHORT).show()
        }


        //backgroundSelector(artwork, cronometro)



        cronometro.start()
        cronometro.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)

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
    }
}

//          scelta dinamica del quadro
fun backgroundSelector(artwork: ImageView, tempo: Chronometer){
    when ((1..3).random()) {
        1 -> {
            artwork.setBackgroundResource(R.drawable.dali_1)
            tempo.onChronometerTickListener = Chronometer.OnChronometerTickListener {
                backgroundEvolution(tempo, 1, artwork)
            }
        }
        2 -> {
            artwork.setBackgroundResource(R.drawable.hopper_1)
            backgroundEvolution(tempo, 2, artwork)
        }
        3 -> {
            artwork.setBackgroundResource(R.drawable.munch_bg_1)
            backgroundEvolution(tempo, 3, artwork)
        }
    }
}

fun backgroundEvolution(tempo: Chronometer, artworkId: Int, artwork: ImageView){
    val elapsed = (SystemClock.elapsedRealtime() - tempo.base).toInt()
    if(elapsed>5) {
        when (artworkId) {
            1 -> {
                artwork.setBackgroundResource(R.drawable.dali_2)
            }
            2 -> {
                artwork.setBackgroundResource(R.drawable.hopper_2)
            }
            3 -> {
                artwork.setBackgroundResource(R.drawable.munch_bg_2)
            }
        }
    }
}

/*
fun shotClockStart(v: View?) {
    val shotclock = findViewById(R.id.chrono1) as Chronometer
    shotclock.onChronometerTickListener =
        OnChronometerTickListener { chronometer ->
            val timeElapsed = SystemClock.elapsedRealtime() - chronometer.base
            if (timeElapsed >= 30000) {
                //  HERE I WANT A VIBRATION ON THE DEVICE.
            } else if (timeElapsed >= 60000) {
                //HERE I WANT A NOTIFICATION ALERT
            }
        }
    shotclock.base = SystemClock.elapsedRealtime()
    shotclock.start()
}
* */