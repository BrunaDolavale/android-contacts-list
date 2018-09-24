package com.dolavale.bruna.contatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String CONTACT_FILENAME = "contacts.txt";
    List<ContactModel> contactList;
    RecyclerView recyclerViewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        //for(int i = 1; i<5; i++){
         //   contactList.add(new ContactModel("9971"+i, "bruna Júnior " + i,
        //            "brunadolavalec@outlook.com "+i,"Rio de Janeiro"));
        //}

        ContactCardsAdapter adapter = new ContactCardsAdapter(contactList);
        recyclerViewContacts = findViewById(R.id.recyclerView_contact_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewContacts.setLayoutManager(layoutManager);

        recyclerViewContacts.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        recyclerViewContacts.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        contactList = new ArrayList<>();
        File inputFile = new File(getFilesDir(), CONTACT_FILENAME);

        try{
            InputStream inputStream = new FileInputStream(inputFile);
            InputStreamReader reader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            while(line != null){
                if(line.equals("#")){
                    String name = bufferedReader.readLine();
                    String email = bufferedReader.readLine();
                    String phoneNumber = bufferedReader.readLine();
                    String city = bufferedReader.readLine();
                    contactList.add(new ContactModel(phoneNumber,name,email,city));
                }
                line = bufferedReader.readLine();
            }

            ContactCardsAdapter adapter = new ContactCardsAdapter(contactList);
            recyclerViewContacts.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            if(!(contactList.size() > 0)){
                Toast.makeText(this, "Nenhum contato cadastrado", Toast.LENGTH_LONG).show();
            }

        }catch (FileNotFoundException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }catch (IOException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void goCreateContact(View view) {
        startActivity(new Intent(this, CreateContactActivity.class));
    }

    public void clearContactList(View view) {
        try{
            File file = new File(getFilesDir(),CONTACT_FILENAME);
            if(file.exists() && file.isFile()){
                file.delete();
                file.createNewFile();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                Toast.makeText(this, "Lista limpada com sucesso", Toast.LENGTH_LONG).show();
            }

        }catch (FileNotFoundException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
