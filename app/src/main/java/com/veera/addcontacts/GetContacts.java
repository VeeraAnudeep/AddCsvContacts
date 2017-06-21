package com.veera.addcontacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Created by veera on 21/06/16.
 */


public class GetContacts extends AsyncTask<String, Void, Void> {

    private Context context;
    private Hashtable<String, User> htContactDetails;
    private ContactListener contactListener;

    public GetContacts(Context context) {
        this.context = context;
    }

    public void setListener(ContactListener contactListener) {
        this.contactListener = contactListener;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (htContactDetails != null) {
            List<User> phoneContacts = new ArrayList<>();
            Set<String> keys = htContactDetails.keySet();
            for (String key : keys) {
                phoneContacts.add(htContactDetails.get(key));
            }
            contactListener.onContactFetch(phoneContacts);
        }
    }

    @Override
    protected Void doInBackground(String... params) {
        getContacts();
        return null;
    }


    private void getContacts() {

        ContentResolver contactResolver = context.getContentResolver();

        String[] projectionAry = new String[]{ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.HAS_PHONE_NUMBER};

        Cursor cursor = contactResolver.query(ContactsContract.Contacts.CONTENT_URI, projectionAry, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        int length;

        if (cursor != null && (length = cursor.getCount()) > 0) {

            htContactDetails = new Hashtable<>(length);
            while (cursor.moveToNext()) {

                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                    Cursor pCur = contactResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ", new String[]{contactId}, null);

                    if (pCur != null) {
                        while (pCur.moveToNext()) {
                            String phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            phone = getValidPhoneNumber(phone);
                            if (phone == null) {
                                continue;
                            }
                            User contact = new User(displayName, phone, photoUri);
                            htContactDetails.put(phone, contact);
                        }
                        pCur.close();
                    }
                }
            }
            cursor.close();
        }
    }


    private String getValidPhoneNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            return null;
        }
        String digits_only = number.replaceAll("[^0-9]", "");
        if (digits_only.length() >= 10)
            return digits_only.substring(digits_only.length() - 10, digits_only.length());
        return null;
    }


}