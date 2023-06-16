# Alquimiasoft: Mi Negocio

¡Bienvenido al proyecto Alquimiasoft: Mi Negocio! Este proyecto ofrece un sistema contable y de facturación electrónica
diseñado específicamente para pequeñas y medianas empresas en Ecuador. Nuestra misión es proporcionar una solución
tecnológica eficiente que optimice la administración y el control de tu negocio.

## Requisitos previos

Antes de comenzar, asegúrate de tener el JDK 11 instalado correctamente en tu PC.

## Pasos para levantar el proyecto

Para levantar el proyecto, sigue estos pasos:

1. Abre tu terminal o línea de comandos.
2. En la base de datos local, crea una base de datos vacía con el nombre "minegocio" que el proyecto pueda utilizar.

   Opcionalmente, si tienes instalado Docker en tu PC, puedes ejecutar el siguiente comando para levantar una base de
   datos PostgreSQL y utilizarla localmente:
   ```shell
   docker run -d --name alquimiasoft -e POSTGRES_PASSWORD=postgres -p 5432:5432 -e POSTGRES_DB=minegocio postgres
   ```
   Este comando creará un contenedor Docker con la base de datos necesaria para el proyecto Alquimiasoft: Mi Negocio.

3. Instala las dependencias del proyecto que se encuentran en el archivo "pom.xml".
4. En la configuración del proyecto, asegúrate de levantarlo utilizando el archivo "application-dev.properties".
5. Ejecuta el proyecto.

## Documentación de la API

Hemos preparado una colección de solicitudes (requests) de API en Postman para facilitar tu interacción con nuestro
sistema. Puedes acceder a la documentación de la API mediante el siguiente enlace:

[Documentación de la API en Postman](https://www.postman.com/universal-star-9692/workspace/alquimiasoft/collection/25929603-f84b7bbb-e3be-4605-a117-c9b8743929a1?action=share&creator=25929603)

En esta documentación encontrarás ejemplos de solicitudes para diferentes endpoints de nuestra API, lo que te ayudará a
comprender cómo interactuar con el sistema contable y de facturación electrónica.

by [efrxngg](https://efrxngg.github.io)

