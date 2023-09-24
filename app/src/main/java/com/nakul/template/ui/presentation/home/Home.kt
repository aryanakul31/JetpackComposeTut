package com.nakul.template.ui.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Home(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

    }
    Text(text = text, modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
}

@Preview
@Composable
fun HomePreview() {
    Home(text = "Heyy")
}