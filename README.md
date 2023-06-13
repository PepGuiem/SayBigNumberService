# Pràctica SayBigNumberService
1. Pipeline
2. Implementació del codi
3. Test Unitaris
4. Deployment
5. Sonar Cloud
6. Test API / Test E2E
7. Diagrames

## Pipeline
El nostre pipeline consisteix en les seguents pases:

Implementar codi > Maven > Deployment > Test End to End / Test Api 

## Implementació del codi

Per a la implementació del codi hem afegit arxius, també hem adaptat tots els arxius que teniem i els hem aplicat a la interfície juntament amb els nous que hem creat.

## Tests unitaris / Maven
Hem fet els test unitaris en Java i després els executa Maven per a asegurar que el codi esta net i llest per a pasar a la següent fase.

## Deployment
Per a fer el nostre deployment ho feim manualmente degut a un problema reportat per varies persones que impedia fer-ho automaticamnt.
Nosaltres hem utilitzat el TOMCAT instal·lat a el servidor que ens ha prestat en Manu.

## Sonar Cloud
El sonar cloud ens ha servit per a veure els problemes i code smells del nostre codi, això ens ha permés millorar la qualitat del nostre codi.

## Test API / E2E
Per als tests API i End to End hem utilitzat una eina anomenada Postman, ens ha permés fer tots els tests necessaris, també hem fet una MOB session porvant els test en aquest apartat.

## Diagrames
Diagrama comunicació:

![Diagrama de comunicacio](/images/<imagen> "com_diagram")

Diagrama Use Case:

![Diagrama Use Case](/images/<imagen> "com_diagram")

Diagrama comunicació:

![Diagrama de seqüència](/images/<imagen> "com_diagram")

Diagrama comunicació:

![Diagrama de fluxe.](/images/<imagen> "com_diagram")

Diagrama comunicació:

![Diagrama de classes.](/images/<imagen> "com_diagram")
