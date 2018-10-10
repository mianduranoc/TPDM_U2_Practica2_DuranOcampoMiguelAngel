package mx.edu.ittepic.tpdm_u2_practica2_duranocampomiguelangel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Poliza {
    BaseDatos base;
    int idcoche, anio;
    String modelo,marca,tipopoliza,iddueno,fechainicio;
    float precio;
    public Poliza(Activity activity){
        base=new BaseDatos(activity,"segurocoche",null,1);
    }
    public Poliza(int id,String modelo,String marca, int anio,String fechainicio,float precio,String tipopoliza,String iddueno){
        this.idcoche=id;
        this.modelo=modelo;
        this.marca=marca;
        this.anio=anio;
        this.fechainicio=fechainicio;
        this.precio= precio;
        this.tipopoliza=tipopoliza;
        this.iddueno=iddueno;
    }
    public boolean insertar(Poliza dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            ContentValues valores=new ContentValues();
            valores.put("MODELO",dato.modelo);
            valores.put("MARCA",dato.marca);
            valores.put("ANIO",dato.anio);
            valores.put("FECHAINICIO",dato.fechainicio);
            valores.put("PRECIO",dato.precio);
            valores.put("TIPOPOLIZA",dato.tipopoliza);
            valores.put("IDDUENO",dato.iddueno);
            long resultado=db.insert("POLIZA","IDCOCHE",valores);
            if (resultado<0)return false;
            return true;
        }catch (SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Poliza dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            String campos[]={dato.idcoche+""};
            long resultado=db.delete("POLIZA","IDCOCHE=?",campos);
            if (resultado<0)return false;
            return true;
        }catch (SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Poliza dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            ContentValues valores=new ContentValues();
            valores.put("IDCOCHE",dato.idcoche);
            valores.put("MODELO",dato.modelo);
            valores.put("MARCA",dato.marca);
            valores.put("ANIO",dato.anio);
            valores.put("FECHAINICIO",dato.fechainicio);
            valores.put("PRECIO",dato.precio);
            valores.put("TIPOPOLIZA",dato.tipopoliza);
            valores.put("IDDUENO",dato.iddueno);
            String campos[]={dato.idcoche+""};
            long resultado=db.update("POLIZA",valores,"IDCOCHE=?",campos);
            if (resultado<0)return false;
            return true;
        }catch (SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }

    public Poliza[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM POLIZA",null);
            if (c.moveToFirst()){
                Poliza []polizas=new Poliza[c.getCount()];
                int pos=0;
                do{
                    polizas[pos]=new Poliza(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getString(4),c.getFloat(5),c.getString(6),c.getString(7));
                    pos++;
                }while(c.moveToNext());
                return polizas;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }


}
