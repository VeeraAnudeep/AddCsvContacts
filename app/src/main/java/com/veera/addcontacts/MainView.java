package com.veera.addcontacts;

import com.veera.addcontacts.mvp.MVPBaseView;

import java.util.List;

/**
 * Created by veera on 20/06/17.
 */

public interface MainView extends MVPBaseView<Presenter> {

    void showContacts(List<User> contact);
}
