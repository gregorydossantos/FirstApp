package br.com.greg.schedule.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.greg.schedule.model.Student;

@Dao
public interface StudentDAO {

    @Insert
    void save(Student student);

    @Query("select * from Student")
    List<Student> allStudents();

    @Delete
    void remove(Student student);

    @Update
    void edit(Student student);
}
