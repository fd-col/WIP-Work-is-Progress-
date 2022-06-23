package it.wip.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class KingdomActivity : AppCompatActivity() {

    private lateinit var viewModel: KingdomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityKingdomBinding = DataBindingUtil.setContentView(this, R.layout.activity_kingdom)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[KingdomViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val numChapters = ""


        //date last story on top of the vertical reycler view
        val lastStoryDate = findViewById<TextView>(R.id.data_ultima_story)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ITALY)
        lastStoryDate.text = viewModel.sortedList[0].createdOn


        // data list of stories settled on the vertical recyclerView inside the KingdomActivity
        val storyList = ArrayList<DataKingdom>()

        for (i in 0..viewModel.sortedList.lastIndex) {
            if(i%2==0) {
                storyList.add(
                    DataKingdom(
                        KingdomListAdapter.THE_FIRST_VIEW,
                        viewModel.sortedList[i].storyName,
                        viewModel.sortedList[i].id,
                        "Chapters: $numChapters"

                    )
                )
            }
            else {
                storyList.add(
                    DataKingdom(
                        KingdomListAdapter.THE_SECOND_VIEW,
                        viewModel.sortedList[i].storyName,
                        viewModel.sortedList[i].id,
                        "Chapters: $numChapters"
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
            rvV.adapter!!.notifyDataSetChanged()
            val realPosition = position+1 //add 1 because the count start from 0
            Toast.makeText(this,"$realPosition. item clicked.",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, StoryDetailActivity()::class.java)

            intent.putExtra("storyID", storyList[position].itemID)
            intent.putExtra("storyName", storyList[position].title)
            startActivity(intent)
        }

        // method to change focus inside KingdomActivity to a single story clicked
        val itemHorizontalOnClick: (Int) -> Unit = { position ->
            rvV.adapter!!.notifyDataSetChanged()
            val realPosition = position+1 //add 1 because the count start from 0
            Toast.makeText(this,"$realPosition. item clicked.",Toast.LENGTH_SHORT).show()

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


        // add the menu fragment to the bottom of KingdomActivity
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()
    }

}