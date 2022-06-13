# Krugger Challenge

## Introducción

La solución plantiada fue una arquitectura de microservicios, cada microservicio será contenerizado y levantado en un orquestador de contenedores.

[Arquitectura Planteada](https://github.com/dflasso/kr-Challenge/blob/main/Kugger%20challenge-Arquitectura.drawio.pdf)

[Modelo de Datos](https://github.com/dflasso/kr-Challenge/blob/main/Kugger%20challenge-Modelos%20de%20Datos.drawio.pdf)

# Estandares de código
- La códificación de los microservicios se realizo siguiendo los principios **SOLID**
- El estilo de código se baso en el Zen de Python

## Contenerización de los microservicios

Cada contenedor consta de tres prefiles para su despliegue según el ambiente: dev, stage y pro. La imagen fue creada con el pluggin jib. Los comando para crear la imagen del contenedor son:

 - Generar la imagen y guardar en el repositorio local:
```mvn compile jib:dockerBuild```

- Generar la imagen y enviar a un repositorio remoto:
```mvn compile jib:jib:build```

#### Nota: remplazar la variable ${URL_REPOSITORIO_IMG_PRIV} de los archivo pom.xml

## Seguridades

- Solo API de login es pública, el resto requiere de un JWT. Los microservicios ademas de verificar el jwt, comprueban que el ROL pueda consumir la API
- Las contraseñas se encuentran encriptadas con bcrypt
- El proveedor de correos es mailgum, los mismos son enviados mediante SMTP. **Nota: al ser una cuenta gratuita, mailgum restringe el envío solo a correo authorizados**
- En el API Gateway se puede configurar origenes, headers y métodos permitidos.

## Comunicación entre microservicios
La comunicación entre microservicios se hizo mediante API REST, de la siguiente forma:
- WebFlux para el consumo de API
- Generación de JWT internos con el ROl = MICROSERVICIO

## Validación de Campos
- La validación de los objetos que se recibe se la realizo con **javax.validation**
- En ciertos casos especificos como la validación de la cédula y la fecha de cumpleaños se creo una **@annotacion** customizada.

## Documentación de las APIs
La documentación de la realizo mediante **swagger-ui v3.0.0** siguiendo las especificaciones de OpenAPI 

## Notificaciones
- Proveedor de correos: mailgum
- Protocolo: SMTP
- Dependencia de Java: **spring-boot-starter-mail**
- Se recomienda usar una base de datos en cache como redis o h2, puesto que las credenciales del correo se encuentran en una base de datos