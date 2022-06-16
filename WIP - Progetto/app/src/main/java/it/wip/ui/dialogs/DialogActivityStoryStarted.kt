package it.wip.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import it.wip.R
import kotlinx.android.synthetic.main.dialog_activity_story_started.view.*

class DialogActivityStoryStarted : DialogFragment() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.dialog_activity_story_started, container, false)
        rootView.dialog_okay_button.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> rootView.dialog_okay_button.setImageResource(R.drawable.okay_button_pressed)
                MotionEvent.ACTION_UP -> rootView.dialog_okay_button.setImageResource(R.drawable.okay_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        rootView.dialog_okay_button.setOnClickListener{
            dismiss()
        }

        return rootView
    }
}