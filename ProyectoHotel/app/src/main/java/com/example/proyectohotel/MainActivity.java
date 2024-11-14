package com.example.proyectohotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etnom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etnom=(EditText) findViewById(R.id.etnom);

    }

    public void IrPP(View view){
        Intent i=new Intent(this, PantallaPrincipal.class);
        i.putExtra("Nombre", etnom.getText().toString());
        startActivity(i);
        etnom.setText("");
    }

    public void VolverMenu (View view) {
        Intent i = new Intent(this, MenuP.class);
        startActivity(i);
        etnom.setText("");
    }
}