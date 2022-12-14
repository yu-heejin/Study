package ddwu.com.mobile.example.lbs.placetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.libraries.places.api.model.Place;

public class DetailActivity extends AppCompatActivity {

    EditText etName;
    EditText etPhone;
    EditText etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent getIntent = getIntent();
        Place place = (Place) getIntent.getSerializableExtra("detail");

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);

        etName.setText(place.getName());
        etPhone.setText(place.getPhoneNumber());
        etAddress.setText(place.getAddress());
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                Toast.makeText(this, "Save Place information", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
