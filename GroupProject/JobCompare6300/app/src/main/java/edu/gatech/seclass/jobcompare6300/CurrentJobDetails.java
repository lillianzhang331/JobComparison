package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentJobDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_job_details);

        Button currentSave = (Button) findViewById(R.id.currentSaveButtonID);
        Button currentCancel = (Button) findViewById(R.id.currentCancelButtonID);
        currentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(CurrentJobDetails.this, MainMenu.class);
                startActivity(retToMain);
                CurrentJobDetails.this.finish();
            }
        });
        currentSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(CurrentJobDetails.this, MainMenu.class);
                startActivity(retToMain);
                CurrentJobDetails.this.finish();
            }
        });
    }
}
