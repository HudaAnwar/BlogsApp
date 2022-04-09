package com.huda.blogsapp.di

import com.google.gson.GsonBuilder
import com.huda.blogsapp.network.AuthorService
import com.huda.blogsapp.network.CommentService
import com.huda.blogsapp.network.PostService
import com.huda.blogsapp.network.model.AuthorDtoMapper
import com.huda.blogsapp.network.model.CommentDtoMapper
import com.huda.blogsapp.network.model.PostDtoMapper
import com.huda.blogsapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideAuthorMapper(): AuthorDtoMapper {
        return AuthorDtoMapper()
    }

    @Singleton
    @Provides
    fun providePostMapper(): PostDtoMapper {
        return PostDtoMapper()
    }

    @Singleton
    @Provides
    fun provideCommentMapper(): CommentDtoMapper {
        return CommentDtoMapper()
    }

    @Singleton
    @Provides
    fun provideAuthorService(): AuthorService {
        return getRetrofit()
            .create(AuthorService::class.java)
    }

    @Singleton
    @Provides
    fun providePostService(): PostService {
        return getRetrofit().create(PostService::class.java)
    }

    @Singleton
    @Provides
    fun provideCommentService(): CommentService {
        return getRetrofit().create(CommentService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}