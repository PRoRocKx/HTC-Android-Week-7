package com.example.evgeniy.week7.t2.dummy;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<>();


    private static final int COUNT = 10;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
    }

    private static String getRandNum(Random random, int bound, int count) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append(random.nextInt(bound));
        }
        return stringBuilder.toString();
    }

    private static DummyItem createDummyItem(int position) {
        String name = "Name " + String.valueOf(position);
        String lname = "LastName " + String.valueOf(position);
        Random random = new Random();
        String phone = "+7 (9" + getRandNum(random, 9, 2) + ") " + getRandNum(random, 9, 3) + " " + getRandNum(random, 9, 2) + " " + getRandNum(random, 9, 2);
        int color = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        return new DummyItem(name, lname, phone, color);
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        private final String name;
        private final String lname;
        private final String phone;
        private final int color;
        private boolean checked = false;

        DummyItem(String name, String lname, String phone, int color) {
            this.name = name;
            this.lname = lname;
            this.phone = phone;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public String getLname() {
            return lname;
        }

        public String getPhone() {
            return phone;
        }

        public int getColor() {
            return color;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
