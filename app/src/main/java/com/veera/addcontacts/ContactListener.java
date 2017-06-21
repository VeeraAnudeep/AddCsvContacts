package com.veera.addcontacts;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by veera on 21/06/17.
 */

public interface ContactListener {

    void onContactFetch(List<User> contacts);
}
