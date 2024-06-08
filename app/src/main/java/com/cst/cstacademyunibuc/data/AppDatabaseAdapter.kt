package com.cst.cstacademyunibuc.data

import android.content.Context
import androidx.room.Room
import com.cst.cstacademyunibuc.BuildConfig
import com.cst.cstacademyunibuc.data.repositories.products.ProductsRepository
import com.cst.cstacademyunibuc.data.repositories.products.ProductsRepositoryLocal
import com.cst.cstacademyunibuc.data.repositories.users.UserRepository
import com.cst.cstacademyunibuc.data.repositories.users.UserRepositoryLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseAdapter {

    //    private val MIGRATION_1_2 = object : Migration(1, 2) {
    //        override fun migrate(database: SupportSQLiteDatabase) {
    //
    //        }
    //    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val database = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
//        if(BuildConfig.DEBUG) {
//            database.fallbackToDestructiveMigration() // DEVELOPMENT ONLY
//        }
//        database.addMigrations(MIGRATION_1_2)
        database.openHelper.writableDatabase
        return database
    }

    @Provides
    @Singleton
    fun provideProductsRepository(database: AppDatabase): ProductsRepository {
        return ProductsRepositoryLocal(database.getProductsDao())
    }

    @Provides
    @Singleton
    fun provideUserRepository(database: AppDatabase): UserRepository {
        return UserRepositoryLocal(database.getUserDao())
    }

}