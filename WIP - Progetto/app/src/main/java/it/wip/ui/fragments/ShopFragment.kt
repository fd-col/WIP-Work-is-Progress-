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

        binding.coinIconAvatar.setOnClickListener() {
            Log.e("Avatar", viewModel.avatars.toString())
            Log.e("Backgrounds", viewModel.backgrounds.toString())
        }

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
        var avatarsTag = avatars.tag.toString().toInt()

        binding.newAvatarSxButton.setOnClickListener {
            avatarsTag--
            if(avatarsTag < 0) {
                avatarsTag = viewModel.avatars.size - 1
            }
            val tempAvatar = viewModel.avatars[avatarsTag]
            avatars.setBackgroundResource(fromShopElementNameToResource(tempAvatar.elementName))
        }

        binding.newAvatarDxButton.setOnClickListener {
            avatarsTag++
            if(avatarsTag + 1 > viewModel.avatars.size) {
                avatarsTag = 0
            }
            val tempAvatar = viewModel.avatars[avatarsTag]
            avatars.setBackgroundResource(fromShopElementNameToResource(tempAvatar.elementName))
        }

        val backgrounds = binding.backgrounds
        var backgroundsTag = backgrounds.tag.toString().toInt()

        binding.newAvatarSxButton.setOnClickListener {
            avatarsTag--
            if(avatarsTag < 0) {
                avatarsTag = viewModel.avatars.size - 1
            }
            val tempAvatar = viewModel.avatars[avatarsTag]
            avatars.setBackgroundResource(fromShopElementNameToResource(tempAvatar.elementName))
        }

        binding.newBackgroundDxButton.setOnClickListener {
            backgroundsTag++
            if(backgroundsTag + 1 > viewModel.avatars.size) {
                avatarsTag = 0
            }
            val tempAvatar = viewModel.avatars[avatarsTag]
            avatars.setBackgroundResource(fromShopElementNameToResource(tempAvatar.elementName))
        }

        // AVATARS AND BACKGROUNDS SWAP
        /*var avatarTag: String = avatarButton.tag.toString()
        var backgroundTag: String = backgroundButton.tag.toString()

        dxAvatar.setOnClickListener {
            when (avatarTag) {
                "1" -> {
                    avatarButton.setBackgroundResource(R.drawable.munch)
                    avatarButton.tag = "2"
                    avatarTag = avatarButton.tag.toString()
                }
                "2" -> {
                    avatarButton.setBackgroundResource(R.drawable.van_gogh_self_portrait)
                    avatarButton.tag = "3"
                    avatarTag = avatarButton.tag.toString()
                }
                "3" -> {
                    avatarButton.setBackgroundResource(R.drawable.david)
                    avatarButton.tag = "1"
                    avatarTag = avatarButton.tag.toString()
                }
            }
        }

        sxAvatar.setOnClickListener {
            when (avatarTag) {
                "1" -> {
                    avatarButton.setBackgroundResource(R.drawable.van_gogh_self_portrait)
                    avatarButton.tag = "3"
                    avatarTag = avatarButton.tag.toString()
                }
                "2" -> {
                    avatarButton.setBackgroundResource(R.drawable.david)
                    avatarButton.tag = "1"
                    avatarTag = avatarButton.tag.toString()
                }
                "3" -> {
                    avatarButton.setBackgroundResource(R.drawable.munch)
                    avatarButton.tag = "2"
                    avatarTag = avatarButton.tag.toString()
                }
            }
        }

        dxBackground.setOnClickListener {
            when (backgroundTag) {
                "1" -> {
                    backgroundButton.setBackgroundResource(R.drawable.adam_off_stand)
                    backgroundButton.tag = "2"
                    backgroundTag = backgroundButton.tag.toString()
                }
                "2" -> {
                    backgroundButton.setBackgroundResource(R.drawable.magritte_kiss_off_stand)
                    backgroundButton.tag = "3"
                    backgroundTag = backgroundButton.tag.toString()
                }
                "3" -> {
                    backgroundButton.setBackgroundResource(R.drawable.field_with_crows_off_stand)
                    backgroundButton.tag = "1"
                    backgroundTag = backgroundButton.tag.toString()
                }
            }
        }

        sxBackground.setOnClickListener {
            when (backgroundTag) {
                "1" -> {
                    backgroundButton.setBackgroundResource(R.drawable.magritte_kiss_off_stand)
                    backgroundButton.tag = "3"
                    backgroundTag = backgroundButton.tag.toString()
                }
                "2" -> {
                    backgroundButton.setBackgroundResource(R.drawable.field_with_crows_off_stand)
                    backgroundButton.tag = "1"
                    backgroundTag = backgroundButton.tag.toString()
                }
                "3" -> {
                    backgroundButton.setBackgroundResource(R.drawable.adam_off_stand)
                    backgroundButton.tag = "2"
                    backgroundTag = backgroundButton.tag.toString()
                }
            }
        }

        avatarButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, OrderDetailFragment("avatarTag", avatarTag))
                ?.commit()
        }

        backgroundButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, OrderDetailFragment("backgroundTag", backgroundTag))
                ?.commit()
        }
*/
        return binding.root
    }
}