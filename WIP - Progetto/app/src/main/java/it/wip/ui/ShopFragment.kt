package it.wip.ui
import it.wip.R
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.os.Bundle

class ShopFragment: Fragment(R.layout.fragment_shop){
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val shop_view = super.onCreateView(inflater, container, savedInstanceState)
        return shop_view
    }
}
