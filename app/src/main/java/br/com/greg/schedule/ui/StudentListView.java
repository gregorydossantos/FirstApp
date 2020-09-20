package br.com.greg.schedule.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.greg.schedule.database.ScheduleDatabase;
import br.com.greg.schedule.database.dao.StudentDAO;
import br.com.greg.schedule.model.Student;
import br.com.greg.schedule.ui.adapter.StudentListAdapter;

public class StudentListView {

    //Attributes
    private final StudentListAdapter adapter;
    private final StudentDAO dao;
    private final Context context;

    public StudentListView(Context context) {
        this.context = context;
        this.adapter = new StudentListAdapter(this.context);
        this.dao = ScheduleDatabase.getInstance(context).getRoomStudentDAO();
    }

    public void confirmRemove(final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removing Student")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Student studentChosen = (Student) adapter.getItem(menuInfo.position);
                        remove(studentChosen);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void updateStudents() {
        adapter.update(dao.allStudents());
    }

    private void remove(Student student) {
        dao.remove(student);
        adapter.remove(student);
    }

    //Initialization
    public void configAdapter(final ListView studentList) {
        studentList.setAdapter(adapter);
    }
}
