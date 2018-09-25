package com.dolavale.bruna.contatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();

        String name = extras.getString("name");
        String city = extras.getString("city");
        String email = extras.getString("email");
        String phoneNumber = extras.getString("phoneNumber");

        TextView tvName = findViewById(R.id.text_name);
        TextView tvEmail = findViewById(R.id.text_email);
        TextView tvCity = findViewById(R.id.text_city);
        TextView tvPhoneNumber = findViewById(R.id.text_phoneNumber);

        tvName.setText(name);
        tvEmail.setText(email);
        tvCity.setText(city);
        tvPhoneNumber.setText(phoneNumber);

    }

    public void closeDetails(View view) {
        finish();
    }
}
