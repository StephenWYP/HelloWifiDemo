package com.example.hellowifidemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.*;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // ActivityResultLauncher for file selection
    private ActivityResultLauncher<Intent> filePickerLauncher;
    // Tag for logging (used in Logcat)
    private static final String TAG = "WIFI_SIM";
    // TextView to display selected file name
    private TextView fileNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        fileNameView = findViewById(R.id.tv_filename); // Connect to the XML TextView

        // Register the file picker launcher to handle selected file
        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        // Get file URI
                        Uri fileUri = result.getData().getData();
                        // Show Toast and log the URI
                        if (fileUri != null){
                            Toast.makeText(MainActivity.this, "File selected: " + fileUri.getLastPathSegment(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "User selected file: " + fileUri.toString());
                            // Update the on-screen TextView with the selected file name
                            fileNameView.setText("Selected: " + fileUri.getLastPathSegment());
                        }
                        // Simulate file transfer
                        Toast.makeText(MainActivity.this, "Transferring file...", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Simulated file transfer complete.");
                    }
                }
        );

        // Keep the system bar insets if needed
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the button by ID
        Button discoverBtn = findViewById(R.id.btn_discover);

        // Set onClick listener for the button
        discoverBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // Show a toast message to simulate discovering devices
               Toast.makeText(MainActivity.this, "Searching for nearby devices...", Toast.LENGTH_SHORT).show();

               // Print log to Logcat for debugging
               Log.d(TAG, "Simulated Wifi-Direct discovery started.");
           }
        });

        // Find the choose file button by ID
        Button chooseFileBtn = findViewById(R.id.btn_choose_file);

        // Set click listener for file selection
        chooseFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open system file picker
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*"); // Allow any file type
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                // Launch the file picker
                filePickerLauncher.launch(Intent.createChooser(intent, "Select a file"));
            }
        });


    }
}

















