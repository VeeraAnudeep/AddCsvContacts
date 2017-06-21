package com.veera.addcontacts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.veera.addcontacts.marshmallowPermissions.PermissionResult;
import com.veera.addcontacts.marshmallowPermissions.PermissionUtils;
import com.veera.addcontacts.mvp.BaseActivity;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends BaseActivity<Presenter> implements MainView {

    private ProgressBar loadingView;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingView = (ProgressBar) findViewById(R.id.loading);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter = new ContactAdapter());
        checkPermissions();
    }

    public void checkPermissions() {
        askCompactPermissions(new String[]{PermissionUtils.Manifest_READ_CONTACTS
                , PermissionUtils.Manifest_WRITE_CONTACTS}, new PermissionResult() {
            @Override
            public void permissionGranted() {
                getPresenter().readCsv();
            }

            @Override
            public void permissionDenied() {
                finish();
            }
        });
    }

    @Override
    public Presenter getNewPresenter() {
        GetContacts getContacts = new GetContacts(this);
        AddContacts addContacts = new AddContacts(this);
        InputStream inputStream = getResources().openRawResource(R.raw.contacts);
        return new Presenter(inputStream, getContacts, addContacts);
    }

    @Override
    public void showContacts(List<User> contacts) {
        contactAdapter.setUsers(contacts);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }
}
