package mx.edu.ittepic.tpdm_u2_practica2_duranocampomiguelangel;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {
    EditText id,nombre,telefono,domicilio;
    Button modificar,eliminar,regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        id=findViewById(R.id.idduae);
        nombre=findViewById(R.id.nombreduae);
        telefono=findViewById(R.id.telefonoduae);
        domicilio=findViewById(R.id.domicilioduae);
        modificar=findViewById(R.id.modificarduenoae);
        eliminar=findViewById(R.id.eliminarduenoae);
        regresar=findViewById(R.id.regresarae);

        String iddueno=getIntent().getExtras().getString("id");
        String nombredueno=getIntent().getExtras().getString("nombre");
        String domiciliodueno=getIntent().getExtras().getString("domicilio");
        String telefonodueno=getIntent().getExtras().getString("telefono");

        id.setText(iddueno);
        nombre.setText(nombredueno);
        domicilio.setText(domiciliodueno);
        telefono.setText(telefonodueno);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dueno dueno=new Dueno(Main6Activity.this);
                boolean seModifico=dueno.actualizar(new Dueno(id.getText().toString(),nombre.getText().toString(),domicilio.getText().toString(),telefono.getText().toString()));
                if (seModifico){
                    mensaje("Se modifico con exito");
                    deshabilitar(false);
                }
                else{
                   mensaje("Error: No se pudo modificar");
                }
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dueno dueno=new Dueno(Main6Activity.this);
                AlertDialog.Builder alerta=new AlertDialog.Builder(Main6Activity.this);
                alerta.setTitle("Atencion")
                        .setMessage("Esta accion eliminara las polizas asociadas,¿Desea Continuar?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean seElimino=dueno.eliminar(new Dueno(id.getText().toString(),"","",""));
                                if (seElimino){
                                    mensaje("Se elimino correctamente");
                                    deshabilitar(false);
                                }
                                else{
                                    mensaje("Error: No se elimino");
                                }
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
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void mensaje(String mensaje){
        Toast.makeText(Main6Activity.this,mensaje,Toast.LENGTH_LONG).show();
    }
    private void deshabilitar(boolean estado){
        id.setEnabled(estado);
        nombre.setEnabled(estado);
        domicilio.setEnabled(estado);
        telefono.setEnabled(estado);
        modificar.setEnabled(estado);
        eliminar.setEnabled(estado);
    }
}
