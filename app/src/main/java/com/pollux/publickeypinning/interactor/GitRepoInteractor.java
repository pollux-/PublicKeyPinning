package com.pollux.publickeypinning.interactor;

import com.pollux.publickeypinning.model.Repo;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Sree Kumar
 * <p/>
 * Copyright (C) 2016
 */
public interface GitRepoInteractor {
    /**
     * Fetch the repositories using web service.
     *
     * @param user github user name
     * @return list of repositories
     */
    Call<List<Repo>> getRepo(final String user);
}
