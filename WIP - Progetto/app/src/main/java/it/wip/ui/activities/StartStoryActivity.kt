package it.wip.ui.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.wip.MainActivity
import it.wip.R
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import it.wip.ui.dialogs.DialogActivityStoryStarted
import it.wip.databinding.ActivityStartStoryBinding
import it.wip.utils.fromShopElementNameToLocalizedName
import it.wip.utils.fromShopElementNameToResource
import it.wip.viewModel.StartStoryViewModel

class StartStoryActivity : AppCompatActivity() {

    private lateinit var viewModel: StartStoryViewModel

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {

        //                              LEFTHAND MODE DETECTION
        val lefthandPreference = applicationContext.getSharedPreferences("lefthandPreference", Context.MODE_PRIVATE)
        val lefthand = lefthandPreference.getInt("lefthand", Context.MODE_PRIVATE)
        if(lefthand==1)  setTheme(R.style.RightToLefTheme) else setTheme(R.style.LeftToRighTheme)

        super.onCreate(savedInstanceState)

        val binding: ActivityStartStoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_start_story)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[StartStoryViewModel::class.java]

        //change drawables' orietation for Lefthand Mode
        if(lefthand==1) {
            binding.avatarDxButton.rotationY = 180F
            binding.avatarSxButton.rotationY = 180F
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        //                              BINDING DATASOURCES
        val storyTitleSetByTheUser = binding.storyTitleSetByTheUser
        val seekBarStoryTime = binding.seekBarStoryTime




        // new story name options with auto complete banner
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,viewModel.storyNamesList.toList())
        storyTitleSetByTheUser.threshold = 1
        storyTitleSetByTheUser.setAdapter(adapter)

        // set the new story name
        storyTitleSetByTheUser.doOnTextChanged { text, _, _, _ ->
            viewModel.setStoryName(text.toString())
        }

        storyTitleSetByTheUser.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(binding.root.windowToken, 0)

                true
            } else
                false
        }

        storyTitleSetByTheUser.setOnItemClickListener { _, _, _, _ ->
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }

        // set new story work-break time
        seekBarStoryTime.addOnChangeListener { _, value, _ ->
            viewModel.setStudyBreakTime(value)
        }

        seekBarStoryTime.setLabelFormatter {

            "${viewModel.studyTime.value!!.toInt()} min " +
                    getString(R.string.work) + "/${viewModel.breakTime.value?.toInt()} min " + getString(R.string.pause)

        }




        //              SWITCH      -SILENT MODE    -HARDCORE MODE
        //--------------------------------------- FONT SETTING ------------------------------------
        binding.switchSilentMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)
        binding.switchHardcoreMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)


        //-------------------------------------- LISTENERS ----------------------------------------
        binding.switchSilentMode.setOnClickListener{
            viewModel.silenceNormal()
        }

        binding.switchHardcoreMode.setOnClickListener{
            viewModel.hardcoreMode(binding.switchSilentMode)
        }
        // info button with mode's explained
        binding.infoButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.infoButton.setImageResource(R.drawable.info_button_pressed)
                MotionEvent.ACTION_UP -> binding.infoButton.setImageResource(R.drawable.info_button)
            }
            v?.onTouchEvent(event) ?: true
        }
        binding.infoButton.setOnClickListener {
            val dialogInfo = DialogActivityStoryStarted()
            dialogInfo.show(supportFragmentManager, "info")
        }




        //                                      SWITCH AVATAR
        val avatar = binding.avatar
        avatar.setBackgroundResource(fromShopElementNameToResource(viewModel.avatarShoppedElements[0]))
        avatar.contentDescription = getString(fromShopElementNameToLocalizedName(viewModel.avatarShoppedElements[0]))
        var avatarTag = avatar.tag.toString().toInt()
        var selectedAvatar = viewModel.avatarShoppedElements[0]


        //------------------------------------- LISTENERS -----------------------------------------
        binding.avatarSxButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.avatarSxButton.setImageResource(R.drawable.avatar_sx_arrow_pressed)
                MotionEvent.ACTION_UP -> binding.avatarSxButton.setImageResource(R.drawable.avatar_sx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        binding.avatarDxButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.avatarDxButton.setImageResource(R.drawable.avatar_dx_arrow_pressed)
                MotionEvent.ACTION_UP -> binding.avatarDxButton.setImageResource(R.drawable.avatar_dx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        //                      AVATARS' CHOOSE ON CLICK BUTTONS RIGHT AND LEFT
        binding.avatarSxButton.setOnClickListener {
            avatarTag--
            if(avatarTag < 0) {
                avatarTag = viewModel.avatarShoppedElements.size - 1
            }
            val tempAvatar = viewModel.avatarShoppedElements[avatarTag]

            avatar.setBackgroundResource(fromShopElementNameToResource(tempAvatar))
            avatar.contentDescription = getString(fromShopElementNameToLocalizedName(tempAvatar))
            selectedAvatar = tempAvatar
        }

        binding.avatarDxButton.setOnClickListener {
            avatarTag++
            if(avatarTag + 1 > viewModel.avatarShoppedElements.size) {
                avatarTag = 0
            }
            val tempAvatar = viewModel.avatarShoppedElements[avatarTag]

            avatar.setBackgroundResource(fromShopElementNameToResource(tempAvatar))
            avatar.contentDescription = getString(fromShopElementNameToLocalizedName(tempAvatar))
            selectedAvatar = tempAvatar
        }




        //                              BUTTON TO START A NEW STORY
        binding.startButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.startButton.setImageResource(R.drawable.start_story_button_pressed)
                MotionEvent.ACTION_UP -> binding.startButton.setImageResource(R.drawable.start_story_button)
            }
            v?.onTouchEvent(event) ?: true
        }
        binding.startButton.setOnClickListener {
            //check if the new story title is empty or not
            if(binding.storyTitleSetByTheUser.text.isNullOrBlank())
                Toast.makeText(this,getString(R.string.story_title_empty_message), Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent(this, StoryStartedActivity::class.java)
                intent.putExtra("newStoryName", viewModel.storyName.value)
                intent.putExtra("studyTime", viewModel.studyTime.value)
                intent.putExtra("breakTime", viewModel.breakTime.value)
                intent.putExtra("selectedAvatar", selectedAvatar)
                intent.putExtra("avatarID", avatar.tag.toString().toInt())
                intent.putExtra("mode", viewModel.selectedMode())
                startActivity(intent)
            }
        }




        //                              BACKAWARDS TO MainActivity
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.backButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.backButton.setImageResource(R.drawable.back_arrow_pressed)
                MotionEvent.ACTION_UP -> binding.backButton.setImageResource(R.drawable.back_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

    }
}