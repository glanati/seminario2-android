package com.example.medidores;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Activity_SeleccionCriterios extends Activity {

	private EditText et1;
	private Button button1;
	private RadioButton radio0;
	private RadioButton radio1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seleccion_criterios);
		et1 = (EditText) findViewById(R.id.et1);
		button1 = (Button) findViewById(R.id.button1);
		radio0 = (RadioButton)findViewById(R.id.radio0);
		radio1 = (RadioButton)findViewById(R.id.radio1);
		
	}

	 public void ingresolecturas(View view) {
	      
		 	Intent i = new Intent(this, IngresoLecturas.class);
	        i.putExtra("rutamedidor", et1.getText().toString());
	        String sele = "";
	       
	        if(radio0.isChecked()){
	       
	        	sele = "asc";
	        
	        }else if(radio1.isChecked()){
	        	
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
