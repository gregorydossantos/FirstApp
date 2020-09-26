package br.com.greg.schedule.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.greg.schedule.R;
import br.com.greg.schedule.model.Student;

public class StudentListAdapter extends BaseAdapter {

    private final List<Student> students = new ArrayList<>();
    private final Context context;

    public StudentListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View createdView = createView(parent);

        //Get a student by your id
        Student studentFound = students.get(position);
        binds(createdView, studentFound);

        return createdView;
    }

    private void binds(View view, Student student) {
        TextView name = view.findViewById(R.id.item_student_name);
        name.setText(student.getFullName());
        TextView phone = view.findViewById(R.id.item_student_phone);
        phone.setText(student.getPhone());
        TextView cellPhone = view.findViewById(R.id.item_student_cellphone);
        cellPhone.setText(student.getCellPhone());
    }

    private View createView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
    }

    public void update(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
    }

    public void remove(Student student) {
        students.remove(student);
        notifyDataSetChanged();
    }
}
