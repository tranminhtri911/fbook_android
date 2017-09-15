package com.framgia.fbook.screen.addCategoryFavorite;

import com.framgia.fbook.data.source.CategoryRepository
import com.framgia.fbook.data.source.remote.api.error.BaseException
import com.framgia.fbook.utils.rx.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Listens to user actions from the UI ({@link AddCategoryFavoriteActivity}), retrieves the data and updates
 * the UI as required.
 */
class AddCategoryFavoritePresenter(private val
mCategoryRepository: CategoryRepository) : AddCategoryFavoriteContract.Presenter {

  private var mViewModel: AddCategoryFavoriteContract.ViewModel? = null
  private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
  private lateinit var mBaseSchedulerProvider: BaseSchedulerProvider

  override fun onStart() {}

  override fun onStop() {
    mCompositeDisposable.clear()
  }

  override fun getCategory() {
    val disposable: Disposable = mCategoryRepository.getCategory()
        .subscribeOn(mBaseSchedulerProvider.io())
        .observeOn(mBaseSchedulerProvider.ui())
        .doOnSubscribe { mViewModel?.onShowProgressDialog() }
        .doAfterTerminate { mViewModel?.onDismissProgressDialog() }
        .subscribe(
            { listCategory ->
              mViewModel?.onGetCategorySuccess(listCategory.items)
            },
            { error ->
              mViewModel?.onError(error as BaseException)
            })
    mCompositeDisposable.add(disposable)
  }

  override fun setViewModel(viewModel: AddCategoryFavoriteContract.ViewModel) {
    mViewModel = viewModel
  }

  fun setSchedulerProvider(baseSchedulerProvider: BaseSchedulerProvider) {
    mBaseSchedulerProvider = baseSchedulerProvider
  }
}
