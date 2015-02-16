package com.example.medidores;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ExportActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_export_acvtivity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.export_acvtivity, menu);
		return true;
	}
	
	public void exportaBase(View view) throws IOException{
		
		BaseDatos base = new BaseDatos(this,"Prueba",null,1);						
		base.ExportToCSV();
		 
	}
	
}
