package com.example.databindingsample.home.usecase

import com.example.databindingsample.common.RequestException
import com.example.databindingsample.common.RequestResult
import com.example.databindingsample.common.di.IoDispatcher
import com.example.databindingsample.home.repository.ImageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetImagesUseCaseImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repository: ImageRepository,
) : GetImagesUseCase {

    override suspend operator fun invoke(): GetImagesResult = withContext(dispatcher) {
        when (val result = repository.getImages()) {
            is RequestResult.Success -> {
                val body = result.data.body()
                    ?: return@withContext GetImagesResult.ServerErrorReceived(RequestException("No data received from the server"))
                GetImagesResult.ImagesReceived(body.hits.map(GetImagesMapper::map))
            }

            is RequestResult.Error -> {
                GetImagesResult.ServerErrorReceived(result.e)
            }

            is RequestResult.Failure -> {
                GetImagesResult.RequestFailure(result.e)
            }
        }
    }
}








