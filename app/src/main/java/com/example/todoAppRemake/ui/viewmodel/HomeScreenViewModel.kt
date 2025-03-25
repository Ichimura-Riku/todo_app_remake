package com.example.todoAppRemake.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoAppRemake.BuildConfig
import com.example.todoAppRemake.data.remote.NotionApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor() : ViewModel() {
    fun fetchNotionDatabase() {
        viewModelScope.launch {
            try {
                val response = NotionApiClient.client.getDatabase(BuildConfig.NOTION_DB_ID)
            } catch (e: Exception) {
                Log.e("HomeScreenViewModel", "Error fetching Notion database", e)
            }
        }
    }
}
