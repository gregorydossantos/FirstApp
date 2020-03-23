package br.com.greg.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.greg.schedule.DAO.StudentDAO;
import br.com.greg.schedule.R;

public class StudentListActivity extends AppCompatActivity {

    //Attributes
    public static final String TITLE_APPBAR = "Student List";
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the main layout for app
        setContentView(R.layout.activity_student_list);
        setTitle(TITLE_APPBAR);

        configFabNewStudent();

        /*
        //Creating an list of students
        List<String> student = new ArrayList<>(Arrays.asList("Greg", "Bia", "Pedro"));
        ListView studentList = findViewById(R.id.activity_student_list_listview);
        */
    }

    //Method to load the activity Student Form
    private void configFabNewStudent() {
        FloatingActionButton newStudent = findViewById(R.id.activity_student_list_fab_new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudentForm();
            }
        });
    }

    private void openStudentForm() {
        startActivity(new Intent(this, StudentFormActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configStudentList();
    }

    //Adding students on the list
    private void configStudentList() {
        ListView studentList = findViewById(R.id.activity_student_list_listview);
        studentList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                dao.allStudents()));
    }
}
