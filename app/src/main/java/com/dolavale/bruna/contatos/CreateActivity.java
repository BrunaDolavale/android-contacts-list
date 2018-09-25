package com.dolavale.bruna.contatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

    public void onCancel(View view) {
        finish();
    }

    public void createContact(View view) {
        EditText nameInput = findViewById(R.id.editText_name);
        EditText emailInput = findViewById(R.id.editText_email);
        EditText phoneNumberInput = findViewById(R.id.editText_phoneNumber);
        EditText cityInput = findViewById(R.id.editText_city);

        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String phoneNumber = phoneNumberInput.getText().toString();
        String city = cityInput.getText().toString();

        if(!name.equals("") && !email.equals("") && !phoneNumber.equals("") && !city.equals("")){

            Model contact = new Model(phoneNumber, name,email, city);

            try{
                File outFile = new File(getFilesDir(), MainActivity.CONTACT_FILENAME);
                OutputStream outputStream = new FileOutputStream(outFile,true);
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);

                writer.write("#\n");
                writer.write(contact.toString());

                writer.close();
                finish();
            }catch(FileNotFoundException exception){
                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }catch (IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "Preencha todos os campos",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void clearFields(View view) {
        EditText nameInput = findViewById(R.id.editText_name);
        EditText emailInput = findViewById(R.id.editText_email);
        EditText phoneNumberInput = findViewById(R.id.editText_phoneNumber);
        EditText cityInput = findViewById(R.id.editText_city);

		nameInput.setText("");
        emailInput.setText("");
        phoneNumberInput.setText("");
        cityInput.setText("");

    }
}
