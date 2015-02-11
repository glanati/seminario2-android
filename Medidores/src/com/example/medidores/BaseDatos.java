package com.example.medidores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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
	    
	  public void ActualizarLectura(final int id,final String descripcion,final int lectura,final int lecanterior){
		  
		  final SQLiteDatabase db = getWritableDatabase();
		  int valida = 0;
		  final ContentValues valores = new ContentValues();
		  
		  try{	
			  
			
			int consumo = lectura - lecanterior;
			valores.put("consumo", consumo);
		  	valores.put("estadomedidor", descripcion);
		  	valores.put("lecactual", lectura);
	        db.update("INDIVIDUOS", valores, "_id="+id, null);
	        db.close();  
		  
		  }catch(Exception e){
			  Toast.makeText(myContext,"Error en la carga de la lectura..",Toast.LENGTH_SHORT).show();
			  valida = 1;
			  e.printStackTrace();
		  }
		  
		  if(valida == 0){
			  
			  Toast.makeText(myContext,"Lectura cargada con exito!!",Toast.LENGTH_LONG).show();
			  
		  }
		  
		  }
	    	    

}
