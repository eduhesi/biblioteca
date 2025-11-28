package br.manogarrafa.biblioteca.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme

//@Composable
//fun Search() {
//    val keyboardController = LocalSoftwareKeyboardController.current
//    TextField(
//        state = rememberTextFieldState(),
//        label = { Text("Label") },
//        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//        onKeyboardAction = { keyboardController?.hide() }
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchPreview() {
//    BibliotecaTheme {
//        Search()
//    }
//}
