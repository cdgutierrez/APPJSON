package info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import com.google.gson.*;

public class Cotizacion {
	private static String APIKEY = "Q6jmxBc25BQI35Ude7RgoZaw";//"r19C4flcGvZ0dzy9qiVZVqKN"; expiro
	private static String DOLAR = "USD";
	private static String EURO = "EUR";
	private static String REAL = "BRL";
	
	
	public Cotizacion() {
		super();
	}
	
	public String obtenerCotizacion() {
		String cotizacion = parsearCotizacion();
		
		if(cotizacion != null)
			return cotizacion;

		return "Hubo un error en la fuente de información. Sepa disculpar";
	}
	
	private String parsearCotizacion() {
		String cadenaJson = obtenerJson();
		
		if(cadenaJson != null) {
			JsonParser jParser = new JsonParser();
			JsonElement jElemento = jParser.parse(cadenaJson);
			JsonArray jArray = jElemento.getAsJsonObject().get("quotes").getAsJsonArray();
			
			StringBuilder cotizaciones = new StringBuilder("");
			
			for (JsonElement jsonElement : jArray) {
				JsonObject objeto = jsonElement.getAsJsonObject();
				
				if(objeto.get("base_currency").isJsonPrimitive() && objeto.get("bid").isJsonPrimitive() && objeto.get("ask").isJsonPrimitive()) {
					cotizaciones.append(
							"Cotización para " + objeto.get("base_currency").getAsString() + "\n"
							 + "Compra: " + objeto.get("bid").getAsString() + "\n"
							 + "Venta: " + objeto .get("ask").getAsString() + "\n\n"							
							);
				}
			}
			return cotizaciones.toString();
			
		}
		
		return null;
	}
	
	
	private String obtenerJson() {
		String dirUrl = "https://web-services.oanda.com/rates/api/v2/rates/spot.json?api_key=" + APIKEY + "&base=" + DOLAR + "&base=" + EURO + "&base=" + REAL + "&quote=ARS";
		try {
			URL url = new URL(dirUrl);
			try {
				InputStream iStream = url.openStream();
				BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream, Charset.forName("UTF-8")));
				int c;
				StringBuilder jString = new StringBuilder("");
				while( (c = bReader.read()) != -1)
					jString.append((char) c);
				
				return jString.toString();
				
			} catch (IOException e) {
				System.out.println("Error al obtener los datos del WebService: " + e.getMessage());
				return null;
			}
			
			
			
		} catch (MalformedURLException e) {
			System.out.println("Error al obtener los datos del WebService: " + e.getMessage());
			return null;
		}
		
	}
	
	
}
