# poc-iobuilders
<!-- @import "[TOC]" {cmd="toc" depthFrom=2 depthTo=5 orderedList=false} -->
<!-- code_chunk_output -->

- [poc-iobuilders](#poc-iobuilders)
  - [Control de cambios](#control-de-cambios)
  - [Necesidades](#necesidades)
  - [Descripción](#descripción)
  - [Instalación](#instalación)
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

El proyecto esta pensado para ser ejecutado con **Maven** , contiene usa serie de depencias (Explicadas en los siguientes puntos) que hacen imprencindible el uso de ciertos plugins.

Para ejecutar el proyecto, con el terminal en la raiz del mismo lanzaremos el siguiente comando:

> mvn clean install spring-boot:run

