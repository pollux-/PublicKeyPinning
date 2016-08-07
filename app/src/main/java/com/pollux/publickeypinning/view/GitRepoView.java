package com.pollux.publickeypinning.view;

/**
 * Created by Sree Kumar
 * <p/>
 * Copyright (C) 2016
 */
public interface GitRepoView extends AbsView {

    /**
     * Callback from the Network request
     * @param repos
     */
    void onRepoFetched(final String repos);

}
