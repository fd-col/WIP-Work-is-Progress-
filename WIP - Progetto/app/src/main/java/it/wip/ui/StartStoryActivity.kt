package it.wip.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import it.wip.MainActivity
import it.wip.R

class StartStoryActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_story)

        val backButton = findViewById<ImageButton>(R.id.back_button)

        val infoButton = findViewById<ImageButton>(R.id.info_button)

        val avatarSxButton = findViewById<ImageButton>(R.id.avatar_sx_button)

        val avatarDxButton = findViewById<ImageButton>(R.id.avatar_dx_button)

        val startButton = findViewById<ImageButton>(R.id.start_button)

        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

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

    }
}