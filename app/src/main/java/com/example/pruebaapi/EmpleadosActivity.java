package com.example.pruebaapi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class EmpleadosActivity extends AppCompatActivity {
    private ListView list;
    private EmpleadoAdapter adapter;
    private EmpleadoOpenHelper empleadoOpenHelper;
    private SQLiteDatabase db;
    private ArrayList<Empleado> listaEmpleado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

        list=findViewById(R.id.listViewEmpleados);
        listaEmpleado=new ArrayList<Empleado>();

        empleadoOpenHelper = new EmpleadoOpenHelper(this);
        db = empleadoOpenHelper.getWritableDatabase();

        // aca deberíamos cargar los datos de la base de datos en la lista empleados
        Empleado empleadoPrueba = new Empleado(0, "Matias", "Sarmiento", "15390619", "admin", "admin", 1);
        listaEmpleado.add(empleadoPrueba);

        ////////
        adapter = new EmpleadoAdapter(listaEmpleado);
        list.setAdapter(adapter);

        cargarDatos();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnNuevo:
                // aca iniciamos la activity para crar uno nuevo
                Intent intent = new Intent(this, NuevoEmpleadoActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    public void cargarDatos(){
        Cursor cursor = db.rawQuery("SELECT * FROM empleados", null);

        if(cursor.moveToFirst()){

            while(cursor.isAfterLast()==false){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("nombre"));
                String domicilio = cursor.getString(cursor.getColumnIndex("domicilio"));
                String telefono = cursor.getString(cursor.getColumnIndex("telefono"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                int habilitado = cursor.getInt(cursor.getColumnIndex("habilitado"));
                String pass = cursor.getString(cursor.getColumnIndex("password"));

                listaEmpleado.add(new Empleado(id, name, domicilio, telefono, email, pass, habilitado));

                cursor.moveToNext();
            }
        }

        adapter.notifyDataSetChanged();


    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
