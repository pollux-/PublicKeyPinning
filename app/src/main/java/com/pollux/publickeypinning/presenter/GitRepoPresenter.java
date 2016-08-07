package com.pollux.publickeypinning.presenter;

import android.support.annotation.NonNull;

/**
 * Created by Sree Kumar
 * <p/>
 * Copyright (C) 2016
 */
public interface GitRepoPresenter extends AbsPresenter {
    /**
     *
     * Fetch the public repository associated with user
     *
     * @param user user name of the github
     */
    void getRepo(@NonNull final String user);
}
