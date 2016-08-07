package com.pollux.publickeypinning.presenter;

import android.support.annotation.NonNull;

import com.pollux.publickeypinning.interactor.GitRepoInteractorImpl;
import com.pollux.publickeypinning.model.Repo;
import com.pollux.publickeypinning.view.GitRepoView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sree Kumar
 * <p/>
 * Copyright (C) 2016
 */
public class GitRepoPresenterImpl implements GitRepoPresenter {

    private final GitRepoInteractorImpl mGitRepoInteractor;
    private GitRepoView mView;

    public GitRepoPresenterImpl(final GitRepoView gitRepoView) {
        mGitRepoInteractor = new GitRepoInteractorImpl();
        mView = gitRepoView;

    }

    /**
     * Fetch the public repository associated with user
     *
     * @param user user name of the github
     */
    @Override
    public void getRepo(@NonNull String user) {
        mView.onRequestSubmitted();
        mGitRepoInteractor.getRepo(user).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (mView != null) {
                    mView.onRequestComplete();
                    mView.onRepoFetched(response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                if (mView != null)
                    mView.onError(t.getMessage());

            }
        });

    }

    /**
     * Clean up the references
     */
    @Override
    public void destroy() {
        mView = null;
    }
}
