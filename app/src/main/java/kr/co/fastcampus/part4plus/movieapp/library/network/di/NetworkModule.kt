package kr.co.fastcampus.part4plus.movieapp.library.network.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.fastcampus.part4plus.movieapp.BuildConfig
import kr.co.fastcampus.part4plus.movieapp.library.network.api.ApiService
import kr.co.fastcampus.part4plus.movieapp.library.network.retrofit.NetworkRequestFactory
import kr.co.fastcampus.part4plus.movieapp.library.network.retrofit.NetworkRequestFactoryImpl
import kr.co.fastcampus.part4plus.movieapp.library.network.retrofit.StringConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton

/**
 * <강의 메모> 20:11
 * 보통 di 패키지에는 Hilt에서 사용되는
 * 규칙들을 만들어주는 class들을 넣어준다.
 * 명명은 일반적으로 XXXXModule로 지워준다.
 */

/**
 * @InstallIn --> Module에서 사용되는 Dependency Injection이
 * 어떠한 scope로 사용되는지 지정해주는 것
 *
 * scope --> 어디에서 접근 가능한지
 *
 * @InstallIn(SingletonComponent::class) --> 전체 모듈에서 사용가능한 컴포넌트 의미
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(logBaseUrl(baseUrl = "https://kgeun.github.io/assets/fastcampus_android_compose/movie/"))
            .addConverterFactory(StringConverterFactory(gson))
            /**
             * .addConverterFactory(StringConverterFactory(gson)) --> GSON을 통해서
             * JSON 파싱하겠다는 의미
             */
            .build()
    }

    private fun logBaseUrl(baseUrl: String): String {
        Timber.d("baseUrl $baseUrl")
        return baseUrl
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    /**
     * <강의 메모> 22:26
     * 항상 di를 사용할때는
     * Interface code와 그 Interface를 사용하는 구현체를 붙혀서
     * Interface만 붙히면 자동으로 그 구현체가 따라갈 수 있게 한다.
     */
    @Provides
    @Singleton
    fun bindNetworkRequestFactory(networkRequestFactory: NetworkRequestFactoryImpl): NetworkRequestFactory =
        networkRequestFactory

}