package com.example.dev.simpledbapplication;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editTextID, editTextName;
    private Button btnLoadData, btnAdd, btnFind, btnDelete, btnUpdate;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextID = findViewById(R.id.studentid);
        editTextName = findViewById(R.id.studentName);
        btnLoadData = findViewById(R.id.btnLoad);
        btnAdd = findViewById(R.id.btnAdd);
        btnFind = findViewById(R.id.btnFind);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        textView = findViewById(R.id.textView);
    }

    public void loadStudents(View v) {
        StudentDbHandler dbHandler = new StudentDbHandler(this, null, null, 1);
        textView.setText(dbHandler.loadHandler());
        editTextID.setText("");
        editTextName.setText("");

    }

    public void addStudent(View v) {
        StudentDbHandler dbHandler = new StudentDbHandler(this, null, null, 1);
        int id = Integer.parseInt(editTextID.getText().toString());
        String name = editTextName.getText().toString();
        Student student = new Student(id, name);
        dbHandler.addHandler(student);
        editTextID.setText("");
        editTextName.setText("");

    }

    public void findStudent(View v) {
        StudentDbHandler dbHandler = new StudentDbHandler(this, null, null, 1);
        Student student = dbHandler.findHandler(editTextName.getText().toString());
        if (student != null) {
            textView.setText(String.valueOf(student.getStudentID()) + " " + student.getStudentName() + System.getProperty("line.separator"));
            editTextID.setText("");
            editTextName.setText("");
        } else {
            editTextID.setText("");
            editTextName.setText("");
            textView.setText("No match found");
        }
    }

    public void deleteStudent(View v) {
        StudentDbHandler dbHandler = new StudentDbHandler(this, null,null,1);
        boolean result = dbHandler.deleteHandler(Integer.parseInt(editTextID.getText().toString()));
        if(result){
            textView.setText("Record deleted");
            editTextID.setText("");
            editTextName.setText("");
        }else{
            textView.setText("No Match Found");
        }
    }

    public void updateStudent(View v) {
        StudentDbHandler dbHandler = new StudentDbHandler(this, null,null,1);
        boolean result = dbHandler.updateHandler(Integer.parseInt(editTextID.getText().toString()),editTextName.getText().toString());
        if(result){
            textView.setText("Record Update");
            editTextID.setText("");
            editTextName.setText("");
        }else{
            textView.setText("No Match Found");
        }

    }
}
