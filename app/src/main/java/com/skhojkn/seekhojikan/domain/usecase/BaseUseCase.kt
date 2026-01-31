package com.skhojkn.seekhojikan.domain.usecase

abstract class BaseUseCase<in P, out R>  {
    abstract suspend operator fun invoke(params: P?): R
}