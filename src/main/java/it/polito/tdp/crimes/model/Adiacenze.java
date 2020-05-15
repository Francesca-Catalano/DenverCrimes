package it.polito.tdp.crimes.model;

public   class Adiacenze implements Comparable<Adiacenze> {
	private String a1;
	private String a2;
	private Double peso;
	
	
	public double getPeso() {
		return peso;
	}
	public String getA1() {
		return a1;
	}
	public String getA2() {
		return a2;
	}
	public Adiacenze(String a1, String a2, double peso) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.peso = peso;
	
	}
	

	
	  @Override public String toString() { return a1 + "  " + a2 + "  " + peso+
	  "\n" ; }
	@Override
	public int compareTo(Adiacenze o) {
		return Double.compare(o.getPeso(), this.peso);
	
	}
	
	 
	
	
	
	
	
	
	
	

}
