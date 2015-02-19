package com.example.medidores;

public class Registro {

	private int nrocuenta;
	private String nombre;
	private String calle;
	private int altura;
	private int rutamedidor;
	private int lecanterior;	
	private int lecactual;	
	private int lecnueva;	
	private String estado;
	private int control;
	
	
	public Registro(int nrocuenta, String nombre, String calle, int altura,int rutamedidor, int lecanterior, int lecactual, int lecnueva, String estado) {
		
		this.nrocuenta = nrocuenta;
		this.nombre = nombre;
		this.calle = calle;
		this.altura = altura;
		this.rutamedidor = rutamedidor;
		this.lecanterior = lecanterior;
		this.lecactual = lecactual;		
		this.lecnueva = lecnueva;		
		this.estado = estado;
		this.control = 0;
	}

	
	public int getNrocuenta() {
		return nrocuenta;
	}
	
	public void setNrocuenta(int nrocuenta) {
		this.nrocuenta = nrocuenta;
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

	public int getLecnueva() {
		return lecnueva;
	}
	
	public void setLecnueva(int lectom) {
		this.lecnueva = lectom;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  nombre +"\n"+ calle +" "+ altura +"\nLectura actual: "+ lecactual +"\nLectura anterior: "+ lecanterior; 
	}
		
}