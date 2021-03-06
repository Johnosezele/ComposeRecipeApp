package com.example.composereciepeeapp.di


import android.content.Context
import com.example.composereciepeeapp.presentation.ui.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)//this is where you define the component you want to install in. In this case the ApplicationComponent
object AppModule{

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRandomString(): String{
        return "hey look a random string"
    }

}