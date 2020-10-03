package com.sungjae.portfolio.remote

import android.util.Log
import com.sungjae.portfolio.data.RemoteDataSource
import com.sungjae.portfolio.data.models.Content
import com.sungjae.portfolio.data.models.ContentItem
import com.sungjae.portfolio.domain.exception.Result
import com.sungjae.portfolio.remote.component.Api
import com.sungjae.portfolio.remote.mapper.RemoteMapper
import com.sungjae.portfolio.remote.models.ContentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: Api
) : RemoteDataSource, RemoteMapper<ContentResponse, Content> {
    override suspend fun getRemoteContents(type: String, query: String): Result<Content> =
        withContext(Dispatchers.IO) {
            try {
                Log.d("RemoteDataSourceImpl", "${api.getContentInfo(type, query).toData(query)}")
                Result.OnSuccess(
                    Content(
                        query = query,
                        contentItems = api.getContentInfo(type, query).toData(query)
                    )
                )
            } catch (e: Exception) {
                Log.d("RemoteDataSourceImpl", "e$e")
                Result.OnError(e)
            }
        }

    override fun ContentResponse.toData(query: String): List<ContentItem> =
        ArrayList<ContentItem>().also { arrayList ->
            this.contentResponseItems.forEach { response ->
                arrayList.add(
                    ContentItem(
                        image = response.image,
                        actor = response.actor,
                        description = response.description,
                        title = response.title,
                        link = response.link
                    )
                )
            }
        }
}