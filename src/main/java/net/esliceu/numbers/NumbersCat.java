package net.esliceu.numbers;
import java.lang.reflect.Array;

public class NumbersCat implements Numbers{

    //Creamos una array de strings para que sea nuestro diccionario y creamos la variable dict que sera nuestro diccionario
    private static final String[] centString = {
            "0 zero", "1 un", "2 dos", "3 tres", "4 quatre", "5 cinc",
            "6 sis", "7 set", "8 vuit", "9 nou", "10 deu",
            "11 onze", "12 dotze", "13 tretze", "14 catorze", "15 quinze",
            "16 setze", "17 disset", "18 divuit", "19 dinou",
            "20 vint", "30 trenta", "40 quaranta", "50 cinquanta", "60 seixanta",
            "70 setanta", "80 vuitanta", "90 noranta",
            "200 milió", "400 bilió", "600 trilió", "100 cent", "1000 mil"
    };
    private static final Dictio dict = new Dictio(centString);

    //Funcion que traduce numeros en bloques de centenas a texto
    private static String centblock(String input) {

        //Si el valor pasado es 100, devolvemos cent
        if (input.contentEquals("100")) return "cent";

        //Creamos una variable que ayuda a construir el resultado
        int nextnumber = 0;
        StringBuilder result = new StringBuilder();

        //Iteramos por cada posicion en el input
        for (int i = 0; i < 3; i++) {

            //Comprobamos si no hay que pronunciarlo ya que o es un cero o un uno que no esta en las unidades. Tambien Filtramos los 1 que esten en las centenas
            if ((input.charAt(i) != '0') && !(input.charAt(i) == '1' && i == 0)) {

                //Guarda el valor numerico de la posicion actual
                nextnumber += Character.getNumericValue(input.charAt(i));

                //Añade los guiones que sean necesarios
                if (i == 2 && nextnumber != 0 && input.charAt(i - 1) != '1' && input.charAt(i - 1) != '0') {
                    result.append("-");

                    //Añade una i si es un veinteavo
                    if (input.charAt(i - 1) == '2') {
                        result.append("i-");
                    }
                }
            }

            //Si el numero esta en la posicion de las centenas, multiplicamos por 10
            if (i == 1) {
                nextnumber *= 10;
            }

            //Escribimos el numero en el resultado y reseteamos la variable a menos que el numero sea 0 o 10 y estemos viendo las decenas
            if (!(i == 1 && nextnumber == 10) && nextnumber != 0) {
                result.append(dict.getName(nextnumber));
                nextnumber = 0;
            }

            //Si el numero esta en la posicion de las centenas añadimos la palabra
            if (i == 0 && input.charAt(i) != '0') {
                if (input.charAt(i) == '1') {
                    result.append("cent ");
                } else {
                    result.append("-cents ");
                }
            }
        }

        //Devolvemos el resultado
        return result.toString();
    }

    //Funcion say que traduce numeros a texto
    public String say(long n) {

        // Si el numero es 0, devolvemos "Zero" y si es 100 devolvemos "Cent"
        if (n == 0) return "Zero";

        //Creamos la variable result y le añadimos "Menys " si el primer digito es negativo y lo pasamos a positivo
        String result = "";
        if (n < 0) {
            result += "Menys ";
            n *= -1;
        }

        //Creamos un variable string donde el numero pasado pasara a formato string para poder hacer ciertos calculos
        String number = "" + n;

        //Averiguamos el numero de subdivisiones y el numero de digitos que debemos añadir a la primera division para tener el formato correcto
        int numberDigits = (number.length()) % 3;

        //Ponemos en el formato correcto la primera posicion añadiendo 0s a la izquierda
        if (numberDigits != 0) {
            number = "0".repeat(3 - numberDigits) + number;
        }

        //Pasamos el string ya formateado y filtrado a la funcion obtenerValor que compone el resultado
        result += obtainValue(number);

        //Ponemos en el formato correcto el resultado poniendo el primer valor en mayuscula y quitando espacios finales que puedan haberse generado
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        while (result.charAt(result.length() - 1) == ' ') {
            result = result.substring(0, result.length() - 1);
        }

        //Devolvemos el valor
        return result;
    }

