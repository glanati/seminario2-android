package com.example.medidores;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
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
	private MiTareaAsincronaDialog tarea2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_import);
		
		//hace que la pantalla se mantenga en modo Portrait
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		btn_import = (Button)findViewById(R.id.btn_import);
		btn_import.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				pDialog = new ProgressDialog(ImportActivity.this);
				pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pDialog.setMessage("Procesando...");
				pDialog.setCancelable(true);
				pDialog.setMax(100);
				
				tarea2 = new MiTareaAsincronaDialog();
				tarea2.execute();
				
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
    		
    		for(int i=1; i<=10; i++) {
				cargaregistros();
				
				publishProgress(i*10);
				
				if(isCancelled())
					break;
			}
    		
    		return true;
    	}
    	
    	@Override
    	protected void onProgressUpdate(Integer... values) {
    		int progreso = values[0].intValue();
    		
    		pDialog.setProgress(progreso);
    	}
    	
    	@Override
    	protected void onPreExecute() {
    		
    		pDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					MiTareaAsincronaDialog.this.cancel(true);
				}
			});
    		
    		pDialog.setProgress(0);
    		pDialog.show();
    	}
    	
    	@Override
    	protected void onPostExecute(Boolean result) {
    		if(result)
    		{
    			pDialog.dismiss();
    			Toast.makeText(ImportActivity.this, "¡Importación finalizada!", Toast.LENGTH_SHORT).show();
    		}
    	}
    	
    	@Override
    	protected void onCancelled() {
    		Toast.makeText(ImportActivity.this, "¡Importación cancelada!", Toast.LENGTH_SHORT).show();
    	}
    	
    }

}
