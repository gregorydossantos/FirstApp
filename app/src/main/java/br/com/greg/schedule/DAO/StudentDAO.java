package br.com.greg.schedule.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.greg.schedule.model.Student;

public class StudentDAO {

    private final static List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> allStudents() {
        return new ArrayList<>(students);
    }
}
