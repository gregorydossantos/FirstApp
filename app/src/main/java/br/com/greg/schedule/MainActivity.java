package br.com.greg.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the main layout for app
        setContentView(R.layout.activity_main);
        setTitle("Student List");

        //Creating an list of students
        List<String> student = new ArrayList<>(Arrays.asList("Greg", "Bia", "Pedro"));
        ListView studentList = findViewById(R.id.activity_main_student_list);

        //Adding students on the list
        studentList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                student));
    }
}
