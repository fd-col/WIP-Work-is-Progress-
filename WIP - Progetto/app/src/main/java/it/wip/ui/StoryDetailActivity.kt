package it.wip.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.wip.R

class StoryDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)

        val rv = findViewById<RecyclerView>(R.id.recycler_vertical_details)

        // assign layout and adapter to the vertical (background of StoryDetailActivity) recycleView
        rv.layoutManager = LinearLayoutManager(this)
        //val rvVAdapter = KingdomListAdapter(this, storyList)
        //rv.adapter = rvVAdapter

        // add the menu fragment to the bottom of StoryDetailActivity
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()
    }
}