package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class JobOfferDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offer_details);

        Button offerSave = (Button) findViewById(R.id.offerSaveButtonID);
        Button offerCancel = (Button) findViewById(R.id.offerCancelButtonID);
        offerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(JobOfferDetails.this, MainMenu.class);
                startActivity(retToMain);
                JobOfferDetails.this.finish();
            }
        });
        offerSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSavedOffer = new Intent(JobOfferDetails.this, SavedJobOffer.class);
                startActivity(goToSavedOffer);
                JobOfferDetails.this.finish();
            }
        });
    }
}
