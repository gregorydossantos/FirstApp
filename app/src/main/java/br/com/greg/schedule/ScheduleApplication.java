package br.com.greg.schedule;

import android.app.Application;

import br.com.greg.schedule.DAO.StudentDAO;
import br.com.greg.schedule.model.Student;

public class ScheduleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createStudentsTests();
    }

    private void createStudentsTests() {
        StudentDAO dao = new StudentDAO();
        dao.save(new Student("Gregory", "(11) 1234-5678", "greg@gmail.com"));
        dao.save(new Student("Renato", "(11) 9012-3456", "renato@retricia.com.br"));
    }
}
