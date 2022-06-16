package it.wip.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import it.wip.R
import kotlinx.android.synthetic.main.dialog_outcome.view.*

class DialogOutcome : DialogFragment() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actualCoins: Int = getString(R.string.actual_coins_string).toInt()
        val rootView: View = inflater.inflate(R.layout.dialog_outcome, container, false)

        if(actualCoins<130){
            rootView.outcome_title.text = getString(R.string.not_enought_coins_title)
            rootView.outcome_quote.text = getString(R.string.negative_quote)
            rootView.outcome_description.text = getString(R.string.not_enought_coins)
        }else if(actualCoins>=130){
            rootView.outcome_title.text = getString(R.string.purchase_completed)
            rootView.outcome_quote.text = getString(R.string.positive_quote)
            rootView.outcome_description.text = getString(R.string.purchase_completed_description)
        }

        rootView.okay_button.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> rootView.okay_button.setImageResource(R.drawable.okay_button_pressed)
                MotionEvent.ACTION_UP -> rootView.okay_button.setImageResource(R.drawable.okay_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        rootView.okay_button.setOnClickListener{
            dismiss()
        }

        return rootView
    }
}