package info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;


import com.google.gson.*;


public class Noticia {
	static final String API_KEY = "60a89893e3e942729f371604d3579614";
	
	private String fuente;
	
	
	public Noticia() {
		this.fuente = "La Gaceta";
	}
	
	
	public String obtenerNoticia() {
		String noticias = parsearNoticia();
		
		if(noticias != null)
			return "Informativo de la actualidad: " + this.fuente + "\n\n" + noticias;

		return "Hubo un error en la fuente de información. Sepa disculpar";

	}
	
	
	private String parsearNoticia() {
		String cadenaJson = obtenerJson();
		
		if(cadenaJson != null) {
			JsonParser jParser = new JsonParser();
			JsonElement jElemento = jParser.parse(cadenaJson);
			JsonArray jArray = jElemento.getAsJsonObject().get("articles").getAsJsonArray();
	
			StringBuilder noticias = new StringBuilder("");
			for (JsonElement jsonElement : jArray) {
				JsonObject objeto = jsonElement.getAsJsonObject();
			
				if(objeto.get("title").isJsonPrimitive() && objeto.get("description").isJsonPrimitive() && objeto.get("url").isJsonPrimitive()) {
					noticias.append(
							objeto.get("title").getAsString() + "\n"
							+ objeto.get("description").getAsString() + "\n"
							+ objeto.get("url").getAsString() + "\n\n"				
							);
				}
					
			}
			
			return noticias.toString();
		}
		return null;
	}
	
	
	
	
	
	private String obtenerJson() {
		
		String url = "https://newsapi.org/v2/top-headlines?sources=la-gaceta&apiKey="+API_KEY;
		
		try {
			URL dirUrl = new URL(url);
			
			
			try {
				InputStream iStream = dirUrl.openStream();
				BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream, Charset.forName("UTF-8")));
				int c;
				StringBuilder jString = new StringBuilder();
				while( (c = bReader.read()) != -1)
					jString.append((char) c);
				
				return jString.toString();
				
			} catch (IOException e) {
				System.out.println("Error al obtener los datos del WebService: " + e.getMessage());
				return null;
			}
			
		} catch (MalformedURLException e) {
			System.out.println("Se produjo un error al intentar conectar al WebService de noticias: " + e.getMessage());
			return null;
		}
		
	}
	
}
