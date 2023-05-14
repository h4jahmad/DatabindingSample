package com.example.databindingsample.home.usecase

interface GetImagesUseCase {
    suspend operator fun invoke(): GetImagesResult
}