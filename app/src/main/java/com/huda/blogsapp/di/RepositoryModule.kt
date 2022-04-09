package com.huda.blogsapp.di

import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.model.Comment
import com.huda.blogsapp.domain.model.Post
import com.huda.blogsapp.network.AuthorService
import com.huda.blogsapp.network.CommentService
import com.huda.blogsapp.network.PostService
import com.huda.blogsapp.network.model.AuthorDtoMapper
import com.huda.blogsapp.network.model.CommentDtoMapper
import com.huda.blogsapp.network.model.PostDtoMapper
import com.huda.blogsapp.repository.AuthorRepository_Impl
import com.huda.blogsapp.repository.CommentRepository_Impl
import com.huda.blogsapp.repository.PostRepository_Impl
import com.huda.blogsapp.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthorRepository(
        authorService: AuthorService,
        mapper: AuthorDtoMapper
    ): Repository<Author> {
        return AuthorRepository_Impl(
            authorService,
            mapper
        )
    }

    @Singleton
    @Provides
    fun providePostRepository(
        postService: PostService,
        mapper: PostDtoMapper
    ): Repository<Post> {
        return PostRepository_Impl(
            postService,
            mapper
        )
    }

    @Singleton
    @Provides
    fun provideCommentRepository(
        commentService: CommentService,
        mapper: CommentDtoMapper
    ): Repository<Comment> {
        return CommentRepository_Impl(
            commentService,
            mapper
        )
    }
}