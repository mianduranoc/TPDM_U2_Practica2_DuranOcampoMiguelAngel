package mx.edu.ittepic.tpdm_u2_practica2_duranocampomiguelangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    EditText modelo,marca,anio,fechainicio,precio;
    Spinner iddueno,tipopoliza;
    Button guardar,regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        modelo=findViewById(R.id.modelo);
        marca=findViewById(R.id.marca);
        anio=findViewById(R.id.anio);
        fechainicio=findViewById(R.id.fechainicio);
        precio=findViewById(R.id.precio);
        iddueno=findViewById(R.id.spinnerduenos);
        tipopoliza=findViewById(R.id.tipopoliza);
        guardar=findViewById(R.id.guardarpoliza);
        regresar=findViewById(R.id.regresarpoliza);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Poliza poliza=new Poliza(Main5Activity.this);
                String cad=iddueno.getSelectedItem().toString();
                boolean seInserto=poliza.insertar(new Poliza(0,modelo.getText().toString(),marca.getText().toString(),Integer.parseInt(anio.getText().toString()),fechainicio.getText().toString(),Float.parseFloat(precio.getText().toString()),tipopoliza.getSelectedItem().toString(),cad));
                if (seInserto){
                    Toast.makeText(Main5Activity.this,"Se inserto correctamente",Toast.LENGTH_LONG).show();
                    modelo.setText("");
                    marca.setText("");
                    anio.setText("");
                    fechainicio.setText("");
                    precio.setText("");
                    iddueno.setSelection(0);
                    tipopoliza.setSelection(0);
                }
                else{
                    Toast.makeText(Main5Activity.this,"Error: No se inserto",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    protected void onStart(){
        super.onStart();
        Dueno dueno=new Dueno(this);
        Dueno duenos[]=dueno.consultar();
        if (duenos==null){
            Toast.makeText(Main5Activity.this,"No se han ingresado dueños, favor de ingresar",Toast.LENGTH_LONG).show();
            modelo.setEnabled(false);
            marca.setEnabled(false);
            anio.setEnabled(false);
            fechainicio.setEnabled(false);
            precio.setEnabled(false);
            iddueno.setEnabled(false);
            tipopoliza.setEnabled(false);
            guardar.setEnabled(false);
        }
        else{
            String duenosArray[]=new String[duenos.length];
            for (int i=0;i<duenosArray.length;i++){
                duenosArray[i]=duenos[i].id;
            }
            ArrayAdapter<String>adap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,duenosArray);
            iddueno.setAdapter(adap);
        }
    }
}
