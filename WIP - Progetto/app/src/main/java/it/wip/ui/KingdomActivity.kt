package it.wip.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.wip.R
import it.wip.data.DataKingdom

class KingdomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kingdom)

        val dataList = ArrayList<DataKingdom>()
        dataList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "1. Geeks View 1", R.drawable.play_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "2. Geeks View 2", R.drawable.stop_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "3. Geeks View 3", R.drawable.play_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "4. Geeks View 4", R.drawable.stop_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "5. Geeks View 5", R.drawable.stop_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "6. Geeks View 6", R.drawable.play_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "7. Geeks View 7", R.drawable.stop_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "8. Geeks View 8", R.drawable.play_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "9. Geeks View 9", R.drawable.stop_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "10. Geeks View 10", R.drawable.stop_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_FIRST_VIEW, "11. Geeks View 11", R.drawable.play_button))
        dataList.add(DataKingdom(KingdomListAdapter.THE_SECOND_VIEW, "12. Geeks View 12", R.drawable.stop_button))

        var rv = findViewById<RecyclerView>(R.id.rv_vertical)

        val recyclerAdapter = KingdomListAdapter(this, dataList)

        rv.layoutManager = LinearLayoutManager(this)
        print("FIRST")
        rv.adapter = recyclerAdapter
        print("SSSS")

    }
}