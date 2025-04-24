package com.vaytree.antic.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainTabAdapter(manager: FragmentManager, private val list: List<Fragment>) :  FragmentPagerAdapter(
    manager,
    BEHAVIOR_SET_USER_VISIBLE_HINT
) {
    override fun getCount() = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int): Long {
        return list[position].hashCode().toLong()
    }

    override fun getItemPosition(any: Any): Int {
        return POSITION_NONE
    }
}