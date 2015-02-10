package com.example.medidores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class IngresoLecturas extends Activity {

	private int rutamedidor;
	private String orden;
	private BaseDatos db2;
	private ListView lv1;
	private ArrayAdapter<Registro> adapter ;
	private ArrayAdapter<String> adapter2;
	private ArrayList<Registro> array;
	private String[] prueba;
	int request_code = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_ingreso_lecturas);
				
			lv1 = (ListView) findViewById(R.id.listView1);
			Bundle bundle = getIntent().getExtras();
			rutamedidor = Integer.parseInt(bundle.getString("rutamedidor"));
			orden = bundle.getString("orden");
			BaseDatos base = new BaseDatos(this,"Prueba",null,1);
			SQLiteDatabase db = base.getWritableDatabase();
			
			
			final Cursor fila;
			Cursor consulta;
			View view = new View(this);
			
			if(orden.equals("asc")){
			
				
				fila= db.rawQuery("select _id,nombre,calle,altura,rutamedidor,lecactual,lecanterior,consumo,estadomedidor  from INDIVIDUOS where rutamedidor="+rutamedidor+" order by altura",null);
				
				
			}else{
				
				fila= db.rawQuery("select _id,nombre,calle,altura,rutamedidor,lecactual,lecanterior,consumo,estadomedidor  from INDIVIDUOS where rutamedidor="+rutamedidor+" order by altura DESC",null);
				
			}
			
			
	        fila.moveToFirst();
	        array = new ArrayList();
	       
	       
	        
	        do{
	        	
	        	Registro r = new Registro(fila.getInt(0),fila.getString(1),fila.getString(2),fila.getInt(3),fila.getInt(4),fila.getInt(5),fila.getInt(6),fila.getInt(7),fila.getString(8));
	        	array.add(r);
	        	
	        	
	        	
	        	//Toast.makeText(getApplicationContext(),fila.getString(1),Toast.LENGTH_SHORT).show();
	       
	        }while(fila.moveToNext());
	        
	        	adapter = new ArrayAdapter<Registro>(this,android.R.layout.simple_list_item_1,array);
	        	lv1.setAdapter(adapter);
	        	
	        	for (int i = 0; i < array.size(); i++) {
				
	        		Registro r = (Registro) lv1.getItemAtPosition(i);
	        		int lecactual = r.getLecactual();
	        		
	        		if(lecactual != 0){
	        			
	        			
	        			
	        		}
	        		
				}
	        	
	        	
	        	lv1.setOnItemClickListener(new OnItemClickListener() {
	                 @Override
	                 public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
	                     
	                	//Toast.makeText(getApplicationContext(),array.get(position).getNombre(),Toast.LENGTH_SHORT).show();
	                	 Intent in = new Intent();
	                	 in.putExtra("id",array.get(position).getId());
	                	 in.putExtra("calle",array.get(position).getCalle());
	                	 in.putExtra("altura",array.get(position).getAltura());
	                	 in.putExtra("nombre",array.get(position).getNombre());
	                	 in.putExtra("lecturaanterior",array.get(position).getLecanterior());
	                     in.setClass(getApplicationContext(), IngresoConsumo.class);
	                     view.setBackgroundColor(Color.rgb(26, 192, 48));
	                     startActivity(in);
	                	 
	                    
	                 }
	             });
	        	
	        	
	        	
	        	db.close();    
	        	
	        
	}
	        	
		
	
		
	
     @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ingreso_lecturas, menu);
		
		return true;
	}
     
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         // TODO Auto-generated method stub
         if ((requestCode == request_code) && (resultCode == RESULT_OK)){
          
        	
         
         }
     }
	
	public void muestra(View view){
		
	/*	BaseDatos base = new BaseDatos(this,"BaseDatos",null,1);
		SQLiteDatabase db = base.getWritableDatabase();
		
        Cursor fila= db.rawQuery("select _id,nombre,calle,altura,rutamedidor,lecactual,lecanterior,consumo  from INDIVIDUOS where rutamedidor="+rutamedidor+" order by calle,altura",null);
        fila.moveToFirst();
        
        do{
        	
        	System.out.println(fila.getString(1));  
        	 Toast.makeText(getApplicationContext(),fila.getString(1),Toast.LENGTH_SHORT).show();
       
        }while(fila.moveToNext());
        
        	db.close();    
        
		}*/
		
		
	
}
}
