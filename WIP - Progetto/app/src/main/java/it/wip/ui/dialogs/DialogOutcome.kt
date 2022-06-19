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
import it.wip.databinding.DialogOutcomeBinding
import it.wip.ui.fragments.ShopFragment

class DialogOutcome(private val shopped: Boolean) : DialogFragment() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: DialogOutcomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_outcome, container, false)

        val outcomeTitle = binding.outcomeTitle
        val outcomeQuote = binding.outcomeQuote
        val outcomeDescription = binding.outcomeDescription
        val okayButton = binding.okayButton

        if(shopped){
            outcomeTitle.text = getString(R.string.purchase_completed)
            outcomeQuote.text = getString(R.string.positive_quote)
            outcomeDescription.text = getString(R.string.purchase_completed_description)
        }else {
            outcomeTitle.text = getString(R.string.not_enought_coins_title)
            outcomeQuote.text = getString(R.string.negative_quote)
            outcomeDescription.text = getString(R.string.not_enought_coins)
        }

        okayButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> okayButton.setImageResource(R.drawable.okay_button_pressed)
                MotionEvent.ACTION_UP -> okayButton.setImageResource(R.drawable.okay_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        okayButton.setOnClickListener{

            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, ShopFragment())
                ?.commit()

            dismiss()
        }

        return binding.root
    }
}