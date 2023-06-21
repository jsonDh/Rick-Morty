package com.json.rick_morty.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.json.rick_morty.CharactersListQuery

interface CharacterRepository {

    suspend fun getCharacterList(): ApolloResponse<CharactersListQuery.Data>

}