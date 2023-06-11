package net.esliceu.numbers;

public class BigNumber implements BigNumberOperator{

    //Variable en la que se guarda el valor de este BigNumber
    String value;

    //Funcion que limpia los 0s a la izquierda de un string
    private String clean(String s) {

        //Mientras la primera posicion de el string sea 0
        while (s.charAt(0) == '0') {

            //Si es el ultimo digito de este string, rompe el ciclo para dejar el 0 puesto
            if (s.length() == 1) break;

            //Elimina el primer gigito del String
            s = s.substring(1);
        }

        //Devuelve el String
        return s;
    }

    // Constructor 1.1. Utilizando un String
    public BigNumber(String s) {

        //Limpiamos el String y despues lo asignamos como valor para este BigNumber
        s = clean(s);
        this.value = s;
    }

    // Constructor 2. Si nos pasan otro BigNumber simplemente igualamos sus valores
    public BigNumber(BigNumber b) {
        this.value = b.value;
    }

    // Suma
    public String add(BigNumber other) {

        //creamos una variable llevar y otra result
        int llevar = 0;
        String result = "";

        //Obtenemos cual es el lenght mas largo de los dos numeros
        int leng = Math.max(this.value.length(), other.value.length());

        //Le añadimos 0s a la izquierda al BigNumber con menos digitos hasta que tenga los mismos digitos que el otro
        while (this.value.length() < leng) this.value = "0" + this.value;
        while (other.value.length() < leng) other.value = "0" + other.value;

        //Por cada par de digitos en el BigNumber empezando desde el final, iteramos
        for (int i = leng - 1; i >= 0; i--) {

            //Obtenemos el valor de la suma de ambos digitos mas lo que llevamos
            int value = Character.getNumericValue(this.value.charAt(i)) + Character.getNumericValue(other.value.charAt(i)) + llevar;

            //Reiniciamos la variable llevar
            llevar = 0;

            //Si el resultado es mas grande que 9
            if (value > 9) {

                //Le restamos 10 al resultado de esta operacion y asignamos 1 a llevar
                value -= 10;
                llevar = 1;
            }

            //Añadimos el resultado de esta ultima operacion a la variable result
            result = value + result;
        }

        //Si llevar es mas grabde que 0, añadimos llevar al frente del resultado
        if (llevar > 0) result = llevar + result;

        //Asignamos el valor de result a este BigNumber y lo devolvemos
        this.value = result;
        return this.toString();
    }

    // Resta
    public String subtract(BigNumber other) {

        //creamos una variable llevar y otra result
        int llevar = 0;
        String result = "";

        //Obtenemos cual es el lenght mas largo de los dos numeros
        int leng = Math.max(this.value.length(), other.value.length());

        //Le añadimos 0s a la izquierda al BigNumber con menos digitos hasta que tenga los mismos digitos que el otro
        while (this.value.length() < leng) this.value = "0" + this.value;
        while (other.value.length() < leng) other.value = "0" + other.value;

        //Por cada par de digitos en el BigNumber empezando desde el final, iteramos
        for (int i = leng - 1; i >= 0; i--) {

            //Obtenemos el valor de la resta de ambos digitos y tambien le restamos llevar
            int value = Character.getNumericValue(this.value.charAt(i)) - Character.getNumericValue(other.value.charAt(i)) - llevar;

            //Reiniciamos la variable llevar
            llevar = 0;

            //Si el resultado es menor que 0
            if (value < 0) {

                //Le sumanos 10 a el resultado de la operacion y le asiganos llevar a 1
                value += 10;
                llevar = 1;
            }

            //Añadimos el resultado de esta ultima operacion a la variable result
            result = value + result;
        }

        //Limpiamos la variable result y se la asiganos a este BigNumber, despues lo devolvemos
        this.value = clean(result);
        return this.toString();
    }

