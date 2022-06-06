package com.example.starterCode.data.repository.rickymortypaging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starterCode.data.remote.RickAndMortyApi
import com.example.starterCode.data.remote.dto.Result
import com.example.starterCode.data.remote.dto.toRickyAndMorty
import com.example.starterCode.domain.model.RickyAndMorty
import java.io.IOException


const val RICKY_AND_MORTY_PAGE_INDEX = 1

class RickyMortyPagingSource(
    private val rickAndMortyApi: RickAndMortyApi
) : PagingSource<Int, RickyAndMorty>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickyAndMorty> {
        val page = params.key ?: RICKY_AND_MORTY_PAGE_INDEX
        return try {
            val response = rickAndMortyApi.getRickyAndMorty(page)
            val rickyAndMorty = response.toRickyAndMorty()
            LoadResult.Page(
                data = rickyAndMorty,
                prevKey = if (page == RICKY_AND_MORTY_PAGE_INDEX) null else page - 1,
                nextKey = if (rickyAndMorty.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (io: IOException) {
            LoadResult.Error(io)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RickyAndMorty>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}