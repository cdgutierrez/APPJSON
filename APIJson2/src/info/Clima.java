package info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Clima {

	static private String KEY_API = "GMhtXHk1MjwYARF58CMA5rFtCrPqCGFI";//"nGg0fXyka1yuneJpzO7Hdi02CNTEGtgZ";
	static private String KEY_LOCALE_DEFAULT = "7188";
	/* KeyLocale = Ciudad
	 * 7188 = Isidro Casanova
	 * 7894 = CABA
	 * 7892 = La plata 
	 * 3196 = Corrientes
	 * 5967 = Rio Gallegos
	 */
	static private String CIUDAD_DEFAULT = "Buenos Aires";
	
	private JsonObject jsonCiudad;
	private int key;
	private String ciudad;

	public Clima() {
		super();
	}
	
	public Clima(int key) {
		super();
		this.key = key;
	}
	
	public Clima(String ciudad) {
		super();
		this.ciudad = ciudad;
	}
	
	
	public String obtenerClima() {
		String clima = climaPorKeyLocation(KEY_LOCALE_DEFAULT);
		if(clima != null)
			return clima;
		
		return "";
	}
	
	public String climaPorKeyLocation(String keyCiudad) {
		String cadenaClima  = obtenerClima(keyCiudad);
		if(cadenaClima != null) {
			JsonParser jParser = new JsonParser();
			JsonElement jElemento = jParser.parse(cadenaClima);
			String ciudad="";
			String provincia="";
			String pais="";
			// Siempre va a devolver 1 elemento por ser la KeyLocale única
			
			JsonObject objeto = jElemento.getAsJsonArray().get(0).getAsJsonObject();
			String tiempo = objeto.get("WeatherText").getAsString();
			String humedad = objeto.get("RelativeHumidity").getAsString();
			double temperatura = objeto.getAsJsonObject("Temperature").getAsJsonObject("Metric").get("Value").getAsDouble();
			char unidad = objeto.getAsJsonObject("Temperature").getAsJsonObject("Metric").get("Unit").getAsCharacter();
			double sensacionTermica = objeto.getAsJsonObject("RealFeelTemperature").getAsJsonObject("Metric").get("Value").getAsDouble();
			double viento = objeto.getAsJsonObject("Wind").getAsJsonObject("Speed").getAsJsonObject("Metric").get("Value").getAsDouble();				
			String unidad_viento = objeto.getAsJsonObject("Wind").getAsJsonObject("Speed").getAsJsonObject("Metric").get("Unit").getAsString();
			int nubosidad = objeto.get("CloudCover").getAsInt();
			if(jsonCiudad!=null){
			 ciudad = jsonCiudad.get("LocalizedName").getAsString();
			 provincia = jsonCiudad.getAsJsonObject("AdministrativeArea").get("LocalizedName").getAsString();
			 pais = jsonCiudad.getAsJsonObject("Country").get("LocalizedName").getAsString();
			}
			return jsonCiudad==null?(tiempo +"\nTemperatura: " + temperatura + " º" + unidad 
					+"\nSensación termica: " + sensacionTermica +" º" + unidad
					+"\nHumedad: " + humedad + "%"
					+"\nVientos: " + viento + " " + unidad_viento
					+"\nNubosidad: " + nubosidad + "%"):
					
					("Ciudad: "+ciudad+'\n' 
					+"Provincia: "+provincia+'\n'
					+"Pais: " +pais +'\n'+
					tiempo +
					"\nTemperatura: " + temperatura + " º" + unidad 
					+"\nSensación termica: " + sensacionTermica +" º" + unidad
					+"\nHumedad: " + humedad + "%"
					+"\nVientos: " + viento + " " + unidad_viento
					+"\nNubosidad: " + nubosidad + "%"
					);	
		}
		
		return null;
	}
	
	
	
	private String obtenerClima(String keyCiudad) {
		String dirUrl = "http://dataservice.accuweather.com/currentconditions/v1/" + keyCiudad + "?apikey=" + KEY_API + Lenguaje.ESPAÑOL;
		try {
			URL url = new URL(dirUrl);
			try {
				InputStream iStream = url.openStream();
				BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream, Charset.forName("UTF-8")));
				int c;
				StringBuilder jString = new StringBuilder();
				while( (c = bReader.read()) != -1)
					jString.append((char)c);
				
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
	
	public String buscarCiudad(String ciudad) {
		//Consigue un JSon con 1 o mas resultados de acuerdo a la ciudad que se ingreso
		String Url = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey="+ KEY_API +"&q=" + ciudad +"&language=es";
		String respuesta;
		try {
			URL url = new URL(Url);
			try {
				InputStream iStream = url.openStream();
				BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream, Charset.forName("UTF-8")));
				int c;
				StringBuilder jString = new StringBuilder();
				while( (c = bReader.read()) != -1)
					jString.append((char)c);
				
				respuesta=jString.toString();
				//Si no hay respuesta el JSon contiene [], caso contrario parseo los resultados para poder mostrarlos y que el usuario elija a cual se referia
				return respuesta.equals("[]")?"No hubo resultados":parsearResultados(jString.toString());
				
				
			} catch (IOException e) {
				System.out.println("Error al obtener los datos del WebService: " + e.getMessage());
				return null;
			}
			
		} catch (MalformedURLException e) {
			System.out.println("Error al obtener los datos del WebService: " + e.getMessage());
			return null;
		}
			
	}
	
	private String parsearResultados(String jsonresult) {
		JsonParser jParser = new JsonParser();
		JsonElement jsonConResultados = jParser.parse(jsonresult);
		String seleccion=null;
		String resultado=null;
		JsonArray arrayResultados = jsonConResultados.getAsJsonArray();
		//Por lo que se ve, puede ser tanto JSonArray de 1 objeto como JSonObject por lo que pregunto si el array es de mas de 1 elemento
		if(arrayResultados.size()>1) {
			//Si tiene mas de uno, hago que el usuario elija cual quiere
			mostrarResultados(jsonConResultados); //Muestra los resultados
			seleccion=IngresoTeclado.realizarSeleccion(jsonConResultados.getAsJsonArray().size()); //Toma una opcion valida para poder saber cual tiene que ir a buscar
			jsonCiudad = arrayResultados.get(Integer.parseInt(seleccion)-1).getAsJsonObject();
			resultado=climaPorKeyLocation(jsonCiudad.get("Key").getAsString()); 
			
			//Es el metodo que ya tenias pero ahora la KEY le recibe por parametro
			
		}else
		{
			//Sino simplemente muestro el resultado
			jsonCiudad = arrayResultados.get(0).getAsJsonObject();
			resultado=climaPorKeyLocation(jsonCiudad.get("Key").getAsString());
		}
		
		return resultado;
	}
	
	private void mostrarResultados(JsonElement jsonConResultados) {
		JsonObject obj;
		JsonArray array = jsonConResultados.getAsJsonArray();
		
		System.out.println("Cantidad de resultados es :"+ array.size());
		System.out.println("------------------------------------------");
		for(int i=0;i<array.size();i++) {
			obj=array.get(i).getAsJsonObject();
			System.out.println("OPCION NRO: " + (i+1));
			System.out.println("Ciudad: " + obj.get("LocalizedName").getAsString());
			System.out.println("Provincia: "+ obj.getAsJsonObject("AdministrativeArea").get("LocalizedName").getAsString());
			System.out.println("Pais: " + obj.getAsJsonObject("Country").get("LocalizedName").getAsString());
			System.out.println("------------------------------------------");
		}
		
		
	}
	

	
}
