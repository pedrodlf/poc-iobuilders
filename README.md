# poc-iobuilders
<!-- @import "[TOC]" {cmd="toc" depthFrom=2 depthTo=5 orderedList=false} -->
<!-- code_chunk_output -->

- [poc-iobuilders](#poc-iobuilders)
  - [Control de cambios](#control-de-cambios)
  - [Necesidades](#necesidades)
  - [Descripción](#descripción)
  - [Instalación](#instalación)
  - [Gestor de Dependencias](#gestor-de-dependencias)
    - [Artefacto](#artefacto)
    - [Propiedades](#propiedades)
    - [Dependencias](#dependencias)
      - [spring-boot-starter-web](#spring-boot-starter-web)
      - [spring-boot-starter-data-jpa](#spring-boot-starter-data-jpa)
 <!-- /code_chunk_output -->

## Control de cambios

| Fecha | Versión | Estado | Modificado
| :------------- | :------------- | :------------- | :------------- |
| 22/11/2021 | 1.0.1 | OK | pedrodlafuente@gmail.com

## Necesidades

API Rest para simular un pequeño banco:

- Registro usuario
- Creación de cuenta (wallet)
- Realización de depósito de dinero
- Visualización de cuenta (wallet) --> Balance y movimientos
- Transferencia de una cuenta A a una cuenta B

Puntos a destacar:

- Arquitectura hexagonal y testing (Obligatorio)
- Libertad en el stack usado en la prueba, aunque preferiblemente algún lenguaje de la JVM, Java, Groovy o  haciendo uso de Spring, Micronaut o Quarkus.

## Descripción

Finalmente he optado por un micro con **Spring-boot** por afinidad con el framework. El micro contiene los tres dominios funcionales *Users*,*Accounts*,*Transactions*.

## Instalación

El proyecto esta pensado para ser ejecutado con un build de **Maven** , contiene usa serie de depencias (Explicadas en los siguientes puntos) que hacen imprencindible el uso de ciertos plugins.

Para ejecutar el proyecto, con el terminal en la raiz del mismo lanzaremos el siguiente comando:

> mvn clean install spring-boot:run

## Gestor de Dependencias

El gestor de dependecias usado es **Maven** , con la siguiente configuración:

### Artefacto

```` xml
<groupId>com.pedrodlf</groupId>
<artifactId>iobuilders</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>iobuilders</name>
<description>POC rest</description>
````

### Propiedades

```` xml
<properties>
 <java.version>1.8</java.version>
 <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
 <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
 <springfox-version>2.7.0</springfox-version>
 <api.version>1.0.1</api.version>
</properties>
````

### Dependencias

las principales depencias son las siguientes:

#### spring-boot-starter-web

Spring MVC, REST y Tomcat como servidor integrado predeterminado. La dependencia única spring-boot-starter-web atrae de forma transitiva todas las dependencias relacionadas con el desarrollo web. También reduce el recuento de dependencias de compilación. Spring-boot-starter-web depende transitivamente de lo siguiente:
org.springframework.boot: spring-boot-starter
org.springframework.boot: spring-boot-starter-tomcat
org.springframework.boot: Spring-boot-starter-validation
com.fasterxml.jackson.core: jackson-databind
org.springframework: spring-web
org.springframework: spring-webmvc

```` xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
````

#### spring-boot-starter-data-jpa

características principales de Spring Data JPA que son las siguientes:

- **Repositorio sin código**: Nos permite implementar nuestro código comercial en un nivel de abstracción superior.
- **Código repetitivo reducido**: proporciona la implementación predeterminada para cada método mediante sus interfaces de repositorio, no es necesario implementar operaciones de lectura y escritura.
- **Consultas generadas**: otra característica de Spring Data JPA es la generación de consultas de base de datos basadas en el nombre del método.

```` xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
````
