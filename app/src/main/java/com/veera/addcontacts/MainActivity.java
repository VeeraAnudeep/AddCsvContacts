package com.veera.addcontacts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.veera.addcontacts.mvp.BaseActivity;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends BaseActivity<Presenter> implements MainView {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter = new ContactAdapter());
        getPresenter().readCsv();
    }

    @Override
    public Presenter getNewPresenter() {
        InputStream inputStream = getResources().openRawResource(R.raw.contacts);
        return new Presenter(inputStream);
    }

    @Override
    public void showContacts(List<User> contacts) {
        contactAdapter.setUsers(contacts);
    }
}
