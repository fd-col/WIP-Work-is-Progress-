package it.wip

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_order_detail.view.*

//CONSULTA: https://www.youtube.com/watch?v=SkFcDWt9GV4
// FARE TRANSAZIONI CON STRINGHE SOTTO AL COMMENTO "ANIMATION" NEL FILE STRINGS.XML!!!!!!!!!!!!

class DialogFragmentOrderDetail : DialogFragment(){
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.dialog_fragment_order_detail, container, false)
        rootView.dialog_no_button.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> rootView.dialog_no_button.setImageResource(R.drawable.no_button_pressed)
                MotionEvent.ACTION_UP -> rootView.dialog_no_button.setImageResource(R.drawable.no_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        rootView.dialog_yes_button.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> rootView.dialog_yes_button.setImageResource(R.drawable.yes_button_pressed)
                MotionEvent.ACTION_UP -> rootView.dialog_yes_button.setImageResource(R.drawable.yes_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        rootView.dialog_no_button.setOnClickListener{
            dismiss()
        }

        rootView.dialog_yes_button.setOnClickListener{
            val outcome = DialogOutcome()
            outcome.show(parentFragmentManager, "outcome")
            dismiss()
        }

        return rootView
    }
}
