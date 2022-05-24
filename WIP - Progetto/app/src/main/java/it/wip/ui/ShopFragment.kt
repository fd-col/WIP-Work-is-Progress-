package it.wip.ui
import android.annotation.SuppressLint
import android.content.Intent
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

        avatarButton?.setOnClickListener() {

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, OrderDetailFragment())
                ?.commit()

        }

        return view
    }
}