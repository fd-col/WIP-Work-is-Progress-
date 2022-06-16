package it.wip.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.wip.R
import it.wip.data.DataKingdom
import it.wip.ui.fragments.MenuFragment

class StoryDetailActivity(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)

        val rv = findViewById<RecyclerView>(R.id.recycler_vertical_details)
        val backButton = findViewById<ImageButton>(R.id.back_button_story_detail)


        val storyDetailList = ArrayList<DataKingdom>()
        storyDetailList.add(DataKingdom(KingdomListAdapter.THE_THIRD_VIEW, "Capitolo1", date = "11/11/2022", time = "1.01.23"))
        storyDetailList.add(DataKingdom(KingdomListAdapter.THE_FORTH_VIEW, "Capitolo2", date = "11/11/2022", time = "1.01.23"))
        storyDetailList.add(DataKingdom(KingdomListAdapter.THE_THIRD_VIEW, "Capitolo3", date = "11/11/2022", time = "1.01.23"))
        storyDetailList.add(DataKingdom(KingdomListAdapter.THE_FORTH_VIEW, "Capitolo4", date = "11/11/2022", time = "1.01.23"))
        storyDetailList.add(DataKingdom(KingdomListAdapter.THE_THIRD_VIEW, "Capitolo5", date = "11/11/2022", time = "1.01.23"))
        storyDetailList.add(DataKingdom(KingdomListAdapter.THE_FORTH_VIEW, "Capitolo6", date = "11/11/2022", time = "1.01.23"))


        // method for activate the new activity StoryDetailActivity when clicked the single story
        val itemOnClick: (Int) -> Unit = { position ->
            rv.adapter!!.notifyDataSetChanged()
            Toast.makeText(this,"$position. item clicked.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ChapterInfoActivity::class.java)
            startActivity(intent)
        }

        // assign layout and adapter to the vertical (background of StoryDetailActivity) recycleView
        rv.layoutManager = LinearLayoutManager(this)
        val rvAdapter = KingdomListAdapter(this, storyDetailList, itemClickListener = itemOnClick)
        rv.adapter = rvAdapter


        // add the menu fragment to the bottom of StoryDetailActivity
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()

        backButton.setOnClickListener {
            startActivity(Intent(this, KingdomActivity::class.java))
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