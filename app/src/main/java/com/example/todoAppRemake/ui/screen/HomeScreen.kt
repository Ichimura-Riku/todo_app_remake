package com.example.todoAppRemake.ui.screen

import android.R.attr.text
import android.R.attr.type
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ModalBottomSheetDefaults.properties
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoAppRemake.data.remote.model.NotionDatabaseProperty
import com.example.todoAppRemake.data.remote.model.NotionDatabaseResponse
import com.example.todoAppRemake.data.remote.model.NotionDatabaseText
import com.example.todoAppRemake.data.remote.model.NotionDatabaseTitle
import com.example.todoAppRemake.ui.theme.TodoAppRemakeTheme
import com.example.todoAppRemake.ui.viewmodel.HomeScreenViewModel
import com.example.todoAppRemake.ui.viewmodel.NotionDatabaseUiState

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier,
) {
    HomeScreenContent(modifier = modifier, uiState = viewModel.notionDatabaseUiState)
}

@Composable
fun HomeScreenContent(
    uiState: NotionDatabaseUiState,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is NotionDatabaseUiState.Loading -> {
            Text(modifier = modifier, text = "Loading")
        }

        is NotionDatabaseUiState.Error -> {
            Text(modifier = modifier, text = "Error")
        }

        is NotionDatabaseUiState.Success -> {
            LazyColumn(
                modifier =
                modifier
                    .fillMaxSize()
                    .padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 0.dp),
            ) {
                items(uiState.databases.title) { title ->
                    TodoCard(
                        modifier = Modifier,
                        cardText = title.text.content,
                        onClick = { Log.v("TodoCard", "click to id:${title.text.content}") },
                    )
                }
            }
            uiState.databases.title
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

@Preview(showBackground = true)
@Composable
private fun HomeScreenContentSuccessPreview() {
    val mockDatabasesResponse =
        NotionDatabaseResponse(
            objectType = "database",
            id = "mock_id",
            title =
            List(5) {
                NotionDatabaseTitle(
                    type = "title",
                    text = NotionDatabaseText(content = "mock_title${it * 2 + 1}"),
                )
            },
            properties =
            mapOf(
                "mock_property_id" to
                    NotionDatabaseProperty(
                        id = "mock_property_id",
                        name = "mock_property_name",
                        type = "title",
                    ),
            ),
        )
    val mockUiState = NotionDatabaseUiState.Success(mockDatabasesResponse)
    TodoAppRemakeTheme {
        HomeScreenContent(uiState = mockUiState, modifier = Modifier)
    }
}
