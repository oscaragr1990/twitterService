package monitor.twitter.servicio.domain;

public class Usuario {

	private String nick;
	private int conteoSeguidores;
	private int conteoAmigos;
	private int conteoFavoritos;
	private String url;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getConteoSeguidores() {
		return conteoSeguidores;
	}

	public void setConteoSeguidores(int conteoSeguidores) {
		this.conteoSeguidores = conteoSeguidores;
	}

	public int getConteoAmigos() {
		return conteoAmigos;
	}

	public void setConteoAmigos(int conteoAmigos) {
		this.conteoAmigos = conteoAmigos;
	}

	public int getConteoFavoritos() {
		return conteoFavoritos;
	}

	public void setConteoFavoritos(int conteoFavoritos) {
		this.conteoFavoritos = conteoFavoritos;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
