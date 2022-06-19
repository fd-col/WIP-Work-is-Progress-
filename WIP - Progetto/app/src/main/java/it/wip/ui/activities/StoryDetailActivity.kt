package it.wip.ui.activities

import android.content.Intent
import android.os.Bundle
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
import org.w3c.dom.Text

class StoryDetailActivity: AppCompatActivity() {

    lateinit var viewModel: StoryDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityStoryDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_story_detail)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[StoryDetailViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        val rv = findViewById<RecyclerView>(R.id.recycler_vertical_details)
        val backButton = findViewById<ImageButton>(R.id.back_button_story_detail)
        val storyDetailTitle = findViewById<TextView>(R.id.story_detail_title)

        val storyTitle = intent.getStringExtra("storyName")
        storyDetailTitle.text = storyTitle

        var numTotChapters: MutableList<Int> = mutableListOf()
        var numChapters = 0


        val storyDetailList = ArrayList<DataKingdom>()

        for (i in 0..viewModel.chaptersName.lastIndex) {
            if (i % 2 == 0) {
                //get intent extra "storyPosition" from the KingdomActivity
                val storyPosition = this.intent.getIntExtra("storyPosition", 3)
                //check the storyIDs inside the List of chapters are equals to the storyPositions, used as storyID
                if(viewModel.chapterStoryId[i] == storyPosition) {

                    storyDetailList.add(
                        DataKingdom(
                            KingdomListAdapter.THE_THIRD_VIEW,
                            viewModel.chaptersName[i],
                            "",
                            viewModel.chapterDates[i],
                            viewModel.chapterTimes[i]
                        )
                    )
                    numChapters++
                }
            } else {
                //get the storyPosition from the KingdomActivity
                var storyPosition = this.intent.getIntExtra("storyPosition", 3)
                //check the storyIDs inside the List of chapters are equals to the storyPosition used as storyID
                if(viewModel.chapterStoryId[i] == storyPosition) {

                    storyDetailList.add(
                        DataKingdom(
                            KingdomListAdapter.THE_FORTH_VIEW,
                            viewModel.chaptersName[i],
                            "",
                            viewModel.chapterDates[i],
                            viewModel.chapterTimes[i]
                        )
                    )
                    numChapters++
                }
            }
            numTotChapters.add(numChapters)
        }

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