package bkm.com.propellerassessment.networkcalls;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import bkm.com.propellerassessment.R;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    @SuppressLint("StaticFieldLeak")
    private static ApiClient instance;
    private static volatile Retrofit retrofit = null;
    private Context context;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public ApiClient(Context context) {
        this.context = context;
    }

    public static synchronized ApiClient with(Context context) {
        if (instance == null) {
            synchronized (ApiClient.class) {
                if (instance == null) {
                    instance = new ApiClient(context);
                }
            }
        }
        return instance;
    }

    public Retrofit getClient() {
        if (retrofit == null) {
            initRetrofit();
        }
        return retrofit;
    }

    private void initRetrofit() {
        File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        if (!httpClient.interceptors().isEmpty()) {
            httpClient.interceptors().clear();
        }

        httpClient.connectTimeout(5, TimeUnit.MINUTES);
        httpClient.writeTimeout(5, TimeUnit.MINUTES);
        httpClient.readTimeout(5, TimeUnit.MINUTES);
        httpClient.cache(cache);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString(R.string.root_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
    }
}
