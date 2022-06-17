package it.wip.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import it.wip.databinding.FragmentShopBinding
import it.wip.R
import it.wip.database.model.ShopElement
import it.wip.utils.fromShopElementNameToResource
import it.wip.viewModel.ShopViewModel

class ShopFragment: Fragment(){

    private lateinit var viewModel: ShopViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding : FragmentShopBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[ShopViewModel::class.java]

        binding.viewModel = viewModel

        binding.lifecycleOwner = activity

        val sxAvatar = binding.newAvatarSxButton
        val dxAvatar = binding.newAvatarDxButton
        val sxBackground = binding.newBackgroundSxButton
        val dxBackground = binding.newBackgroundDxButton

        val avatars = binding.avatars
        val avatarPrice = binding.avatarPrice
        val avatarCoin = binding.coinIconAvatar

        val backgrounds = binding.backgrounds
        val backgroundPrice = binding.backgroundPrice
        val backgroundCoin = binding.coinIconBackground

        var avatarsTag = avatars.tag.toString().toInt()
        var backgroundsTag = backgrounds.tag.toString().toInt()

        //Check fragment arguments
        if(this.arguments?.isEmpty == false) {

            //Set avatar image, tag and price
            avatars.setBackgroundResource(fromShopElementNameToResource(this.arguments?.get("selectedAvatar").toString()))
            avatarsTag = this.requireArguments().getInt("avatarsTag")

            if(this.arguments?.get("avatarPrice") == 0)
                setPriceVisibility(false, this.arguments?.get("avatarPrice").toString(), avatarPrice, avatarCoin)
            else
                setPriceVisibility(true, this.arguments?.get("avatarPrice").toString(), avatarPrice, avatarCoin)

            //Set background image, tag and price
            backgrounds.setBackgroundResource(fromShopElementNameToResource(this.arguments?.get("selectedBackground").toString()))
            backgroundsTag = this.requireArguments().getInt("backgroundsTag")

            if(this.arguments?.get("backgroundPrice") == 0)
                setPriceVisibility(false, this.arguments?.get("backgroundPrice").toString(), backgroundPrice, backgroundCoin)
            else
                setPriceVisibility(true, this.arguments?.get("backgroundPrice").toString(), backgroundPrice, backgroundCoin)

        } else { //Set default values

            avatarPrice.text = null
            avatarPrice.setBackgroundResource(R.drawable.check)
            avatarCoin.visibility = View.INVISIBLE

            backgroundPrice.text = null
            backgroundPrice.setBackgroundResource(R.drawable.check)
            backgroundCoin.visibility = View.INVISIBLE

        }

        sxAvatar.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> sxAvatar.setImageResource(R.drawable.avatar_sx_arrow_pressed)
                MotionEvent.ACTION_UP -> sxAvatar.setImageResource(R.drawable.avatar_sx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }


