package com.example.todoAppRemake.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoAppRemake.ui.theme.TodoAppRemakeTheme
import com.example.todoAppRemake.ui.viewmodel.HomeScreenViewModel
import com.example.todoAppRemake.ui.viewmodel.NotionDatabaseUiState

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier,
) {
    val notionDatabaseUiState = viewModel.notionDatabaseUiState
    if (notionDatabaseUiState is NotionDatabaseUiState.Success) {
        LazyColumn(
            modifier =
            modifier
                .fillMaxSize()
                .padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 0.dp),
        ) {
            items(notionDatabaseUiState.databases.title) { title ->
                TodoCard(
                    modifier = Modifier,
                    cardText = title.text.content,
                    onClick = { Log.v("TodoCard", "click to id:${title.text.content}") },
//                    onClick = { viewModel.fetchNotionDatabase() },
                )
            }
        }
        notionDatabaseUiState.databases.title
    } else {
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    TodoAppRemakeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Small Top App Bar")
                    },
                )
            },
        ) { innerPadding ->
            HomeScreen(viewModel = HomeScreenViewModel(), modifier = Modifier.padding(innerPadding))
        }
    }
}

data class MockTodoCardProperties(
    val id: Int,
    val text: String,
)
