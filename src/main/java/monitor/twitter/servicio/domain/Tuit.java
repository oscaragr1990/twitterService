package monitor.twitter.servicio.domain;

import java.util.Date;

public class Tuit {

	private Usuario usuario;
	private Localizacion localizacion;
	private String texto;
	private int conteoFavoritos;
	private int conteoRetweet;
	private Date fechaPublicacion;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getConteoFavoritos() {
		return conteoFavoritos;
	}

	public void setConteoFavoritos(int conteoFavoritos) {
		this.conteoFavoritos = conteoFavoritos;
	}

	public int getConteoRetweet() {
		return conteoRetweet;
	}

	public void setConteoRetweet(int conteoRetweet) {
		this.conteoRetweet = conteoRetweet;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
	

}
