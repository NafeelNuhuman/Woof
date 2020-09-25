package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.database.petOwnerModel;

public class register extends AppCompatActivity {

    EditText fname, lname, contact, dateOfBirth, email, pwd, conPwd;
    Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.etFName);
        lname = findViewById(R.id.etLName);
        contact = findViewById(R.id.etPhone);
        email = findViewById(R.id.etEmail);
        dateOfBirth = findViewById(R.id.etAddress);
        pwd = findViewById(R.id.etPwd);
        conPwd = findViewById(R.id.etConPwd);
        regBtn = findViewById(R.id.btnReg);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petOwnerModel pomodel = null;
                DBHelper dbHelper = new DBHelper(register.this);
                String emaila = email.getText().toString();

                if (pwd.getText().toString().equals(conPwd.getText().toString())) {
                    boolean checkmail = dbHelper.checkpetownermail(emaila);
                    if (checkmail == true) {
                        try {
                            pomodel = new petOwnerModel(-1,
                                    fname.getText().toString(),
                                    lname.getText().toString(),
                                    email.getText().toString(),
                                    dateOfBirth.getText().toString(),
                                    Integer.parseInt(contact.getText().toString()),
                                    pwd.getText().toString());
                            dbHelper.addPetOwner(pomodel);
                            Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(register.this, Login.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(register.this, "Enter necessary information with appropriate values", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(register.this, "Email already registered", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    recreate();
                }
            }
        });
    }

}