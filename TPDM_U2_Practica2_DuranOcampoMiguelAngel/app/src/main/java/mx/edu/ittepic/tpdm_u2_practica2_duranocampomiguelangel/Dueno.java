package mx.edu.ittepic.tpdm_u2_practica2_duranocampomiguelangel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Dueno {
    String id,nombre,domicilio,telefono;
    BaseDatos base;
    public Dueno(Activity activity){
        base=new BaseDatos(activity,"segurocoche",null,1);
    }
    public Dueno(String i,String nombre,String domicilio,String telefono){
        id=i;
        this.nombre=nombre;
        this.domicilio=domicilio;
        this.telefono=telefono;
    }
    public boolean insertar(Dueno dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            ContentValues valores=new ContentValues();
            valores.put("ID",dato.id);
            valores.put("NOMBRE",dato.nombre);
            valores.put("DOMICILIO",dato.domicilio);
            valores.put("TELEFONO",dato.telefono);
            long resultado=db.insert("DUENO",null,valores);
            if (resultado<0)return false;
            return true;
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminar(Dueno dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            Poliza p=new Poliza(new Activity());
            String campos[]={dato.id};
            long resultado2=db.delete("POLIZA","IDDUENO=?",campos);
            long resultado=db.delete("DUENO","ID=?",campos);
            if (resultado<0)return false;
            return true;
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean actualizar(Dueno dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            ContentValues valores=new ContentValues();
            valores.put("NOMBRE",dato.nombre);
            valores.put("DOMICILIO",dato.domicilio);
            valores.put("TELEFONO",dato.telefono);
            String args[]={dato.id};
            long resultado=db.update("DUENO",valores,"ID=?",args);
            if (resultado<0)return false;
            return true;
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }
    public Dueno[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM DUENO",null);
            if (c.moveToFirst()){
                Dueno[] duenos=new Dueno[c.getCount()];
                int pos=0;
                do{
                    duenos[pos]=new Dueno(c.getString(0),c.getString(1),c.getString(2),c.getString(3));
                    pos++;
                }while(c.moveToNext());
                return duenos;
            }
        }catch (SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
