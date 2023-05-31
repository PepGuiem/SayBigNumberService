package net.esliceu.numbers;
import java.util.Hashtable;

//Clase diccionario bidireccional, fusiona dos diccionarios y hace espejo el uno del otro
public class Dictio {

    //Se declaran los diccionarios basicos que se usaran
    private static Hashtable<String, Integer> Strings= new Hashtable<>();
    private static Hashtable<Integer, String> Integers= new Hashtable<>();

    //Constructor de la clase dictio
    public Dictio(String[] elements){

        //Por cada elemento en el array de string
        for (String element: elements) {

            //Divide en dos mitades el elemento a partir de un espacio, con eso obtiene los dos valores
            String[] values = element.split(" ");

            //Guarda ambos valores de manera invertida en el diccionario
            Integers.put(Integer.parseInt(values[0]), values[1]);
            Strings.put(values[1], Integer.parseInt(values[0]));
        }
    }

    //Funcion publica para recuperar el numero dando una llave en string
    public int getNumber(String key){
        return Strings.get(key);
    }

    //Funcion publica para recuperar el string dando una llave en numero
    public String getName(int key){
        return Integers.get(key);
    }
}
