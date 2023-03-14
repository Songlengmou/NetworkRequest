package com.anningtex.networkrequest.api;

import com.anningtex.networkrequest.converter.StringConverterFactory;
import com.anningtex.networkrequest.converter.LocalCookieJar;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Song
 */
public class ApiManager {
    /**
     * 返回json数据(是对象）时请求所用的对象
     */
    private ApiService jsonApiService;

    /**
     * 返回json是string数据时请求所用的对象
     */
    private ApiService strApiService;

    /**
     * 访问HttpMethods时创建单例
     */
    private static final class SingletonHolder {
        private static final ApiManager INSTANCE = new ApiManager();
    }

    /**
     * 获取单例
     */
    public static ApiManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ApiManager() {
        jsonApiService = getJsonServiceInstance();
        strApiService = getStrServiceInstance();
    }

    public ApiService getJsonApiService() {
        return jsonApiService;
    }

    public ApiService getStrApiService() {
        return strApiService;
    }

    private ApiService getJsonServiceInstance() {
        if (null == jsonApiService) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
                Request request = chain.request().newBuilder().build();
                return chain.proceed(request);
            }).cookieJar(new LocalCookieJar()).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
            Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(ApiConstants.Companion.getBaseUrl()).
                    //json解析
//                    addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
                    //gson解析
                            addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
            jsonApiService = retrofit.create(ApiService.class);
        }
        return jsonApiService;
    }

    private ApiService getStrServiceInstance() {
        if (null == strApiService) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
                Request request = chain.request().newBuilder().build();
                return chain.proceed(request);
            }).cookieJar(new LocalCookieJar()).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
            Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(ApiConstants.Companion.getBaseUrl()).addConverterFactory(StringConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
            strApiService = retrofit.create(ApiService.class);
        }
        return strApiService;
    }
}
