package com.pollux.publickeypinning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pollux.publickeypinning.presenter.GitRepoPresenter;
import com.pollux.publickeypinning.presenter.GitRepoPresenterImpl;
import com.pollux.publickeypinning.view.GitRepoView;

public class PinningActivity extends AppCompatActivity implements GitRepoView {

    private static final String TAG = "PinningActivity";
    private GitRepoPresenter mGitRepoPresenter;
    private TextView mResponseView;
    private View mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinnig);

        mResponseView = (TextView) findViewById(R.id.response);
        mProgressBar = findViewById(R.id.progressBar);

        mGitRepoPresenter = new GitRepoPresenterImpl(this);

        findViewById(R.id.fetch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGitRepoPresenter.getRepo("pollux-");

            }
        });
    }

    /**
     * Callback from the Network request
     *
     * @param repos
     */
    @Override
    public void onRepoFetched(String repos) {
        mResponseView.setText(repos);

    }

    /**
     * Callback for Request submission
     */
    @Override
    public void onRequestSubmitted() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Callback for Request completion
     */
    @Override
    public void onRequestComplete() {

        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * Callback for network Errors
     *
     * @param message
     */
    @Override
    public void onError(String message) {
        Log.e(TAG, "Request failed with exception " + message);
        mProgressBar.setVisibility(View.GONE);

    }
}
