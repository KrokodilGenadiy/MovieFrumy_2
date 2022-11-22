package com.zaus_app.moviefrumy_2.domain

import androidx.paging.*
import com.christopher_elias.features.movies.data.data_source.FilmsRemoteDataSource
import com.zaus_app.moviefrumy_2.utils.Converter
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 1

class FilmsPagingSource(
    private val interactor: Interactor
) : PagingSource<Int, Film>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = interactor.getFilmsFromApi(pageIndex)
            val films = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
            val nextKey =
                if (films.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = films,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

const val NETWORK_PAGE_SIZE = 25

internal class FilmsRemoteDataSourceImpl(
    private val interactor: Interactor
) : FilmsRemoteDataSource {

    override fun getMovies(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FilmsPagingSource(interactor)
            }
        ).flow
    }
}