package br.com.greg.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.greg.schedule.DAO.StudentDAO;
import br.com.greg.schedule.R;
import br.com.greg.schedule.model.Student;

import static br.com.greg.schedule.ui.activity.ConstantActivities.STUDENT_KEY;

public class StudentListActivity extends AppCompatActivity {

    //Attributes
    public static final String TITLE_APPBAR = "Student List";

    private final StudentDAO dao = new StudentDAO();
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the main layout for app
        setContentView(R.layout.activity_student_list);
        setTitle(TITLE_APPBAR);
        configFabNewStudent();
        configStudentList();

        dao.save(new Student("Gregory", "1234-5678", "greg@gmail.com"));
        dao.save(new Student("Renato", "9012-3456", "renato@retricia.com.br"));

        /*
        //Creating an list of students
        List<String> student = new ArrayList<>(Arrays.asList("Greg", "Bia", "Pedro"));
        ListView studentList = findViewById(R.id.activity_student_list_listview);
        */
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
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student studentChosen = adapter.getItem(menuInfo.position);
            remove(studentChosen);
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
        updateStudents();
    }

    private void updateStudents() {
        adapter.clear();
        adapter.addAll(dao.allStudents());
    }

    //Adding students on the list
    private void configStudentList() {
        ListView studentList = findViewById(R.id.activity_student_list_listview);
        configAdapter(studentList);
        configListenerItemClicked(studentList);
        //configListernerItemLongClicked(studentList);
        registerForContextMenu(studentList);
    }

    /*
    private void configListernerItemLongClicked(ListView studentList) {
        studentList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student studentChosen = (Student) parent.getItemAtPosition(position);
                remove(studentChosen);
                return false;
            }
        });
    }
    */

    private void remove(Student student) {
        dao.remove(student);
        adapter.remove(student);
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

    private void configAdapter(ListView studentList) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1);
        studentList.setAdapter(adapter);
    }
}
