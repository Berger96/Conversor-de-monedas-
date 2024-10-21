# Conversor de Monedas App

## Descripción

Esta es una aplicación de conversión de monedas desarrollada en **Java** utilizando la biblioteca **Swing** para la interfaz gráfica de usuario (GUI) y **Exchange Rate API** para obtener tasas de cambio en tiempo real. La aplicación permite al usuario seleccionar diferentes monedas como origen y destino para realizar conversiones precisas, y muestra el resultado en una interfaz amigable y personalizable.

## Características

- Conversión de diferentes monedas en tiempo real usando **Exchange Rate API**.
- Interfaz gráfica con componentes interactivos para ingresar el monto, seleccionar la moneda de origen y la moneda de destino.
- Soporte para varias monedas: USD, EUR, CLP, BRL, CAD, COP (pueden añadirse más fácilmente).
- Personalización de colores y fuentes en la interfaz gráfica.
- Muestra el resultado de la conversión con un simple clic.

## Tecnologías utilizadas

- **Java**: Lenguaje principal para la lógica y la creación de la interfaz gráfica.
- **Swing**: Biblioteca utilizada para crear la interfaz gráfica de usuario.
- **HttpClient**: Cliente HTTP para enviar solicitudes a la API.
- **JSON**: Librería utilizada para parsear la respuesta JSON de la API.
- **Exchange Rate API**: API que proporciona las tasas de cambio actualizadas.

## Requisitos previos

Antes de ejecutar la aplicación, asegúrate de tener lo siguiente:

- **JDK 11 o superior**.
- Conexión a Internet (para acceder a la API de tasas de cambio).
- **Librería JSON**. Si no la tienes, puedes añadirla con Maven o descargando el JAR de [org.json](https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar).

### Dependencias Maven (opcional)

Si utilizas Maven, añade la siguiente dependencia en tu `pom.xml`:

```xml
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20210307</version>
</dependency>
