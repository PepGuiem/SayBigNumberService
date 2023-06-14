# Pràctica SayBigNumberService
1. Funcionament del programa
2. Pipeline
3. Implementació del codi
4. Test Unitaris
5. Delivery
6. Sonar Cloud
7. Test API / Test E2E
8. Deployment
9. Dificultats de la pràctica
10. Piràmide testing
11. Diagrames

## Funcionament del programa
El funcionament del programa consisteix en que tenim 3 serveis: 

          1. El primer servei és que si ens passen un nombre el puguem passar a escrit.
          2. El segon servei és un calculador senzilla que ens permet fer càlculs amb nombres grans.
          3. I finalment el tercer servei consisteix en el conjunt dels dos serveis, és a dir ens passen
              una operació i nosaltres la solucionem i retorna'm el resultat però per escrit.

## Pipeline
El nostre pipeline consisteix en les seguents pases:

Implementar codi > Unit test > Maven/Tomcat > Deployment > Test End to End / Test Api  

## Implementació del codi

Per a la implementació del codi hem afegit arxius que els hem aconseguit a base dels arxius de les pràctiques anteriors. 
Posteriorment hem adaptat l'arxiu say Big Numbers Service i hem comprovat a que minim compila.

## Tests unitaris
Hem implementat els test unitaris que el que fem és comprovar que funciona el NumbersCat, també el BigNumber i posteriorment mirem que 
compila. Dins els tests unitaris es proven casos especials i casos normals.

## Tomcat/Maven
Posteriorment probam el tomcat local ficant hi el .war dins la webapp i posteriorment fer proves dins la web.

## Deployment
Per a fer el nostre deployment ho feim manualmente degut a un problema reportat per varies persones que impedia fer-ho automaticamnt.
Nosaltres hem utilitzat el TOMCAT instal·lat a el servidor que ens ha prestat en Manu.

## Sonar Cloud
El sonar cloud ens ha servit per a veure els problemes i code smells del nostre codi, això ens ha permét millorar la qualitat del nostre codi.

## Test API / E2E
Per als tests API i End to End hem utilitzat una eina anomenada Postman, ens ha permès fer tots els tests necessaris, també hem fet una MOB session
provant els test en el nostre servidor de prova.

## Dificultats de la pràctica
Les principals dificultats que hem tingut sobre la pràctica han estat a l'hora de crear el .war i el maven, ja que, teniem una versio de java diferenta
i aixo ens provocava conflictes i a l'hora de desplegar el programa a la webapp que l'hem fet manualment.

## Piràmide testing
Dins la piràmide testing tenim dividit en tres nivells: 

              1. El primer és unit test que bàsicament prova'm el codi funcioni i prova'm casos més especials.
              2. El segon nivell és bassa amb els tests API que és bàsicament provar casos més complexos com pot ser nombres més grans.
              3. Finalment, acabam la piràmide amb els tests E2E que bàsicament era provar que funcioni com toca.

## Diagrames
Diagrama comunicació:

![Diagrama de comunicacio](/img/Comunication.png "com_diagram")

Diagrama Use Case:

![Diagrama Use Case](/img/UseCase.png "com_diagram")

Diagrama seqüència:

![Diagrama de seqüència](/img/Sequence.png "com_diagram")

Diagrama fluxe:

![Diagrama de fluxe.](/img/Flowchart.png "com_diagram")

Diagrama classes:

![Diagrama de classes.](/img/ClassDiagram.png "com_diagram")
