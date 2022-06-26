package it.wip.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
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
import it.wip.utils.fromShopElementNameToResource
import it.wip.viewModel.StartStoryViewModel

class StartStoryActivity : AppCompatActivity() {

    private lateinit var viewModel: StartStoryViewModel

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityStartStoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_start_story)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[StartStoryViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //binding.seekBarStoryTime.value = viewModel.studyTime.value!!

        val seekBarStoryTime = binding.seekBarStoryTime

        //new story title
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,viewModel.storyNamesList.toList())
        binding.storyTitleSetByTheUser.threshold = 1
        binding.storyTitleSetByTheUser.setAdapter(adapter)


        binding.storyTitleSetByTheUser.doOnTextChanged { text, _, _, _ ->
            viewModel.setStoryName(text.toString())
        }

        // new story work-break time
        seekBarStoryTime.addOnChangeListener { _, value, _ ->
            viewModel.setStudyBreakTime(value)
        }

        seekBarStoryTime.setLabelFormatter {

            "${viewModel.studyTime.value!!.toInt()} min " +
                    getString(R.string.work) + "/${viewModel.breakTime.value?.toInt()} min " + getString(R.string.pause)

        }


        //              SWITCH      -SILENT MODE    -HARDCORE MODE
        binding.switchSilentMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)
        binding.switchHardcoreMode.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)


        binding.switchSilentMode.setOnClickListener{
            viewModel.silenceNormal()
        }

        binding.switchHardcoreMode.setOnClickListener{
            viewModel.hardcoreMode(binding.switchSilentMode)
        }
        //INFO ABOUT MODEs
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


        //              SWITCH AVATAR
        val avatar = binding.avatar
        val avatarSource = avatar.sourceLayoutResId
        avatar.setBackgroundResource(fromShopElementNameToResource(viewModel.avatarShoppedElements[0]))
        var avatarTag = avatar.tag.toString().toInt()
        var selectedAvatar = "girl_with_pearl_earring"


        //AVATARS CHOOSE BEFORE STARTING A STORY
        binding.avatarSxButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.avatarSxButton.setImageResource(R.drawable.avatar_sx_arrow_pressed)
                MotionEvent.ACTION_UP -> binding.avatarSxButton.setImageResource(R.drawable.avatar_sx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }
        binding.avatarSxButton.setOnClickListener {
            avatarTag--
            if(avatarTag < 0) {
                avatarTag = viewModel.avatarShoppedElements.size - 1
            }
            val tempAvatar = viewModel.avatarShoppedElements[avatarTag]

            avatar.setBackgroundResource(fromShopElementNameToResource(tempAvatar))
            selectedAvatar = tempAvatar
        }

        binding.avatarDxButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> binding.avatarDxButton.setImageResource(R.drawable.avatar_dx_arrow_pressed)
                MotionEvent.ACTION_UP -> binding.avatarDxButton.setImageResource(R.drawable.avatar_dx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }
        binding.avatarDxButton.setOnClickListener {
            avatarTag++
            if(avatarTag + 1 > viewModel.avatarShoppedElements.size) {
                avatarTag = 0
            }
            val tempAvatar = viewModel.avatarShoppedElements[avatarTag]

            avatar.setBackgroundResource(fromShopElementNameToResource(tempAvatar))
            selectedAvatar = tempAvatar
        }


        // button backwards
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


        //button to start the new Story
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
                Toast.makeText(this,"Please insert a name for your new story", Toast.LENGTH_SHORT).show()
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

    }
}