package com.example.opgg.di

import androidx.lifecycle.ViewModel
import com.example.opgg.ui.MainActivity
import com.example.opgg.ui.MainApplication
import com.example.opgg.ui.ui.main.MainFragment
import com.example.opgg.ui.ui.main.MainViewModel
import dagger.Binds
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.IntoMap
import javax.inject.Singleton
import kotlin.reflect.KClass


@Singleton
@Component(
    modules = [
        //Helper module for generation and location of subcomponents
        RepositoryModule::class,
        ViewModelModule::class,
        MainBindModule::class,
        ApiModule::class,
        AndroidSupportInjectionModule::class

    ]
)
interface AppComponent : AndroidInjector<MainApplication>

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)


@Module
abstract class MainBindModule {
    @ContributesAndroidInjector
    abstract fun bindMain(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}