@file:JvmMultifileClass
package com.example.citipoc.di.components

import android.app.Application
import com.example.citipoc.di.modules.activitymodule.ActivityBuilderModule
import com.example.citipoc.di.modules.RepositoryModule
import com.example.citipoc.di.modules.NetworkModule
import com.example.citipoc.di.modules.viewmodelmodule.ViewModelFactoryModule
import com.example.citipoc.presentation.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, ViewModelFactoryModule::class, NetworkModule::class, RepositoryModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    //    @Component.Builder
//    abstract class Builder : AndroidInjector.Builder<SpaceXApplication>()
    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(application: Application)
}
/*public interface AppComponent  {
    fun inject(app: Application)

}*/