    //Funcion que obtiene los valores textuales de un numero previamente preparado para el uso de esta funcion la cual montara con el texto de un numero en bloques de centenas
    private static String obtainValue(String number) {

        //Creamos la variable result y creamos una variable buffer para saber si debemos poner texto posicional cuando el las posiociones de millon
        StringBuilder result = new StringBuilder();
        String checkbuffer = "";
        int subdivisions = (number.length() - 1) / 3 + 1;

        //Por cada subdivision, la escribimos en el resultado
        for (int i = 0; i < subdivisions; i++) {

            //Obtenemos el valor de la primera division
            String hundred = number.substring(0, 3);
            number = number.substring(3);

            //Obtenemos el texto que se deba mostrar segun posicion
            String pos = getPos(subdivisions, hundred, checkbuffer, i);

            //Si el valor posicional es mil y el valor de la posicion es 1, lo añadimos directamente al resultado y continuamos el bucle.
            if (hundred.contentEquals("001") && pos.contentEquals("mil ")) {
                result.append(pos);
                continue;
            }

            //Creamos la centena y el espaciado. Para crear la centena, llamamos a la funcion centblock
            String hundredNumber = centblock(hundred);

            //si la centena tiene valor, le añadimos al espaciado un espacio
            String spacing = !hundredNumber.contentEquals("") ? " " : "";

            //Formamos el resultado juntando la centena, el espaciado y la posicion
            result.append(hundredNumber).append(spacing).append(pos);

            //Preparamos el valor del buffer
            checkbuffer = hundred;
        }

        //Retornamos el resultado
        return result.toString();
    }

    //Funcion que obtiene el valor textual adecuado para indicar la posicion de una centena numerica en texto
    private static String getPos(int subdivisiones, String hundred, String checkbuffer, int pos) {

        //Comprobamos si es la ultima posicion de la division, si lo es devolvemos un string vacio
        if (pos + 1 == subdivisiones) return "";

        //Si la subdivision actual es posicion par y no es 0, devolvemos "mil " como valor posicional
        if ((subdivisiones - pos) % 2 == 0 && !hundred.contentEquals("000")) return "mil ";

        //Si es impar, y o la divisio actual o la previa tenian valor, devolvemos el valor adecuado segun si es millon, billon o trillon
        if ((!checkbuffer.contentEquals("000") || !hundred.contentEquals("000")) && (subdivisiones - pos) % 2 == 1) {

            //Obtenemos el valor del diccionario, el valor esta guardado segun la posicion que es representado por cada uno * 100
            String value = dict.getName(100 * (subdivisiones - pos - 1)) + " ";

            //Si el valor de la posicion actual no es 1, transforma el resultado a plural
            if (!hundred.contentEquals("001")) {
                value = value.substring(0, value.length() - 2) + "ons ";
            }

            //Devuelve el valor
            return value;
        }

        //Si no es ninguno de los casos, no se ha de retornar nada
        return "";

    }

    //Funcion words que coge un input del usuario y lo prepara y manda a la funcion transforma para que lo traduzca
    public static long words(String s) {

        //Cambiamos el formato del input para que facilitar trabajar con el.
        String[] values = s.replace("-i-", " ").replace("-", " ").toLowerCase()
                .replace("cents", "cent").replace("bilions", "bilió")
                .replace("milions", "milió").replace("trilions", "trilió").split(" ");

        //Devolvemos el resultado de pasar el input ya formateado por la funcion que transforma en numero.
        return Long.parseLong(eval(transform(values)));
    }

    //Funcion que evalue y calcula operaciones aritmeticas en formato string
    private static String eval(String input) {

        //Creamos las variables necesarias y convertimos el input a un string array partido por espacios
        StringBuilder result = new StringBuilder();
        String[] word = input.split(" ");
        long operation = 0;
        String operOrder = "*";
        String operOrder2 = "/";

        //Si el array es mas pequeño que 3, devolvemos el valor del input
        if (Array.getLength(word) < 3) return input;

        //Hacemos dos recorridos al array. Uno para multiplicacion y division y el otro para suma y resta
        for (int e = 0; e < 2; e++) {

            //Recorremos el array saltando de operacion en operacion
            for (int i = 1; i < Array.getLength(word) - 1; i += 2) {

                //Si la operacion es de las que estamos buscando, entramos y hacemos la operacion de pertinente usando los numeros a ambos lados del simbolo
                if (word[i].contentEquals(operOrder) || word[i].contentEquals(operOrder2)) {
                    switch (word[i]) {
                        case "+" -> operation = Long.parseLong(word[i - 1]) + Long.parseLong(word[i + 1]);
                        case "-" -> operation = Long.parseLong(word[i - 1]) - Long.parseLong(word[i + 1]);
                        case "*" -> operation = Long.parseLong(word[i - 1]) * Long.parseLong(word[i + 1]);
                        case "/" -> operation = Long.parseLong(word[i - 1]) / Long.parseLong(word[i + 1]);
                    }

                    //Vaciamos las posiciones y reemplazamos la mas cercana al siguiente simbolo por el resultado de la operacion
                    word[i - 1] = "";
                    word[i] = "";
                    word[i + 1] = "" + operation;
                }
            }

            //Por cada valor em el array ya calculado de el array, si es diferente de un string vacio, lo guardamos en una variable helper
            StringBuilder helper = new StringBuilder();
            for (String value : word) {
                if (!value.contentEquals("")) {
                    helper.append(value).append(" ");
                }
            }

            //Hacemos que word sea igual a helper dividido por los espacios
            word = helper.toString().split(" ");

            //Cambiamos las operaciones a realizar
            operOrder = "+";
            operOrder2 = "-";
        }

        //Por cada valor en el array word, lo agregamos a result
        for (String value : word) result.append(value);

        //retornamos result
        return result.toString();
    }

