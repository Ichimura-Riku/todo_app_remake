package com.example.todoAppRemake.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoAppRemake.BuildConfig
import com.example.todoAppRemake.data.remote.NotionApiService
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

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor(
    val notionApiService: NotionApiService,
) : ViewModel() {
    var notionDatabaseUiState: NotionDatabaseUiState by mutableStateOf(NotionDatabaseUiState.Loading)
        private set

    init {
        fetchNotionDatabase()
    }

    fun fetchNotionDatabase() {
        viewModelScope.launch {
            notionDatabaseUiState =
                try {
                    notionDatabaseUiState = NotionDatabaseUiState.Loading
                    val response =
                        withContext(Dispatchers.IO) {
                            notionApiService.getDatabase(BuildConfig.NOTION_DB_ID)
                        }
                    Log.d("HomeScreenViewModel", "Response: $response")
                    NotionDatabaseUiState.Success(response)
                } catch (e: IOException) {
                    Log.e("HomeScreenViewModel", "IOException Error fetching Notion database$e")
                    NotionDatabaseUiState.Error
                } catch (e: Exception) {
                    Log.e("HomeScreenViewModel", "Error fetching Notion database$e")
                    NotionDatabaseUiState.Error
                }
        }
    }
}
