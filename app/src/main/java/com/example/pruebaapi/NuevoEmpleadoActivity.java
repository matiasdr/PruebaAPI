package com.example.pruebaapi;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NuevoEmpleadoActivity extends AppCompatActivity {
    private Button btnGuardar;
    private EmpleadoOpenHelper empleadoOpenHelper;
    private SQLiteDatabase db;
    EditText txtNombre;
    EditText txtMail;
    EditText txtDomicilio;
    EditText txtPassword;
    EditText txtTelefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_empleado);
        empleadoOpenHelper = new EmpleadoOpenHelper(this);
        db = empleadoOpenHelper.getWritableDatabase();

        cargarControles();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarEmpleado();

            }
        });
    }

    public void cargarControles(){
        btnGuardar=findViewById(R.id.buttonGuardar);
        txtNombre = findViewById(R.id.editTextNombre);
        txtMail = findViewById(R.id.editTextMail);
        txtDomicilio = findViewById(R.id.editTextDomicilio);
        txtPassword = findViewById(R.id.editTextPassword);
        txtTelefono = findViewById(R.id.editTextTelefono);
    }

    public void guardarEmpleado() {
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("nombre", txtNombre.getText().toString());
        nuevoRegistro.put("domicilio", txtDomicilio.getText().toString());
        nuevoRegistro.put("telefono", txtTelefono.getText().toString());
        nuevoRegistro.put("email", txtMail.getText().toString());
        nuevoRegistro.put("password", txtPassword.getText().toString());
        nuevoRegistro.put("habilitado", 1);

        db.insert("empleados", null, nuevoRegistro);

        Intent intent = new Intent(this, EmpleadosActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
