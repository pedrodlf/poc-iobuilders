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
      - [h2](#h2)
      - [spring-boot-starter-test](#spring-boot-starter-test)
      - [springfox-swagger2](#springfox-swagger2)
      - [springfox-swagger-ui](#springfox-swagger-ui)
      - [spring-boot-starter-actuator](#spring-boot-starter-actuator)
      - [org.iban4j](#orgiban4j)
    - [Build](#build)
 <!-- /code_chunk_output -->

## Control de cambios

| Fecha      | Versión | Estado | Modificado               |
| :--------- | :------ | :----- | :----------------------- |
| 22/11/2021 | 1.0.1   | OK     | pedrodlafuente@gmail.com |

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

Hecho esto el swagger del proyecto deberia ser accesible via navegador en <http://localhost:8080/swagger-ui.html#/>

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

#### h2

Las principales características de H2 son:

- API JDBC de código abierto.
- Modos integrados y de servidor; Bases de datos en disco o en memoria.
- Bases de datos encriptadas.
- Búsqueda de texto completo.
- Java con tamaño reducido: alrededor de 2,5 MB de tamaño de archivo jar.
- Controlador ODBC.

```` xml
<dependency>
 <groupId>com.h2database</groupId>
 <artifactId>h2</artifactId>
 <scope>runtime</scope>
</dependency>
````

#### spring-boot-starter-test

Contiene la mayoria de los elementos necesarios para la realización del testing. El tag **<scope> test </scope>** permite ignorar todas las dependecias declaradas con el *scope test* cuando el servicio se empaqueta para el despliegue.

```` xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-test</artifactId>
 <scope>test</scope>
</dependency>
````

#### springfox-swagger2

Produce la documentación de la API Swagger 2.0 que podemos ver en <http://localhost:8080/swagger-ui.html#/> cuando el proyecto está en ejecución.

```` xml
<dependency>
 <groupId>io.springfox</groupId>
 <artifactId>springfox-swagger2</artifactId>
 <version>${springfox-version}</version>
</dependency>
````

#### springfox-swagger-ui

Crea un webjar que contiene el contenido estático de swagger-ui. Agrega un punto final JSON /swagger-resourcesque enumera todos los recursos y versiones de swagger configurados para una aplicación determinada. La página de la interfaz de usuario de Swagger debería estar disponible en  <http://localhost:8080/swagger-ui.html#/>

```` xml
<dependency>
 <groupId>io.springfox</groupId>
 <artifactId>springfox-swagger-ui</artifactId>
 <version>${springfox-version}</version>
</dependency>
````

#### spring-boot-starter-actuator

Nos ayudara a monitorizar y gestionar nuestra aplicación. Podemos usar tanto HTTP como JMX para gestionar y monitorizar la aplicación.
Simplemente añadiendo el starter que nos ofrece Spring Boot tendremos una serie de características en nuestra aplicación sobre su ejecución.

```` xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
````

#### org.iban4j

Nos permite generar IBAN Randomizados pero que cumples con los estandares españoles para la POC.

```` xml
<dependency>
 <groupId>org.iban4j</groupId>
 <artifactId>iban4j</artifactId>
 <version>3.2.3-RELEASE</version>
</dependency>
````

### Build

Esta build de  maven es la que nos permite realizar la generación automática de los Contratos de los controladores y los DTO a partir de la definicion en **Swagger2.0**. Es por eso que al menos la primera vez que se ejecute el proyecto es imprescindible su construcción con **Maven**. En mi caso he tenido que configurar el plugin de eclipse para que funcione correctamente.

```` xml
<build>
  <sourceDirectory>src/main/java</sourceDirectory>
  <resources>
   <resource>
    <directory>${project.build.sourceDirectory}/../resources</directory>
   </resource>
   <resource>
    <directory>${project.build.directory}/generated-sources/swagger/src/main/resources</directory>
   </resource>
  </resources>
  <plugins>

   <plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <executions>
     <execution>
      <goals>
       <goal>repackage</goal>
      </goals>
     </execution>
    </executions>
   </plugin>
   <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
   </plugin>
   <plugin>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-codegen-maven-plugin</artifactId>
    <version>2.2.3</version>
    <executions>
     <execution>
      <id>generate-spring-mvc</id>
      <goals>
       <goal>generate</goal>
      </goals>
      <configuration>

       <inputSpec>${project.build.sourceDirectory}/../resources/generador_${api.version}.yaml</inputSpec>
       <language>spring</language>
       <modelPackage>com.pedrodlf.iobuilders.model</modelPackage>
       <apiPackage>com.pedrodlf.iobuilders.api</apiPackage>
       <configOptions>
        <interfaceOnly>true</interfaceOnly>
        <dateLibrary>java.util.Date</dateLibrary>

       </configOptions>
      </configuration>
     </execution>
    </executions>
   </plugin>
   <plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>build-helper-maven-plugin</artifactId>
    <executions>
     <execution>
      <id>add-source</id>
      <phase>generate-sources</phase>
      <goals>
       <goal>add-source</goal>
      </goals>
      <configuration>
       <sources>
        <source>target/generated-sources/swagger/src/main/java/</source>
       </sources>
      </configuration>
     </execution>
    </executions>
   </plugin>
  </plugins>
  <pluginManagement>
   <plugins>
    <!--This plugin's configuration is used to store Eclipse m2e settings
					only. It has no influence on the Maven build itself. -->
    <plugin>
     <groupId>org.eclipse.m2e</groupId>
     <artifactId>lifecycle-mapping</artifactId>
     <version>1.0.0</version>
     <configuration>
      <lifecycleMappingMetadata>
       <pluginExecutions>
        <pluginExecution>
         <pluginExecutionFilter>
          <groupId>io.swagger</groupId>
          <artifactId>
           swagger-codegen-maven-plugin
          </artifactId>
          <versionRange>
           [2.2.3,)
          </versionRange>
          <goals>
           <goal>generate</goal>
          </goals>
         </pluginExecutionFilter>
         <action>
          <ignore></ignore>
         </action>
        </pluginExecution>
       </pluginExecutions>
      </lifecycleMappingMetadata>
     </configuration>
    </plugin>
   </plugins>
  </pluginManagement>
 </build>
````
