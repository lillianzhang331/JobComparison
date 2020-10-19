package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;

public class MainMenu extends AppCompatActivity {
    JobCompareDbHelper dbHelper = new JobCompareDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button editCurrentJob = (Button) findViewById(R.id.editCurrentJobButtonID);
        Button enterJobOffer = (Button) findViewById(R.id.enterJobOfferButtonID);
        Button editComparisonSettings = (Button) findViewById(R.id.editComparisonSettingButtonID);
        final Button makeComparison = (Button) findViewById(R.id.compareJobsButtonID);

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
                if ((dbHelper.getJobOfferNumRowIDs() > 0 && dbHelper.isCurrentJobAvailable()) ||
                        dbHelper.getJobOfferNumRowIDs() > 1) {
                    makeComparison.setEnabled(true);
                    Intent goToRanked = new Intent(MainMenu.this, RankedJobs.class);
                    startActivity(goToRanked);
                } else {
                    if (!dbHelper.isCurrentJobAvailable())
                        Toast.makeText(view.getContext(), "At least 1 Job Offer and Current." +
                                " Job are required to Compare", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(view.getContext(), "Not enough Job Offers" +
                                        "\nAdd jobs using ENTER JOB OFFER",
                                Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
