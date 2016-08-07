package com.pollux.publickeypinning.network.service;

import com.pollux.publickeypinning.network.api.GitHubApi;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Sree Kumar
 * <p/>
 * Copyright (C) 2016
 */
public class GitHubService {

    public static final String HOST = "api.github.com";
    public static final String PUBLIC_KEY_HASH = "sha256/6wJsqVDF8K19zxfLxV5DGRneLyzso9adVdUN/exDacw=";
    public static final String END_POINT = "https://api.github.com/";
    private static GitHubApi sService;

    /**
     * Configure the OkHttp client and initialize the retrofit.
     *
     * @return
     */
    public static GitHubApi getGitHubService() {

        if (sService == null) {

            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                    .add(HOST, PUBLIC_KEY_HASH)
                    .build();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .certificatePinner(certificatePinner)
                    .addNetworkInterceptor(interceptor)
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            sService = retrofit.create(GitHubApi.class);
        }
        return sService;
    }
}
