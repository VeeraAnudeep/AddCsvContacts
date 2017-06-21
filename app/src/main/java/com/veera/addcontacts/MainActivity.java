package com.veera.addcontacts;

import android.os.Bundle;

import com.veera.addcontacts.mvp.BaseActivity;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends BaseActivity<Presenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public Presenter getNewPresenter() {
        return new Presenter();
    }
}
