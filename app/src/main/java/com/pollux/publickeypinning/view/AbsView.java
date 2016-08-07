package com.pollux.publickeypinning.view;

/**
 * Created by Sree Kumar
 * <p/>
 * Copyright (C) 2016
 */


/**
 * Common callback for the Network requests.
 */
public interface AbsView {
    /**
     * Callback for Request submission
     */
    void onRequestSubmitted();

    /**
     * Callback for Request completion
     */
    void onRequestComplete();

    /**
     * Callback for network Errors
     * @param message
     */
    void onError(final String message);
}
