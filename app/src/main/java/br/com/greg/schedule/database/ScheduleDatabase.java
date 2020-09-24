package br.com.greg.schedule.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.greg.schedule.database.dao.StudentDAO;
import br.com.greg.schedule.model.Student;
import br.com.greg.schedule.useful.DateUseful;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
@TypeConverters({DateUseful.class})
public abstract class ScheduleDatabase extends RoomDatabase {

    private static final String NAME_DB = "schedule.db";

    public abstract StudentDAO getRoomStudentDAO();

    public static ScheduleDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, ScheduleDatabase.class, NAME_DB)
                .allowMainThreadQueries()
                .build();
    }
}
