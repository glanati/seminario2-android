package com.example.medidores;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ImportActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_import);
		//hace que la pantalla se mantenga en modo Portrait
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	public void cargaregistros(View view) {
	 	
	 	BaseDatos base = new BaseDatos(this,"Prueba",null,1);
	 	
	 	base.ImportSociostoDB();
	 	base.ImportCuentastoDB();
		
		Toast.makeText(getApplicationContext(), "¡Importación exitosa!", Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(this, Activity_SeleccionCriterios.class );
	    startActivity(i);
    }

}
