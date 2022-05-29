package it.wip.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.wip.R
import it.wip.data.DataHeaderKingdom
import it.wip.data.DataKingdom

class KingdomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kingdom)
        // data list of stories in the background inside the KingdomActivity
        val storyList = ArrayList<DataKingdom>()
        storyList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "Workout", R.drawable.venere_pixellata))
        storyList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "Study", R.drawable.gear_button))
        storyList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "3. Geeks View 3", R.drawable.venere_pixellata))
        storyList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "4. Geeks View 4", R.drawable.gear_button))
        storyList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "5. Geeks View 5", R.drawable.venere_pixellata))
        storyList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "6. Geeks View 6", R.drawable.gear_button))
        storyList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "7. Geeks View 7", R.drawable.venere_pixellata))
        storyList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "8. Geeks View 8", R.drawable.play_button))
        storyList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "9. Geeks View 9", R.drawable.stop_button))
        storyList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "10. Geeks View 10", R.drawable.stop_button))
        storyList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "11. Geeks View 11", R.drawable.play_button))
        storyList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "12. Geeks View 12", R.drawable.stop_button))
        // data list of stories in the header  inside the KingdomActivity
        val storyHeaderList = ArrayList<DataHeaderKingdom>()
        storyHeaderList.add(DataHeaderKingdom("Workout"))
        storyHeaderList.add(DataHeaderKingdom("StudyMoreAMore"))
        storyHeaderList.add(DataHeaderKingdom("Stucazz"))
        storyHeaderList.add(DataHeaderKingdom("P"))
        storyHeaderList.add(DataHeaderKingdom("Pane"))
        storyHeaderList.add(DataHeaderKingdom("Frutta"))
        storyHeaderList.add(DataHeaderKingdom("Mirtillo"))


        var rvV = findViewById<RecyclerView>(R.id.rv_vertical)
        var rvH = findViewById<RecyclerView>(R.id.rv_horizontal)

        // assign layout and adapter to the vertical (background of KingdomActivity) recycleView
        rvV.layoutManager = LinearLayoutManager(this)
        val rvVAdapter = KingdomListAdapter(this, storyList)
        rvV.adapter = rvVAdapter

        // assign layout and adapter to the horizontal (header of KingdomActivity) recycleView
        rvH.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val rvHAdapter = KingdomHeaderAdapter(this, storyHeaderList)
        rvH.adapter = rvHAdapter

    }
}