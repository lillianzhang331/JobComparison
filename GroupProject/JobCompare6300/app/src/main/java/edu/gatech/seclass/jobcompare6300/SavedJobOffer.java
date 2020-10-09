package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SavedJobOffer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_job_offer);

        Button compareWithCurrent = (Button) findViewById(R.id.compareCurrentButtonID);
        Button enterAnotherOffer = (Button) findViewById(R.id.enterAnotherOfferButtonID);
        Button returnToMain = (Button) findViewById(R.id.returnToMainButtonID);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(SavedJobOffer.this, MainMenu.class);
                startActivity(retToMain);
                SavedJobOffer.this.finish();
            }
        });
        enterAnotherOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enterAnotherOffer = new Intent(SavedJobOffer.this, JobOfferDetails.class);
                startActivity(enterAnotherOffer);
                SavedJobOffer.this.finish();
            }
        });
        compareWithCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compareWithCurrent = new Intent(SavedJobOffer.this, JobComparison.class);
                startActivity(compareWithCurrent);
                SavedJobOffer.this.finish();
            }
        });
    }
}