        dxAvatar.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> dxAvatar.setImageResource(R.drawable.avatar_dx_arrow_pressed)
                MotionEvent.ACTION_UP -> dxAvatar.setImageResource(R.drawable.avatar_dx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        sxBackground.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> sxBackground.setImageResource(R.drawable.avatar_sx_arrow_pressed)
                MotionEvent.ACTION_UP -> sxBackground.setImageResource(R.drawable.avatar_sx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        dxBackground.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> dxBackground.setImageResource(R.drawable.avatar_dx_arrow_pressed)
                MotionEvent.ACTION_UP -> dxBackground.setImageResource(R.drawable.avatar_dx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }


        sxAvatar.setOnClickListener {

            avatarsTag--
            if(avatarsTag < 0) {
                avatarsTag = viewModel.avatars.size - 1
            }

            val tempAvatar = viewModel.avatars[avatarsTag]

            avatars.setBackgroundResource(fromShopElementNameToResource(tempAvatar.elementName))

            if(viewModel.shoppedElementNames.contains(tempAvatar.elementName))
                setPriceVisibility(false, tempAvatar.price.toString(), avatarPrice, avatarCoin)
            else
                setPriceVisibility(true, tempAvatar.price.toString(), avatarPrice, avatarCoin)

        }

        dxAvatar.setOnClickListener {

            avatarsTag++
            if(avatarsTag + 1 > viewModel.avatars.size) {
                avatarsTag = 0
            }

            val tempAvatar = viewModel.avatars[avatarsTag]

            avatars.setBackgroundResource(fromShopElementNameToResource(tempAvatar.elementName))

            if(viewModel.shoppedElementNames.contains(tempAvatar.elementName))
                setPriceVisibility(false, tempAvatar.price.toString(), avatarPrice, avatarCoin)
            else
                setPriceVisibility(true, tempAvatar.price.toString(), avatarPrice, avatarCoin)

        }

        sxBackground.setOnClickListener {

            backgroundsTag--
            if(backgroundsTag < 0) {
                backgroundsTag = viewModel.backgrounds.size - 1
            }

            val tempBackground = viewModel.backgrounds[backgroundsTag]

            backgrounds.setBackgroundResource(fromShopElementNameToResource(tempBackground.elementName))

            if(viewModel.shoppedElementNames.contains(tempBackground.elementName))
                setPriceVisibility(false, tempBackground.price.toString(), backgroundPrice, backgroundCoin)
            else
                setPriceVisibility(true, tempBackground.price.toString(), backgroundPrice, backgroundCoin)

        }

        dxBackground.setOnClickListener {
            backgroundsTag++
            if(backgroundsTag + 1 > viewModel.backgrounds.size) {
                backgroundsTag = 0
            }
            val tempBackground = viewModel.backgrounds[backgroundsTag]

            backgrounds.setBackgroundResource(fromShopElementNameToResource(tempBackground.elementName))

            if(viewModel.shoppedElementNames.contains(tempBackground.elementName))
                setPriceVisibility(false, tempBackground.price.toString(), backgroundPrice, backgroundCoin)
            else
                setPriceVisibility(true, tempBackground.price.toString(), backgroundPrice, backgroundCoin)

        }


        avatars.setOnClickListener {
            fragmentTransactionOrderDetail(
                viewModel.avatars[avatarsTag], avatarsTag, avatarPrice.text.toString(), backgroundsTag, backgroundPrice.text.toString())
        }

        backgrounds.setOnClickListener {
            fragmentTransactionOrderDetail(
                viewModel.backgrounds[backgroundsTag], avatarsTag, avatarPrice.text.toString(), backgroundsTag, backgroundPrice.text.toString())
        }

        return binding.root
    }

    private fun setPriceVisibility(willBeVisible: Boolean, tempElementPrice: String, elementPrice: TextView, elementCoin: ImageView) {
        if(willBeVisible){
            elementPrice.text = tempElementPrice
            elementPrice.setBackgroundResource(0)
            elementCoin.visibility = View.VISIBLE
        } else {
            elementPrice.text = null
            elementPrice.setBackgroundResource(R.drawable.check)
            elementCoin.visibility = View.INVISIBLE
        }
    }

    private fun fragmentTransactionOrderDetail(shopElement: ShopElement, avatarsTag: Int, avatarPrice: String, backgroundsTag: Int, backgroundPrice: String) {

        val orderDetailFragment = OrderDetailFragment(shopElement)

        val bundle = Bundle()

        bundle.putString("selectedAvatar", viewModel.avatars[avatarsTag].elementName)
        bundle.putInt("avatarsTag", avatarsTag)

        // price = 0 => unlocked; price != 0 => locked
        if(avatarPrice == "")
            bundle.putInt("avatarPrice", 0)
        else
            bundle.putInt("avatarPrice", avatarPrice.toInt())

        bundle.putString("selectedBackground", viewModel.backgrounds[backgroundsTag].elementName)
        bundle.putInt("backgroundsTag", backgroundsTag)

        // price = 0 => unlocked; price != 0 => locked
        if(backgroundPrice == "")
            bundle.putInt("backgroundPrice", 0)
        else
            bundle.putInt("backgroundPrice", backgroundPrice.toInt())

        orderDetailFragment.arguments = bundle

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frame_layout, orderDetailFragment)
            ?.commit()

    }

}