package it.wip.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.wip.R

class KingdomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kingdom)

        var list = arrayOf(R.drawable.frame, R.drawable.crown_button, R.drawable.frame)

        val rv = findViewById<RecyclerView>(R.id.rv_horizontal)

        val recyclerAdapter = KingdomListAdapter(this, list)

        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = recyclerAdapter
    }
}