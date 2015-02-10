package com.example.medidores;

public class Registro {

	private int id;
	private String nombre;
	private String calle;
	private int altura;
	private int rutamedidor;
	private int lecactual;
	private int lecanterior;
	private int consumo;
	private String estado;
	private int control;
	
	
	public Registro(int id, String nombre, String calle, int altura,int rutamedidor, int lecactual, int lecanterior, int consumo,String estado) {
		
		this.id = id;
		this.nombre = nombre;
		this.calle = calle;
		this.altura = altura;
		this.rutamedidor = rutamedidor;
		this.lecactual = lecactual;
		this.lecanterior = lecanterior;
		this.consumo = consumo;
		this.estado = estado;
		this.control = 0;
	}


	public int getControl() {
		return control;
	}


	public void setControl(int control) {
		this.control = control;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public int getAltura() {
		return altura;
	}


	public void setAltura(int altura) {
		this.altura = altura;
	}


	public int getRutamedidor() {
		return rutamedidor;
	}


	public void setRutamedidor(int rutamedidor) {
		this.rutamedidor = rutamedidor;
	}


	public int getLecactual() {
		return lecactual;
	}


	public void setLecactual(int lecactual) {
		this.lecactual = lecactual;
	}


	public int getLecanterior() {
		return lecanterior;
	}


	public void setLecanterior(int lecanterior) {
		this.lecanterior = lecanterior;
	}


	public int getConsumo() {
		return consumo;
	}


	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  nombre +"\n"+ calle +" "+ altura +"\nLectura anterior: "+ lecanterior+"\nConsumo: "+consumo;
	}
	
	
	
	
}
