package it.wip

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
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

//CONSULTA: https://www.youtube.com/watch?v=SkFcDWt9GV4

/*
class DialogActivityStoryStarted : DialogFragment() {
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.dialog_activity_story_started, null))
                .setPositiveButton(R.string.parameter,{ dialog, id ->

            })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
* */

/*
class StartGameDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return activity?.let {
        val builder = AlertDialog.Builder(it)
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater;

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                // Add action buttons
                .setPositiveButton(R.string.signin,
                        DialogInterface.OnClickListener { dialog, id ->
                            // sign in the user ...
                        })
                .setNegativeButton(R.string.cancel,
                        DialogInterface.OnClickListener { dialog, id ->
                            getDialog().cancel()
                        })
        builder.create()
    } ?: throw IllegalStateException("Activity cannot be null")
}
}
* */