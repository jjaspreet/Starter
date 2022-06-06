package com.example.starterCode.domain.usecase

import androidx.paging.PagingData
import com.example.starterCode.common.Resource
import com.example.starterCode.data.remote.dto.Result
import com.example.starterCode.data.remote.dto.toRickyAndMorty
import com.example.starterCode.domain.model.RickyAndMorty
import com.example.starterCode.domain.repository.RickyAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class GetRickyAndMortyUseCase @Inject constructor(
     val rickyAndMortyRepository: RickyAndMortyRepository
) {
/*
     operator fun invoke(): Flow<Resource<List<RickyAndMorty>>> = flow {
        try {
            emit(Resource.Loading())
            val rickyMortyResponse = rickyAndMortyRepository.getRickyAndMorty().toRickyAndMorty()
            emit(Resource.Success(rickyMortyResponse))
        } catch (httpException: HttpException) {
            emit(Resource.Error(httpException.localizedMessage ?: "An unexpected error occurred"))
        } catch (ioException: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }catch (e : Exception){
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
    */

     fun getResponse():Flow<PagingData<RickyAndMorty>> = flow{

         rickyAndMortyRepository.getRickyAndMorty();
    }
}


