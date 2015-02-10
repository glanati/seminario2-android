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

	private TextView tv_nombre;
	private TextView tv_calle_altura;
//	private TextView tv4;
	private ArrayAdapter<String> adapter;
	private Spinner spinner_estado;
	private EditText et_lecActual;
	private int idfinal;
	String[] arrayEstados = new String[]{"OK","Medidor Roto","Tapado","Ilegible","Sin medidor"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingreso_consumo);
		
		tv_nombre = (TextView) findViewById(R.id.tv_nombre);
		tv_calle_altura = (TextView) findViewById(R.id.tv_calle_altura);
		et_lecActual = (EditText)findViewById(R.id.editText_lecActual);
		spinner_estado = (Spinner)findViewById(R.id.spinner_estado);
		
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayEstados);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinner_estado.setAdapter(adapter);
		
		// recupero la informacion pasada en el intent
		Bundle bundle = getIntent().getExtras();
		String nombre = bundle.getString("nombre").toString();
		int id = bundle.getInt("id");	// ID simula al numero de cuenta
		
		idfinal = id;	// ¿por que no guardas directamente en idfinal?
		
		//int lecanterior = bundle.getInt("lecturaanterior");		
		String calle = bundle.getString("calle").toString();
		int altura = bundle.getInt("altura");
		
		tv_nombre.setText(nombre);
		tv_calle_altura.setText(calle +" "+altura);
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
		int lectura = Integer.parseInt(et_lecActual.getText().toString());
		String estado = spinner_estado.getSelectedItem().toString();
		base.ActualizarLectura(idfinal, estado, lectura);
		
		
		
	}

}
