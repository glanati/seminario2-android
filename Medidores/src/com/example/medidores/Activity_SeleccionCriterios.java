package com.example.medidores;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_SeleccionCriterios extends Activity {

	private Spinner selec_ruta;
	private RadioButton radio_asc;
	private RadioButton radio_desc;

	private ArrayAdapter<String> adapter;
	private AdaptadorSpinner adaptador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.seleccion_criterios);
		
		BaseDatos base = new BaseDatos(this,"Prueba",null,1);		
		ArrayList arrayRutas = base.ObtenerRutas();		
		
		//hace que la pantalla se mantenga en modo Portrait
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


		selec_ruta = (Spinner)findViewById(R.id.spinner1);
		radio_asc = (RadioButton)findViewById(R.id.radio_asc);
		radio_desc = (RadioButton)findViewById(R.id.radio_desc);

		//adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayRutas);
		adaptador = new AdaptadorSpinner(this,arrayRutas);
		adaptador.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		selec_ruta.setAdapter(adaptador);
		radio_asc.setChecked(true);
	}

	public void ingresolecturas(View view) {
	      	
		 	try {
				
		 		// creamos el elemento de comunicacion entre el activity_seleccionCriterios y el activity_ingreso_lecturas
			 	Intent i = new Intent(this, IngresoLecturas.class);
			 	
			 	// mediante el intent pasamos la información
		        i.putExtra("rutamedidor", selec_ruta.getSelectedItem().toString());
		        String sele = "";
		       
		        if(radio_asc.isChecked()){
		       
		        	sele = "asc";
		        
		        }else if(radio_desc.isChecked()){
		        	
		        	sele = "desc";
		        
		        }
		        
		        i.putExtra("orden", sele);
		        startActivity(i);
		 		
			} catch (Exception e) {
				
				Toast.makeText(getApplicationContext(), "La base de datos esta vacía, debe importar registros primero.", Toast.LENGTH_SHORT).show();
				finish();
				
				Intent i = new Intent(this, ImportActivity.class );
				startActivity(i);
				
			}
	 }
	
}