    // Multiplica
    BigNumber mult(BigNumber other) {

        //Creamos un BigNumber resultado y un un int para lo que llevamos entre operaciones
        BigNumber result = new BigNumber("0");
        int llevar = 0;

        //Por cada numero en uno de los digitos empezando desde el final, iteramos
        for (int i = other.value.length(); i > 0; i--) {

            //Creamos un BigNumber vacio para la suma de esta iteracion
            BigNumber sum = new BigNumber("0");
            sum.value = "";

            //Por cada digito en el segundo numero emoezando desde el final, iteramos
            for (int e = this.value.length(); e > 0; e--) {

                //Multiplicamos el digito actual de ambos BigNumber segun su ciclo correspondiente y le sumamos la variable llevar
                int operation = llevar + (Character.getNumericValue(other.value.charAt(i - 1)) * Character.getNumericValue(this.value.charAt(e - 1)));

                //Vaciamos llevar tras su uso
                llevar = 0;

                //Mientras el resultado de la multiplicacion sea mayor a 9, le restamos 10 y le añadimos 1 a llevar
                while (operation > 9) {
                    operation -= 10;
                    llevar++;
                }

                //Añadimos el resultado final de esta multiplicion enfrente de los digitos previos de sum
                sum.value = operation + sum.value;
            }

            //Si llevar es mas grande que 0, tenemos que añadirla al frente de sum
            if (llevar > 0) sum.value = llevar + sum.value;

            //Le añadimos a value 0s igual a la cantidad de ciclos previos a el
            sum.value += "0".repeat(other.value.length() - i);

            //Sumamos la suma de esta iteracion al resultado y ponemos llevar a 0
            result.add(sum);
            llevar = 0;
        }

        //Limpiamos resultado y lo guardamos dentro de este BigNumber, despues devolvemos este BigNumber
        this.value = clean(result.value);
        return this;
    }

    // Divideix
    BigNumber div(BigNumber other) {

        //Si other es igual a 0, la operacion es imposible
        if (other.value.equals("0")) throw new RuntimeException("No se puede dividir entre 0");

        //Creamos una variable resultado donde ir guardando el quociente, tambien creamos un BigNumber donde ir guardando el valor de lo que bajamos
        String result = "";
        BigNumber bajar = new BigNumber("0");

        //Guardamos el lenght original de este numero para poder iterar
        int leng = this.value.length();

        //Por cada digito de este numero, iteramos
        for (int i = 0; i < leng; i++) {

            //Concatenamos a bajar el valor de el primer caracter del dividendo y limpiamos
            bajar.value += this.value.charAt(0);
            bajar.value = clean(bajar.value);

            //Cortamos el digito que acabamos de bajar del dividendo
            this.value = this.value.substring(1);

            //Si el valor de bajar es mas grande o igual que el divisor, debemos operar
            if (other.compareTo(bajar) != 1) {

                //Contamos del 9 al 1
                for (int j = 9; j > 0; j--) {

                    //Multiplicamos el contador por el divisor
                    BigNumber multiplicacio = new BigNumber("" + j).mult(other);

                    //Si el valor de la multiplicacion es mas pequeño o igual a lo que tenemos bajado
                    if (multiplicacio.compareTo(bajar) != 1) {

                        //Restamos a bajar el resultado de la multiplicacion, concatenamos el contador a el resultado y rompemos este ciclo
                        bajar.subtract(multiplicacio);
                        result += "" + j;
                        break;

                    }
                }

                //Si el valor de bajar es mas pequeño, no se puede operar y añadimos un 0 al resultado
            } else result += "0";
        }

        //Limpiamos el valor de result, lo guardamos en este numero y devolvemos el BigNumber
        this.value = clean(result);
        return this;
    }

    // Arrel quadrada
    BigNumber sqrt() {

        //Creamos variabkes para el numero original como String, lo que hemos bajado hasta ahora, la operacion y el resultado
        String original = this.value;
        long bajar;
        BigNumber operacion = new BigNumber("0");
        String resultado = "0";

        //Si el numero tiene una cantidad de digitos impar, añadimos un 0 a la izquierda
        if (original.length() % 2 == 1) original = "0" + original;

        //Creamos un bucle infito del que se sale cuando se cumple el break
        while (true) {

            //Asignamos la variable bajar como los dos primeros digitos del numero
            bajar = Integer.parseInt(original.substring(0, 2));


            operacion.mult(new BigNumber("100")).add(new BigNumber("" + bajar));

            //Contamos desde 9 hasta 1
            for (int i = 9; i >= 0; i--) {

                //Creamos una variable que es igual al doble del resultado y despues le concatenamos el contador a esta variable
                BigNumber doble = new BigNumber(resultado).mult(new BigNumber("2"));
                doble.value += i;

                //Multplicamos el numero doble por el itinerador para ver si el resultado es mas grande que el numero que hemos bajado,
                // si no lo es, hemos encontrado el siguiente paso del resultado
                if (operacion.compareTo(new BigNumber(doble).mult(new BigNumber("" + i))) != -1) {

                    //Añadimos el dijito que hemos encontrado al resultadi
                    resultado += i;

                    //Le restamos el resultado de la multiplicacion a lo que hemos bajado
                    operacion.subtract(doble.mult(new BigNumber("" + i)));
                    break;
                }
            }

            //Si el length restante del numero es 2, rompemos el ciclo porque hemos terminado de operar
            if (original.length() == 2) break;

            //Le quitamos los dos primeros digitos al valor del numero original
            original = original.substring(2);
        }

        //Limpiamos la variable resultado y se la asignamos a este numero como valor, despues devolvemos este numero
        this.value = clean(resultado);
        return this;
    }

