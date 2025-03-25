package com.example.todoAppRemake.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoAppRemake.ui.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier,
) {
    val mockTodoPropertiesList =
        List(5) { index ->
            MockTodoCardProperties(
                id = index,
                text = "hello id: $index",
            )
        }

    LazyColumn(
        modifier =
        modifier
            .fillMaxSize()
            .padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 0.dp),
    ) {
        items(mockTodoPropertiesList) { mockTodoProperties ->
            TodoCard(
                modifier = Modifier,
                cardText = mockTodoProperties.text,
//                onClick = { Log.v("TodoCard", "click to id:${mockTodoProperties.id}") },
                onClick = { viewModel.fetchNotionDatabase() },
            )
        }
    }
}

@Composable
fun TodoCard(
    modifier: Modifier = Modifier,
    cardText: String = "hello",
    onClick: () -> Unit = {},
) {
    Box(
        modifier =
        modifier
            .fillMaxWidth()
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = cardText,
            modifier =
            Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}

// @Preview(showBackground = true)
// @Composable
// private fun GreetingPreview() {
//    TodoAppRemakeTheme {
//        AppScaffold(modifier = Modifier.fillMaxSize())
//    }
// }

data class MockTodoCardProperties(
    val id: Int,
    val text: String,
)
