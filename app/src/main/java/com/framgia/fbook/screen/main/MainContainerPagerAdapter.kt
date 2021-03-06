package com.framgia.fbook.screen.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by levutantuan on 8/30/17.
 */
class MainContainerPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

  override fun getItem(position: Int): Fragment? {
    return MainContainerFragment.newInstance(position)
  }

  override fun getCount(): Int {
    return TOTAL_TAB
  }

  companion object {
    private val TOTAL_TAB = 4
  }
}
