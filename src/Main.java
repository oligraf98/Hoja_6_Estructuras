/*Oliver Graf - 17190
*Este programa utiliza las clases Carta y MapFactory para crear un deck de cartas de una coleccion o base de datos de cartas extraidas de un archivo txt.
*
* */


//==============================================================================================================================================================================
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static String menuImplementacion(){
        return "Cual implementacion de Map desea emplear? Escriba el numero o el nombre de la opcion que desea:\n\t1. HashMap\n\t2. TreeMap\n\t3. LinkedHashMap";
    }

    public static String menuPrincipal(){
        return "1. Agregar una carta a su deck\n2. Mostrar el tipo de una carta\n3. Mostrar cartas del deck\n4. Mostrar todas las cartas disponibles\n5. Salir del programa.";
    }

    public static String subMenu(){
        return "1. Ordenar por nombre\n2. Ordenar por tipo";
    }


    public static void main(String [] args){
        //Inicio del programa
        Scanner input = new Scanner(System.in);
        MapFactory<String, Carta> factory = new MapFactory<>();
        Boolean on = false;
        Boolean go = true;

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
        Map<String, Carta> deck = factory.getMap(implementacion);

        //Se obtiene el archivo que contiene la coleccion de cartas.
        do {
            System.out.println("Ingrese la direccion del archivo como se muestra en el ejemplo: C:\\\\Users\\\\ejemplo\\\\Desktop\\\\test.txt");
            String direccion = input.nextLine();
            try {

                File f = new File(direccion);

                BufferedReader b = new BufferedReader(new FileReader(f));

                String readLine;

                while ((readLine = b.readLine()) != null) {
                    String nombre = "";
                    String tipo = "";
                    int cambio = 0;
                    for (int i = 0; i < readLine.length(); i++) {
                        char c = readLine.charAt(i);
                        if (c == '|') {
                            cambio++;
                        } else if (cambio > 0) {
                            tipo = tipo + c;
                        } else {
                            nombre = nombre + c;
                        }
                    }
                    /*System.out.println(nombre);
                    System.out.println(tipo);*/
                    Carta carta = new Carta(nombre, tipo);
                    coleccion.put(carta.getNombre(), carta);
                }
                System.out.println("Se ha creado exitosamente la coleccion.");
                on = true;

            } catch (IOException e) {
                System.out.println("No fue posible crear la coleccion de cartas con la direccion que especifico. Intentelo de nuevo...");
            }
        }while(on == false);

        //Si se llega hasta aqui, es porque fue exitosa la creacion de la base de datos de cartas, con la implementacion que eligio el usuario.
        //Aqui se despliega el menu principal y el usuario puede elegir entre una de las 5 opciones.

        System.out.println("Elija el numero de la opcion que desea.");
        String opcion;
        String nombre;
        do{
            System.out.println(menuPrincipal());
            opcion = input.nextLine();
            switch(opcion){
                case "1":
                    System.out.println("Ingrese el nombre de la carta que desea buscar: ");
                    nombre = input.nextLine();
                    String cant;
                    Boolean x = false;
                    try{
                        Carta carta = coleccion.get(nombre);
                        System.out.println("Cuantas cartas de "+ nombre +" desea agregar a su deck agregar? (Min 1)");
                        cant = input.nextLine();
                        Integer.parseInt(cant);
                        x = true;
                        if(Integer.parseInt(cant)>0){
                            carta.setCantidad(Integer.parseInt(cant));
                            System.out.println("Se han agregado "+ cant + " cartas de " + nombre + " en el deck.");
                            deck.put(carta.getNombre(), carta);
                        }else{

                            System.out.println("Unicamente se permite agregar una cantidad positiva de cartas. Se ha colocado 1 carta de "+ nombre +  " por defecto.\n");
                            deck.put(carta.getNombre(), carta);
                        }

                    }catch (Exception e){
                        if(x){
                            System.out.println("No se encontraron resultados con el nombre " + nombre + "...\n");

                        } else{
                            System.out.println("La cantidad que ingreso no es valida. Intentelo de nuevo.");
                        }
                    }
                    break;
                case "2":
                    System.out.println("Ingrese el nombre de la carta que desea buscar: ");
                    nombre = input.nextLine();
                    try{
                        System.out.println("La carta " + nombre + " es de tipo " + coleccion.get(nombre).getTipo() + " .\n");
                    } catch (Exception e){
                        System.out.println("No se encontraron resultados con el nombre " + nombre + "...\n");
                    }
                    break;
                case "3":
                    System.out.println(subMenu());
                    String opc = input.nextLine();
                    switch (opc){
                        case "1":
                            Map<Character, ArrayList<Carta>> abc = new LinkedHashMap<>();
                            for(Map.Entry<String, Carta> entrada: deck.entrySet()){
                                if(abc.containsKey(entrada.getValue().getNombre().charAt(0))){
                                    abc.get(entrada.getValue().getNombre().charAt(0)).add(entrada.getValue());
                                } else{
                                    abc.put(entrada.getValue().getNombre().charAt(0), new ArrayList<>());
                                    abc.get(entrada.getValue().getNombre().charAt(0)).add(entrada.getValue());
                                }
                            }
                            for(Map.Entry<Character, ArrayList<Carta>> entry: abc.entrySet()){
                                for(Carta carta: entry.getValue()){
                                    System.out.println("Nombre: "+ carta.getNombre() + "\nTipo: " + carta.getTipo()+ "\nCantidad en el deck: "+ String.valueOf(carta.getCantidad())+"\n");
                                }
                            }
                            break;
                        case"2":
                            ArrayList<Carta> monstruos = new ArrayList<>();
                            ArrayList<Carta> hechizos = new ArrayList<>();
                            ArrayList<Carta> trampas = new ArrayList<>();
                            for(Map.Entry<String, Carta> entrada: deck.entrySet()){
                                if(entrada.getValue().getTipo().equals("Monstruo")){
                                    monstruos.add(entrada.getValue());
                                } else if(entrada.getValue().getTipo().equals("Hechizo")){
                                    hechizos.add(entrada.getValue());
                                } else if(entrada.getValue().getTipo().equals("Trampa")){
                                    trampas.add(entrada.getValue());
                                }
                            }
                            for(Carta c: monstruos){
                                System.out.println("Nombre: " + c.getNombre() + "\nTipo: "+ c.getTipo() +"\nCantidad en el deck: "+ c.getCantidad()+ "\n");
                            }
                            for(Carta c: hechizos){
                                System.out.println("Nombre: " + c.getNombre() + "\nTipo: "+ c.getTipo() +"\nCantidad en el deck: "+ c.getCantidad()+ "\n");
                            }
                            for(Carta c: trampas){
                                System.out.println("Nombre: " + c.getNombre() + "\nTipo: "+ c.getTipo() +"\nCantidad en el deck: "+ c.getCantidad()+ "\n");
                            }
                            break;
                        default:
                            break;
                    }
                    /*for (Map.Entry<String, Carta> entrada: deck.entrySet()){
                        System.out.println("\nNombre: "+entrada.getKey()+"\nTipo: "+entrada.getValue().getTipo() + "\nCantidad en el deck: "+ String.valueOf(entrada.getValue().getCantidad())+"\n");

                    }*/
                    break;
                case "4":
                    System.out.println(subMenu());
                    String opc1 = input.nextLine();
                    switch (opc1){
                        case "1":
                            Map<Character, ArrayList<Carta>> abc = new LinkedHashMap<>();
                            for(Map.Entry<String, Carta> entrada: coleccion.entrySet()){
                                if(abc.containsKey(entrada.getValue().getNombre().charAt(0))){
                                    abc.get(entrada.getValue().getNombre().charAt(0)).add(entrada.getValue());
                                } else{
                                    abc.put(entrada.getValue().getNombre().charAt(0), new ArrayList<>());
                                    abc.get(entrada.getValue().getNombre().charAt(0)).add(entrada.getValue());
                                }
                            }
                            for(Map.Entry<Character, ArrayList<Carta>> entry: abc.entrySet()){
                                for(Carta carta: entry.getValue()){
                                    System.out.println("Nombre: "+ carta.getNombre() + "\nTipo: " + carta.getTipo()+ "\n");
                                }
                            }
                            break;
                        case "2":
                            ArrayList<Carta> monstruos = new ArrayList<>();
                            ArrayList<Carta> hechizos = new ArrayList<>();
                            ArrayList<Carta> trampas = new ArrayList<>();
                            for(Map.Entry<String, Carta> entrada: coleccion.entrySet()){
                                if(entrada.getValue().getTipo().equals("Monstruo")){
                                    monstruos.add(entrada.getValue());
                                } else if(entrada.getValue().getTipo().equals("Hechizo")){
                                    hechizos.add(entrada.getValue());
                                } else if(entrada.getValue().getTipo().equals("Trampa")){
                                    trampas.add(entrada.getValue());
                                }
                            }
                            for(Carta c: monstruos){
                                System.out.println("Nombre: " + c.getNombre() + "\nTipo: "+ c.getTipo() +"\n");
                            }
                            for(Carta c: hechizos){
                                System.out.println("Nombre: " + c.getNombre() + "\nTipo: "+ c.getTipo() +"\n");
                            }
                            for(Carta c: trampas){
                                System.out.println("Nombre: " + c.getNombre() + "\nTipo: "+ c.getTipo() +"\n");
                            }
                            break;
                        default:
                            System.out.println("Opcion no valida. Intentelo de nuevo.\n");
                            break;
                    }

                    break;
                case "5":
                    System.out.println("Cerrando el programa.");
                    go = false;
                    break;
                default:
                    System.out.println("No es valida la opcion que ingreso. Intentelo de nuevo.");
                    break;
            }
        }while(go);







    }

}
