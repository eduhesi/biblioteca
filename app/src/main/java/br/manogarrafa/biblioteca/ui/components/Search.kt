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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme
import br.manogarrafa.biblioteca.ui.utils.SearchByOption
import android.R as androidR

@Composable
fun SearchBySelector(
    modifier: Modifier = Modifier,
    selected: Pair<SearchByOption, (SearchByOption) -> Unit>,
) {
    var expanded by remember { mutableStateOf(false) }

    val (optionSelected, action) = selected

    Box(modifier = modifier) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                painter = painterResource(id = optionSelected.iconRes),
                contentDescription = optionSelected.description,
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
                        action(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun OrderSelection(
    modifier: Modifier = Modifier,
    order: Pair<Boolean, (Boolean) -> Unit>
) {
    IconButton(onClick = {
        order.second(order.first.not())
    }, modifier) {
        Icon(
            painter = painterResource(id = androidR.drawable.ic_menu_directions),
            contentDescription = "Alterar ordem",
            tint = if (order.first) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun Search(
    selected: Pair<SearchByOption, (SearchByOption) -> Unit>,
    onSearch: (String) -> Unit,
    order: Pair<Boolean, (Boolean) -> Unit>
) {
    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBySelector(
            selected = selected,
            modifier = Modifier.weight(0.1f)
        )
        OrderSelection(order = order, modifier = Modifier.weight(0.1f))
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
                .weight(1f),
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
//        IconButton(onClick = {
//            focusManager.clearFocus()
//            onSearch(text)
//        }) {
//            Icon(
//                painter = painterResource(id = androidR.drawable.ic_menu_search),
//                contentDescription = "Buscar"
//            )
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    BibliotecaTheme {
        Search(selected = SearchByOption.Name to { it }, onSearch = { it }, order = true to { it })
    }
}
