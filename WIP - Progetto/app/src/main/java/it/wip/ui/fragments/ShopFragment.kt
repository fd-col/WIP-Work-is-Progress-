package it.wip.ui.fragments
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import it.wip.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import it.wip.databinding.FragmentShopBinding
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

        viewModel = ViewModelProviders.of(this)[ShopViewModel::class.java]

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        val sxAvatar = binding.newAvatarSxButton
        val dxAvatar = binding.newAvatarDxButton
        val sxBackground = binding.newBackgroundSxButton
        val dxBackground = binding.newBackgroundDxButton
        val avatarButton = binding.avatars
        val backgroundButton = binding.backgrounds

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


        val avatars = binding.avatars
        val avatarPrice = binding.avatarPrice
        var avatarsTag = avatars.tag.toString().toInt()

        sxAvatar.setOnClickListener {
            avatarsTag--
            if(avatarsTag < 0) {
                avatarsTag = viewModel.avatars.size - 1
            }
            val tempAvatar = viewModel.avatars[avatarsTag]
            avatars.setBackgroundResource(fromShopElementNameToResource(tempAvatar.elementName))
            avatarPrice.text = tempAvatar.price.toString()
        }

        dxAvatar.setOnClickListener {
            avatarsTag++
            if(avatarsTag + 1 > viewModel.avatars.size) {
                avatarsTag = 0
            }
            val tempAvatar = viewModel.avatars[avatarsTag]
            avatars.setBackgroundResource(fromShopElementNameToResource(tempAvatar.elementName))
            avatarPrice.text = tempAvatar.price.toString()
        }

        val backgrounds = binding.backgrounds
        val backgroundPrice = binding.backgroundPrice
        var backgroundsTag = backgrounds.tag.toString().toInt()

        sxBackground.setOnClickListener {
            backgroundsTag--
            if(backgroundsTag < 0) {
                backgroundsTag = viewModel.backgrounds.size - 1
            }
            val tempBackground = viewModel.backgrounds[backgroundsTag]
            backgrounds.setBackgroundResource(fromShopElementNameToResource(tempBackground.elementName))
            backgroundPrice.text = tempBackground.price.toString()
        }

        dxBackground.setOnClickListener {
            backgroundsTag++
            if(backgroundsTag + 1 > viewModel.backgrounds.size) {
                backgroundsTag = 0
            }
            val tempBackground = viewModel.backgrounds[backgroundsTag]
            backgrounds.setBackgroundResource(fromShopElementNameToResource(tempBackground.elementName))
            backgroundPrice.text = tempBackground.price.toString()
        }

        if(this.arguments?.isEmpty == false) {
            avatars.setBackgroundResource(fromShopElementNameToResource(this.arguments?.get("selectedAvatar").toString()))
            avatarsTag = this.requireArguments().getInt("avatarsTag")
            avatarPrice.text = this.arguments?.get("avatarPrice").toString()
            backgrounds.setBackgroundResource(fromShopElementNameToResource(this.arguments?.get("selectedBackground").toString()))
            backgroundsTag = this.requireArguments().getInt("backgroundsTag")
            backgroundPrice.text = this.arguments?.get("backgroundPrice").toString()
        }

        avatarButton.setOnClickListener {

            val fragment = OrderDetailFragment(viewModel.avatars[avatarsTag])

            val bundle = Bundle()
            bundle.putString("selectedAvatar", viewModel.avatars[avatarsTag].elementName)
            bundle.putInt("avatarsTag", avatarsTag)
            bundle.putInt("avatarPrice", avatarPrice.text.toString().toInt())
            bundle.putString("selectedBackground", viewModel.backgrounds[backgroundsTag].elementName)
            bundle.putInt("backgroundsTag", backgroundsTag)
            bundle.putInt("backgroundPrice", backgroundPrice.text.toString().toInt())
            fragment.arguments = bundle

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, fragment)
                ?.commit()
        }

        backgroundButton.setOnClickListener {

            val fragment = OrderDetailFragment(viewModel.backgrounds[backgroundsTag])

            val bundle = Bundle()
            bundle.putString("selectedAvatar", viewModel.avatars[avatarsTag].elementName)
            bundle.putInt("avatarsTag", avatarsTag)
            bundle.putInt("avatarPrice", avatarPrice.text.toString().toInt())
            bundle.putString("selectedBackground", viewModel.backgrounds[backgroundsTag].elementName)
            bundle.putInt("backgroundsTag", backgroundsTag)
            bundle.putInt("backgroundPrice", backgroundPrice.text.toString().toInt())

            fragment.arguments = bundle

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, fragment)
                ?.commit()
        }

        return binding.root
    }
}