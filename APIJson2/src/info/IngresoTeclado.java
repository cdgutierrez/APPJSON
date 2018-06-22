package info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;

import com.google.gson.JsonElement;

public class IngresoTeclado {

		static public String ingresoCiudad() {
			

			InputStreamReader sr = new InputStreamReader(System.in);
			BufferedReader br= new BufferedReader(sr);
			System.out.println("Ingrese la ciudad que desea buscar: ");
			String ingreso=null;
			try {
				ingreso = br.readLine();
				ingreso=normalizar(ingreso);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ingreso;
	}
		static private String normalizar(String ingreso) {
			ingreso=Normalizer.normalize(ingreso, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			
			char[] charArray = ingreso.toCharArray();
			
			for(int i=0;i<charArray.length;i++) {
				if(charArray[i]==' ') 
					charArray[i]='+';
				
			}
			return new String(charArray);
		}
	
		static public String realizarSeleccion(int size) {
			
			boolean flag=true;
			InputStreamReader is = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
			String aux;
			
			try {
				System.out.println("Ingrese una opcion: ");
				aux = br.readLine();
				
				while(flag) {
					//El metodo es bastante bruto pero no deberia haber tantos resultados para una busqueda por lo que no se si es tan urgente cambiarlo, de momento funciona
						for(Integer i=1;i<size+1;i++) {
							if(aux.equals(i.toString())) {
								flag=false;
								break;
							}									
						}
						
						if(flag) {
							System.out.println("Valor incorrecto");
						System.out.println("Vuelva a ingresar una opcion: ");
						aux = br.readLine();
						}
			
				
				}
					return aux;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				return null;
			}
			
		
		}
}
