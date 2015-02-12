package com.example.medidores;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class IngresoConsumo extends Activity {

	private TextView tv_nombre;
	private TextView tv_calle_altura;
//	private TextView tv4;
	private ArrayAdapter<String> adapter;
	private EditText et_lecActual;
	private Spinner spinner_estado;
	private Button button_guardar_lectura;
	private int idfinal;
	private int rutamedidor;
	private String orden;
	String[] arrayEstados = new String[]{"OK","Medidor Roto","Tapado","Ilegible","Sin medidor"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingreso_consumo);
		
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
		rutamedidor = bundle.getInt("rutamedidor");
		orden = bundle.getString("orden");
		
		idfinal = id;	// �por que no guardas directamente en idfinal?
		
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
	
	
	// METODO DEL BOTON
	
	public void guardar(View view){
		
			final BaseDatos base = new BaseDatos(this,"Prueba",null,1);
			final SQLiteDatabase db = base.getWritableDatabase();
			
			// recupero el valor del editText
			String lect = et_lecActual.getText().toString();
			
			// verifico que no sea un string vacio
			if (TextUtils.isEmpty(lect)) {
			     Toast.makeText(this, "Debe ingresar el consumo", 
			             Toast.LENGTH_SHORT).show();
			} else {
				
				// parseo el valor del editText como int para utilizarlo
				final int lectura = Integer.parseInt(lect);
				
				final String estado = spinner_estado.getSelectedItem().toString();
				Cursor fila= db.rawQuery("select _id,nombre,calle,altura,rutamedidor,lecactual,lecanterior,consumo,estadomedidor  from INDIVIDUOS where _id="+idfinal,null);
				fila.moveToFirst();
				final int lecanterior = fila.getInt(6);
			

			
				// VERIFICA SI LA LECTURA INGRESADA ES MENOR A LA LECTURA ANTERIOR
				if(lectura < lecanterior){
				
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
				
				}else{
				
					base.ActualizarLectura(idfinal, estado, lectura,lecanterior);
					db.close();
					finish();
				
				}
				
			}

			
	}

}