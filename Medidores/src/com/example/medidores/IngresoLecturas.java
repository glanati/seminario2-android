package com.example.medidores;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class IngresoLecturas extends Activity {

	private int rutamedidor;
	private String orden;
	private ListView lv_domicilios;
	private Adaptador adapter;		// los adapters son una interfaz entre el modelo de datos y los controles de selección (textview, button, etc)
	private ArrayList<Registro> array;
	int request_code = 1;
	private int posicion;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
					
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_ingreso_lecturas);
			
			//hace que la pantalla se mantenga en modo Portrait
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				
			lv_domicilios = (ListView) findViewById(R.id.listView_domicilios);
			
			// recupero la informacion pasada en el intent
			Bundle bundle = this.getIntent().getExtras();
			rutamedidor = Integer.parseInt(bundle.getString("rutamedidor"));
			orden = bundle.getString("orden");
					
			BaseDatos base = new BaseDatos(this,"Prueba",null,1);			
			array = base.ObtenerLecturas(rutamedidor, orden);
			

	    	for (int i = 0; i < array.size(); i++) {
	        	
	        	// creamos el Arrayadapter para listar los registros cargados
	        //	adapter = new ArrayAdapter<Registro>(this,android.R.layout.simple_list_item_1,array);
	        	adapter = new Adaptador(this,array);
	        	// seteamos los valores del adapter en el ListView
	        	lv_domicilios.setAdapter(adapter);
	        	
	        	// acción que se realiza cuando se clickea en algun item del ListView
	        	lv_domicilios.setOnItemClickListener(new OnItemClickListener() {
	                 @Override
	                 public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
	                     
	                	//Toast.makeText(getApplicationContext(),array.get(position).getNombre(),Toast.LENGTH_SHORT).show();
	                	 Intent in = new Intent();
	                	 int currentPosition = lv_domicilios.getFirstVisiblePosition();
	                	// mediante el intent pasamos las información al activity_ingreso_consumo
	                	 in.putExtra("id",array.get(position).getNrocuenta());		//numero de cuenta
	                	 in.putExtra("calle",array.get(position).getCalle());
	                	 in.putExtra("altura",array.get(position).getAltura());
	                	 in.putExtra("nombre",array.get(position).getNombre());
	                	 in.putExtra("lecturaanterior",array.get(position).getLecactual()); // lecant
	                	 in.putExtra("orden", orden);
	                	 in.putExtra("rutamedidor", rutamedidor);
	                	 
	                	 
	                	 in.setClass(getApplicationContext(), IngresoConsumo.class);			// es lo mismo que haber seteado arriba Intent in = new Intent(this, IngresoConsumo.class) ???
	                     startActivity(in);
	                	   
	                 }
	             });	        	
	        	 
	   		}
	}
	        	

     @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ingreso_lecturas, menu);
		
		return true;
	}
     
     
     
     @Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		setContentView(R.layout.activity_ingreso_lecturas);
		
		lv_domicilios = (ListView) findViewById(R.id.listView_domicilios);
		
		
		// recupero al informacion pasada en el intent
		Bundle bundle = this.getIntent().getExtras();
		rutamedidor = Integer.parseInt(bundle.getString("rutamedidor"));
		orden = bundle.getString("orden");
				
		BaseDatos base = new BaseDatos(this,"Prueba",null,1);
		array = base.ObtenerLecturas(rutamedidor, orden);

    	for (int i = 0; i < array.size(); i++) {
        	
        	// creamos el Arrayadapter para listar los registros cargados
        	//adapter = new ArrayAdapter<Registro>(this,android.R.layout.simple_list_item_1,array);
        	adapter = new Adaptador(this,array);
        	// seteamos los valores del adapter en el ListView
        	
        	// seteamos los valores del adapter en el ListView
        	lv_domicilios.setAdapter(adapter);
        	
        	 	
        	// acción que se realiza cuando se clickea en algun item del ListView
        	lv_domicilios.setOnItemClickListener(new OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                     
                	//Toast.makeText(getApplicationContext(),array.get(position).getNombre(),Toast.LENGTH_SHORT).show();
                	 Intent in = new Intent();
                	 
                	// mediante el intent pasamos las información al activity_ingreso_consumo
                	 in.putExtra("id",array.get(position).getNrocuenta());		//numero de cuenta
                	 in.putExtra("calle",array.get(position).getCalle());
                	 in.putExtra("altura",array.get(position).getAltura());
                	 in.putExtra("nombre",array.get(position).getNombre());
                	 in.putExtra("lecturaanterior",array.get(position).getLecactual()); // lecant
                	 in.putExtra("orden", orden);
                	 in.putExtra("rutamedidor", rutamedidor);
                	 
                	 
                     in.setClass(getApplicationContext(), IngresoConsumo.class);			// es lo mismo que haber seteado arriba Intent in = new Intent(this, IngresoConsumo.class) ???
                     view.setBackgroundColor(Color.rgb(26, 192, 48));
                     startActivity(in);
                	   
                 }
             });
        
    	}

	}


//--------------------------------------------------------------------------------------------------------------

	@Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         // TODO Auto-generated method stub
         if ((requestCode == request_code) && (resultCode == RESULT_OK)){
          

         }
     }
	
}
