package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etMunicipality;

    public static final String EXTRA_MUNICIPALITY =
            "com.example.practicalfive.MUNICIPALITY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        // Step 1: Find the EditText
        etMunicipality = findViewById(R.id.etMunicipality);

        // Step 2: Detect the Done key
        etMunicipality.setOnEditorActionListener(
                (v, actionId, event) -> {

                    if (actionId == EditorInfo.IME_ACTION_DONE) {

                        // Get user input
                        String input =
                                etMunicipality.getText().toString();

                        // Step 3: Validate input
                        TextProcessor processor = new TextProcessor();

                        String validName =
                                processor.processMunicipality(input);
                        // Debug: Check input and validation result
                        android.util.Log.d("INPUT_TEST", "Input = [" + input + "]");
                        android.util.Log.d("INPUT_TEST", "Result = [" + validName + "]");

                        // Step 4: Handle invalid input
                        if (validName == null) {

                            etMunicipality.setError(
                                    "Please enter Beijing, Shanghai, " +
                                            "Chongqing or Tianjin"
                            );

                            return true;
                        }

                        // Step 5: Create an explicit Intent
                        Intent intent = new Intent(
                                MainActivity.this,
                                Municipality.class
                        );

                        // Step 6: Pass the validated name
                        intent.putExtra(
                                EXTRA_MUNICIPALITY,
                                validName
                        );

                        // Step 7: Start the second Activity
                        startActivity(intent);

                        return true;
                    }

                    return false;
                });
    }
}