package it.wip.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import it.wip.DialogFragmentOrderDetail
import it.wip.DialogInfo
import it.wip.R
import it.wip.databinding.FragmentOrderDetailBinding

class OrderDetailFragment(val artTag: String, val artworkId: String): Fragment(){

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding : FragmentOrderDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_detail, container, false)

        val buyButton = binding.buyButton
        val backButton = binding.orderDetailBackButton
        val shopInfoButton = binding.shopInfoButton
        val artwork = binding.avatarDetail

        artworkSelected(artTag, artworkId, artwork)


        buyButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> buyButton.setImageResource(R.drawable.buy_button_pressed)
                MotionEvent.ACTION_UP -> buyButton.setImageResource(R.drawable.buy_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        backButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> backButton.setImageResource(R.drawable.back_arrow_pressed)
                MotionEvent.ACTION_UP -> backButton.setImageResource(R.drawable.back_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        shopInfoButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> shopInfoButton.setImageResource(R.drawable.shop_info_button_pressed)
                MotionEvent.ACTION_UP -> shopInfoButton.setImageResource(R.drawable.shop_info_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        buyButton.setOnClickListener {
            val dialogShop = DialogFragmentOrderDetail()
            dialogShop.show(parentFragmentManager, "shop")
        }

        backButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, ShopFragment())
                ?.commit()
        }

        shopInfoButton.setOnClickListener {
            val dialogHistoryInfo = DialogInfo()
            dialogHistoryInfo.show(parentFragmentManager, "historyInfo")
        }

        return binding.root
    }


    //          AUTO-AGGIORNAMENTO DIPINTO MENTRE SCORRE IL TIMER
    fun artworkSelected(tag: String, artworkId: String, artwork:ImageView?){

        when (tag) {
            "avatarTag" -> {
                when (artworkId) {
                    "1" -> artwork?.setBackgroundResource(R.drawable.david)
                    "2" -> artwork?.setBackgroundResource(R.drawable.munch)
                    "3" -> artwork?.setBackgroundResource(R.drawable.van_gogh_self_portrait)
                }
            }
            "backgroundTag" -> {
                when (artworkId) {
                    "1" -> artwork?.setBackgroundResource(R.drawable.field_with_crows_off_stand)
                    "2" -> artwork?.setBackgroundResource(R.drawable.adam_off_stand)
                    "3" -> artwork?.setBackgroundResource(R.drawable.magritte_kiss_off_stand)
                }
            }
        }
    }
}

/*
MaterialAlertDialogBuilder(context)
        .setTitle(resources.getString(R.string.title))
        .setMessage(resources.getString(R.string.supporting_text))
        .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
            // Respond to neutral button press
        }
        .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
            // Respond to negative button press
        }
        .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
            // Respond to positive button press
        }
        .show()
* */