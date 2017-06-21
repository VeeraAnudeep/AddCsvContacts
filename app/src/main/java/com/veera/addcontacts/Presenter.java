package com.veera.addcontacts;

import com.veera.addcontacts.mvp.MVPBasePresenter;

import java.io.InputStream;
import java.util.List;

/**
 * Created by veera on 20/06/17.
 */

public class Presenter extends MVPBasePresenter<MainView> implements ContactListener {
    private static boolean contactsAdded = false;
    private InputStream inputStream;
    private GetContacts getContacts;
    private AddContacts addContacts;


    public Presenter(InputStream inputStream, GetContacts getContacts, AddContacts addContacts) {
        this.inputStream = inputStream;
        this.getContacts = getContacts;
        this.addContacts = addContacts;
    }

    public void getContacts() {
        getContacts.setListener(this);
        getContacts.execute();
    }

    public void readCsv() {
        getView().showLoading();
        if (!contactsAdded) {
            CSVFile csvFile = new CSVFile(inputStream);
            List<String[]> contacts = csvFile.read();
            for (int i = 1; i < contacts.size(); i++) {
                String[] contact = contacts.get(i);
                addContacts.addContact(contact[1], contact[0]);
            }
            contactsAdded = true;
        }
        getContacts();
    }

    public void onSelection(List<User> selectedContacts) {
        String selection = "";
        for (User user : selectedContacts) {
            selection = selection + user.getName() + ", ";
        }
        getView().showSelectedContacts(selection);
    }

    @Override
    public void onContactFetch(List<User> contacts) {
        if (isViewAlive()) {
            getView().hideLoading();
            getView().showContacts(contacts);
        }
    }
}
