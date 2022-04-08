package com.huda.blogsapp.network.model

import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.util.DomainMapper

class AuthorDtoMapper : DomainMapper<AuthorDto, Author> {
    override fun mapToDomainModel(model: AuthorDto): Author {
        with(model) {
            return Author(
                id = id,
                name = name,
                userName = userName,
                email = email,
                imgUrl = imgUrl,
                address = listOf(
                    address?.latitude?.toDouble() ?: 0.0,
                    address?.longitude?.toDouble() ?: 0.0
                )
            )
        }
    }

    override fun mapFromDomainModel(domainModel: Author): AuthorDto {
        with(domainModel) {
            return AuthorDto(
                id = id,
                name = name,
                userName = userName,
                email = email,
                imgUrl = imgUrl,
                address = Address(address[0].toString(), address[1].toString())
            )
        }
    }

}