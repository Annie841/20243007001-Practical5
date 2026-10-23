package com.example.practicalfive;

import android.os.Bundle;
import android.content.res.ColorStateList;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.net.Uri;

public class Municipality extends AppCompatActivity {

    private TextView tvMunicipalityName;
    private TextView tvMunicipalityInfo;
    private ImageView ivMunicipality;
    private Button btnVisitSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_municipality);
        // Keep the layout clear of the status and navigation bars
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.municipalityRoot),
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        // Step 1: Find UI components
        tvMunicipalityName = findViewById(R.id.tvMunicipalityName);
        tvMunicipalityInfo = findViewById(R.id.tvMunicipalityInfo);
        ivMunicipality = findViewById(R.id.ivMunicipality);
        btnVisitSite = findViewById(R.id.btnVisitSite);



        // Step 2: Receive municipality name
        String municipality = getIntent().getStringExtra(
                MainActivity.EXTRA_MUNICIPALITY
        );

        // Step 3: Validate municipality name
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

        // Step 4: Display municipality name
        tvMunicipalityName.setText(validName);

        // Step 5: Get municipality information
        String[] information = getResources().getStringArray(
                R.array.municipalities_array
        );

        // Step 6: Select resources
        int color;

        switch (validName) {

            case "Beijing":

                ivMunicipality.setImageResource(
                        R.drawable.beijing
                );

                tvMunicipalityInfo.setText(information[0]);

                color = getColor(R.color.color_beijing);

                break;

            case "Shanghai":

                ivMunicipality.setImageResource(
                        R.drawable.shanghai
                );

                tvMunicipalityInfo.setText(information[1]);

                color = getColor(R.color.color_shanghai);

                break;

            case "Chongqing":

                ivMunicipality.setImageResource(
                        R.drawable.chongqing
                );

                tvMunicipalityInfo.setText(information[2]);

                color = getColor(R.color.color_chongqing);

                break;

            case "Tianjin":

                ivMunicipality.setImageResource(
                        R.drawable.tianjin
                );

                tvMunicipalityInfo.setText(information[3]);

                color = getColor(R.color.color_tianjin);

                break;

            default:

                finish();
                return;
        }

        // Step 7: Change name background colour
        tvMunicipalityName.setBackgroundColor(color);

        // Step 8: Change information text colour
        tvMunicipalityInfo.setTextColor(color);

        // Step 9: Change Visit site button colour
        btnVisitSite.setBackgroundTintList(
                ColorStateList.valueOf(color)
        );

        // Step 10: Keep text on coloured backgrounds readable
        tvMunicipalityName.setTextColor(
                getColor(android.R.color.white)
        );

        btnVisitSite.setTextColor(
                getColor(android.R.color.white)
        );
        // Section 10: Open the official municipality website

        btnVisitSite.setOnClickListener(v -> {

            // Step 1: Determine the official website
            String websiteUrl;

            switch (validName) {

                case "Beijing":
                    websiteUrl = "https://english.beijing.gov.cn/";
                    break;

                case "Shanghai":
                    websiteUrl = "https://english.shanghai.gov.cn/";
                    break;

                case "Tianjin":
                    websiteUrl = "https://en.tj.gov.cn/";
                    break;

                case "Chongqing":
                    websiteUrl = "https://english.cq.gov.cn/";
                    break;

                default:
                    Toast.makeText(
                            Municipality.this,
                            "Invalid municipality",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
            }

            // Step 2: Create an implicit Intent
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(websiteUrl)
            );

            // Step 3: Open the official website
            try {
                startActivity(intent);

            } catch (ActivityNotFoundException e) {

                // Step 4: Handle the case where no browser is available
                Toast.makeText(
                        Municipality.this,
                        "No application available to open the website",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });
    }
}