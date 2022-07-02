package it.wip.ui.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
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
import it.wip.ui.dialogs.DialogSettings
import it.wip.ui.fragments.HeaderFragment
import it.wip.ui.fragments.MenuFragment
import it.wip.viewModel.SettingsViewModel
import kotlin.math.roundToInt

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[SettingsViewModel::class.java]

        //                                  LEFTHAND MODE DETECTION
        val lefthandPreference = applicationContext.getSharedPreferences("lefthandPreference", Context.MODE_PRIVATE)
        val lefthand = lefthandPreference.getInt("lefthand", Context.MODE_PRIVATE)
        if(lefthand==1) {
            setTheme(R.style.RightToLefTheme)
            viewModel.setLefthandMode(true)
        } else {
            setTheme(R.style.LeftToRighTheme)
            viewModel.setLefthandMode(false)
        }

        super.onCreate(savedInstanceState)

        val binding: ActivitySettingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel




        //              BINDING DATASOURCES
        val seekBarSettings = binding.seekBarSettings
        val maxStoryTime = binding.maxStoryTime
        val lefthandMode = binding.lefhandMode
        val settingsInfoButton = binding.settingsInfoButton




        //              GRAPHICAL LISTENERS RELATED TO BINDING RESOURCES

        // ------------------------ SEEKBAR LISTENERS ------------------------
        // listener che aggiorna il testo sulla seekbar quando scorriamo il thumb lungo il track
        seekBarSettings.setLabelFormatter { value: Float ->
            "${value.toInt()} min " + getString(R.string.work) + "/${viewModel.breakTime} min " + getString(R.string.pause)
        }

        /*
        * listener che setta la partizione del tempo studio/pausa di default (si ha aggiornamento dei dati nel db);
        * in altre parole, questo listener imposta la partizione studio/pausa che esce di default in startStoryActivity
        */
        seekBarSettings.addOnChangeListener { _, value, _ ->
            viewModel.setStudyBreakTime(value)
        }




        // ------------------------ EDIT-TEXT LISTENERS ------------------------
        var currentText = ""

        // listener che aggiorna dinamicamente il testo nell'edit text a seconda di ciò che scriviamo
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

        /*
        * listener che impedisce lo spostamento del cursore nell'edit-text al fine di non permettere
        * all'utente l'inserimento di valori oltre la stringa "min"
        * */
        maxStoryTime.accessibilityDelegate = object: View.AccessibilityDelegate() {

            override fun sendAccessibilityEvent(host: View?, eventType: Int) {
                super.sendAccessibilityEvent(host, eventType)
                if (eventType == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED){
                    if(currentText.length > 4)
                        maxStoryTime.setSelection(currentText.substring(0, currentText.length - 4).length)
                }
            }

        }

        /*
        * listener che gestisce:
        * - tempo minimo impostabile come max-time
        * - tempo massimo impostabile come max-time
        * - arrotondamento a multipli di 10 del valore immesso da tastiera
        * con relativi aggiornamenti nel viewModel e nel DB
        * */
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

                    if(viewModel.studyTime.value!! >= maxStudyTime)
                        viewModel.setStudyBreakTime(maxStudyTime.toFloat() - 10)

                    currentText = "$maxStudyTime min"

                } else {
                    maxStoryTime.setText(viewModel.maxStudyTime.value.toString())
                }

                true
            } else
                false
        }




        // ------------------------ LEFTHAND MODE MANAGMENT ------------------------
        lefthandMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)
        // starting selection for lefthand mode in Setting activity
        lefthandMode.isChecked = viewModel.lefthandMode.value.toString().toBoolean()

        lefthandMode.setOnCheckedChangeListener { _, checked ->
            // get and edit SharedPreferences with "lefthandPreference"
            val lefthandPreference = applicationContext.getSharedPreferences("lefthandPreference", Context.MODE_PRIVATE)
            val editor = lefthandPreference.edit()
            // on checking the relative swticher, lefthand mode will be activated
            if(checked) {
                editor.putInt("lefthand", 1)
                editor.apply()
            }
            else {
                editor.putInt("lefthand", 0)
                editor.apply()
            }
            //restart this Activity with lefthand mode activated/disactivated
            startActivity(Intent(this, SettingsActivity::class.java))
        }



        // ------------------------ INFO BUTTON LISTENERS------------------------
        settingsInfoButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> settingsInfoButton.setImageResource(R.drawable.shop_info_button_pressed)
                MotionEvent.ACTION_UP -> settingsInfoButton.setImageResource(R.drawable.shop_info_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        settingsInfoButton.setOnClickListener{
            val dialogSettings = DialogSettings()
            dialogSettings.show(supportFragmentManager, "dialogSettings")
        }




        //              MENU-BAR MANAGER
        // add header and menu fragment on SettingsActivity's bottom to ensure graphical consistency
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.header_layout, HeaderFragment())
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()

    }
}