package com.example.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge UI
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle window insets for proper padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_query_form), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Add Spinner for Country Codes
        setupCountryCodeSpinner();

        // Set up the submit button listener
        setupSubmitButton();
    }

    /**
     * Populates the Spinner with country codes and sets up the adapter.
     */
    private void setupCountryCodeSpinner() {
        // Define country codes
        String[] countryCodes = {"+1", "+91", "+44", "+61", "+81"};

        // Find the Spinner in the layout
        Spinner countryCodeSpinner = findViewById(R.id.countryCodeSpinner);

        // Create an ArrayAdapter using the country codes
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                countryCodes
        );

        // Set the dropdown view resource
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the adapter to the Spinner
        countryCodeSpinner.setAdapter(adapter);
    }

    /**
     * Sets up the Submit button to send data via email.
     */
    private void setupSubmitButton() {
        // Find the Submit button
        Button submitButton = findViewById(R.id.submitButton);

        // Find the EditText fields and Spinner
        EditText nameEditText = findViewById(R.id.nameInput);
        EditText phoneEditText = findViewById(R.id.mobileInput);
        Spinner countryCodeSpinner = findViewById(R.id.countryCodeSpinner);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered data
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String countryCode = countryCodeSpinner.getSelectedItem().toString();

                // Combine phone number with country code
                String fullPhoneNumber = countryCode + " " + phone;

                // Prepare the email intent
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(android.net.Uri.parse("mailto:rashikaagrawal1108@example.com")); // Replace with the app owner's email
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "User Data Submission");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Name: " + name + "\nPhone: " + fullPhoneNumber);

                // Start the email activity
                try {
                    startActivity(emailIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Handle the case when no email client is available
                    // Optionally, show a Toast or Snackbar
                }
            }
        });
    }
}
