package br.manogarrafa.biblioteca.ui.components

import android.widget.Toast
import androidx.compose.foundation.border
import android.R as androidR
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme

@Composable
fun Search(
    onSearch: (String) -> Unit
) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
                .weight(0.75f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {
            onSearch(text)
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
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
        Search { it }
    }
}
