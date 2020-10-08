package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class JobComparison extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_comparison);

        Button returnToMain = (Button) findViewById(R.id.returnToMainMenuButtonID);
        Button compareAnother = (Button) findViewById(R.id.makeAnotherComparisonButtonID);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(JobComparison.this, MainMenu.class);
                startActivity(retToMain);
            }
        });
        compareAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRankedJobs = new Intent(JobComparison.this, RankedJobs.class);
                startActivity(goToRankedJobs);
                JobComparison.this.finish();
            }
        });
    }
}
