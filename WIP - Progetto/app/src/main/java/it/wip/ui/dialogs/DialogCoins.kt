package it.wip.ui.dialogs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import it.wip.MainActivity
import it.wip.R
import it.wip.databinding.DialogCoinsBinding

class DialogCoins(val coins: String) : DialogFragment(){
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: DialogCoinsBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_coins, container, false)


        val okayButton = binding.coinDialogOkayButton
        val earnedCoins = binding.coinDialogDescription2
        earnedCoins.text = coins


        okayButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> okayButton.setImageResource(R.drawable.okay_button_pressed)
                MotionEvent.ACTION_UP -> okayButton.setImageResource(R.drawable.okay_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        okayButton.setOnClickListener{
            startActivity(Intent(activity, MainActivity::class.java))
        }
        dialog?.setOnCancelListener{
            startActivity(Intent(activity, MainActivity::class.java))
        }

        return binding.root
    }
}