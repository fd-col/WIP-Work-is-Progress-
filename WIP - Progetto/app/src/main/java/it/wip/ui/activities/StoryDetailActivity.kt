package it.wip.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.wip.R
import it.wip.data.DataKingdom
import it.wip.databinding.ActivityStoryDetailBinding
import it.wip.ui.fragments.MenuFragment
import it.wip.utils.KingdomListAdapter
import it.wip.viewModel.StoryDetailViewModel

class StoryDetailActivity: AppCompatActivity() {

    lateinit var viewModel: StoryDetailViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityStoryDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_story_detail)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[StoryDetailViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val storyDetailTitle = findViewById<TextView>(R.id.story_detail_title)
        val rv = findViewById<RecyclerView>(R.id.recycler_vertical_details)
        val backButton = findViewById<ImageButton>(R.id.back_button_story_detail)


        //get the story name related with the story clicked
        val storyTitle = intent.getStringExtra("storyName")
        //set the story detail name related with the story clicked
        storyDetailTitle.text = storyTitle


        //get the story ID related with the story clicked in the Kingdom
        val storyID = intent.getIntExtra("storyID", 0)


        //create the list of Chapters
        val storyDetailList = ArrayList<DataKingdom>()


        val chapters = viewModel.getChapters(storyID)

        //iterate over all chapters inside our DB
        for (i in 0..chapters.lastIndex) {
            //check if id's story, given by the previous intent "storyID", is the same as chapters
            if (storyID == chapters[i].story) {
                //put Cards with data to the left
                if (i % 2 == 0) {

                    storyDetailList.add(
                        DataKingdom(
                            KingdomListAdapter.THE_THIRD_VIEW,
                            chapters[i].chapterName,
                            chapters[i].id,
                            chapters.size.toString(),
                            chapters[i].createdOn,
                            chapters[i].time
                        )
                    )
                    //numChapters++

                //put Cards with data to the right
                } else {

                    storyDetailList.add(
                        DataKingdom(
                            KingdomListAdapter.THE_FORTH_VIEW,
                            chapters[i].chapterName,
                            chapters[i].id,
                            chapters.size.toString(),
                            chapters[i].createdOn,
                            chapters[i].time
                        )
                    )
                    //numChapters++
                }

            }
            //numTotChapters.add(numChapters)
        }



        // method for activate the new activity StoryDetailActivity when clicked the single story
        val itemOnClick: (Int) -> Unit = { position ->
            rv.adapter!!.notifyDataSetChanged()
            val realPosition = position+1 //add 1 because the count start from 0
            Toast.makeText(this,"$realPosition. item clicked.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ChapterInfoActivity()::class.java)

            intent.putExtra("chapterID", storyDetailList[position].itemID)
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


        //button to go back in the previous view
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