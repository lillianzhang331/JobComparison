package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button editCurrentJob = (Button) findViewById(R.id.editCurrentJobButtonID);
        Button enterJobOffer = (Button) findViewById(R.id.enterJobOfferButtonID);
        Button editComparisonSettings = (Button) findViewById(R.id.editComparisonSettingButtonID);
        Button makeComparison = (Button) findViewById(R.id.compareJobsButtonID);

        editCurrentJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCurrent = new Intent(MainMenu.this, CurrentJobDetails.class);
                startActivity(goToCurrent);
            }
        });
        enterJobOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToOffer = new Intent(MainMenu.this, JobOfferDetails.class);
                startActivity(goToOffer);
            }
        });
        editComparisonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettings = new Intent(MainMenu.this, ComparisonSettings.class);
                startActivity(goToSettings);
            }
        });
        makeComparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRanked = new Intent(MainMenu.this, RankedJobs.class);
                startActivity(goToRanked);
            }
        });
    }
}
