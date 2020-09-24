package br.com.greg.schedule.useful;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class DateUseful {

    @TypeConverter
    public Long toLong(Calendar value) {
        if (value != null)
            return value.getTimeInMillis();

        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long value) {
        Calendar currentTime = Calendar.getInstance();
        if (value != null)
            currentTime.setTimeInMillis(value);

        return currentTime;
    }
}
