package it.wip.ui.fragments
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import it.wip.R
import androidx.fragment.app.Fragment

class ShopFragment: Fragment(R.layout.fragment_shop){
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val sxAvatar = view?.findViewById<ImageButton>(R.id.new_avatar_sx_button)
        val dxAvatar = view?.findViewById<ImageButton>(R.id.new_avatar_dx_button)
        val sxBackground = view?.findViewById<ImageButton>(R.id.new_background_sx_button)
        val dxBackground = view?.findViewById<ImageButton>(R.id.new_background_dx_button)
        val avatarButton = view?.findViewById<ImageButton>(R.id.avatars)
        val backgroundButton = view?.findViewById<ImageButton>(R.id.backgrounds)

        sxAvatar?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> sxAvatar.setImageResource(R.drawable.avatar_sx_arrow_pressed)
                MotionEvent.ACTION_UP -> sxAvatar.setImageResource(R.drawable.avatar_sx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        dxAvatar?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> dxAvatar.setImageResource(R.drawable.avatar_dx_arrow_pressed)
                MotionEvent.ACTION_UP -> dxAvatar.setImageResource(R.drawable.avatar_dx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        sxBackground?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> sxBackground.setImageResource(R.drawable.avatar_sx_arrow_pressed)
                MotionEvent.ACTION_UP -> sxBackground.setImageResource(R.drawable.avatar_sx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        dxBackground?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> dxBackground.setImageResource(R.drawable.avatar_dx_arrow_pressed)
                MotionEvent.ACTION_UP -> dxBackground.setImageResource(R.drawable.avatar_dx_arrow)
            }
            v?.onTouchEvent(event) ?: true
        }

        avatarButton?.setOnClickListener {

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, OrderDetailFragment())
                ?.commit()

        }


        // AVATARS AND BACKGROUNDS SWAP
        var avatarTag: String = avatarButton?.tag.toString()
        var backgroundTag: String = backgroundButton?.tag.toString()

        dxAvatar?.setOnClickListener {
            when (avatarTag) {
                "1" -> {
                    avatarButton?.setBackgroundResource(R.drawable.munch)
                    avatarButton?.tag = "2"
                    avatarTag = avatarButton?.tag.toString()
                }
                "2" -> {
                    avatarButton?.setBackgroundResource(R.drawable.van_gogh_self_portrait)
                    avatarButton?.tag = "3"
                    avatarTag = avatarButton?.tag.toString()
                }
                "3" -> {
                    avatarButton?.setBackgroundResource(R.drawable.david)
                    avatarButton?.tag = "1"
                    avatarTag = avatarButton?.tag.toString()
                }
            }
        }

        sxAvatar?.setOnClickListener {
            when (avatarTag) {
                "1" -> {
                    avatarButton?.setBackgroundResource(R.drawable.van_gogh_self_portrait)
                    avatarButton?.tag = "3"
                    avatarTag = avatarButton?.tag.toString()
                }
                "2" -> {
                    avatarButton?.setBackgroundResource(R.drawable.david)
                    avatarButton?.tag = "1"
                    avatarTag = avatarButton?.tag.toString()
                }
                "3" -> {
                    avatarButton?.setBackgroundResource(R.drawable.munch)
                    avatarButton?.tag = "2"
                    avatarTag = avatarButton?.tag.toString()
                }
            }
        }

        dxBackground?.setOnClickListener {
            when (backgroundTag) {
                "1" -> {
                    backgroundButton?.setBackgroundResource(R.drawable.adam_off_stand)
                    backgroundButton?.tag = "2"
                    backgroundTag = backgroundButton?.tag.toString()
                }
                "2" -> {
                    backgroundButton?.setBackgroundResource(R.drawable.magritte_kiss_off_stand)
                    backgroundButton?.tag = "3"
                    backgroundTag = backgroundButton?.tag.toString()
                }
                "3" -> {
                    backgroundButton?.setBackgroundResource(R.drawable.field_with_crows_off_stand)
                    backgroundButton?.tag = "1"
                    backgroundTag = backgroundButton?.tag.toString()
                }
            }
        }

        sxBackground?.setOnClickListener {
            when (backgroundTag) {
                "1" -> {
                    backgroundButton?.setBackgroundResource(R.drawable.magritte_kiss_off_stand)
                    backgroundButton?.tag = "3"
                    backgroundTag = backgroundButton?.tag.toString()
                }
                "2" -> {
                    backgroundButton?.setBackgroundResource(R.drawable.field_with_crows_off_stand)
                    backgroundButton?.tag = "1"
                    backgroundTag = backgroundButton?.tag.toString()
                }
                "3" -> {
                    backgroundButton?.setBackgroundResource(R.drawable.adam_off_stand)
                    backgroundButton?.tag = "2"
                    backgroundTag = backgroundButton?.tag.toString()
                }
            }
        }

        return view
    }
}