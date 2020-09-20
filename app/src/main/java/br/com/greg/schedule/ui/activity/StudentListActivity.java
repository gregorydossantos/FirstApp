package br.com.greg.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.greg.schedule.R;
import br.com.greg.schedule.model.Student;
import br.com.greg.schedule.ui.StudentListView;

import static br.com.greg.schedule.ui.activity.ConstantActivities.STUDENT_KEY;

public class StudentListActivity extends AppCompatActivity {

    //Attributes
    public static final String TITLE_APPBAR = "Student List";
    private StudentListView studentListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the main layout for app
        setContentView(R.layout.activity_student_list);
        setTitle(TITLE_APPBAR);
        studentListView = new StudentListView(this);
        configFabNewStudent();
        configStudentList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_student_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_student_list_menu_remove) {
            studentListView.confirmRemove(item);
        }
        return super.onContextItemSelected(item);
    }

    //Method to load the activity Student Form
    private void configFabNewStudent() {
        FloatingActionButton newStudent = findViewById(R.id.activity_student_list_fab_new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudentFormToInsert();
            }
        });
    }

    private void openStudentFormToInsert() {
        startActivity(new Intent(this, StudentFormActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentListView.updateStudents();
    }

    //Adding students on the list
    private void configStudentList() {
        ListView studentList = findViewById(R.id.activity_student_list_listview);
        studentListView.configAdapter(studentList);
        configListenerItemClicked(studentList);
        registerForContextMenu(studentList);
    }

    private void configListenerItemClicked(ListView studentList) {
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student studentChosen = (Student) parent.getItemAtPosition(position);
                openStudentFormToEdit(studentChosen);
            }
        });
    }

    //Method for transferring data
    private void openStudentFormToEdit(Student student) {
        Intent goStudentForm = new Intent(StudentListActivity.this, StudentFormActivity.class);
        goStudentForm.putExtra(STUDENT_KEY, student);
        startActivity(goStudentForm);
    }
}
