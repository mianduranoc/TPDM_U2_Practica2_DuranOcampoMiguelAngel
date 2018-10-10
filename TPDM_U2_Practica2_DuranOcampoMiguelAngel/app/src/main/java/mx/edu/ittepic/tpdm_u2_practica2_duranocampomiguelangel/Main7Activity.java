package mx.edu.ittepic.tpdm_u2_practica2_duranocampomiguelangel;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main7Activity extends AppCompatActivity {
    EditText id,modelo,marca,anio,fechainicio,precio;
    Spinner iddueno,tipopoliza;
    Button modificar,eliminar,regresar;
    String idduenoae;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        modelo=findViewById(R.id.modeloae);
        id=findViewById(R.id.idpolizaae);
        marca=findViewById(R.id.marcaae);
        anio=findViewById(R.id.anioae);
        fechainicio=findViewById(R.id.fechainicioae);
        precio=findViewById(R.id.precioae);
        iddueno=findViewById(R.id.spinnerduenosae);
        tipopoliza=findViewById(R.id.tipopolizaae);
        modificar=findViewById(R.id.modificarpolizaae);
        eliminar=findViewById(R.id.eliminarpolizaae);
        regresar=findViewById(R.id.regresarpolizaae);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int idcoche=getIntent().getExtras().getInt("idcoche");
        id.setText(idcoche+"");
        String modeloae=getIntent().getExtras().getString("modelo");
        mensaje(modeloae);
        String marcaae=getIntent().getExtras().getString("marca");
        int anioae=getIntent().getExtras().getInt("anio");
        String fechainicioae=getIntent().getExtras().getString("fechainicio");
        float precioae=getIntent().getExtras().getFloat("precio");
        String tipopolizaae=getIntent().getExtras().getString("tipopoliza");
        idduenoae=getIntent().getExtras().getString("iddueno");
        int tpoliza=0;
        switch (tipopolizaae){
            case "Integral":
                tpoliza=0;
                break;
            case "Amplia":
                tpoliza=1;
                break;
            case "Limitada":
                tpoliza=2;
                break;
            case "Responsabilidad Civil":
                tpoliza=3;
                break;
        }

        modelo.setText(modeloae);
        marca.setText(marcaae);
        anio.setText(anioae+"");
        fechainicio.setText(fechainicioae);
        precio.setText(precioae+"");
        tipopoliza.setSelection(tpoliza);
        iddueno.setPrompt(iddueno+"");
        Dueno dueno=new Dueno(Main7Activity.this);
        Dueno duenos[]=dueno.consultar();
        String d[]=new String[duenos.length];
        for (int i=0;i<d.length;i++){
            d[i]=duenos[i].id;
        }
        ArrayAdapter<String> adap=new ArrayAdapter<String>(Main7Activity.this,android.R.layout.simple_list_item_1,d);
        iddueno.setAdapter(adap);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Poliza poliza=new Poliza(Main7Activity.this);
                String []selec=iddueno.getSelectedItem().toString().split("-");
                boolean seModifico=poliza.actualizar(new Poliza(Integer.parseInt(id.getText().toString()),modelo.getText().toString(),marca.getText().toString(),Integer.parseInt(anio.getText().toString()),fechainicio.getText().toString(),Float.parseFloat(precio.getText().toString()),tipopoliza.getSelectedItem().toString(),selec[0]));
                if (seModifico){
                    mensaje("Se modifico exitosamente");
                    deshabilitar(false);
                }
                else{
                    mensaje("Error, no se modifico");
                }
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Poliza poliza=new Poliza(Main7Activity.this);
                boolean seElimino=poliza.eliminar(new Poliza(Integer.parseInt(id.getText().toString()),"","",0,"",0,"",""));
                if (seElimino){
                    mensaje("Se elimino correctamente");
                    deshabilitar(false);
                }
                else{
                    mensaje("Error: No se elimino");
                }
            }
        });
    }

    private void deshabilitar(boolean estado) {
        id.setEnabled(estado);
        modelo.setEnabled(estado);
        marca.setEnabled(estado);
        anio.setEnabled(estado);
        fechainicio.setEnabled(estado);
        precio.setEnabled(estado);
        tipopoliza.setEnabled(estado);
        iddueno.setEnabled(estado);
        modificar.setEnabled(estado);
        eliminar.setEnabled(estado);
    }

    private void mensaje(String mensaje){
        Toast.makeText(Main7Activity.this,mensaje,Toast.LENGTH_LONG).show();
    }
    protected void onStart(){
        super.onStart();
        int pos=0;
        Dueno p=new Dueno(Main7Activity.this);
        Dueno duenos[]=p.consultar();
        for (int i=0;i<duenos.length;i++){
            if (idduenoae.equals(duenos[i].id)){
                pos=i;
            }
        }
        iddueno.setSelection(pos);
    }
}
