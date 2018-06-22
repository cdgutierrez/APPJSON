package com.tests.clima;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import info.Clima;

public class TestClima {

	@Test
	public void testClimaPorCiudad(){
		
		Clima mockClima = mock(Clima.class);
		Clima clima = new Clima();
		String ciudad = "isidro+casanova";
		when(mockClima.buscarCiudad("isidro casanova")).thenReturn("Ciudad: Isidro Casanova"+'\n'+
												"Provincia: Buenos Aires"+'\n'+
												"Pais: Argentina"+'\n'+
												"Parcialmente soleado"+'\n'+
												"Temperatura: 11.8 ºC"+'\n'+
												"Sensación termica: 13.2 ºC"+'\n'+
												"Humedad: 57%"+'\n'+
												"Vientos: 9.8 km/h"+'\n'+
												"Nubosidad: 35%");
		System.out.println(mockClima.buscarCiudad("isidro casanova"));

		Assert.assertEquals(mockClima.buscarCiudad("isidro casanova"),clima.buscarCiudad(ciudad));
	}
	
	@Test
	public void testClimaPorKeyLocation(){
		Clima mockClima = mock(Clima.class);
		Clima clima = new Clima();
		
		String keyLocation = "7188";
		
		when(mockClima.climaPorKeyLocation(keyLocation)).thenReturn(
												"Parcialmente soleado"+'\n'+
												"Temperatura: 11.8 ºC"+'\n'+
												"Sensación termica: 13.2 ºC"+'\n'+
												"Humedad: 57%"+'\n'+
												"Vientos: 9.8 km/h"+'\n'+
												"Nubosidad: 35%");
		Assert.assertEquals(mockClima.climaPorKeyLocation(keyLocation),clima.climaPorKeyLocation(keyLocation));
	}
}
