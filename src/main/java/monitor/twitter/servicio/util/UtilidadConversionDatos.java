package monitor.twitter.servicio.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

public class UtilidadConversionDatos {
	/**
	 * Se realiza la conversion de una clase a mapa
	 * 
	 * @param clase
	 * @return
	 */
	public static <T> Map<String, Object> convertirClaseAMapa(T clase) {
		TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
		};

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> datos = mapper.convertValue(clase, typeReference);

		return datos;
	}

	/**
	 * Convierte un objeto List a una lista de mapas
	 * 
	 * @param lista
	 * @return
	 */
	public static <T> List<Map<String, Object>> convertirListaAListaDeMapas(List<T> lista) {

		return lista.parallelStream().map(e -> convertirClaseAMapa(e)).collect(Collectors.toList());

	}

	/**
	 * Convierte un objeto Json a mapa
	 * 
	 * @param json
	 * @return
	 */
	public final static Map<String, String> convertirDesdeJsonAMapa(String json) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<Map<String, String>> typeRef = new TypeReference<Map<String, String>>() {
		};
		try {
			return mapper.readValue(json, typeRef);
		} catch (IOException e) {
			return Maps.newLinkedHashMap();
		}
	}

	/**
	 * Convierte un objeto Json a mapa de objetos
	 * 
	 * @param json
	 * @return
	 */
	public final static Map<String, Object> convertirDesdeJsonAObjeto(String json) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
		};
		try {
			return mapper.readValue(json, typeRef);
		} catch (IOException e) {
			return Maps.newLinkedHashMap();
		}
	}

	public final static <T> T jsonAObjeto(String json, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Convierte un objeto Json a una lista de mapa
	 * 
	 * @param json
	 * @return
	 */
	public final static List<Map<String, Object>> convertirDesdeJsonAListaMapa(String json) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Map<String, Object>>> typeRef = new TypeReference<List<Map<String, Object>>>() {
		};
		try {
			return mapper.readValue(json, typeRef);
		} catch (IOException e) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add(Maps.newLinkedHashMap());
			return list;
		}
	}

	public final static <T> List<T> convertirDesdeJsonALista(String json, Class<T> clazz) {
		if (Strings.isNullOrEmpty(json)) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			List<T> dtos = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
			return dtos;
		} catch (IOException e) {
			return null;
		}
	}

	public final static <T> List<T> jsonALista(String json, Class<T> clazz) {
		if (Strings.isNullOrEmpty(json)) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			List<T> dtos = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
			return dtos;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