    // Potència
    BigNumber power(int n) {

        //Si el valor de n es 0, hacemos el valor de este BigNumber 1
        if (n == 0) this.value = "1";

        //Guardamos el valor original de este BigNumber
        BigNumber original = new BigNumber(this);

        //Iteramos n veces y en cada iteracion multiplicamos a este BigNumber su valor original
        for (int i = 0; i + 1 < n; i++) this.mult(original);

        //Devolvemos este BigNumber
        return this;
    }

    // Factorial
    BigNumber factorial() {

        //Si el valor de este numero es 0, lo cambiamos a 1
        if (value.equals("0")) value = "1";

        //Guardamos el valor original de este BigNumber para poder saber cuando parar
        String original = this.value;

        //Empezando por 1, contamos hacia arriba de 1 en 1 hasta alcanzar el valor previo al orginal y en cada iteracion multiplicamos ek contador por el valor de este BigNumber
        for (BigNumber contador = new BigNumber("1"); !contador.value.equals(original); contador.add(new BigNumber("1"))) {
            this.mult(contador);
        }

        //Devolvemos este BigNumber
        return this;
    }

    // MCD. Torna el Màxim comú divisor
    BigNumber mcd(BigNumber other) {

        //Creamos dos BigNumber, uno donde guardar el numero mas grande y otro donde guardar el numero mas pequeño
        BigNumber menor = this.compareTo(other) == 1 ? new BigNumber(other) : new BigNumber(this);
        BigNumber mayor = this.compareTo(other) == 1 ? new BigNumber(this) : new BigNumber(other);

        //Creamos un BigNumber resultado
        BigNumber res = new BigNumber("0");

        //Usamos el algoritmo de Euclides para resolver el maximo comun divisor que almacenamos en la variable res
        while (menor.compareTo(new BigNumber("0")) != 0) {
            res = new BigNumber(menor);
            menor = new BigNumber(mayor);
            mayor = new BigNumber(res);
        }

        //Devolvemos res
        return res;
    }

    // Compara dos BigNumber. Torna 0 si són iguals, -1
    // si el primer és menor i torna 1 si el segon és menor
    public int compareTo(BigNumber other) {

        //Si ambos BigNumber son iguales, devuelve 0
        if (equals(other)) return 0;

        //Compara si el valor del lenght de los BigNumber son iguales o diferentes

        //Si son iguales, recorre ambos BigNumber hasta encontrar un digito en el cual difieram, devuelve el numero mas grande dependiendo de cual de los dos digitos es el mayor.
        if (other.value.length() == this.value.length()) for (int i = 0; i < other.value.length(); i++) {
            if (Character.getNumericValue(this.value.charAt(i)) < Character.getNumericValue(other.value.charAt(i)))
                return -1;
            if (Character.getNumericValue(this.value.charAt(i)) > Character.getNumericValue(other.value.charAt(i)))
                return 1;
        }

        //Si other tiene un mayor lenght, es que es un numero mas grande
        if (other.value.length() > this.value.length()) return -1;

        //Si no ha entrado a ninguno de los if, significa que el lenght de este BigNumber es mayor y, por tanto, es el numero mas grande.
        return 1;
    }

    // Torna un String representant el número
    public String toString() {
        return value;
    }

    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) {

        //Creamos un BigNumber donde guardar el objeto pasado
        BigNumber n = (BigNumber) other;

        //Devolvemos el resultado de comparar el valor de other con el valor de este BigNumber
        return n.value.equals(this.value);
    }
}
