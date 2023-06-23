package com.json.rick_morty.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.json.rick_morty.CharacterQuery
import com.json.rick_morty.R
import com.json.rick_morty.presentation.viewmodel.CharacterState
import com.json.rick_morty.utils.convertDateStringToReadable


@Composable
fun CharacterDetailStates(characterState: CharacterState) {
    when (characterState) {
        is CharacterState.Success -> {
            val character = characterState.data?.character
            if (character != null)
                ShowCharacterDetails(character = character)
        }

        is CharacterState.Loading -> ShowLoading()
        is CharacterState.Error -> ShowError(message = stringResource(id = R.string.characters_details_error))
        else -> {}
    }
}

@Composable
fun ShowCharacterDetails(character: CharacterQuery.Character) {
    Column {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .padding(86.dp, 26.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.onPrimary, CircleShape),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = character.name,
                    placeholder = painterResource(id = R.drawable.rick_ic),
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = character.name.toString(),
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = character.species.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 12.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        }
        Column {
            val date = character.created.toString().convertDateStringToReadable().toString()
            DetailData(
                image = R.drawable.heart,
                property = "Status",
                detail = character.status.toString()
            )
            DetailData(
                image = R.drawable.gender,
                property = "Gender",
                detail = character.gender.toString()
            )
            DetailData(image = R.drawable.date, property = "Created", detail = date)
        }
    }
}

@Composable
fun DetailData(image: Int, property: String, detail: String) {
    val capitalizeProperty = property.replaceFirstChar { it.uppercase() }
    Row(
        modifier = Modifier
            .heightIn(0.dp, 80.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.padding(22.dp),
                painter = painterResource(id = image),
                contentDescription = property
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = capitalizeProperty, fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = detail,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 70.dp),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
    )
}