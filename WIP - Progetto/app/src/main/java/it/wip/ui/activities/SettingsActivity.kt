package it.wip.ui.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
import kotlin.math.roundToInt

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySettingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[SettingsViewModel::class.java]

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val seekBarSettings = binding.seekBarSettings
        val maxStoryTime = binding.maxStoryTime
        val lefthandMode = binding.lefhandMode

        seekBarSettings.setLabelFormatter { value: Float ->
            "${value.toInt()} min " + getString(R.string.work) + "/${viewModel.breakTime} min " + getString(R.string.pause)
        }

        seekBarSettings.addOnChangeListener { _, value, _ ->
            viewModel.setStudyBreakTime(value)
        }

        var currentText = ""

        maxStoryTime.doOnTextChanged { text, _, _, _ ->

            if(text.toString() != currentText) {

                currentText =
                    if(text.toString().contains(" min")) {
                            text?.substring(0, text.length - 4).toString() + " min"
                    } else
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

        maxStoryTime.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(v.windowToken, 0)

                if(currentText != "") {
                    val currentValue = currentText.substring(0, currentText.length - 4).toFloat()

                    var maxStudyTime = currentValue.toInt()

                    maxStudyTime = (maxStudyTime / 10.0).roundToInt() * 10

                    if(maxStudyTime < 60)
                        maxStudyTime = 60
                    else if(maxStudyTime > 120)
                        maxStudyTime = 120

                    viewModel.setMaxStudyTime(maxStudyTime)

                    if(viewModel.studyTime.value!! > maxStudyTime)
                        viewModel.setStudyBreakTime(maxStudyTime.toFloat() - 10)

                    currentText = "$maxStudyTime min"

                } else {
                    maxStoryTime.setText(viewModel.maxStudyTime.value.toString())
                }

                true
            } else
                false
        }

        lefthandMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)

        lefthandMode.setOnCheckedChangeListener { _, checked ->
            viewModel.setLefthandMode(checked)
        }

        // add the menu fragment to the bottom of KingdomActivity
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.header_layout, HeaderFragment())
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()

    }
}