package com.itservices.itservices;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Usuarios extends AppCompatActivity {
    private EditText EIdentificacion,ENombres,EApellidos,Eemail,ENumero,EAddress,EPass;
    private Spinner  VerPlanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        EIdentificacion =(EditText)findViewById(R.id.EIdentificacion);
        ENombres = (EditText) findViewById(R.id.ENombres);
        EApellidos = (EditText) findViewById(R.id.EApellidos);
        Eemail = (EditText) findViewById(R.id.Eemail);
        ENumero = (EditText) findViewById(R.id.ENumero);
        EAddress = (EditText) findViewById(R.id.EAddress);
        EPass = (EditText) findViewById(R.id.EPass);
        VerPlanes=(Spinner) findViewById(R.id.Planes);

        String[] Opciones ={"Selecione un Plan","Plan 5 Megas ","Plan 10 Megas","Plan 15 Megas"};

        ArrayAdapter<String> Adaptador = new ArrayAdapter<String>(this, R.layout.spinner_item_planes,Opciones);
        VerPlanes.setAdapter(Adaptador);

    }



    public void Crear(View view) {
        GestionBasedatos AdminBD = new GestionBasedatos
                (this, "Administracion",null,1);
        SQLiteDatabase Basededatos = AdminBD.getWritableDatabase();


        String Identifacion = EIdentificacion.getText().toString();
        String Nombres = ENombres.getText().toString();
        String Apellidos = EApellidos.getText().toString();
        String Email = Eemail.getText().toString();
        String Telefono = ENumero.getText().toString();
        String Direccion = EAddress.getText().toString();
        String Contrasena = EPass.getText().toString();
        String Planes = VerPlanes.getSelectedItem().toString();

        if(!Identifacion.isEmpty() && !Nombres.isEmpty() && !Apellidos.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("Identifacion",Identifacion);
            registro.put("Nombres",Nombres);
            registro.put("Apellidos",Apellidos);
            registro.put("Email",Email);
            registro.put("Telefono",Telefono);
            registro.put("Direccion",Direccion);
            registro.put("Contrasena",Contrasena);
            registro.put("PLANUSER",Planes);

            Basededatos.insert("Usuarios",null,registro);
            Basededatos.close();

            EIdentificacion.setText("");
            ENombres.setText("");
            EApellidos.setText("");
            Eemail.setText("");
            ENumero.setText("");
            EAddress.setText("");
            EPass.setText("");

            Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Debes Llenar Todos los Campos",Toast.LENGTH_LONG).show();
        }
    }


    public void Buscar(View view) {
        GestionBasedatos AdminBD = new GestionBasedatos
                (this, "Administracion",null,1);
        SQLiteDatabase Basededatos = AdminBD.getWritableDatabase();

        String Identifacion = EIdentificacion.getText().toString();

        if (!Identifacion.isEmpty()){
            Cursor fila = Basededatos.rawQuery
                    ("Select * From Usuarios where Identifacion="+Identifacion,null);
            if(fila.moveToFirst()){
                ENombres.setText(fila.getString(1));
                EApellidos.setText(fila.getString(2));
                Eemail.setText(fila.getString(3));
                ENumero.setText(fila.getString(4));
                EAddress.setText(fila.getString(5));
                EPass.setText(fila.getString(6));
            }
            else{
                Toast.makeText(this,"Usuario No existe",Toast.LENGTH_LONG).show();
                EIdentificacion.setText("");
                ENombres.setText("");
                EApellidos.setText("");
                Eemail.setText("");
                ENumero.setText("");
                EAddress.setText("");
                EPass.setText("");
            }
            Basededatos.close();
        }
        else{
            Toast.makeText(this,"Ingresa Identificación",Toast.LENGTH_LONG).show();
        }
    }

    public void Actualizar(View view) {
        GestionBasedatos AdminBD = new GestionBasedatos
                (this, "Administracion",null,1);
        SQLiteDatabase Basededatos = AdminBD.getWritableDatabase();
        String Identifacion = EIdentificacion.getText().toString();
        String Nombres = ENombres.getText().toString();
        String Apellidos = EApellidos.getText().toString();
        String Email = Eemail.getText().toString();
        String Telefono = ENumero.getText().toString();
        String Direccion = EAddress.getText().toString();
        String Contrasena = EPass.getText().toString();
        String Planes = VerPlanes.getSelectedItem().toString();

        if(!Identifacion.isEmpty() && !Nombres.isEmpty() && !Apellidos.isEmpty()){
            ContentValues UpdateData = new ContentValues();
            UpdateData.put("Identifacion",Identifacion);
            UpdateData.put("Nombres",Nombres);
            UpdateData.put("Apellidos",Apellidos);
            UpdateData.put("Email",Email);
            UpdateData.put("Telefono",Telefono);
            UpdateData.put("Direccion",Direccion);
            UpdateData.put("Contrasena",Contrasena);
            UpdateData.put("PLANUSER",Planes);

            int Actualizar = Basededatos.update
                    ("Usuarios",UpdateData,"Identifacion="+Identifacion,null);

            Basededatos.close();

            if(Actualizar==1){
                Toast.makeText(this,"Registro Actualizado",Toast.LENGTH_LONG).show();
            }
            else
                { Toast.makeText(this,"Usuario No existe",Toast.LENGTH_LONG).show();
            }

            EIdentificacion.setText("");
            ENombres.setText("");
            EApellidos.setText("");
            Eemail.setText("");
            ENumero.setText("");
            EAddress.setText("");
            EPass.setText("");

        }
        else{
            Toast.makeText(this,"Debes Llenar Todos los Campos",Toast.LENGTH_LONG).show();
            }
    }

    public void Eliminar(View view) {
        GestionBasedatos AdminBD = new GestionBasedatos
                (this, "Administracion",null,1);
        SQLiteDatabase Basededatos = AdminBD.getWritableDatabase();

        String Identifacion = EIdentificacion.getText().toString();

        if (!Identifacion.isEmpty()){
            int DatoEliminado = Basededatos.delete
                    ("Usuarios","Identifacion="+Identifacion,null);
            Basededatos.close();
            EIdentificacion.setText("");
            ENombres.setText("");
            EApellidos.setText("");
            Eemail.setText("");
            ENumero.setText("");
            EAddress.setText("");
            EPass.setText("");
            if(DatoEliminado ==1){
                Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"Usuario No existe",Toast.LENGTH_LONG).show();
            }
            Basededatos.close();
        }
        else{
            Toast.makeText(this,"Ingresa Identificación",Toast.LENGTH_LONG).show();
        }

    }
}