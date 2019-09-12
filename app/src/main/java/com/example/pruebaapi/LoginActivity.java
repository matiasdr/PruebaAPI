package com.example.pruebaapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class LoginActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "ArchivoPref";
    SharedPreferences pref ;
    EditText editTextMail;
    EditText editTextPassword;
    Switch aSwitch;
    Button buttonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = getSharedPreferences("archivo", Context.MODE_PRIVATE);

        editTextMail = findViewById(R.id.editTextMail);
        editTextPassword = findViewById(R.id.editTextPassword);
        aSwitch = findViewById(R.id.switch1);
        buttonIngresar = findViewById(R.id.buttonIngresar);
        String mailGuardado= pref.getString("MAIL", "");
        String passGuardado = pref.getString("PASS", "");
        if(mailGuardado.isEmpty() || passGuardado.isEmpty()){
            aSwitch.setChecked(false);
        } else
        {
            editTextMail.setText(mailGuardado);
            editTextPassword.setText(passGuardado);
            editTextPassword.setEnabled(false);
            editTextMail.setEnabled(false);
            aSwitch.setChecked(true);
        }
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==false){
                    editTextPassword.setEnabled(true);
                    editTextMail.setEnabled(true);
                    editTextMail.setText("");
                    editTextPassword.setText("");

                }
            }
        });

    }

    private void validar() {
        if(aSwitch.isChecked()){
            SharedPreferences.Editor editor = pref.edit();
            String mail = editTextMail.getText().toString();
            String pass = editTextPassword.getText().toString();
            editor.putString("MAIL", mail);
            editor.putString("PASS", pass);
            editor.commit();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
