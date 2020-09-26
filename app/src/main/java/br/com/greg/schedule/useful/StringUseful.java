package br.com.greg.schedule.useful;

import android.widget.EditText;

public class StringUseful {

    public static boolean isNullOrEmpty(String value) {
        return value == null || !value.isEmpty();
    }
}
