# Conversor de Monedas by Alfredo

Bienvenido/a al conversor de Monedas by Alfredo, una aplicación en Java que permite convertir entre varias monedas y mantener un historial de conversiones.

## Funcionalidades

- Conversión entre diferentes pares de monedas:
  - Dólar >>>>>> Real brasileño
      - Real brasileño >>>>>> Dólar
      - Dólar >>>>>> Peso Colombiano
      - Peso Colombiano >>>>>> Dólar
      - Dólar >>>>>> Peso Mexicano
       -  Peso Mexicano >>>>>>  Dólar
       - Dólar >>>>>> Peso Argentino
      - Peso Argentino >>>>>> Dólar
- Visualización del historial de conversiones
- Almacenamiento del historial de conversiones en un archivo de texto

## Resumen de Funcionamiento

El conversor de monedas es una aplicación de consola que se conecta a una API de tipos de cambio para realizar conversiones entre diferentes monedas. Los usuarios pueden elegir el tipo de conversión, ingresar la cantidad a convertir y obtener el resultado. La aplicación mantiene un historial de hasta 30 conversiones recientes, el cual se muestra al usuario y se guarda en un archivo de texto para futuras referencias.
## Requisitos

- Java 11 o superior
- Maven

## Instalación

1. Clona el repositorio en tu máquina local. Abre PowerShell y ejecuta:
    git clone https://github.com/Moon18888/ConversorDeMoneda/tree/main/ConversorDeMonedas-master/ConversorDeMonedas-master
   
2. Navega al directorio del proyecto:
    cd ConversorDeMoneda
   
4. Compila el proyecto con Maven. Si Maven no está instalado, descárgalo desde aquí y sigue las instrucciones de instalación para Windows. Luego, ejecuta: mvn exec:java -Dexec.mainClass="Principal"

   
## Uso

1. Ejecuta la aplicación:
    mvn exec:java -Dexec.mainClass="Principal"
2. Sigue las instrucciones en pantalla para realizar conversiones y ver el historial.


   
## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir, por favor crea un pull request con tus cambios en el repositorio.
