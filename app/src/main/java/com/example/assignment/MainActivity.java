package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;

import java.lang.reflect.Type;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText email, number;
    private RecyclerView recyclerView;
    private ArrayList<PersonModel> personDataModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.editTextEmail);
        number = findViewById(R.id.editTextPhone);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void submit(View view) {
        String userEmail = email.getText().toString();
        String usermobile = number.getText().toString();

        if (isValidEmail(userEmail)) {
            if (validCellPhone(usermobile)) {

                boolean isEmail = false;
                boolean isMobile = false;
                String personsListInString = MySharedPreferences.getPreferences(MainActivity.this, "PERSONS_LIST_DATA");
                if (personsListInString != null && !personsListInString.isEmpty()) {
                    ArrayList<PersonModel> updatedList = getArrayList(personsListInString);
                    if (updatedList != null && updatedList.size() > 0) {
                        for (int i = 0; i < updatedList.size(); i++) {
                            if (userEmail.equalsIgnoreCase(updatedList.get(i).getEmail())) {
                                isEmail = true;
                                break;
                            }
                        }

                        for (int i = 0; i < updatedList.size(); i++) {
                            if (usermobile.equalsIgnoreCase(updatedList.get(i).getNumber())) {
                                isMobile = true;
                                break;
                            }
                        }
                    }
                }

                if (!isEmail) {
                    if (!isMobile) {
                        PersonModel personModel = new PersonModel();
                        personModel.setEmail(userEmail);
                        personModel.setNumber(usermobile);
                        personDataModelList.add(personModel);
                        Gson gson = new Gson();
                        String json = gson.toJson(personDataModelList);
                        MySharedPreferences.setPreference(MainActivity.this, "PERSONS_LIST_DATA", json);
                        onResume();
                    } else {
                        Toast.makeText(MainActivity.this, "Already added this number!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Already added this email!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Invalid number", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Invalid email!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validCellPhone(String number) {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    protected void onResume() {
        super.onResume();
        String personsListInString = MySharedPreferences.getPreferences(MainActivity.this, "PERSONS_LIST_DATA");
        if (personsListInString != null && !personsListInString.isEmpty()) {
            ArrayList<PersonModel> updatedList = getArrayList(personsListInString);
            if (updatedList != null && updatedList.size() > 0) {
                recyclerView.setAdapter(new Myadapter(MainActivity.this, updatedList));
            }
        }
    }

    public ArrayList<PersonModel> getArrayList(String json) {
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        // String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<PersonModel>>() {
        }.getType();
        //DataObject obj = gson.fromJson(arrayString, ArrayList.class);
        return gson.fromJson(json, type);
    }
}