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

public class StudentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        setTitle("Student details");

        int pos = getIntent().getIntExtra("student_pos", 0);

        Button btn = findViewById(R.id.edit_student_btn);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this,StudentEdit.class);
            intent.putExtra("student_pos", pos);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        int pos = getIntent().getIntExtra("student_pos", 0);

        TextView nameTv = findViewById(R.id.studentdetails_name_tv);
        TextView idTv = findViewById(R.id.studentdetails_id_tv);
        CheckBox cb = findViewById(R.id.studentdetails_cb);
        cb.setEnabled(false);

        List<Student> data = Model.instance().getAllStudents();
        Student st = data.get(pos);
        nameTv.setText(st.name);
        idTv.setText(st.id);
        cb.setChecked(st.cb);
    }
}