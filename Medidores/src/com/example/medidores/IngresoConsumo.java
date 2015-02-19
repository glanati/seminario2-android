package com.example.medidores;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class IngresoConsumo extends Activity {

	private TextView tv_nombre;
	private TextView tv_calle_altura;
	private EditText et_lecActual;
	private Spinner spinner_estado;
	private Button button_guardar_lectura;
	
	private ArrayAdapter<String> adapter;
	
	private int idfinal;
	private int rutamedidor;
	private String orden;
	private int lecanterior;

	
	String[] arrayEstados = new String[]{"OK","Medidor Roto","Tapado","Ilegible","Sin medidor"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingreso_consumo);
		
		//hace que la pantalla se mantenga en modo Portrait
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		tv_nombre = (TextView) findViewById(R.id.tv_nombre);
		tv_calle_altura = (TextView) findViewById(R.id.tv_calle_altura);
		et_lecActual = (EditText)findViewById(R.id.editText_lecActual);
		spinner_estado = (Spinner)findViewById(R.id.spinner_estado);
		button_guardar_lectura = (Button)findViewById(R.id.button_guardar_lectura);
		
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayEstados);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinner_estado.setAdapter(adapter);
		
		// recupero la informacion pasada en el intent
		Bundle bundle = getIntent().getExtras();
		String nombre = bundle.getString("nombre").toString();
		int id = bundle.getInt("id");	// ID simula al numero de cuenta
		
		
		
		lecanterior = bundle.getInt("lecturaanterior");
		rutamedidor = bundle.getInt("rutamedidor");
		orden = bundle.getString("orden");
		
		idfinal = id;
				
		String calle = bundle.getString("calle").toString();
		int altura = bundle.getInt("altura");
		
		tv_nombre.setText(nombre);
		tv_calle_altura.setText(calle +" "+altura);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ingreso_consumo, menu);
		return true;
	}
		
	// Metodo del boton "Guardar lectura"
	public void guardar(View view){
		
			final BaseDatos base = new BaseDatos(this,"Prueba",null,1);
			final SQLiteDatabase db = base.getWritableDatabase();
			
			// recupero el valor del editText
			String lect = et_lecActual.getText().toString();
					
			final String estado = spinner_estado.getSelectedItem().toString();
		

			if (estado.equals("OK")) {
				
				// verifico que no sea un string vacio
				if (TextUtils.isEmpty(lect)) {
				     Toast.makeText(this, "Debe ingresar el consumo", 
				             Toast.LENGTH_SHORT).show();
				} else {
					
					// parseo el valor del editText como int para utilizarlo
					final int lectura = Integer.parseInt(lect);
					
					// Verifica si la lectura ingresada es menor a la lectura anterior
					if (lectura < lecanterior){
					
						AlertDialog.Builder dialog = new AlertDialog.Builder(this);
						dialog.setMessage("La lectura ingresada es menor que la anterior, desea ingresarla de todos modos?");
						dialog.setTitle("Advertencia");
						dialog.setCancelable(false);
						dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
						  
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								base.ActualizarLectura(idfinal, estado, lectura,lecanterior);
								db.close();
								finish();
								
							}
							
						 });
					
						dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
						 
							@Override
							public void onClick(DialogInterface dialog, int which) {
						      
								IngresoConsumo.this.finish();
							   
							}
							
						});
					
						dialog.create();
						dialog.show();
					
					} else {
						
						base.ActualizarLectura(idfinal, estado, lectura,lecanterior);
						db.close();
						finish();
						
					}
					
				}
				
			} else {	// Si el estado del medidor no es "OK" permite no ingresar un consumo y la lectura actual se indica igual a la anterior
				
				base.ActualizarLectura(idfinal, estado, lecanterior,lecanterior);
				db.close();
				finish();
				
			}
			
	}

}
