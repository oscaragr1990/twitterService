package monitor.twitter.servicio.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import monitor.twitter.servicio.config.TwitterConfig;
import monitor.twitter.servicio.domain.CoordenadaGeografica;
import monitor.twitter.servicio.domain.Localizacion;
import monitor.twitter.servicio.domain.Tuit;
import monitor.twitter.servicio.domain.Usuario;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.Query.Unit;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

@Component
public class UtilidadTwitter {

	private Twitter twitter;

	public UtilidadTwitter() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(TwitterConfig.class);
		twitter = (Twitter) context.getBean("twitterSource");
		context.close();
	}

	public List<Map<String, Object>> buscarTuits(String inConcepto, GeoLocation inGeoLocation, int inRadio,
			Unit inUnidad) {

		List<Tuit> listaTuits = new ArrayList<Tuit>();
		Query query = new Query(inConcepto);
		query.setGeoCode(inGeoLocation, inRadio, Query.KILOMETERS);
		query.setCount(100);
		System.out.println(inConcepto);

		try {

			int i = 0;
			QueryResult result = null;
			do {
				try {
					i++;
					result = twitter.search(query);
					System.out.println("pagina:" + i + " Cant" + result.getTweets().size());
					Tuit tuit;
					Usuario usuario;
					Localizacion localizacion;
					for (Status status : result.getTweets()) {

						if (!status.isRetweet()) {
							tuit = new Tuit();
							tuit.setFechaPublicacion(status.getCreatedAt());
							tuit.setTexto(status.getText());
							tuit.setConteoFavoritos(status.getFavoriteCount());
							tuit.setConteoRetweet(status.getRetweetCount());
							// Usuario
							usuario = new Usuario();
							usuario.setConteoAmigos(status.getUser().getFriendsCount());
							usuario.setConteoFavoritos(status.getUser().getFavouritesCount());
							usuario.setConteoSeguidores(status.getUser().getFollowersCount());
							usuario.setNick(status.getUser().getScreenName());
							usuario.setUrl(status.getUser().getURL());
							tuit.setUsuario(usuario);

							// Ubicacion
							if (status.getPlace() != null) {
								localizacion = new Localizacion();
								localizacion.setLugar(status.getPlace().getFullName());
								localizacion.setCoordenadaGeografica(new CoordenadaGeografica(
										status.getGeoLocation().getLongitude(), status.getGeoLocation().getLatitude()));
								tuit.setLocalizacion(localizacion);
							}

							listaTuits.add(tuit);
						}
					}
					query = result.nextQuery();

				} catch (Exception e) {
					result = null;
					System.out.println(e.getMessage());
				}
			} while ((result != null && result.hasNext())|| listaTuits.size() < 200);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(listaTuits.size());

		return UtilidadConversionDatos.convertirListaAListaDeMapas(listaTuits);

	}
}
