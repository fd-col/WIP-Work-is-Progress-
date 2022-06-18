package it.wip.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import it.wip.R
import it.wip.databinding.DialogActivityStoryStartedBinding

class DialogActivityStoryStarted : DialogFragment() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: DialogActivityStoryStartedBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_activity_story_started, container, false)

        val okayButton = binding.dialogOkayButton

        okayButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> okayButton.setImageResource(R.drawable.okay_button_pressed)
                MotionEvent.ACTION_UP -> okayButton.setImageResource(R.drawable.okay_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        okayButton.setOnClickListener{
            dismiss()
        }

        return binding.root
    }
}