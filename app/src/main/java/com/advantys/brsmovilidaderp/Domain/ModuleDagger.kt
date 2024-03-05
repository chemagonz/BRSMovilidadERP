package com.advantys.brsmovilidaderp.Domain

import android.content.Context
import com.advantys.brsmovilidaderp.Data.DataBase.BD
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Series_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.TipoOperacion_Dao
import com.advantys.brsmovilidaderp.Utils.BDUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDagger {

    @Singleton
    @Provides
    fun provideBD(@ApplicationContext contexto: Context):BD{
        return BD(contexto)
    }

    @Provides
    fun providesTipoOperacion_Dao(bdUtil: BDUtil):TipoOperacion_Dao{
        return TipoOperacion_Dao(bdUtil)
    }

    @Provides
    fun providesCentros_Dao(bdUtil: BDUtil):Centros_Dao{
        return Centros_Dao(bdUtil)
    }

    fun providesSeries_Dao(bdUtil: BDUtil):Series_Dao{
        return Series_Dao((bdUtil))
    }
}