package com.pollux.publickeypinning.network.api;

import com.pollux.publickeypinning.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {
  @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);
}