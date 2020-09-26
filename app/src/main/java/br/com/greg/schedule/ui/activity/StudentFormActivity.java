package br.com.greg.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import br.com.greg.schedule.R;
import br.com.greg.schedule.database.ScheduleDatabase;
import br.com.greg.schedule.database.dao.StudentDAO;
import br.com.greg.schedule.model.Student;
import br.com.greg.schedule.useful.StringUseful;

import static br.com.greg.schedule.ui.activity.ConstantActivities.STUDENT_KEY;

public class StudentFormActivity extends AppCompatActivity {

    //Attributes
    private static final String TITLE_APPBAR_NEW_STUDENT = "New student";
    private static final String TITLE_APPBAR_EDIT_STUDENT = "Edit student";
    private EditText nameField;
    private EditText lastNameField;
    private EditText phoneField;
    private EditText cellPhoneField;
    private EditText emailField;
    private Student student;
    private StudentDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        ScheduleDatabase database = ScheduleDatabase.getInstance(this);
        dao = database.getRoomStudentDAO();
        bootFields();
        loadStudent();
        setPhoneMask();
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
            saveStudent();

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
        lastNameField.setText(student.getLastname());
        phoneField.setText(student.getPhone());
        cellPhoneField.setText(student.getCellPhone());
        emailField.setText(student.getEmail());
    }

    private void saveStudent() {
        //TODO check the name and cell fields, and only allow creating a student when both are filled
        //Today there isn't check and allows to create an empty student in db
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
        lastNameField = findViewById(R.id.activity_student_form_lastname);
        phoneField = findViewById(R.id.activity_student_form_phone);
        cellPhoneField = findViewById(R.id.activity_student_form_cellphone);
        emailField = findViewById(R.id.activity_student_form_email);
    }

    //Method to create a new student
    private void createStudent() {
        String name = nameField.getText().toString();
        String lastname = lastNameField.getText().toString();
        String phone = phoneField.getText().toString();
        String cellPhone = cellPhoneField.getText().toString();
        String email = emailField.getText().toString();

        student.setName(name);
        student.setLastname(lastname);
        student.setPhone(phone);
        student.setCellPhone(cellPhone);
        student.setEmail(email);
    }

    private void setPhoneMask() {
        //Mask to phone
        SimpleMaskFormatter phoneSMF = new SimpleMaskFormatter("(NN) NNNN-NNNN");
        MaskTextWatcher phoneMTW = new MaskTextWatcher(phoneField, phoneSMF);
        phoneField.addTextChangedListener(phoneMTW);

        //Mask to cell phone
        SimpleMaskFormatter cellPhoneSMF = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher cellPhoneMTW = new MaskTextWatcher(cellPhoneField, cellPhoneSMF);
        cellPhoneField.addTextChangedListener(cellPhoneMTW);
    }
}
