package com.example.medidores;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adaptador extends ArrayAdapter {

	Activity context;
	private ArrayList<Registro> datos;
	
		Adaptador(Activity context,ArrayList<Registro> datos) {
		super(context, R.layout.list_personalizada, datos);
		this.context = context;
		this.datos = datos;
	}
	
		public View getView(int position, View convertView, ViewGroup parent){
		
			int lecactual = datos.get(position).getLecactual();
			String estado = datos.get(position).getEstado();
			int lecanterior = datos.get(position).getLecanterior();
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.list_personalizada, null);
			TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
			lblTitulo.setText(datos.get(position).getNombre());
			TextView lblSubtitulo =(TextView)item.findViewById(R.id.LblSubtitulo);
			TextView lblSubtitulo2 =(TextView)item.findViewById(R.id.LblSubtitulo_2);
			lblSubtitulo.setText(datos.get(position).getCalle() + " " + datos.get(position).getAltura());
			lblSubtitulo2.setText("Lectura anterior: "+datos.get(position).getLecanterior());
			
			if(lecactual != 0){
				
				item.setBackgroundColor(Color.rgb(26, 192, 48));
				
			}
			
			
			
			if(estado.equals("Medidor Roto") || estado.equals("Sin medidor") ){
				
				item.setBackgroundColor(Color.rgb(235, 14, 14));
			
					}else if(estado.equals("Ilegible") || estado.equals("Tapado")){
				
						item.setBackgroundColor(Color.rgb(191, 214, 62));
					
						}else if(lecactual < lecanterior){
					
							item.setBackgroundColor(Color.rgb(235, 140, 38));
					
						}
			
			
			
			return(item);
			
			
		}

}
