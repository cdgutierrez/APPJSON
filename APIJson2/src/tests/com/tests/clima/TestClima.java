package tests.com.tests.clima;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import info.Clima;

public class TestClima {

/*	@Test
	public void testClimaPorCiudad() {

		Clima mockClima = mock(Clima.class);
		Clima clima = new Clima();
		String ciudad = "isidro+casanova";
		when(mockClima.buscarCiudad("isidro casanova")).thenReturn(
				"Ciudad: Isidro Casanova" + '\n' + "Provincia: Buenos Aires" + '\n' + "Pais: Argentina" + '\n'
						+ "Parcialmente soleado" + '\n' + "Temperatura: 11.8 �C" + '\n' + "Sensaci�n termica: 13.2 �C"
						+ '\n' + "Humedad: 57%" + '\n' + "Vientos: 9.8 km/h" + '\n' + "Nubosidad: 35%");
		System.out.println(mockClima.buscarCiudad("isidro casanova"));

		Assert.assertEquals(mockClima.buscarCiudad("isidro casanova"), clima.buscarCiudad(ciudad));
	}

	@Test
	public void testClimaPorKeyLocation() {
		Clima mockClima = mock(Clima.class);
		Clima clima = new Clima();

		String keyLocation = "7188";

		when(mockClima.climaPorKeyLocation(keyLocation))
				.thenReturn("Parcialmente soleado" + '\n' + "Temperatura: 11.8 �C" + '\n' + "Sensaci�n termica: 13.2 �C"
						+ '\n' + "Humedad: 57%" + '\n' + "Vientos: 9.8 km/h" + '\n' + "Nubosidad: 35%");
		Assert.assertEquals(mockClima.climaPorKeyLocation(keyLocation), clima.climaPorKeyLocation(keyLocation));
	}
*/
	@Test
	public void testClima() {
		Clima mockClima = mock(Clima.class);
		when(mockClima.obtenerClima("7188")) //Simulo lo que devolveria la API para tal key
				.thenReturn("[{\"LocalObservationDateTime\":\"2018-06-22T22:35:00-03:00\",\"EpochTime\":1529717700,"
						+ "\"WeatherText\":\"Nublado\",\"WeatherIcon\":7,\"IsDayTime\":false,\"Temperature\":{\"Metric\":{\"Value\":12.3,\"Unit\":\"C\","
						+ "\"UnitType\":17},\"Imperial\":{\"Value\":54.0,\"Unit\":\"F\",\"UnitType\":18}},"
						+ "\"RealFeelTemperature\":{\"Metric\":{\"Value\":12.2,\"Unit\":\"C\",\"UnitType\":17},"
						+ "\"Imperial\":{\"Value\":54.0,\"Unit\":\"F\",\"UnitType\":18}},\"RealFeelTemperatureShade\":{\"Metric\":{\"Value\":12.2,"
						+ "\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":54.0,\"Unit\":\"F\",\"UnitType\":18}},\"RelativeHumidity\":68,"
						+ "\"DewPoint\":{\"Metric\":{\"Value\":6.7,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":44.0,\"Unit\":\"F\","
						+ "\"UnitType\":18}},\"Wind\":{\"Direction\":{\"Degrees\":0,\"Localized\":\"N\",\"English\":\"N\"},"
						+ "\"Speed\":{\"Metric\":{\"Value\":6.6,\"Unit\":\"km/h\",\"UnitType\":7},\"Imperial\":{\"Value\":4.1,\"Unit\":\"mi/h\",\"UnitType\":9}}},\"WindGust\":{\"Speed\":{\"Metric\":{\"Value\":8.3,\"Unit\":\"km/h\",\"UnitType\":7},\"Imperial\":{\"Value\":5.2,\"Unit\":\"mi/h\",\"UnitType\":9}}},\"UVIndex\":0,\"UVIndexText\":\"Bajo\",\"Visibility\":{\"Metric\":{\"Value\":16.1,\"Unit\":\"km\",\"UnitType\":6},\"Imperial\":{\"Value\":10.0,\"Unit\":\"mi\",\"UnitType\":2}},\"ObstructionsToVisibility\":\"\",\"CloudCover\":95,\"Ceiling\":{\"Metric\":{\"Value\":1951.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":6400.0,\"Unit\":\"ft\",\"UnitType\":0}},\"Pressure\":{\"Metric\":{\"Value\":1011.0,\"Unit\":\"mb\",\"UnitType\":14},\"Imperial\":{\"Value\":29.85,\"Unit\":\"inHg\",\"UnitType\":12}},\"PressureTendency\":{\"LocalizedText\":\"Constante\",\"Code\":\"S\"},\"Past24HourTemperatureDeparture\":{\"Metric\":{\"Value\":3.7,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":7.0,\"Unit\":\"F\",\"UnitType\":18}},\"ApparentTemperature\":{\"Metric\":{\"Value\":14.4,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":58.0,\"Unit\":\"F\",\"UnitType\":18}},\"WindChillTemperature\":{\"Metric\":{\"Value\":12.2,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":54.0,\"Unit\":\"F\",\"UnitType\":18}},\"WetBulbTemperature\":{\"Metric\":{\"Value\":9.5,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":49.0,\"Unit\":\"F\",\"UnitType\":18}},\"Precip1hr\":{\"Metric\":{\"Value\":0.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.0,\"Unit\":\"in\",\"UnitType\":1}},\"PrecipitationSummary\":{\"Precipitation\":{\"Metric\":{\"Value\":0.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.0,\"Unit\":\"in\",\"UnitType\":1}},\"PastHour\":{\"Metric\":{\"Value\":0.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.0,\"Unit\":\"in\",\"UnitType\":1}},\"Past3Hours\":{\"Metric\":{\"Value\":0.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.0,\"Unit\":\"in\",\"UnitType\":1}},\"Past6Hours\":{\"Metric\":{\"Value\":0.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.0,\"Unit\":\"in\",\"UnitType\":1}},\"Past9Hours\":{\"Metric\":{\"Value\":0.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.0,\"Unit\":\"in\",\"UnitType\":1}},\"Past12Hours\":{\"Metric\":{\"Value\":0.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.0,\"Unit\":\"in\",\"UnitType\":1}},\"Past18Hours\":{\"Metric\":{\"Value\":0.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.0,\"Unit\":\"in\",\"UnitType\":1}},\"Past24Hours\":{\"Metric\":{\"Value\":0.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.0,\"Unit\":\"in\",\"UnitType\":1}}},\"TemperatureSummary\":{\"Past6HourRange\":{\"Minimum\":{\"Metric\":{\"Value\":12.3,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":54.0,\"Unit\":\"F\",\"UnitType\":18}},\"Maximum\":{\"Metric\":{\"Value\":14.2,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":58.0,\"Unit\":\"F\",\"UnitType\":18}}},\"Past12HourRange\":{\"Minimum\":{\"Metric\":{\"Value\":11.8,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":53.0,\"Unit\":\"F\",\"UnitType\":18}},\"Maximum\":{\"Metric\":{\"Value\":16.6,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":62.0,\"Unit\":\"F\",\"UnitType\":18}}},\"Past24HourRange\":{\"Minimum\":{\"Metric\":{\"Value\":3.4,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":38.0,\"Unit\":\"F\",\"UnitType\":18}},\"Maximum\":{\"Metric\":{\"Value\":16.6,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":62.0,\"Unit\":\"F\",\"UnitType\":18}}}},\"MobileLink\":\"http://m.accuweather.com/es/ar/isidro-casanova/7188/current-weather/7188\",\"Link\":\"http://www.accuweather.com/es/ar/isidro-casanova/7188/current-weather/7188\"}]");
		when(mockClima.climaPorKeyLocation("7188")).thenCallRealMethod(); //Hago que el metodo trabaje tal cual lo hacia originalmente
		Assert.assertEquals(
				"Nublado\n"+
				"Temperatura: 12.3 �C\n"+
				"Sensaci�n termica: 12.2 �C\n"+
				"Humedad: 68%\n"+
				"Vientos: 6.6 km/h\n"+
				"Nubosidad: 95%"
				, mockClima.climaPorKeyLocation("7188"));
	
	}
}
