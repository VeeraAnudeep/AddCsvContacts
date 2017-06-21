package com.veera.addcontacts;

import com.veera.addcontacts.mvp.MVPBasePresenter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by veera on 20/06/17.
 */

public class Presenter extends MVPBasePresenter<MainView> {
    private InputStream inputStream;

    public Presenter(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void readCsv() {
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> contacts = csvFile.read();
        List<User> users = new ArrayList<>();
        for (int i = 1; i < contacts.size(); i++) {
            String[] contact = contacts.get(i);
            User user = new User();
            user.setName(contact[0]);
            user.setPhone(contact[1]);
            users.add(user);
        }
        getView().showContacts(users);
    }
}
