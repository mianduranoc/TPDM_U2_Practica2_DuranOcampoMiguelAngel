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

public class Main2Activity extends AppCompatActivity {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main2Activity.this,Main4Activity.class);
                startActivity(i);
            }
        });
        lista=findViewById(R.id.listaduenos);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(Main2Activity.this);
                Dueno dueno=new Dueno(Main2Activity.this);
                final Dueno duenos[]=dueno.consultar();
                final int pos=position;
                alerta.setTitle("Detalle de operacion")
                        .setMessage("Deseas modificar/eliminar los datos de:"+duenos[pos].nombre)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(Main2Activity.this,Main6Activity.class);
                                i.putExtra("id",duenos[pos].id);
                                i.putExtra("nombre",duenos[pos].nombre);
                                i.putExtra("domicilio",duenos[pos].domicilio);
                                i.putExtra("telefono",duenos[pos].telefono);
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
        Dueno dueno=new Dueno(this);
        Dueno duenos[]=dueno.consultar();
        String vacio[]={};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vacio);
        lista.setAdapter(adap);
        if (duenos==null){
            Toast.makeText(Main2Activity.this,"No hay registros",Toast.LENGTH_LONG).show();
        }
        else{
            String NoExp[] = new String[duenos.length];
            for (int i = 0; i < NoExp.length; i++) {
                NoExp[i] = duenos[i].id + "\n" + duenos[i].nombre;
            }
            ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NoExp);
            lista.setAdapter(adapt);
        }
    }

}
