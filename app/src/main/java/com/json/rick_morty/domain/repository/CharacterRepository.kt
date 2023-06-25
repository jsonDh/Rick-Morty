package com.json.rick_morty.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.json.rick_morty.CharacterQuery
import com.json.rick_morty.CharactersListQuery

interface CharacterRepository {

    suspend fun getCharacterList(): ApolloResponse<CharactersListQuery.Data>
    suspend fun getCharacterDetails(id: String) : ApolloResponse<CharacterQuery.Data>

}