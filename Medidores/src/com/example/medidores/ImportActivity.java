package com.example.medidores;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ImportActivity extends Activity {
	
	private Button btn_import;
	private ProgressDialog pDialog;
	private MiTareaAsincronaDialog tarea;
	private AlertDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_import);
		
		//hace que la pantalla se mantenga en modo Portrait
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		btn_import = (Button)findViewById(R.id.btn_import);
		btn_import.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
		
				tarea = new MiTareaAsincronaDialog();
				tarea.execute();
				
			}
		});
	
	}
	
	public void cargaregistros() {
	 	
	 	BaseDatos base = new BaseDatos(this,"Prueba",null,1);
	 	
	 	base.ImportSociostoDB();
	 	base.ImportCuentastoDB();
		
    }
	
	private class MiTareaAsincronaDialog extends AsyncTask<Void, Integer, Boolean> {
    	
    	@Override
    	protected Boolean doInBackground(Void... params) {
    		
    		
				cargaregistros();
				return true;
    	}
    	
    /*	@Override
    	protected void onProgressUpdate(Integer... values) {
    		int progreso = values[0].intValue();
    		
    		pDialog.setProgress(progreso);
    	}*/
    	
    	@Override
    	protected void onPreExecute() {
    		
    	/*	pDialog = new ProgressDialog(ImportActivity.this);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.setTitle("Importando Base de datos");
			pDialog.setMessage("Este proceso puede tomar varios minutos");
			pDialog.setCancelable(false);
			pDialog.setIndeterminate(true);
			pDialog.show();*/
    		dialog = new AlertDialog.Builder(ImportActivity.this).create();
    		dialog.setMessage("Este proceso puede llevar varios minutos.. Aguarde por favor.");
			dialog.setTitle("Importando Base de Datos");
			dialog.setCancelable(false);
			dialog.show();

    	}
    	
    	@Override
    	protected void onPostExecute(Boolean result) {
    		if(result)
    		{
    			dialog.dismiss();
    			//pDialog.dismiss();
    			
    			Toast.makeText(ImportActivity.this, "¡Importación finalizada!", Toast.LENGTH_SHORT).show();
    		}
    	}
    	
    	@Override
    	protected void onCancelled() {
    		Toast.makeText(ImportActivity.this, "¡Importación cancelada!", Toast.LENGTH_SHORT).show();
    		finish();
    	}
    	
    }

}
