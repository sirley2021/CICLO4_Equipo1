package com.itservices.itservices;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IngresarUsuario extends AppCompatActivity {
    private EditText ETIdUser, ETPass, VisorTemporal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_usuario);
        ETIdUser = (EditText) findViewById(R.id.ETIdUser);
        ETPass = (EditText) findViewById(R.id.ETPass);

    }

    public void IngresarCuenta(View view) {

        GestionBasedatos AdminBD = new GestionBasedatos
                (this, "Administracion", null, 1);
        SQLiteDatabase Basededatos = AdminBD.getWritableDatabase();

        String Identifacion = ETIdUser.getText().toString();
        String Password = ETPass.getText().toString();

        if (!Identifacion.isEmpty() && !Password.isEmpty()) {
            Cursor Pass = Basededatos.rawQuery
                    ("Select Contrasena From Usuarios where Identifacion=" + Identifacion, null);

            if (Pass.moveToFirst()) {
                Toast.makeText(this,"Algo Pasara",Toast.LENGTH_LONG).show();
                Intent I2 = new Intent(view.getContext(),DatosUsuario.class);
                I2.putExtra("Identificacion",Identifacion);
                startActivity(I2);
            } else {
                Toast.makeText(this, "Usuario No existe", Toast.LENGTH_LONG).show();
                ETIdUser.setText("");
                ETPass.setText("");


            }
            Basededatos.close();
        } else {
            Toast.makeText(this, "Llena Todos los Datos", Toast.LENGTH_LONG).show();
        }
    }
}