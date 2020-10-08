package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class ComparisonSettings extends AppCompatActivity {
    private SeekBar commuteWeight, salaryWeight, bonusWeight, retirementWeight, leaveWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_settings);

        commuteWeight = (SeekBar) findViewById(R.id.commuteWeightID);
        salaryWeight = (SeekBar) findViewById(R.id.salaryWeightID);
        bonusWeight = (SeekBar) findViewById(R.id.bonusWeightID);
        retirementWeight = (SeekBar) findViewById(R.id.retirementWeightID);
        leaveWeight = (SeekBar) findViewById(R.id.leaveWeightID);

        Button returnToMain = (Button) findViewById(R.id.returnToMainButtonID);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(ComparisonSettings.this, MainMenu.class);
                startActivity(retToMain);
            }
        });
    }
}