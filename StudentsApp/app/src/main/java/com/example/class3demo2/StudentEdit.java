package com.example.class3demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;

import java.util.List;

public class StudentEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        setTitle("Edit student");

        int pos = getIntent().getIntExtra("student_pos", 0);
        List<Student> data = Model.instance().getAllStudents();
        Student st = data.get(pos);

        TextView nameTv = findViewById(R.id.editstudent_name_et);
        TextView idTv = findViewById(R.id.editstudent_id_et);
        CheckBox cb = findViewById(R.id.editstudent_checkbox);

        nameTv.setText(st.name);
        idTv.setText(st.id);
        cb.setChecked(st.cb);

        Button saveBtn = findViewById(R.id.editstudent_save_btn);
        Button cancelBtn = findViewById(R.id.editstudent_cancel_btn);
        Button deleteBtn = findViewById(R.id.editstudent_delete_btn);

        saveBtn.setOnClickListener(view -> {
            String name = nameTv.getText().toString();
            String id = idTv.getText().toString();
            Boolean checked = cb.isChecked();
            st.name = name;
            st.id = id;
            st.cb = checked;
            finish();
        });

        deleteBtn.setOnClickListener(view -> {
            Model.instance().removeStudent(pos);
            Intent intent = new Intent(this,StudentRecyclerList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}