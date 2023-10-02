package kr.co.fastcampus.part4plus.movieapp.features.feed.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.GetFeedCategoryUseCase
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.GetMovieListUseCase
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.IGetFeedCategoryUseCase
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.IGetMovieListUseCase
import javax.inject.Singleton

/*
<강의 메모> 16:02 ch18
    <중요!>
    FeedUseCaseModule가 없으면
    UseCase에 대해서 DI를 설정 안했다는 에러가 나타난다.
    그래서 모듈을 만들어줘야 한다.

/Users/sangpillyoon/AndroidStudioProjects/MovieApp/app/build/tmp/hiltJavaCompileDebug/compileTransaction/annotation-output/kr/co/fastcampus/part4plus/movieapp/App_HiltComponents.java:135: error: [Dagger/MissingBinding] kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.IGetFeedCategoryUseCase cannot be provided without an @Provides-annotated method.
public abstract static class SingletonC implements FragmentGetContextFix.FragmentGetContextFixEntryPoint,
^
kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.IGetFeedCategoryUseCase is injected at
kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel.FeedViewModel(getFeedCategoryUseCase, …)
kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel.FeedViewModel is injected at
kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel.FeedViewModel_HiltModules.BindsModule.binds(arg0)
@dagger.hilt.android.internal.lifecycle.HiltViewModelMap java.util.Map<java.lang.String,javax.inject.Provider<androidx.lifecycle.ViewModel>> is requested at
dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ViewModelFactoriesEntryPoint.getHiltViewModelMap() [kr.co.fastcampus.part4plus.movieapp.App_HiltComponents.SingletonC → kr.co.fastcampus.part4plus.movieapp.App_HiltComponents.ActivityRetainedC → kr.co.fastcampus.part4plus.movieapp.App_HiltComponents.ViewModelC]
/Users/sangpillyoon/AndroidStudioProjects/MovieApp/app/build/tmp/hiltJavaCompileDebug/compileTransaction/annotation-output/kr/co/fastcampus/part4plus/movieapp/App_HiltComponents.java:135: error: [Dagger/MissingBinding] kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.IGetMovieListUseCase cannot be provided without an @Provides-annotated method.
public abstract static class SingletonC implements FragmentGetContextFix.FragmentGetContextFixEntryPoint,
^
kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.IGetMovieListUseCase is injected at
kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel.FeedViewModel(…, getMovieListUseCase)
kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel.FeedViewModel is injected at
kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel.FeedViewModel_HiltModules.BindsModule.binds(arg0)
@dagger.hilt.android.internal.lifecycle.HiltViewModelMap java.util.Map<java.lang.String,javax.inject.Provider<androidx.lifecycle.ViewModel>> is requested at
dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ViewModelFactoriesEntryPoint.getHiltViewModelMap() [kr.co.fastcampus.part4plus.movieapp.App_HiltComponents.SingletonC → kr.co.fastcampus.part4plus.movieapp.App_HiltComponents.ActivityRetainedC → kr.co.fastcampus.part4plus.movieapp.App_HiltComponents.ViewModelC]
*/
@Module
@InstallIn(SingletonComponent::class)
abstract class FeedUseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetFeedCategoryUseCase(getMovieListUseCase: GetFeedCategoryUseCase): IGetFeedCategoryUseCase

    @Singleton
    @Binds
    abstract fun bindGetMovieListUseCase(getMovieListUseCase: GetMovieListUseCase): IGetMovieListUseCase
}
