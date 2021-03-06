package com.example.messageapp.di

import android.content.Context
import com.example.messageapp.db.sqldb.SqlHelper
import com.example.messageapp.repos.MessageRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    @Provides
    fun providesMsgRepo(@ApplicationContext context: Context) = MessageRepo(SqlHelper(context))

}