package com.example.pruebaapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EmpleadoOpenHelper empleadoOpenHelper;
    SQLiteDatabase db;
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
        empleadoOpenHelper = new EmpleadoOpenHelper(this);
        db = empleadoOpenHelper.getReadableDatabase();


        editTextMail = findViewById(R.id.editTextMail);
        editTextPassword = findViewById(R.id.editTextPassword);
        aSwitch = findViewById(R.id.switch1);
        buttonIngresar = findViewById(R.id.buttonIngresar);

        String mailGuardado= pref.getString("MAIL", "");
        String passGuardado = pref.getString("PASS", "");
        if(mailGuardado.isEmpty() || passGuardado.isEmpty()){
            aSwitch.setChecked(false);
        }
        else
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
                if(!isChecked){
                    editTextPassword.setEnabled(true);
                    editTextMail.setEnabled(true);
                    editTextMail.setText("");
                    editTextPassword.setText("");
                    SharedPreferences.Editor editor = pref.edit();
                    editor.clear();
                    editor.commit();

                }
            }
        });

    }

    private void validar() {
        String m = editTextMail.getText().toString();
        String p = editTextPassword.getText().toString();
        String[] args = {m,p};
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM empleados WHERE email = ? AND password = ?", new String[]{m,p});

            if(!cursor.moveToFirst()){
                Toast.makeText(this, "Usuario o Constarsena incorrecta", Toast.LENGTH_SHORT).show();
            } else {
                if (aSwitch.isChecked()) {
                    SharedPreferences.Editor editor = pref.edit();
                    String mail = editTextMail.getText().toString();
                    String pass = editTextPassword.getText().toString();
                    editor.putString("MAIL", mail);
                    editor.putString("PASS", pass);
                    editor.commit();
                }
                Intent intent = new Intent(this, EmpleadosActivity.class);
                startActivity(intent);
            }

        } catch (Exception e){
            Toast.makeText(this, "Usuario o Constarsena incorrecta", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
