package com.veera.addcontacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veera on 21/06/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<User> users;
    private List<User> selectedContacts;
    private SelectListener selectListener;

    public ContactAdapter(SelectListener selectListener) {
        selectedContacts = new ArrayList<>();
        this.selectListener = selectListener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view, selectListener);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        User user = users.get(position);
        holder.phone.setText(user.getPhone());
        holder.name.setText(user.getName());
        holder.checkBox.setChecked(user.isSelected());
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        TextView name;
        TextView phone;
        CheckBox checkBox;
        SelectListener selectListener;

        public ContactViewHolder(View itemView, SelectListener selectListener) {
            super(itemView);
            this.selectListener = selectListener;
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            checkBox.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            User user = users.get(getAdapterPosition());
            if (isChecked) {
                if (!selectedContacts.contains(user)) {
                    user.setSelected(true);
                    selectedContacts.add(user);
                }
            } else {
                user.setSelected(false);
                selectedContacts.remove(user);
            }
            selectListener.onSelection(selectedContacts);
        }
    }
}
