package monitor.twitter.servicio.domain;

public class Localizacion {
	private String lugar;
	private CoordenadaGeografica coordenadaGeografica;

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public CoordenadaGeografica getCoordenadaGeografica() {
		return coordenadaGeografica;
	}

	public void setCoordenadaGeografica(CoordenadaGeografica coordenadaGeografica) {
		this.coordenadaGeografica = coordenadaGeografica;
	}
}
