package mx.edu.ittepic.tpdm_u2_practica2_duranocampomiguelangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    EditText id,nombre,telefono,domicilio;
    Button guardar,regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        id=findViewById(R.id.iddu);
        nombre=findViewById(R.id.nombredu);
        telefono=findViewById(R.id.telefonodu);
        domicilio=findViewById(R.id.domiciliodu);
        guardar=findViewById(R.id.guardardueno);
        regresar=findViewById(R.id.regresari);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void insertar(){
        Dueno dueno=new Dueno(this);
        boolean resultado=dueno.insertar(new Dueno(id.getText().toString(),nombre.getText().toString(),domicilio.getText().toString(),telefono.getText().toString()));
        if (resultado){
            Toast.makeText(Main4Activity.this, "Se inserto correctamente", Toast.LENGTH_LONG).show();
            nombre.setText("");
            telefono.setText("");
            domicilio.setText("");
            id.setText("");
        }
        else{
            Toast.makeText(Main4Activity.this,"Error: no se inserto",Toast.LENGTH_LONG).show();
        }
    }
}
