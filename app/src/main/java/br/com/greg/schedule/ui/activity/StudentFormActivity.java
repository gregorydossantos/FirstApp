package br.com.greg.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.greg.schedule.DAO.StudentDAO;
import br.com.greg.schedule.R;
import br.com.greg.schedule.model.Student;

import static br.com.greg.schedule.ui.activity.ConstantActivities.STUDENT_KEY;

public class StudentFormActivity extends AppCompatActivity {

    //Attributes
    private static final String TITLE_APPBAR_NEW_STUDENT = "New student";
    private static final String TITLE_APPBAR_EDIT_STUDENT = "Edit student";
    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;
    private Student student;
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        bootFields();
        //configSaveButton();
        loadStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_student_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.activity_student_form_menu_save)
            endsForm();

        return super.onOptionsItemSelected(item);
    }

    private void loadStudent() {
        Intent datas = getIntent();
        if (datas.hasExtra(STUDENT_KEY)) {
            setTitle(TITLE_APPBAR_EDIT_STUDENT);
            student = (Student) datas.getSerializableExtra(STUDENT_KEY);
            fillsFields();
        } else {
            setTitle(TITLE_APPBAR_NEW_STUDENT);
            student = new Student();
        }
    }

    private void fillsFields() {
        nameField.setText(student.getName());
        phoneField.setText(student.getPhone());
        emailField.setText(student.getEmail());
    }

    //Method to create and configure the save button
    /*
    private void configSaveButton() {
        Button save = findViewById(R.id.activity_student_form_save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Student student = createStudent();
                save(student);*//*
                endsForm();
            }
        });
    }
    */


    private void endsForm() {
        createStudent();

        if (student.hasId())
            dao.edit(student);
        else
            dao.save(student);

        finish();
    }

    //Defining the local variables that will take the fields on the app
    private void bootFields() {
        nameField = findViewById(R.id.activity_student_form_name);
        phoneField = findViewById(R.id.activity_student_form_phone);
        emailField = findViewById(R.id.activity_student_form_email);
    }

    //Method to save a new student
    private void save(Student student) {
        dao.save(student);
        finish();
    }

    //Method to create a new student
    private void createStudent() {
        String name = nameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();

        student.setName(name);
        student.setPhone(phone);
        student.setEmail(email);
    }
}
