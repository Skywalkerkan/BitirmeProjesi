package com.example.bitirmeprojesi.di

import android.content.Context
import androidx.room.Room
import com.example.bitirmeprojesi.data.datasource.YemeklerRoomDataSource
import com.example.bitirmeprojesi.data.repo.YemeklerRoomRepository
import com.example.bitirmeprojesi.room.VeriTabani
import com.example.bitirmeprojesi.room.YemeklerDaoRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomAppModule {
    @Provides
    @Singleton
    fun provideKisilerRepository(yds: YemeklerRoomDataSource): YemeklerRoomRepository{
        return YemeklerRoomRepository(yds)
    }

    @Provides
    @Singleton
    fun provideKisilerDataSource(kdao: YemeklerDaoRoom): YemeklerRoomDataSource{
        return  YemeklerRoomDataSource(kdao)
    }

    @Provides
    @Singleton
    fun provideKisilerDao(@ApplicationContext context: Context): YemeklerDaoRoom{

        val vt = Room.databaseBuilder(context, VeriTabani::class.java, "yemekler.sqlite")
            .createFromAsset("yemekler.sqlite").build()

        return vt.getYemeklerDao()
    }


}