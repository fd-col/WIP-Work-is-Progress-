package it.wip.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.wip.MainActivity
import it.wip.R

class StoryDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)

        val rv = findViewById<RecyclerView>(R.id.recycler_vertical_details)
        val backButton = findViewById<ImageButton>(R.id.back_button)

        // assign layout and adapter to the vertical (background of StoryDetailActivity) recycleView
        rv.layoutManager = LinearLayoutManager(this)
        //val rvVAdapter = KingdomListAdapter(this, storyList)
        //rv.adapter = rvVAdapter

        // add the menu fragment to the bottom of StoryDetailActivity
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()
/*
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
*/

    }
}