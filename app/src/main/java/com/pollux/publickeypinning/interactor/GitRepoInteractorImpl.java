package com.pollux.publickeypinning.interactor;

import com.pollux.publickeypinning.model.Repo;
import com.pollux.publickeypinning.network.service.GitHubService;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Sree Kumar
 * <p/>
 * Copyright (C) 2016
 */
public class GitRepoInteractorImpl implements GitRepoInteractor {
    /**
     * Fetch the repositories using web service.
     *
     * @param user github user name
     * @return list of repositories
     */
    @Override
    public Call<List<Repo>> getRepo(String user) {
        return GitHubService.getGitHubService().listRepos(user);
    }
}
