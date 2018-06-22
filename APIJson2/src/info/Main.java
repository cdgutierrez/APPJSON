package info;

public class Main {

	public static void main(String[] args) {

		String ciudad;
		Noticia noticia = new Noticia();
		System.out.println(noticia.obtenerNoticia());
		
		Cotizacion cotizacion = new Cotizacion();
		System.out.println(cotizacion.obtenerCotizacion());
		
		
		Clima clima = new Clima();
		ciudad=IngresoTeclado.ingresoCiudad();
		//Falta ingreso de ciudad por consola.
		//En vez de espacio agrega el + xq sino la URL no se arma bien y tira error
		System.out.println(clima.buscarCiudad(ciudad));
	}

}
