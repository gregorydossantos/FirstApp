package br.com.greg.schedule.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.greg.schedule.DAO.StudentDAO;
import br.com.greg.schedule.R;
import br.com.greg.schedule.model.Student;

public class StudentFormActivity extends AppCompatActivity {

    //Attributes
    public static final String TITLE_APPBAR = "New student";
    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        setTitle(TITLE_APPBAR);

        bootFields();
        configSaveButton();
    }

    //Method to create and configure the save button
    private void configSaveButton() {
        Button save = findViewById(R.id.activity_student_form_save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = createStudent();
                save(student);
            }
        });
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
    private Student createStudent() {
        String name = nameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();

        return new Student(name, phone, email);
    }
}
