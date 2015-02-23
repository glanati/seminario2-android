package com.example.medidores;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorSpinner extends ArrayAdapter<Integer> {

	
	Activity context;
	private ArrayList<Integer> datos;
	
	AdaptadorSpinner(Activity context, ArrayList<Integer> datos) {
		super(context, R.layout.spinner_personalizado, datos);
		this.context = context;
		this.datos = datos;
	}

public View getView(int position, View convertView, ViewGroup parent){
		
		BaseDatos base = new BaseDatos(context,"Prueba",null,1);
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.spinner_personalizado, null);
		
		TextView lblTitulo = (TextView)item.findViewById(R.id.LblNumeroRuta);
		lblTitulo.setText("Ruta "+Integer.toString(datos.get(position)));
		
		boolean estado = base.ObtenerEstadoRuta(position);
		
		if(estado){
			
			item.setBackgroundColor(Color.rgb(59, 223, 95));
			
		}else{
			
			item.setBackgroundColor(Color.rgb(255, 122, 171));
			
		}
		
			
		return(item);
	
		}
	
}
