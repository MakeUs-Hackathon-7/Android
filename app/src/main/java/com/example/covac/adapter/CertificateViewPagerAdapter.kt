package com.example.covac.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.covac.ui.CertificateFragment
import com.example.covac.ui.GetVacInfoFragment

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-13
 */
class CertificaterViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> GetVacInfoFragment()
            1 -> CertificateFragment()
            else -> CertificateFragment()
        }
    }
    override fun getItemCount():Int = 2
}