package br.manogarrafa.biblioteca.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme
import br.manogarrafa.biblioteca.ui.utils.Book
import android.R as androidR

sealed class SearchByOption(
    val iconRes: Int,
    val description: String,
    val filter: (Book, String) -> Boolean
) {
    object Name : SearchByOption(
        iconRes = androidR.drawable.ic_menu_crop,
        description = "Busca por nome",
        filter = { book, query -> book.title.contains(query, ignoreCase = true) }
    )

    object Quantity : SearchByOption(
        iconRes = androidR.drawable.ic_menu_zoom,
        description = "Busca por quantidade",
        filter = { book, query -> book.quantity.toString() == query }
    )

    object PublishYear : SearchByOption(
        iconRes = androidR.drawable.ic_menu_my_calendar,
        description = "Busca por ano",
        filter = { book, query -> book.publicationYear?.toString() == query }
    )

    companion object {
        val values = listOf(Name, Quantity, PublishYear)
    }
}

@Composable
fun SearchBySelector(
    modifier: Modifier = Modifier,
    selected: SearchByOption,
    onSelect: (SearchByOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                painter = painterResource(id = selected.iconRes),
                contentDescription = selected.description,
                tint = if (expanded) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            SearchByOption.values.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            option.description,
                            fontWeight = if (option == selected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = option.iconRes),
                            contentDescription = option.description,
                            tint = if (selected == option) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface
                        )
                    },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun Search(
    selected: SearchByOption,
    onSearch: (String) -> Unit,
    onSelect: (SearchByOption) -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBySelector(
            selected = selected,
            onSelect = { onSelect(it) },
            modifier = Modifier.weight(0.1f)
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Buscar") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary, // Borda inferior em foco
                focusedLabelColor = MaterialTheme.colorScheme.tertiary,     // Label em foco
                cursorColor = MaterialTheme.colorScheme.tertiary            // Cursor em foco
            ),
            modifier = Modifier
                .weight(0.75f),
            singleLine = true,
            // Fecha o teclado ao pressionar "Done" no teclado
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onSearch(text)
                }
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {
            focusManager.clearFocus()
            onSearch(text)
        }) {
            Icon(
                painter = painterResource(id = androidR.drawable.ic_menu_search),
                contentDescription = "Buscar"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    BibliotecaTheme {
        Search(selected = SearchByOption.Name, onSearch = { it }, onSelect = { it })
    }
}
