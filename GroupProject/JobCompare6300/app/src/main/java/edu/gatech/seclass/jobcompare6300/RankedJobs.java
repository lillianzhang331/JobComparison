package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RankedJobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranked_jobs);

        Button rankedMakeComparison = (Button) findViewById(R.id.rankedMakeComparisonButtonID);
        rankedMakeComparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToJobComparison = new Intent(RankedJobs.this, JobComparison.class);
                startActivity(goToJobComparison);
                RankedJobs.this.finish();
            }
        });
    }
}
