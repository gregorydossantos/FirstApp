package br.com.greg.schedule.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.greg.schedule.database.dao.StudentDAO;
import br.com.greg.schedule.model.Student;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class ScheduleDatabase extends RoomDatabase {

    private static final String NAME_DB = "schedule.db";

    public abstract StudentDAO getRoomStudentDAO();

    public static ScheduleDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, ScheduleDatabase.class, NAME_DB)
                .allowMainThreadQueries()
                .build();
    }
}
