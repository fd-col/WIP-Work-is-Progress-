package it.wip.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.wip.R
import it.wip.data.DataKingdom
import it.wip.databinding.ActivityKingdomBinding
import it.wip.ui.fragments.MenuFragment
import it.wip.utils.KingdomListAdapter
import it.wip.viewModel.KingdomViewModel
import kotlin.collections.ArrayList

class KingdomActivity : AppCompatActivity() {

    private lateinit var viewModel: KingdomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        //LeftHand mode activation
        val lefthandPreference = applicationContext.getSharedPreferences("lefthandPreference", Context.MODE_PRIVATE)
        val lefthand = lefthandPreference.getInt("lefthand", Context.MODE_PRIVATE)
        if(lefthand==1)  setTheme(R.style.RightToLefTheme) else setTheme(R.style.LeftToRighTheme)

        super.onCreate(savedInstanceState)

        val binding: ActivityKingdomBinding = DataBindingUtil.setContentView(this, R.layout.activity_kingdom)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[KingdomViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        // List of Stories inside the vertical recyclerView (inside the KingdomActivity)
        val storyList = ArrayList<DataKingdom>()

        for (i in 0..viewModel.sortedList.lastIndex) {
            //return an array of Chapters filtered by the Story's ID
            val chapters = viewModel.getChapters(viewModel.sortedList[i].id)

            if(i%2==0) { //even indexes on the left
                storyList.add(
                    DataKingdom(
                        KingdomListAdapter.THE_FIRST_VIEW,
                        viewModel.sortedList[i].storyName,
                        viewModel.sortedList[i].id,
                        "Chapters:\n${chapters.size}"

                    )
                )
            }
            else { //odd indexes on the left
                storyList.add(
                    DataKingdom(
                        KingdomListAdapter.THE_SECOND_VIEW,
                        viewModel.sortedList[i].storyName,
                        viewModel.sortedList[i].id,
                        "Chapters:\n${chapters.size}"
                    )
                )
            }
        }

        // data list of stories settled on the horizontal recyclerView inside the KingdomActivity
        val storyHorizontalList = ArrayList<DataKingdom>()
        for((i, item) in storyList.withIndex()) {
            storyHorizontalList.add(DataKingdom(
                KingdomListAdapter.THE_HORIZONTAL_VIEW, storyList[i].textData, storyList[i].itemID))
        }


        val rvV = findViewById<RecyclerView>(R.id.rv_vertical)
        val rvH = findViewById<RecyclerView>(R.id.rv_horizontal)



        // method to activate the new activity StoryDetailActivity when clicked the single story
        val itemOnClick: (Int) -> Unit = { position ->
            val intent = Intent(this, StoryDetailActivity()::class.java)

            intent.putExtra("storyID", storyList[position].itemID)
            intent.putExtra("storyName", storyList[position].title)
            startActivity(intent)
        }

        // method to change focus inside KingdomActivity to a single story clicked
        val itemHorizontalOnClick: (Int) -> Unit = { position ->

            rvH.scrollToPosition(position)
            rvV.scrollToPosition(position)
        }


        // assign layout and adapter to the vertical (background of KingdomActivity) recycleView
        rvV.layoutManager = LinearLayoutManager(this)
        val rvVAdapter = KingdomListAdapter(this, storyList, itemClickListener = itemOnClick)
        rvV.adapter = rvVAdapter

        // assign layout and adapter to the horizontal (header of KingdomActivity) recycleView
        rvH.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val rvHAdapter = KingdomListAdapter(this, storyHorizontalList, itemClickListener = itemHorizontalOnClick)
        rvH.adapter = rvHAdapter

/*
        //BUTTON TO DELETE A STORY (BE CAREFUL)
        binding.searchButton.setOnClickListener {
            GlobalScope.launch { viewModel.deleteLastStory() }
        }
*/

        // add the menu fragment to the bottom of KingdomActivity
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()

    }

}