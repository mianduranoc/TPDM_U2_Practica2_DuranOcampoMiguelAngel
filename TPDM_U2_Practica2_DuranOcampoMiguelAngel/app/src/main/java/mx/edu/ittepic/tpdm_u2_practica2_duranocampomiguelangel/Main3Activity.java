package mx.edu.ittepic.tpdm_u2_practica2_duranocampomiguelangel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Main3Activity extends AppCompatActivity {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main3Activity.this,Main5Activity.class);
                startActivity(i);
            }
        });

        lista=findViewById(R.id.listapolizas);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(Main3Activity.this);
                Poliza poliza=new Poliza(Main3Activity.this);
                final Poliza polizas[]=poliza.consultar();
                final int pos=position;
                alerta.setTitle("Detalle de operacion")
                        .setMessage("Deseas modificar/eliminar los datos de:"+polizas[pos].idcoche)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(Main3Activity.this,Main7Activity.class);
                                i.putExtra("idcoche",polizas[pos].idcoche);
                                i.putExtra("marca",polizas[pos].marca);
                                i.putExtra("modelo",polizas[pos].modelo);
                                i.putExtra("anio",polizas[pos].anio);
                                i.putExtra("precio",polizas[pos].precio);
                                i.putExtra("fechainicio",polizas[pos].fechainicio);
                                i.putExtra("tipopoliza",polizas[pos].tipopoliza);
                                i.putExtra("iddueno",polizas[pos].iddueno);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });
    }
    protected void onStart(){
        super.onStart();
        Poliza poliza=new Poliza(this);
        Poliza polizas[]=poliza.consultar();
        String vacio[]={};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vacio);
        lista.setAdapter(adap);
        if (polizas==null){
            Toast.makeText(Main3Activity.this,"No hay registros",Toast.LENGTH_LONG).show();
        }
        else{
            String polizasArray[]=new String[polizas.length];
            for (int i=0;i<polizasArray.length;i++){
                polizasArray[i]=polizas[i].idcoche+"\n"+"Marca:"+polizas[i].marca+"\nModelo:"+polizas[i].modelo;
            }
            ArrayAdapter <String> adapt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,polizasArray);
            lista.setAdapter(adapt);
        }
    }
}
