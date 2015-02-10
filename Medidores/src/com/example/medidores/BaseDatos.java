package com.example.medidores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseDatos extends SQLiteOpenHelper {
	
		//private static String DB_PATH = "/data/data/com.example.medidores/databases/";
	    //private static String DB_NAME = "BaseDatos";
	    private SQLiteDatabase myDataBase;
	    private static Context myContext;
	 
	    public BaseDatos(Context contexto, String nombre, CursorFactory factory,int version) {
	 
	        super(contexto, nombre, factory, version);
	        this.myContext = contexto;
	    }
	 
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	    	
	    	db.execSQL("CREATE TABLE INDIVIDUOS (_id INTEGER PRIMARY KEY, nombre TEXT, calle TEXT, altura INTEGER, rutamedidor INTEGER, lecactual INTEGER, lecanterior INTEGER, consumo INTEGER,estadomedidor TEXT, control INTEGER)");
	    	//db.execSQL("CREATE TABLE estadomedidor(_id INTEGER PRIMARY KEY,descripcion TEXT)");
	      // db.execSQL("CREATE TABLE SOCIOS (_id INTEGER PRIMARY KEY,nrosoc INTEGER NOT NULL ,apeynom TEXT ,domicilio TEXT NULL ,codpost INTEGER NULL ,tipodoc TEXT NULL ,nrodoc TEXT NULL ,telefono TEXT NULL ,tipsoc INTEGER NULL ,estado INTEGER NULL)");
	      // db.execSQL("CREATE TABLE CUENTAS (_id INTEGER PRIMARY KEY,nrocta INTEGER NOT NULL ,nrosoc INTEGER NOT NULL ,calle TEXT NOT NULL ,altura INTEGER NOT NULL ,bis TEXT NULL ,pisodpto TEXT NULL ,rutacorr INTEGER NOT NULL ,rutamedi INTEGER NOT NULL ,condiva TEXT NOT NULL ,fecalt TEXT NULL ,estado INTEGER NULL ,lecvie INTEGER NOT NULL ,lecant INTEGER NOT NULL ,lecact INTEGER NOT NULL ,lectom INTEGER NULL ,fecvie TEXT NULL ,fecant TEXT NULL ,fecact TEXT NULL ,fectom TEXT NULL ,saldo REAL NULL ,domcta TEXT NULL ,nrocuit TEXT NULL ,perade INTEGER NULL ,ccs INTEGER NULL ,serv_a_hab TEXT NULL ,serv_c_red TEXT NULL ,serv_c_con TEXT NULL )");
	    }
	 
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // Cuando haya cambios en la estructura deberemos
	    	//db.execSQL("DROP TABLE IF EXISTS INDIVIDUOS");
	    	
	    }
	 
	    public void insertarCONTACTO(int id, String nom, String calle, int altura,int rutamedidor,int leactual,int leanterior,int consumo,String estado) {
	        
	    	SQLiteDatabase db = getWritableDatabase();
	      
	    	if(db != null){
	            ContentValues valores = new ContentValues();
	            valores.put("_id", id);
	            valores.put("nombre", nom);
	            valores.put("calle", calle);
	            valores.put("altura", altura);
	            valores.put("rutamedidor", rutamedidor);
	            valores.put("lecactual", leactual);
	            valores.put("lecanterior", leanterior);
	            valores.put("consumo", consumo);
	            valores.put("estadomedidor", estado);
	            db.insert("INDIVIDUOS", null, valores);
	            db.close();   
	        }
	    }
	    
	  public void ActualizarLectura(int id,String descripcion,int lectura){
		  
		  SQLiteDatabase db = getWritableDatabase();
		  
		  	
		  	ContentValues valores = new ContentValues();
		  	valores.put("estadomedidor", descripcion);
		  	valores.put("lecactual", lectura);
	        db.update("INDIVIDUOS", valores, "_id="+id, null);
	        db.close();   
	        
	    
		  }
	    
	   
	    
	  /*  private static void copiarBaseDatos() {
	        
	    	String ruta = "/data/data/com.example.sqlite/databases/";
	        String archivo = "BaseDatosUltima.db";
	        File archivoDB = new File(ruta + archivo);
	        if (!archivoDB.exists()) {
	        try {
	            InputStream IS = myContext.getApplicationContext().getAssets().open(archivo);
	            OutputStream OS = new FileOutputStream(archivoDB);
	            byte[] buffer = new byte[1024];
	            int length = 0;
	            while ((length = IS.read(buffer))>0){
	                OS.write(buffer, 0, length);
	            }
	            OS.flush();
	            OS.close();
	            IS.close();
	        } catch (FileNotFoundException e) {
	            Log.e("ERROR", "Archivo no encontrado, " + e.toString());
	        } catch (IOException e) {
	            Log.e("ERROR", "Error al copiar la Base de Datos, " + e.toString());
	        }
	    }
	

}*/

}
