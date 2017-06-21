package com.veera.addcontacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by veera on 21/06/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<User> users;

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        User user = users.get(position);
        holder.phone.setText(user.getPhone());
        holder.name.setText(user.getName());
        holder.checkBox.setSelected(user.isSelected());
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phone;
        CheckBox checkBox;

        public ContactViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
