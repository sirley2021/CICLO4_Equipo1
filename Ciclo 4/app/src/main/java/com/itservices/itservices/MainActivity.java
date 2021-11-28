package com.itservices.itservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Usuarios(View view) {
        Toast.makeText(this,"Algo Pasara",Toast.LENGTH_LONG).show();
        Intent I1 = new Intent(view.getContext(),Usuarios.class);
        startActivity(I1);
        //Intent I3 = new Intent(view.getContext(),Usuarios.class);
        //startActivity(I3);
    }

    public void IngresarCuenta(View view) {
        Toast.makeText(this,"Algo Pasara",Toast.LENGTH_LONG).show();
        Intent I2 = new Intent(view.getContext(),IngresarUsuario.class);
        startActivity(I2);

    }
}