    //Funcion transforma que traduce un numero en string a numeros
    private static String transform(String[] values) {

        //Creamos una serie de variables con las que montaremos el resultado
        StringBuilder result = new StringBuilder();
        long number = 0;
        int buffernumber = 0;
        int thousands = 0;

        //Recorremos todos los valores dados
        for (String word : values) {

            //Guardamos el resultado de pasar la palabra por la funcion operatorCharacters
            String symbol = operatorCharacters(word);

            //Si ha devuelto un valor no vacio, realizamos unas instrucciones diferentes
            if (!symbol.contentEquals("")) {

                //Primero, forzamos el incorporar los numoeros en la variable number
                number += thousands + buffernumber;

                //Si number es diferente de 0, lo añadimos al resultado
                if (number != 0) result.append(number);

                //Si number es 0, añadimos el simbolo al resultado sin espaciado. Si no es 0, lo añadimos con espaciado
                if (number == 0) result.append(symbol);
                else result.append(" ").append(symbol).append(" ");

                //Resetemos los valores i continuamos
                number = 0;
                buffernumber = 0;
                thousands = 0;
                continue;
            }

            //Guardamos el valor numerico de la palabra en la variable currentnumber
            long currentNumber = dict.getNumber(word);

            //Si el numero es mas grande que 99, hay que realizar operaciones especiales
            if (currentNumber > 99) {

                //Si el numero del buffer es 0 y tambien lo son los miles o el numero actual es 100, debemos cambiar el valor del buffer a 1 para evitar un fallo
                buffernumber = buffernumber == 0 && (thousands == 0 || currentNumber == 100) ? 1 : buffernumber;

                //Si el valor es 100, debemos multiplicar el valor por 100 y continuar
                if (currentNumber == 100) {
                    buffernumber *= 100;
                    continue;
                }

                //Si el valor es 1000, debemos multiplicar el valor por 1000 y almazenarlo dentro de la variable mil para uso mas tarde y continuar
                if (currentNumber == 1000) {
                    thousands = buffernumber * 1000;
                    buffernumber = 0;
                    continue;
                }

                //Obtenemos el valor real de currentNumber, si es 100 o 1000 no pasa nada pero si es uno de los de tipo millon transforma el valor de diccionario en el real
                long realnumber = (long) Math.pow(10, 3 * ((double) currentNumber / 100));

                //Sumamos a number el numero del buffer + el numero de los miles y lo multiplicamos por el numero real
                number += (thousands + buffernumber) * realnumber;
                thousands = 0;
                buffernumber = 0;

                //Si no es el caso, que es mas grande que 99, sumamos el numero actual al numero de buffer
            } else {
                buffernumber += currentNumber;
            }
        }

        //Sumamos lo que queda al final del ciclo dentro de number y ponemos el valor de number dentro de result
        number += thousands + buffernumber;
        result.append(number);

        //Retornamos result
        return result.toString();
    }

    //Funcion que detecta los numbres de los caracteres especiales y devuelve su simbilo
    private static String operatorCharacters(String word) {

        //Switch con todas las opciones y default si no es ninguna para retornar vacio
        return switch (word) {
            case "menys" -> "-";
            case "més" -> "+";
            case "dividit" -> "/";
            case "per" -> "*";
            default -> "";
        };
    }

    //Funcion oper que opera usando como input las cadenas de texto.
    public String oper(String s) {

        //Transforman el string que ens han donat en numeric, que resol la operacio tambe i la transforma de nou a parauler
        return say(words(s));
    }
}