package com.example.ejemplosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EjemploSql extends AppCompatActivity {

    //Button btnCrear;
    Button btnGuardar;
    Button btnBorrar;
    Button btnEditar;
    Button btnConsultar;
    EditText etId,etNombre,etTelefono,etDireccion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etId = findViewById(R.id.etIDUsuario);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etDireccion = findViewById(R.id.etDireccion);

        btnConsultar = findViewById(R.id.btnConsultar);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnEditar = findViewById(R.id.btnEditar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              consulta();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              editar();
            }
        });


        }

        //CONSULTAR
    public void consulta () {
         OpenHelper admin = new OpenHelper(this, "cliente",null, 1);
         SQLiteDatabase db = admin.getWritableDatabase();
         //id
         String idCliente = etId.getText().toString();
         //consulta realizada
         Cursor fila = db.rawQuery("SELECT nombre, telefono, direccion FROM clientes WHERE idCliente="+idCliente, null);
        //campos de informacion obtenida
         if (fila.moveToFirst()){
             etNombre.setText(fila.getString(0));
             etTelefono.setText(fila.getString(1));
             etDireccion.setText(fila.getString(2));
         } else {
             Toast.makeText(this, "No existe ningun cliente con ese ID", Toast.LENGTH_LONG).show();
    }
  }
  public void guardar(){
    OpenHelper admin = new OpenHelper(this, "cliente", null, 1 );
    SQLiteDatabase db = admin.getWritableDatabase();
    String idCliente = etId.getText().toString();
    String nombre = etNombre.getText().toString();
    String telefono = etTelefono.getText().toString();
    String direccion = etDireccion.getText().toString();

      ContentValues registro = new ContentValues();

      registro.put("idCliente", idCliente);
      registro.put("nombre", nombre);
      registro.put("telefono", telefono);
      registro.put("direccion", direccion);

      //Insertar Datos
      db.insert("clientes", null, registro);
      db.close();

      //limpiar campos
      etId.setText("");
      etNombre.setText("");
      etTelefono.setText("");
      etDireccion.setText("");

      Toast.makeText(this, "Datos registrados correctamente", Toast.LENGTH_LONG).show();
  }

  public void borrar(){
    OpenHelper admin = new OpenHelper(this, "cliente", null, 1 );
    SQLiteDatabase db = admin.getWritableDatabase();

      String idCliente = etId.getText().toString();
     int cant = db.delete("clientes", "idCliente="+idCliente,null);
    db.close();
     etId.setText("");
     etNombre.setText("");
     etTelefono.setText("");
     etDireccion.setText("");

   if (cant==1){
       Toast.makeText(this, "Cliente eliminado", Toast.LENGTH_LONG).show();
   } else {
       Toast.makeText(this, "No existe cliente", Toast.LENGTH_LONG).show();
   }
    }

  public void editar (){
      OpenHelper admin = new OpenHelper(this, "cliente", null, 1 );
      SQLiteDatabase db = admin.getWritableDatabase();

      String idCliente = etId.getText().toString();
      String nombre = etNombre.getText().toString();
      String telefono = etTelefono.getText().toString();
      String direccion = etDireccion.getText().toString();

      ContentValues registro = new ContentValues();

      registro.put("nombre", nombre);
      registro.put("telefono", telefono);
      registro.put("direccion", direccion);

      int cant = db.update("clientes",registro,"idCliente="+idCliente,null);
       db.close();
      etId.setText("");
      etNombre.setText("");
      etTelefono.setText("");
      etDireccion.setText("");

      if (cant==1){
          Toast.makeText(this, "Cliente modificado con exito", Toast.LENGTH_LONG).show();
  } else {
          Toast.makeText(this, "No existe cliente", Toast.LENGTH_LONG).show();
  }
  }
}

        //btnCrear = findViewById(R.id.btnCrear);
        //btnCrear.setOnClickListener(new View.OnClickListener() {
            /*@Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(EjemploSql.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null){
                    Toast.makeText(EjemploSql.this, "BASE DE DATOS CREADA",Toast.LENGTH_LONG).show();
                }  else {
                    Toast.makeText(EjemploSql.this, "ERROR AL CREAR LA BASE DE DATOS",Toast.LENGTH_LONG).show();
                }
            }
        });
        //Grabar Cliente
       /* btnGrabar = findViewById(R.id.btnGuardar);
        //txtID=findViewById(R.id.editTextID);
        txtNombre=findViewById(R.id.editTextNombre);
        txtApellido=findViewById(R.id.editTextApellido);
        txtTelefono=findViewById(R.id.editTextTelefono);
        txtDireccion=findViewById(R.id.editTextDireccion);
        txtCorreo=findViewById(R.id.editTextCorreo);
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Clientes c= new Clientes();
                //c.setId(Integer.parseInt(txtID.getText().toString()));
                c.setNombre(txtNombre.getText().toString());
                c.setApellido(txtApellido.getText().toString());
                c.setTelefono(txtTelefono.getText().toString());
                c.setDireccion(txtDireccion.getText().toString());
                c.setCorreo_electronico(txtCorreo.getText().toString());
                System.out.println(txtCorreo.getText().toString());
                c.guardar(EjemploSql.this);
                Toast.makeText(getApplicationContext(),"CLIENTE CREADO",Toast.LENGTH_LONG).show();
            }
        });*/

