package com.json.rick_morty.data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.json.rick_morty.CharacterQuery
import com.json.rick_morty.CharactersListQuery
import com.json.rick_morty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) :
    CharacterRepository {
    override suspend fun getCharacterList(): ApolloResponse<CharactersListQuery.Data> =
        apolloClient.query(CharactersListQuery()).execute()

    override suspend fun getCharacterDetails(id: String): ApolloResponse<CharacterQuery.Data> =
        apolloClient.query(CharacterQuery(id)).execute()
}