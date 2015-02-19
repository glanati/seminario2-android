package com.example.medidores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
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
	    	
	       db.execSQL("CREATE TABLE SOCIOS (nrosoc INTEGER PRIMARY KEY ,apeynom TEXT ,domicilio TEXT NULL ,codpost INTEGER NULL ,tipodoc TEXT NULL ,nrodoc TEXT NULL ,telefono TEXT NULL ,tipsoc INTEGER NULL ,estado INTEGER NULL)");
	       db.execSQL("CREATE TABLE CUENTAS (nrocta INTEGER NOT NULL, nrosoc INTEGER NOT NULL, calle TEXT NOT NULL ,altura INTEGER NOT NULL ,bis TEXT NULL ,pisodpto TEXT NULL ,rutacorr INTEGER NOT NULL ,rutamedi INTEGER NOT NULL ,condiva TEXT NOT NULL ,fecalt TEXT NULL ,estado INTEGER NULL ,lecvie INTEGER NOT NULL ,lecant INTEGER NOT NULL ,lecact INTEGER NOT NULL ,lectom INTEGER NULL ,fecvie TEXT NULL ,fecant TEXT NULL ,fecact TEXT NULL ,fectom TEXT NULL ,saldo REAL NULL ,domcta TEXT NULL ,nrocuit TEXT NULL ,perade INTEGER NULL ,ccs INTEGER NULL ,serv_a_hab TEXT NULL ,serv_c_red TEXT NULL ,serv_c_con TEXT NULL, estadomed TEXT NULL, PRIMARY KEY (nrocta, nrosoc))");
	    }
	 
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // Cuando haya cambios en la estructura deberemos
	    	//db.execSQL("DROP TABLE IF EXISTS INDIVIDUOS");
	    	
	    }
	    
	    
	    public ArrayList<Registro> ObtenerLecturas(int rutamedidor, String orden) {
	        
	    	SQLiteDatabase db = getWritableDatabase();
	    	
	    	final Cursor fila;
	
	    	fila= db.rawQuery("SELECT C.nrocta, S.apeynom, C.calle, C.altura, C.rutamedi, C.lecant, C.lecact, C.lectom, C.estadomed FROM CUENTAS C INNER JOIN SOCIOS S ON S.nrosoc = C.nrosoc WHERE C.estado = 1 AND C.rutamedi =" + rutamedidor + " ORDER BY C.altura " + orden, null);
	    	
	    	fila.moveToFirst();
	        ArrayList <Registro> lecturas = new ArrayList<Registro>();
	        	        	       
	        while(fila.moveToNext()){
	        	
	        	Registro r = new Registro(fila.getInt(0),fila.getString(1),fila.getString(2),fila.getInt(3),fila.getInt(4),fila.getInt(5),fila.getInt(6),fila.getInt(7),fila.getString(8));
	        	lecturas.add(r);
	        		
			}		
	    
	    	db.close();	    	
	    	return lecturas;
	        	 
	    }
	    
	    public ArrayList<Integer> ObtenerRutas() {
	        
	    	SQLiteDatabase db = getWritableDatabase();
	    	
	    	final Cursor fila;
	
	    	fila = db.rawQuery("SELECT DISTINCT rutamedi FROM CUENTAS ORDER BY rutamedi ASC", null);
	    	
	    	fila.moveToFirst();
	        ArrayList<Integer> rutas = new ArrayList<Integer>();
	        	        	       
	        while(fila.moveToNext()){
	        	
	        	int rutamedi  = fila.getInt(0);
        		rutas.add(rutamedi);
	        		
			}		
	    
	    	db.close();	    	
	    	return rutas;
	        	 
	    }
	 
	    
	  public void ActualizarLectura(final int nrocta,final String estadomed, final int lectura, final int lecanterior) {
		  
		  Calendar c = Calendar.getInstance();
		  
		  int dia = c.get(Calendar.DAY_OF_MONTH);
		  String mes = new SimpleDateFormat("MMM").format(c.getTime());
		  int año = c.get(Calendar.YEAR);
		  
		  String fecha = dia + "-" +  mes + "-" + año;
  
		  final SQLiteDatabase db = getWritableDatabase();
		  int valida = 0;
		  final ContentValues valores = new ContentValues();
		  
		  try {	
			  	
			valores.put("fectom", fecha);
			valores.put("lectom", lectura);
		  	valores.put("estadomed", estadomed);
	        db.update("CUENTAS", valores, "nrocta=" + nrocta, null);
	        db.close();  
		  
		  } catch (Exception e){
			  Toast.makeText(myContext,"Error en la carga de la lectura",Toast.LENGTH_SHORT).show();
			  valida = 1;
			  e.printStackTrace();
		  }
		  
		  if(valida == 0){
			  
			  Toast.makeText(myContext,"¡Lectura cargada con éxito!",Toast.LENGTH_LONG).show();
			  
		  }
		  
	  }
	    	    
	  public void ImportSociostoDB() {
		  
		String fileName = "/SOCIOS.csv";
		  
		File sdCardDir = Environment.getExternalStorageDirectory();  
          	  
 		File readFile = new File(sdCardDir, fileName);
          
          	final SQLiteDatabase db = getWritableDatabase();
          
          
          	BufferedReader in  = null;
		  
	        try {
			
		     in = new BufferedReader(new FileReader(readFile));
			
	             String reader = "";         
        	  
			while ((reader = in.readLine()) != null)
			  
			  {
			      String[] RowData = reader.split(";");
			      	
			      ContentValues valores = new ContentValues();
			      
			      valores.put("nrosoc",    RowData[0]); 	 	         
			      valores.put("apeynom",    RowData[1]);
			      valores.put("domicilio",  RowData[2]);
			      valores.put("codpost",    RowData[3]);
			      valores.put("tipodoc",   	RowData[4]);
			      valores.put("nrodoc",     RowData[5]);
			      valores.put("telefono",   RowData[6]);
			      valores.put("tipsoc",     RowData[7]);
			      valores.put("estado", 	RowData[8]);

			      db.insert("SOCIOS", null, valores);		             
			     
			  }
			
			 in.close();
			 db.close();
          
	          } catch (IOException e) {
	        	  e.printStackTrace();          
          
        	  } 	         
          
	    }
	  
	  
	  public void ImportCuentastoDB() {
		  
		String fileName = "/CUENTAS.csv";
		  
		File sdCardDir = Environment.getExternalStorageDirectory();  
          	  
 		File readFile = new File(sdCardDir, fileName);
          
          	final SQLiteDatabase db = getWritableDatabase();
          
          
          	BufferedReader in  = null;
		  
	        try {
			
		     in = new BufferedReader(new FileReader(readFile));
			
	             String reader = "";         
        	  
			while ((reader = in.readLine()) != null)
			  
			  {
			      String[] RowData = reader.split(";");
			      	
			      ContentValues valores = new ContentValues();
			      
			     // if (RowData[10].equals("2")){
			     //	System.out.println("cuenta inactiva" + RowData[0]);
			     // }
			      
			      if (RowData[10].equals("1")){ //se importan sólo cuentas activas
			    	  
			    	  valores.put("nrocta",    RowData[0]);     	 	         
			    	  valores.put("nrosoc",    RowData[1]);        	 	         
			    	  valores.put("calle",      RowData[2]);
			    	  valores.put("altura",     RowData[3]);
			    	  valores.put("bis",        RowData[4]);        	 	         
			    	  valores.put("pisodpto",   RowData[5]);        	 	         
			    	  valores.put("rutacorr",   RowData[6]);        	 	         
			    	  valores.put("rutamedi",   RowData[7]);        	 	         
			    	  valores.put("condiva",    RowData[8]);        	 	         
			    	  valores.put("fecalt", 	RowData[9]);			      
			    	  valores.put("estado", 	RowData[10]);
			    	  valores.put("lecvie", 	RowData[11]);
			    	  valores.put("lecant", 	RowData[12]);
			    	  valores.put("lecact", 	RowData[13]);
			    	  valores.put("lectom", 	RowData[14]);
			    	  valores.put("fecvie", 	RowData[15]);
			    	  valores.put("fecant", 	RowData[16]);
			    	  valores.put("fecact", 	RowData[17]);
			    	  valores.put("fectom", 	RowData[18]);
			    	  valores.put("saldo", 		RowData[19]);
			    	  valores.put("domcta",		RowData[20]);
			    	  valores.put("nrocuit",	RowData[21]);
			    	  valores.put("perade",		RowData[22]);
			    	  valores.put("ccs",		RowData[23]);
			    	  valores.put("serv_a_hab",	RowData[24]);
			    	  valores.put("serv_c_red",	RowData[25]);
			    	  valores.put("serv_c_con",	RowData[26]);
			    	  valores.put("estadomed",	"");

			    	  db.insert("CUENTAS", null, valores);		             
			     
			      }
			      
			  }
			
			 in.close();
			 db.close();
          
	          } catch (IOException e) {
	        	  e.printStackTrace();          
          
        	  } 	         
          
	    }

	    public void ExportCuentasToCSV() throws IOException {  
		       
		    Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		    String TimeStampDB = sdf.format(cal.getTime());
		      
	    	String fileName = "/CUENTAS_"+TimeStampDB+".csv";
	    	String split = ";";
	    			
	    	final SQLiteDatabase sampleDB = getWritableDatabase();	    		
		    Cursor c = sampleDB.rawQuery("SELECT * FROM CUENTAS", null);

		    File sdCardDir = Environment.getExternalStorageDirectory();  
            File saveFile = new File(sdCardDir, fileName);  
            
            try{
            	
            FileOutputStream fOut = new FileOutputStream(saveFile);
	        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);	          

	         if (c != null) {
	              if (c.moveToFirst()) {
	                  do {
 
	                      int nrocta = c.getInt(c.getColumnIndex("nrocta"));
	                      int nrosoc = c.getInt(c.getColumnIndex("nrosoc"));	                      
	                      String calle = c.getString(c.getColumnIndex("calle"));
	                      int altura = c.getInt(c.getColumnIndex("altura"));
	                      String bis = c.getString(c.getColumnIndex("bis"));
	                      String pisodpto = c.getString(c.getColumnIndex("pisodpto"));
	                      int rutacorr = c.getInt(c.getColumnIndex("rutacorr"));
	                      int rutamedi = c.getInt(c.getColumnIndex("rutamedi"));
	                      String condiva = c.getString(c.getColumnIndex("condiva"));
	                      String fecalt = c.getString(c.getColumnIndex("fecalt"));	                      
	                      int estado = c.getInt(c.getColumnIndex("estado"));
	                      int lecvie = c.getInt(c.getColumnIndex("lecvie"));
	                      int lecant = c.getInt(c.getColumnIndex("lecant"));
	                      int lecact = c.getInt(c.getColumnIndex("lecact"));
	                      int lectom = c.getInt(c.getColumnIndex("lectom"));	                      
	                      String fecvie = c.getString(c.getColumnIndex("fecvie"));
	                      String fecant = c.getString(c.getColumnIndex("fecant"));	                      
	                      String fecact = c.getString(c.getColumnIndex("fecact"));
	                      String fectom = c.getString(c.getColumnIndex("fectom"));	                      
	                      double saldo = c.getDouble(c.getColumnIndex("saldo"));
	                      String domcta = c.getString(c.getColumnIndex("domcta"));
	                      String nrocuit = c.getString(c.getColumnIndex("nrocuit"));
	                      int perade = c.getInt(c.getColumnIndex("perade"));
	                      int ccs = c.getInt(c.getColumnIndex("ccs"));	                      
	                      String serv_a_hab = c.getString(c.getColumnIndex("serv_a_hab"));
	                      String serv_c_red = c.getString(c.getColumnIndex("serv_c_red"));
	                      String serv_c_con = c.getString(c.getColumnIndex("serv_c_con"));

	                      myOutWriter.append(nrocta + split + nrosoc + split + calle + split + altura + split + bis + split + pisodpto + split + rutacorr + split + rutamedi + split + condiva + split + fecalt + split + estado + split + lecvie + split + lecant + split + lecact + split + lectom + split + fecvie + split + fecant + split + fecact + split + fectom + split + saldo + split + domcta + split + nrocuit + split + perade + split + ccs + split + serv_a_hab + split + serv_c_red + split + serv_c_con);
	                      myOutWriter.append("\n");
	                  }

	                  while (c.moveToNext());
	              }

	              c.close();
	              myOutWriter.close();
	              fOut.close();

	          }
	          
	      } catch (SQLiteException se) {
	          Log.e(getClass().getSimpleName(),"No se puede abrir la base de datos");
	      }

	      finally {

	    	  sampleDB.close();
	          Toast.makeText(myContext, "Operacion realizada!", Toast.LENGTH_SHORT).show();

	      }
            
        }

	
}
