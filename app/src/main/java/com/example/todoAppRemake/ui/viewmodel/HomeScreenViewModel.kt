package com.example.todoAppRemake.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoAppRemake.BuildConfig
import com.example.todoAppRemake.data.remote.NotionApiService
import com.example.todoAppRemake.data.remote.model.NotionDatabasePagesResponse
import com.example.todoAppRemake.data.remote.model.NotionDatabaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

sealed interface NotionDatabaseUiState {
    data class Success(
        val databases: NotionDatabaseResponse,
    ) : NotionDatabaseUiState

    object Error : NotionDatabaseUiState

    object Loading : NotionDatabaseUiState
}

sealed interface NotionDatabasePageUiState {
    data class Success(
        val pages: NotionDatabasePagesResponse,
    ) : NotionDatabasePageUiState

    object Error : NotionDatabasePageUiState

    object Loading : NotionDatabasePageUiState
}

sealed interface NotionDataUiState {
    data class Success(
        val databases: NotionDatabaseResponse,
        val pages: NotionDatabasePagesResponse,
    ) : NotionDataUiState

    object Error : NotionDataUiState

    object Loading : NotionDataUiState
}

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor(
    val notionApiService: NotionApiService,
) : ViewModel() {
    private var notionDatabaseUiState: NotionDatabaseUiState by mutableStateOf(
        NotionDatabaseUiState.Loading,
    )
    private var notionDatabasePagesUiState: NotionDatabasePageUiState by mutableStateOf(
        NotionDatabasePageUiState.Loading,
    )

    var notionDataUiState: NotionDataUiState by mutableStateOf(NotionDataUiState.Loading)

    init {
        fetchNotionData()
    }

    fun fetchNotionData() {
        viewModelScope.launch {
            notionDatabaseUiState = fetchNotionDatabase()
            notionDatabasePagesUiState = fetchNotionDatabasePage()

            if (notionDatabaseUiState is NotionDatabaseUiState.Success &&
                notionDatabasePagesUiState is NotionDatabasePageUiState.Success
            ) {
                notionDataUiState =
                    NotionDataUiState.Success(
                        (notionDatabaseUiState as NotionDatabaseUiState.Success).databases,
                        (notionDatabasePagesUiState as NotionDatabasePageUiState.Success).pages,
                    )
            }
        }
    }

    private suspend fun fetchNotionDatabase(): NotionDatabaseUiState = try {
        val databaseResponse =
            withContext(Dispatchers.IO) {
                notionApiService.getDatabase(BuildConfig.NOTION_DB_ID)
            }
        NotionDatabaseUiState.Success(databaseResponse)
    } catch (e: IOException) {
        Log.e("HomeScreenViewModel", "IOException Error fetching Notion database$e")
        NotionDatabaseUiState.Error
    }

    private suspend fun fetchNotionDatabasePage(): NotionDatabasePageUiState = try {
        val databasePageResponse =
            withContext(Dispatchers.IO) {
                notionApiService.getDatabasePages(BuildConfig.NOTION_DB_ID)
            }
        NotionDatabasePageUiState.Success(databasePageResponse)
    } catch (e: IOException) {
        Log.e("HomeScreenViewModel", "IOException Error fetching Notion database$e")
        NotionDatabasePageUiState.Error
    }
}
