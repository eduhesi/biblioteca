package br.manogarrafa.biblioteca.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme

@Composable
fun AddButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0x6B124578),
            disabledContentColor = Color(0x6B007777)
        ),
        shape = ButtonDefaults.elevatedShape
    ) {
        Text("Adicionar livro")
    }
}

@Preview
@Composable
fun PreviewAddButton() {
    BibliotecaTheme {
        AddButton { }
    }
}