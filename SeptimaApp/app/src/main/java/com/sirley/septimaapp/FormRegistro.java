package com.sirley.septimaapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FormRegistro extends AppCompatActivity {
    int op = 0;
    LinearLayout CONTE;
    Button B1;
    Fragment FRAG;
    EditText E1, E2, E3, P, PC;
    TextView T1;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_registro);
        CONTE = (LinearLayout) findViewById(R.id.contenedor);
        CONTE.setVisibility(View.INVISIBLE);
        B1 = (Button) findViewById(R.id.b1);
        E1 = (EditText) findViewById(R.id.e1);
        E2 = (EditText) findViewById(R.id.e2);
        E3 = (EditText) findViewById(R.id.e3);
        T1 = (TextView) findViewById(R.id.t1);
        DB=new DatabaseHelper(this);
    }

    public void metodoPolitica(View view) {
        switch (op) {
            case 0:
                CONTE.setVisibility(View.VISIBLE);
                FRAG = new Fragment1();
                op = 1;
                cargarFragmento(FRAG);
                break;
            case 1:
                FRAG = new Fragmento2();
                op = 2;
                cargarFragmento(FRAG);
                break;
            case 2:
                CONTE.setVisibility(View.INVISIBLE);
                op = 0;
        }
    }

    private void cargarFragmento(Fragment frag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, frag);
        transaction.commit();
    }

    public void crearUsuario(View view) {
        String nombre, apellido, telefono;
        int v1, v2, v3;
        nombre = E1.getText().toString();
        v1 = validarCaracteres(1, nombre);
        apellido = E2.getText().toString();
        v2 = validarCaracteres(1, apellido);
        telefono = E3.getText().toString();
        v3 = validarCaracteres(2, telefono);
        //if(v1!=v2 || v2!=v3 || v1!=v3 || v1!=1)
        if (v1 != 1 || v2 != 1 || v3 != 1) {
            //no se creará el usuario
            T1.setText("error");
        } else {
            //crear usuario nuevo
            //guardar datos
        if(DB.insertData(nombre,apellido,telefono))
        {
            T1.setText("Registro exitoso");
        }
        }
    }

    private int validarCaracteres(int i, String cadena) {
        int validacion = 0, pos, c;
        switch (i) {
            case 1:
                //caracteres alfabeticos
                for (pos = 0; pos < cadena.length(); pos = pos + 1) {
                    c = cadena.charAt(pos);
                    if ((c >= 67 && c <= 90) || (c > 97 && c <= 122))
                    //if(c<67 || c>90)
                    {
                        validacion = 1;
                        //pos=cadena.length();
                    } else {
                        pos = cadena.length();
                        validacion = 0;
                    }
                }
                break;
            case 2:
                //caracteres numericos
                for (pos = 0; pos < cadena.length(); pos = pos + 1) {
                    c = cadena.charAt(pos);
                    if (c < 48 || c > 57) {
                        validacion = 0;
                        pos = cadena.length();
                    } else {
                        //pos=cadena.length();
                        validacion = 1;
                    }
                }
                break;
        }
        return validacion;
    }
}