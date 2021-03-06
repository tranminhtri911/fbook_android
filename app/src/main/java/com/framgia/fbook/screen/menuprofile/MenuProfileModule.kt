package com.framgia.fbook.screen.menuprofile

import android.support.v4.app.Fragment
import com.framgia.fbook.utils.dagger.FragmentScope
import com.framgia.fbook.utils.navigator.Navigator
import dagger.Module
import dagger.Provides

/**
 * This is a Dagger module. We use this to pass in the View dependency to
 * the [MenuProfilePresenter].
 */
@Module
class MenuProfileModule(private val fragment: Fragment) {

  @FragmentScope
  @Provides
  fun providePresenter(): MenuProfileContract.Presenter {
    return MenuProfilePresenter()
  }

  @FragmentScope
  @Provides
  fun provideNavigator(): Navigator {
    return Navigator(fragment)
  }
}
