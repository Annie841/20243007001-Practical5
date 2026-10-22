package com.example.practicalfive;

import android.os.Bundle;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Municipality extends AppCompatActivity {

    private TextView tvMunicipalityName;
    private TextView tvMunicipalityInfo;
    private ImageView ivMunicipality;
    private Button btnVisitSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_municipality);

        tvMunicipalityName = findViewById(R.id.tvMunicipalityName);
        tvMunicipalityInfo = findViewById(R.id.tvMunicipalityInfo);
        ivMunicipality = findViewById(R.id.ivMunicipality);
        btnVisitSite = findViewById(R.id.btnVisitSite);

        // Receive municipality name from MainActivity
        String municipality = getIntent().getStringExtra("municipality");

        // Validate using TextProcessor
        TextProcessor processor = new TextProcessor();

        String validName = processor.processMunicipality(municipality);

        if (validName == null) {
            Toast.makeText(
                    this,
                    "Invalid municipality",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
            return;
        }

        // Display municipality name
        tvMunicipalityName.setText(validName);

        // Get information array
        String[] information =
                getResources().getStringArray(
                        R.array.municipalities_array
                );

        int color;

        switch (validName) {

            case "Beijing":

                ivMunicipality.setImageResource(R.drawable.beijing);

                tvMunicipalityInfo.setText(information[0]);

                color = Color.RED;

                break;

            case "Shanghai":

                ivMunicipality.setImageResource(R.drawable.shanghai);

                tvMunicipalityInfo.setText(information[1]);

                color = Color.MAGENTA;

                break;

            case "Chongqing":

                ivMunicipality.setImageResource(R.drawable.chongqing);

                tvMunicipalityInfo.setText(information[2]);

                color = Color.rgb(255, 165, 0);

                break;

            case "Tianjin":

                ivMunicipality.setImageResource(R.drawable.tianjin);

                tvMunicipalityInfo.setText(information[3]);

                color = Color.DKGRAY;

                break;

            default:
                finish();
                return;
        }

        // Apply theme colors
        tvMunicipalityName.setBackgroundColor(color);
        btnVisitSite.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(color)
        );

    }
}