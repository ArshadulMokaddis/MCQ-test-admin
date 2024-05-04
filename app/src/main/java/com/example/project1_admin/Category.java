package com.example.project1_admin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Category extends AppCompatActivity {

    private Spinner spinner1, spinner2, spinner3;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Initialize Spinners and Button
//        spinner1 = findViewById(R.id.spinner1);
//        spinner2 = findViewById(R.id.spinner2);
//        spinner3 = findViewById(R.id.spinner3);
        nextButton = findViewById(R.id.next);

        // Create and set adapters for the Spinners
//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
//                R.array.dropdown1_options, R.layout.item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner1.setAdapter(adapter1);
//
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
//                R.array.dropdown2_options, R.layout.item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner2.setAdapter(adapter2);
//
//        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
//                R.array.dropdown3_options, R.layout.item);
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner3.setAdapter(adapter3);

        // Handle the next button click
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected items from Spinners
//                String selectedExmType = spinner1.getSelectedItem().toString();
//                String selectedExmSubject = spinner2.getSelectedItem().toString();
//                String selectedYear = spinner3.getSelectedItem().toString();

                // Create an Intent to start the SetQuestion activity
                Intent intent = new Intent(Category.this, SetQuestion.class);

                // Pass the selected data to the SetQuestion activity
//                intent.putExtra("exmType", selectedExmType);
//                intent.putExtra("exmSubject", selectedExmSubject);
//                intent.putExtra("year", selectedYear);
                startActivity(intent);
            }
        });
    }
}