package com.framgia.fbook.screen.SearchBook;

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.framgia.fbook.MainApplication
import com.framgia.fbook.R
import com.framgia.fbook.databinding.ActivitySearchbookBinding
import com.framgia.fbook.screen.BaseActivity
import com.framgia.fbook.utils.navigator.Navigator
import javax.inject.Inject

/**
 * SearchBook Screen.
 */
class SearchBookActivity : BaseActivity(), SearchBookContract.ViewModel {

  @Inject
  lateinit var mPresenter: SearchBookContract.Presenter
  @Inject
  lateinit var mNavigator: Navigator
  @Inject
  lateinit var searchBookPagerAdapter: SearchBookPagerAdapter
  private lateinit var mSearchBookComponent: SearchBookComponent
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    mSearchBookComponent = DaggerSearchBookComponent.builder()
        .appComponent((application as MainApplication).appComponent)
        .searchBookModule(SearchBookModule(this))
        .build()
    mSearchBookComponent.inject(this)

    val binding = DataBindingUtil.setContentView<ActivitySearchbookBinding>(this,
        R.layout.activity_searchbook)
    binding.viewModel = this
  }

  override fun onStart() {
    super.onStart()
    mPresenter.onStart()
  }

  override fun onStop() {
    mPresenter.onStop()
    super.onStop()
  }

  fun getSearchBookComponent(): SearchBookComponent {
    return mSearchBookComponent
  }

  fun onClickArrowBack() {
    mNavigator.finishActivity()
  }
}
