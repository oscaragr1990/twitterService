package monitor.twitter.servicio.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import monitor.twitter.servicio.domain.Tuit;
import monitor.twitter.servicio.util.UtilidadTwitter;
import twitter4j.GeoLocation;
import twitter4j.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "twitter/tuits", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ServicioBusquedaPalabra {

	@Autowired
	private UtilidadTwitter utilidadBusqueda;
	private static final Logger logger = LoggerFactory.getLogger(ServicioBusquedaPalabra.class);

	/**
	 * Endoint para busqueda twitter por conceptos
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/concepto/{concepto}", method = RequestMethod.GET)
	public List<Map<String, Object>> obtenerTuitsXConcepto(@PathVariable("concepto") String concepto,
			@RequestParam(value = "localizacion", required = false) String[] inLocalizacion,
			@RequestParam(value = "radio", required = false) Integer inRadio) {
		GeoLocation geoLocationParam = null;

		if (inLocalizacion == null || inLocalizacion.length != 2) {
			geoLocationParam = new GeoLocation(4.00000, -72.0000000);
		} else {
			geoLocationParam = new GeoLocation(Double.parseDouble(inLocalizacion[0]), Double.parseDouble(inLocalizacion[1]));
		}

		if (inRadio == null || inRadio == 0) {
			inRadio = 1000;
		}
		logger.debug("Busqueda x Concepto -> Concepto=" + concepto + ", " + geoLocationParam + ", Radio =" + inRadio);

		return utilidadBusqueda.buscarTuits(concepto, geoLocationParam, inRadio, Query.KILOMETERS);
	}

}
