package com.example.medidores;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class IngresoConsumo extends Activity {

	private TextView tv1;
	private TextView tv2;
	private TextView tv4;
	private ArrayAdapter<String> adapter;
	private Spinner spi ;
	private EditText et1;
	private int idfinal;
	String[] arrayEstados = new String[]{"OK","Medidor Roto","Tapado","Ilegible","Sin medidor"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingreso_consumo);
		
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		et1 = (EditText)findViewById(R.id.editText1);
		spi = (Spinner)findViewById(R.id.spinner1);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayEstados);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spi.setAdapter(adapter);
		Bundle bundle = getIntent().getExtras();
		
		String nombre = bundle.getString("nombre").toString();
		int id = bundle.getInt("id");
		idfinal = id;
		int lecanterior = bundle.getInt("lecturaanterior");
		String calle = bundle.getString("calle").toString();
		int altura = bundle.getInt("altura");
		
		tv1.setText(nombre);
		tv2.setText(calle +" "+altura);
		//tv4.setText("Lectura anterior: "+lecanterior);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ingreso_consumo, menu);
		return true;
	}
	
	public void guardar(View view){
		
		BaseDatos base = new BaseDatos(this,"Prueba",null,1);
		int lectura = Integer.parseInt(et1.getText().toString());
		String estado = spi.getSelectedItem().toString();
		base.ActualizarLectura(idfinal, estado, lectura);
		
		
		
	}

}
