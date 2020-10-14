package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.model.ComparisonSettingsModel;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;

public class ComparisonSettings extends AppCompatActivity {
    private SeekBar commuteWeight;
    private SeekBar salaryWeight;
    private SeekBar bonusWeight;
    private SeekBar retirementWeight;
    private SeekBar leaveWeight;
    private ComparisonSettingsModel settings;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_settings);

        commuteWeight = (SeekBar) findViewById(R.id.commuteWeightID);
        salaryWeight = (SeekBar) findViewById(R.id.salaryWeightID);
        bonusWeight = (SeekBar) findViewById(R.id.bonusWeightID);
        retirementWeight = (SeekBar) findViewById(R.id.retirementWeightID);
        leaveWeight = (SeekBar) findViewById(R.id.leaveWeightID);

        //private MyApplication myApplication;
        JobCompareDbHelper settingsDb = new JobCompareDbHelper(this);
        settings = new ComparisonSettingsModel(settingsDb);

        commuteWeight.setProgress(settings.getCommuteWeight());
        salaryWeight.setProgress(settings.getSalaryWeight());
        bonusWeight.setProgress(settings.getBonusWeight());
        retirementWeight.setProgress(settings.getRetirementbenefitsWeight());
        leaveWeight.setProgress(settings.getLeaveWeight());

        Button returnToMain = (Button) findViewById(R.id.returnToMainButtonID);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setComparisonSettings(commuteWeight.getProgress(),
                        salaryWeight.getProgress(),bonusWeight.getProgress(),
                        retirementWeight.getProgress(),leaveWeight.getProgress());
                Intent retToMain = new Intent(ComparisonSettings.this, MainMenu.class);
                startActivity(retToMain);
            }
        });
    }
}