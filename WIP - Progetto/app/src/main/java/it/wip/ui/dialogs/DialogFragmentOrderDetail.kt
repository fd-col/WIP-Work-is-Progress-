package it.wip.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import it.wip.R
import it.wip.database.model.ShopElement
import it.wip.databinding.DialogFragmentOrderDetailBinding
import it.wip.viewModel.DialogOrderDetailViewModel

//CONSULTA: https://www.youtube.com/watch?v=SkFcDWt9GV4
// FARE TRANSAZIONI CON STRINGHE SOTTO AL COMMENTO "ANIMATION" NEL FILE STRINGS.XML!!!!!!!!!!!!

class DialogFragmentOrderDetail(private val shopElement: ShopElement) : DialogFragment(){

    private lateinit var viewModel: DialogOrderDetailViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: DialogFragmentOrderDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_order_detail, container, false)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[DialogOrderDetailViewModel::class.java]

        viewModel.shopElementName = shopElement.elementName

        binding.viewModel = viewModel

        binding.lifecycleOwner = activity

        val dialogNoButton = binding.dialogNoButton
        val dialogYesButton = binding.dialogYesButton

        binding.shopDetailDescription.text = getString(R.string.order_detail_description, shopElement.price)

        dialogNoButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> dialogNoButton.setImageResource(R.drawable.no_button_pressed)
                MotionEvent.ACTION_UP -> dialogNoButton.setImageResource(R.drawable.no_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        dialogYesButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> dialogYesButton.setImageResource(R.drawable.yes_button_pressed)
                MotionEvent.ACTION_UP -> dialogYesButton.setImageResource(R.drawable.yes_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        dialogNoButton.setOnClickListener{
            dismiss()
        }

        dialogYesButton.setOnClickListener{

            viewModel.buyShopElement(shopElement.price)

            DialogOutcome().show(parentFragmentManager, "outcome")
            dismiss()
        }

        return binding.root
    }
}
