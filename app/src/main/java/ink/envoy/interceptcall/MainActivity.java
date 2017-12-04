package ink.envoy.interceptcall;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private SharedPreferences sharedPreferences;
    private static final int PERMISSION_REQUEST_CODE_NEW_OUTGOING_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        bindViews();
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editText.getText().toString().trim();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("number", number);
                editor.apply();
                Toast.makeText(getApplicationContext(), "Saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

        applyForPermissions();
    }

    private void bindViews() {
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
    }

    private void applyForPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                    PERMISSION_REQUEST_CODE_NEW_OUTGOING_CALL);
            Toast.makeText(this, "Permission PROCESS_OUTGOING_CALLS granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission PROCESS_OUTGOING_CALLS already granted", Toast.LENGTH_SHORT).show();
        }
    }
}
