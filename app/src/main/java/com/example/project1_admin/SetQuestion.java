package com.example.project1_admin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SetQuestion extends AppCompatActivity {

    private Spinner spinnerExmType;
    private Spinner spinnerExmSubject;
    private Spinner spinnerYear;
    private EditText editTextQuestionText;
    private EditText editTextOptionA;
    private EditText editTextOptionB;
    private EditText editTextOptionC;
    private EditText editTextOptionD;
    private EditText editTextCorrectOption;
    private Button buttonInsertData;

    private ArrayAdapter<String> exmTypeAdapter;
    private ArrayAdapter<String> exmSubjectAdapter;
    private ArrayAdapter<String> yearAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_question);

        // Initialize UI elements
        spinnerExmType = findViewById(R.id.spinnerExmType);
        spinnerExmSubject = findViewById(R.id.spinnerExmSubject);
        spinnerYear = findViewById(R.id.spinnerYear);
        editTextQuestionText = findViewById(R.id.editTextQuestionText);
        editTextOptionA = findViewById(R.id.editTextOptionA);
        editTextOptionB = findViewById(R.id.editTextOptionB);
        editTextOptionC = findViewById(R.id.editTextOptionC);
        editTextOptionD = findViewById(R.id.editTextOptionD);
        editTextCorrectOption = findViewById(R.id.editTextCorrectOption);
        buttonInsertData = findViewById(R.id.buttonInsertData);

        // Set up Spinners
        exmTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getExmTypeData());
        spinnerExmType.setAdapter(exmTypeAdapter);

        exmSubjectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getExmSubjectData());
        spinnerExmSubject.setAdapter(exmSubjectAdapter);

        yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getYearData());
        spinnerYear.setAdapter(yearAdapter);

        // Set a click listener for the insert data button
        buttonInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected values from Spinners
                String exmType = spinnerExmType.getSelectedItem().toString();
                String exmSubject = spinnerExmSubject.getSelectedItem().toString();
                String year = spinnerYear.getSelectedItem().toString();
                String questionText = editTextQuestionText.getText().toString();
                String optionA = editTextOptionA.getText().toString();
                String optionB = editTextOptionB.getText().toString();
                String optionC = editTextOptionC.getText().toString();
                String optionD = editTextOptionD.getText().toString();
                String correctOption = editTextCorrectOption.getText().toString();

                // Call the insertData method in a background task
                new InsertDataTask().execute(exmType, exmSubject, year, questionText, optionA, optionB, optionC, optionD, correctOption);
            }
        });
    }

    private String[] getExmTypeData() {
        // Replace with your code to fetch exam types from your data source
        String[] examTypes = {"Jsc", "Ssc", "Hsc"};
        return examTypes;
    }

    private String[] getExmSubjectData() {
        // Replace with your code to fetch exam subjects from your data source
        String[] examSubjects = {"Bangla", "English", "Math"};
        return examSubjects;
    }

    private String[] getYearData() {
        // Replace with your code to fetch years from your data source
        String[] years = {"2022", "2023", "2026"};
        return years;
    }

    // AsyncTask to insert data in the background
    private class InsertDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // Call the insertData method with the provided parameters
            return insertData(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8]);
        }

        @Override
        protected void onPostExecute(String result) {
            // Display the result in a Toast
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }

    // Your insertData method remains the same
    public static String insertData(String exmType, String exmSubject, String year, String questionText,
                                    String optionA, String optionB, String optionC, String optionD, String correctOption) {
        try {
            URL url = new URL("https://3333manaman.000webhostapp.com/apps/questions.php"); // Replace with your server URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Create a map of data to send to the PHP script
            Map<String, String> data = new HashMap<>();
            data.put("exm_type", exmType);
            data.put("exm_subject", exmSubject);
            data.put("year", year);
            data.put("question_text", questionText);
            data.put("option_a", optionA);
            data.put("option_b", optionB);
            data.put("option_c", optionC);
            data.put("option_d", optionD);
            data.put("correct_option", correctOption);

            // Send the data as POST parameters
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : data.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(param.getKey()).append('=').append(param.getValue());
            }
            writer.write(postData.toString());
            writer.flush();
            writer.close();
            os.close();

            // Read the response from the PHP script
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            int bytesRead;
            char[] buffer = new char[4096];
            StringBuilder response = new StringBuilder();
            while ((bytesRead = isr.read(buffer)) != -1) {
                response.append(buffer, 0, bytesRead);
            }
            isr.close();
            is.close();
            conn.disconnect();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
