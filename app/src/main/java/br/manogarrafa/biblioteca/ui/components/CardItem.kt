package br.manogarrafa.biblioteca.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.manogarrafa.biblioteca.R
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme
import br.manogarrafa.biblioteca.ui.theme.TitleBackground
import br.manogarrafa.biblioteca.ui.theme.Typography
import br.manogarrafa.biblioteca.ui.utils.Book

@Composable
fun CardItem(title: String, count: Int = 0) {
    val cardHeight = 250
    val cardWidth = cardHeight * 0.75
    val titleHeight = cardHeight * 0.20
    Box(
        modifier = Modifier
            .size(width = cardWidth.dp, height = cardHeight.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.vampeerz),
            contentDescription = "Capa de Vampeerz nº 2",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                .height(titleHeight.dp)
                .background(TitleBackground)
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = Typography.titleMedium,
            )
        }
        if (count > 1) {
            CardItemCounter(count, Modifier.padding(start = 8.dp, 8.dp))
        }
    }
}

@Composable
fun CardItemCounter(value: Int, modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(24.dp, 24.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Text(
            text = value.toString(),
            textAlign = TextAlign.Center,
            style = Typography.labelSmall,
            color = Color.Black
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardList(list: List<Book>, modifier: Modifier = Modifier) {
    FlowRow(
        maxItemsInEachRow = 2,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        overflow = FlowRowOverflow.Clip,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        list.forEach {
            CardItem(it.title, it.quantity)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    BibliotecaTheme {
        FlowRow(
            maxItemsInEachRow = 3,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            CardItem("Vampeerz", 5)
            CardItem("That time I got reincarned as a slime", 21)
            CardItem("I prefer girls")
        }
    }
}