package com.example.databindingsample.common.data.remote

import com.example.databindingsample.common.RequestResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<RequestResult<<Foo>> or Call<RequestResult<out Foo>>"
        }
        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != RequestResult::class.java) return null

        check(responseType is ParameterizedType) {
            "Response must be parameterized as RequestResult<Foo> or RequestResult<out Foo>"
        }

        val bodyType = getParameterUpperBound(0, responseType)

        return object : CallAdapter<Any, Call<RequestResult<Any>>> {
            override fun responseType(): Type = bodyType

            override fun adapt(call: Call<Any>): Call<RequestResult<Any>> = NetworkResponseCall(call)
        }
    }
}

private class NetworkResponseCall<T>(
    private val delegate: Call<T>
) : Call<RequestResult<T>> {

    override fun enqueue(callback: Callback<RequestResult<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(mapResponse(response))
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) =
                callback.onResponse(this@NetworkResponseCall, Response.success(mapException(t)))
        })
    }

    override fun clone(): Call<RequestResult<T>> = NetworkResponseCall(delegate.clone())

    override fun execute(): Response<RequestResult<T>> =
        throw UnsupportedOperationException("Synchronize network request is prohibited.")

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

}