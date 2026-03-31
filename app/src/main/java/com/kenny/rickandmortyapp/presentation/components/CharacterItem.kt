package com.kenny.rickandmortyapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kenny.rickandmortyapp.R
import com.kenny.rickandmortyapp.domain.model.Character
import com.kenny.rickandmortyapp.domain.model.CharacterStatus

@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = character.name,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.error_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = character.species,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(character.status.toColor())
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = character.status.displayName,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

private fun CharacterStatus.toColor(): Color = when (this) {
    CharacterStatus.ALIVE -> Color(0xFF4CAF50)
    CharacterStatus.DEAD -> Color(0xFFF44336)
    CharacterStatus.UNKNOWN -> Color(0xFF9E9E9E)
}

private val CharacterStatus.displayName: String
    get() = when (this) {
        CharacterStatus.ALIVE -> "Alive"
        CharacterStatus.DEAD -> "Dead"
        CharacterStatus.UNKNOWN -> "Unknown"
    }
