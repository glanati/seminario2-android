package com.example.medidores;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adaptador extends ArrayAdapter<Registro> {

	Activity context;
	private ArrayList<Registro> datos;
	
	Adaptador(Activity context, ArrayList<Registro> datos) {
		super(context, R.layout.list_personalizada, datos);
		this.context = context;
		this.datos = datos;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		
		int lecanterior = datos.get(position).getLecanterior();
		int lecactual = datos.get(position).getLecactual();
		String estado = datos.get(position).getEstado();	
		
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.list_personalizada, null);
		
		TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
		lblTitulo.setText(datos.get(position).getNombre());
		
		TextView lblSubtitulo =(TextView)item.findViewById(R.id.LblSubtitulo);
		lblSubtitulo.setText(datos.get(position).getCalle() + " " + datos.get(position).getAltura());
		
		TextView lblSubtitulo2 =(TextView)item.findViewById(R.id.LblSubtitulo_2);	
		lblSubtitulo2.setText("Lectura anterior: "+datos.get(position).getLecanterior());
		
		TextView lblSubtitulo3 =(TextView)item.findViewById(R.id.LblSubtitulo_3);	
		lblSubtitulo3.setText("Lectura actual: "+datos.get(position).getLecactual());
		
		TextView lblSubtitulo4 =(TextView)item.findViewById(R.id.LblSubtitulo_4);	
		lblSubtitulo4.setText("Consumo: "+datos.get(position).getConsumo());
		
		TextView lblSubtitulo5 =(TextView)item.findViewById(R.id.LblSubtitulo_5);	
		lblSubtitulo5.setText("Estado del medidor: "+datos.get(position).getEstado());
			
		if(lecactual != 0){
				
			item.setBackgroundColor(Color.rgb(26, 192, 48));	//color verde
				
		}
				
		if (estado.equals("Medidor Roto") || estado.equals("Sin medidor") ) {
				
			item.setBackgroundColor(Color.rgb(235, 14, 14));	//color rojo
			
				} else if (estado.equals("Ilegible") || estado.equals("Tapado")){
				
						item.setBackgroundColor(Color.rgb(46, 16, 236));	//color azul
					
						} else if ((lecactual < lecanterior) && (lecactual != 0)) {
					
							item.setBackgroundColor(Color.rgb(235, 140, 38));	//colo naranja
					
						}

			return(item);
	
		}

 }
