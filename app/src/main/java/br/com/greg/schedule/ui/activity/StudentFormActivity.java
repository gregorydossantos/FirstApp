package br.com.greg.schedule.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.greg.schedule.DAO.StudentDAO;
import br.com.greg.schedule.R;
import br.com.greg.schedule.model.Student;

public class StudentFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        final StudentDAO dao = new StudentDAO();

        //Defining the local variables that will take the fields on the app
        final EditText nameField = findViewById(R.id.activity_student_form_name);
        final EditText phoneField = findViewById(R.id.activity_student_form_phone);
        final EditText emailField = findViewById(R.id.activity_student_form_email);

        //Create the method to save a new student
        Button save = findViewById(R.id.activity_student_form_save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString();
                String phone = phoneField.getText().toString();
                String email = emailField.getText().toString();

                Student student = new Student(name, phone, email);
                dao.save(student);

                finish();
            }
        });
    }
}
