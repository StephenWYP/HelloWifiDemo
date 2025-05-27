package com.example.hellowifidemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Tag for logging (used in Logcat)
    private static final String TAG = "WIFI_SIM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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

    }
}

















