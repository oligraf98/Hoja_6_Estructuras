import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static String menuImplementacion(){
        return "Cual implementacion de Map desea emplear?\n\t1. HashMap\n\t2. TreeMap\n\t3. LinkedHashMap";
    }

    public static String menuPrincipal(){
        return "1. Agregar una carta a su deck\n2. Mostrar el tipo de una carta\n3. Mostrar cartas del deck\n4. Mostrar todas las cartas disponibles";
    }

    public static String subMenu(){
        return "1. Ordenar por nombre\n2. Ordenar por tipo";
    }


    public static void main(String [] args){
        //Inicio del programa
        Scanner input = new Scanner(System.in);
        MapFactory<String, Carta> factory = new MapFactory<>();
        Boolean on = true;

        System.out.println("Bienvenido a su creador de decks.");

        //Se elije la implementacion de MAP
        System.out.println(menuImplementacion());
        String impl = input.nextLine();
        String implementacion;
        if(impl.equals("1")||impl.equals("HashMap")){
            implementacion = "HashMap";
        } else if (impl.equals("2")||impl.equals("TreeMap")){
            implementacion = "TreeMap";
        } else if (impl.equals("3")||impl.equals("LinkedHashMap")){
            implementacion = "LinkedHashMap";
        } else{
            System.out.println("La opcion que ingreso no es valida. Se usara HashMap como implementacion por defecto.");
            implementacion = "HashMap";
        }
        Map<String, Carta> coleccion = factory.getMap(implementacion);

        //Se obtiene el archivo que contiene la coleccion de cartas.
        System.out.println("Calculadora encendida\n\nIngrese la direccion del archivo como se muestra en el ejemplo: C:\\\\Users\\\\ejemplo\\\\Desktop\\\\test.txt\nSi desea cerrar el programa escriba el numero cero: ");
        String direccion = input.nextLine();

        try {

            File f = new File(direccion);

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            while ((readLine = b.readLine()) != null) {
                String nombre ="";
                String tipo = "";
                int cambio = 0;
                for (int i = 0; i < readLine.length(); i++){
                    char c = readLine.charAt(i);
                    if(c=='|'){
                        cambio++;
                    } else if (cambio>0){
                        tipo = tipo + c;
                    } else {
                        nombre = nombre + c;
                    }
                }
                System.out.println(readLine);
                System.out.println(nombre);
                System.out.println(tipo);
                Carta carta = new Carta(nombre, tipo);
                coleccion.put(carta.getNombre(), carta);
            }


        } catch (IOException e) {
            System.out.println("No fue posible crear la coleccion de cartas con la direccion que especifico.");
        }



        /*try {
            File file = new File(direccion);//Obtiene el archivo
            Scanner sc = new Scanner(file);//Escanea el archivo
            String nombre = "";
            String tipo = "";
            int activado = 0;
            int cambioDeString = 0;

            while (sc.hasNextLine()) {//Lee e archivo
                if (!sc.next().equals("|") && cambioDeString == 0) {
                    //Se entra aqui cuando se esta copiando el nombre de la carta
                    nombre = nombre + sc.next();
                    System.out.println(nombre);
                } else {
                    activado++;
                    if (activado > 2) {
                        //Se entra aqui cuando se esta copiando el tipo de la carta
                        tipo = tipo + sc.next();
                        System.out.println(tipo);
                    }
                }
                Carta carta = new Carta(nombre, tipo);
                coleccion.put(carta.getNombre(), carta);

            }



        } catch (Exception e){
            System.out.println("No se encontro el archivo de texto que especifico.");
        }*/

        /*while(on){

        }*/
    }

}
