package com.example.medidores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	 public void lanzarSeleccionCriterios(View view) {
	        Intent i = new Intent(this, Activity_SeleccionCriterios.class );
	        startActivity(i);
	 }
	 
	 public void lanzarImportacion(View view) {
	        Intent i = new Intent(this, ImportActivity.class );
	        startActivity(i);
	 } 
	
	 public void lanzarExportacion(View view) {
	        Intent i = new Intent(this, ExportActivity.class );
	        startActivity(i);
	 } 
	 
}
