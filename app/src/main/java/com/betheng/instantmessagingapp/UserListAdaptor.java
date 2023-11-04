package com.betheng.instantmessagingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdaptor extends BaseAdapter {
        private Context context;
        private ArrayList<UserListItem> data;

        public UserListAdaptor(Context context, ArrayList<UserListItem> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);
                //LayoutInflater use to create instances of View objects from XML layouts
                //attachToRoot = false --> not attached to parent View first, will be attached when convertView is returned
                // inflated view is held in the convertView variable first, which is then customized with data and returned in the getView method of the custom adapter
            }

            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView textView = convertView.findViewById(R.id.textView);

            UserListItem item = data.get(position);
            imageView.setImageResource(item.getImageId());
            textView.setText(item.getText());

            return convertView;
        }
}
