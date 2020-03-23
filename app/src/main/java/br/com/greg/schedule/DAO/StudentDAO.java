package br.com.greg.schedule.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.greg.schedule.model.Student;

public class StudentDAO {

    private final static List<Student> students = new ArrayList<>();
    private static int counterId = 1;

    public void save(Student student) {
        student.setId(counterId);
        students.add(student);
        updateIds();
    }

    private void updateIds() {
        counterId++;
    }

    public void edit(Student student) {
        Student studentFound = searchStudentForId(student);

        if (studentFound != null) {
            int studentPosition = students.indexOf(studentFound);
            students.set(studentPosition, student);
        }
    }

    private Student searchStudentForId(Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId())
                return s;
        }
        return null;
    }

    public List<Student> allStudents() {
        return new ArrayList<>(students);
    }

    public void remove(Student student) {
        Student studentFound = searchStudentForId(student);
        if (studentFound != null)
            students.remove(studentFound);
    }
}
