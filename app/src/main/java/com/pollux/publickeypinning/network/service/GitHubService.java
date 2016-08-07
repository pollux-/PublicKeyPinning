package com.pollux.publickeypinning.network.service;

import android.util.Log;

import com.pollux.publickeypinning.network.api.GitHubApi;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import okhttp3.CertificatePinner;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Sree Kumar
 * <p/>
 * Copyright (C) 2016
 */
public class GitHubService {
    private static final String TAG = "GitHubService";
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

            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

            /**
             *
             * Enable the Network Logs
             *
             */

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            /**
             * Set the SHA256 hash obtained from the Certificate
             *
             * Run this command to get the SHA256 fingerprint
             * openssl s_client -connect api.github.com:443 | openssl x509 -pubkey -noout | openssl rsa -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64
             *
             *
             */
            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                    .add(HOST, PUBLIC_KEY_HASH)
                    .build();


            /**
             * Force the connection to use only TLS v.1.2 avoiding the fallback to older version to avoid vulnerabilities
             *
             */
            final ConnectionSpec.Builder connectionSpec =
                    new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS);
            connectionSpec.tlsVersions(TlsVersion.TLS_1_2).build();

            /**
             * Enable TLS specific version V.1.2
             * Issue Details : https://github.com/square/okhttp/issues/1934
             */

            TLSSocketFactory tlsSocketFactory;

            try {
                tlsSocketFactory = new TLSSocketFactory();
                httpBuilder.sslSocketFactory(tlsSocketFactory, tlsSocketFactory.systemDefaultTrustManager());
            } catch (KeyManagementException | NoSuchAlgorithmException e) {
                    Log.d(TAG, "Failed to create Socket connection ");
                    e.printStackTrace();
                }


            final OkHttpClient client = httpBuilder.certificatePinner(certificatePinner)
                    .addNetworkInterceptor(interceptor)
                    .connectionSpecs(Collections.singletonList(connectionSpec.build()))
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            sService = retrofit.create(GitHubApi.class);
        }
        return sService;
    }
}
