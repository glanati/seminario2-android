package com.example.medidores;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class Activity_SeleccionCriterios extends Activity {

	private EditText et_ruta_med;
	private Button button_ingreso_lecturas;
	private RadioButton radio_asc;
	private RadioButton radio_desc;
	private Spinner selec_ruta;
	private ArrayAdapter<String> adapter;
	
	private String[] arrayRutas = new String[]{"200","202","204","206"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seleccion_criterios);
		//et_ruta_med = (EditText) findViewById(R.id.et_ruta_med);
		button_ingreso_lecturas = (Button) findViewById(R.id.button_ingreso_lecturas);
		radio_asc = (RadioButton)findViewById(R.id.radio_asc);
		radio_desc = (RadioButton)findViewById(R.id.radio_desc);
		selec_ruta = (Spinner)findViewById(R.id.spinner1);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayRutas);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		selec_ruta.setAdapter(adapter);
		radio_asc.setChecked(true);
	}

	 public void ingresolecturas(View view) {
	      	
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
	    }
	 
	 public void cargaregistros(View view) {
		 	
		 	BaseDatos base = new BaseDatos(this,"Prueba",null,1);
			SQLiteDatabase db = base.getWritableDatabase();
		 	
			base.insertarCONTACTO(1, "Jeremias Osuna", "San Martin", 804, 200, 0, 589, 0,"OK");
			base.insertarCONTACTO(2, "Perez Raul", "San Martin", 125, 200, 0, 659, 0,"OK");
			base.insertarCONTACTO(3, "Fernadez Miriam", "San Martin", 75, 200, 0, 589, 0,"OK");
			base.insertarCONTACTO(4, "Marzino Juan", "San Martin", 523, 200, 0, 589, 0,"OK");
			base.insertarCONTACTO(5, "Mutti Leopoldo", "San Martin", 1114, 200, 0, 589, 0,"OK");
			base.insertarCONTACTO(6, "Matias Krannevitter", "San Martin", 2152, 200, 0, 589, 0,"OK");
			base.insertarCONTACTO(7, "Aldo Funes", "Mitre", 800, 202, 0, 589, 0,"OK");
			base.insertarCONTACTO(8, "Moricconi Ariel", "Mitre", 800, 202, 0, 589, 0,"OK");
			base.insertarCONTACTO(9, "Stelze Carlos", "Mitre", 800, 202, 0, 589, 0,"OK");
			base.insertarCONTACTO(10, "Di Maria Hernan", "Mitre", 800, 202, 0, 589, 0,"OK");
			base.insertarCONTACTO(11, "Tosco Maximiliano", "Mitre", 800, 202, 0, 589, 0,"OK");
			base.insertarCONTACTO(12, "Hernandez Maria", "Sarmiento", 526, 204, 0, 589, 0,"OK");
			base.insertarCONTACTO(13, "Soltre Mariana", "Sarmiento", 796, 204, 0, 589, 0,"OK");
			base.insertarCONTACTO(14, "Genova Gabriel", "Sarmiento", 103, 204, 0, 589, 0,"OK");
			base.insertarCONTACTO(15, "Lopez Lisandro", "Sarmiento", 15, 204, 0, 589, 0,"OK");
			base.insertarCONTACTO(16, "Ramirez Ruben", "Sarmiento", 963, 204, 0, 589, 0,"OK");
			base.insertarCONTACTO(17, "Mancuello Julio", "Sarmiento", 1532, 204, 0, 589, 0,"OK");
			base.insertarCONTACTO(18, "Maidana Roman", "Pellegrini", 302, 206, 0, 589, 0,"OK");
			base.insertarCONTACTO(19, "Moralez Jimena", "Pellegrini", 697, 206, 0, 589, 0,"OK");
			base.insertarCONTACTO(20, "Alfonso Martin", "Pellegrini", 254, 206, 0, 589, 0,"OK");
			base.insertarCONTACTO(21, "Mercado Herminia", "Pellegrini", 1369, 206, 0, 589, 0,"OK");
			base.insertarCONTACTO(22, "Vangioni Sara", "Cabral", 201, 206, 0, 589, 0,"OK");
			base.insertarCONTACTO(23, "Bernardi Gustavo", "Cabral", 696, 206, 0, 589, 0,"OK");
			base.insertarCONTACTO(24, "Mateo Luciano", "Cabral", 120, 206, 0, 589, 0,"OK");
			base.insertarCONTACTO(25, "Delgado Pablo", "Cabral", 474, 206, 0, 589, 0,"OK");
			base.insertarCONTACTO(26, "Pirinte Lucia", "Cabral", 1254, 206, 0, 589, 0,"OK");
	    }
	
	
}
