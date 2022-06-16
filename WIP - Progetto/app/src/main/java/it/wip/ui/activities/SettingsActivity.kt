package it.wip.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import it.wip.R
import it.wip.databinding.ActivitySettingsBinding
import it.wip.ui.fragments.HeaderFragment
import it.wip.ui.fragments.MenuFragment
import it.wip.viewModel.SettingsViewModel

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySettingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[SettingsViewModel::class.java]

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val slider = findViewById<com.google.android.material.slider.Slider>(R.id.seekBar_settings)
        slider.setLabelFormatter { value: Float ->
            "${value.toInt()} min study/${60-value.toInt()} min pause"
        }

        binding.lefHandMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)

        // add the menu fragment to the bottom of KingdomActivity
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.header_layout, HeaderFragment())
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()

    }
}