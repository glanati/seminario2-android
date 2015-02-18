package com.example.medidores;

public class Tool {
	
	private String titulo;
	private String sub;
	private String sub2;
		
	public Tool(String titulo, String sub,String sub2) {
		this.titulo = titulo;
		this.sub = sub;
		this.sub2 = sub2;
	}

	
	public String getSub2() {
		return sub2;
	}


	public void setSub2(String sub2) {
		this.sub2 = sub2;
	}


	public String getTitulo() {
		return titulo;
	}

	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
	public String getSub() {
		return sub;
	}

	
	public void setSub(String sub) {
		this.sub = sub;
	}
	
}
