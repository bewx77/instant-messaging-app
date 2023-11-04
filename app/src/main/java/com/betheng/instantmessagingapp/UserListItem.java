package com.betheng.instantmessagingapp;

public class UserListItem {
        private int imageId;
        private String text;

        public UserListItem(int imageResId, String text) {
            this.imageId = imageResId;
            this.text = text;
        }

        public int getImageId() {
            return imageId;
        }

        public String getText() {
            return text;
        }
}
