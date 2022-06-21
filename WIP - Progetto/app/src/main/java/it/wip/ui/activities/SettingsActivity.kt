package it.wip.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import it.wip.R
import it.wip.databinding.ActivitySettingsBinding
import it.wip.ui.fragments.HeaderFragment
import it.wip.ui.fragments.MenuFragment
import it.wip.viewModel.SettingsViewModel

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySettingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[SettingsViewModel::class.java]

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val maxStoryTime = binding.maxStoryTime

        val slider = findViewById<com.google.android.material.slider.Slider>(R.id.seekBar_settings)
        slider.setLabelFormatter { value: Float ->
            "${value.toInt()} min " + getString(R.string.work) + "/${60-value.toInt()} min " + getString(R.string.pause)
        }

        var currentText = ""

        maxStoryTime.doOnTextChanged { text, _, _, _ ->

            if(text.toString() != currentText) {

                currentText =
                    if(text.toString().contains(" min"))
                        text.toString().substring(0, text!!.length - 4) + " min"
                    else
                        text.toString() + " min"

                maxStoryTime.setText(currentText)
                maxStoryTime.setSelection(currentText.substring(0, currentText.length - 4).length)

                if(currentText == " min") {
                    currentText = ""
                    maxStoryTime.setText(currentText)
                }

            }
        }

        maxStoryTime.accessibilityDelegate = object: View.AccessibilityDelegate() {

            override fun sendAccessibilityEvent(host: View?, eventType: Int) {
                super.sendAccessibilityEvent(host, eventType)
                if (eventType == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED){
                    if(currentText.length > 4)
                        maxStoryTime.setSelection(currentText.substring(0, currentText.length - 4).length)
                }
            }

        }

        binding.lefHandMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)

        // add the menu fragment to the bottom of KingdomActivity
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.header_layout, HeaderFragment())
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()

    }
}