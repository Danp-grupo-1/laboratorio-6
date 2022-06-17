package dev.araozu.laboratorio6

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.araozu.laboratorio6.model.Candidato

class CandidatoSource: PagingSource<Int, Candidato>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Candidato> {
        val nextPage = params.key ?: 1
        val CandidatosList = Candidato.candidatos

        return LoadResult.Page(
            data = CandidatosList.map { candidato ->
                Candidato(
                    nombre = candidato.nombre,
                    partido = candidato.partido,
                    foto = candidato.foto,
                    biografia = candidato.biografia,
                    distrito = candidato.distrito
                )
            },
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = if (CandidatosList.isNotEmpty()) null else nextPage + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Candidato>): Int? {
        return state.anchorPosition
    }

}


