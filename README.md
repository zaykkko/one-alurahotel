
# Hotel Alura
Aplicación de escritorio de gestión de reservaciones para el Sprint 2 del Challenge ONE.

Se utilizó el [repositorio base del challenge](https://github.com/alura-challenges/challenge-one-alura-hotel-latam) que ya tenía hecho la UI de la aplicación.

## Solución del Challenge - Sprint 2 - ONE Java

La consigna puede visualizarse haciendo [clic aquí](https://www.aluracursos.com/challenges/oracle-one-java/sprint02-aplicacion-base-de-datos) y la solución es un [archivo ejecutable](https://github.com/zaykkko/one-alurahotel/releases).

<ins>**Aclaración**</ins>: en vez de utilizar MySQL _(como dice la consigna)_, se utilizó [PostgreSQL](https://www.postgresql.org/) como gestor de base de datos.

## Configurables
En caso de necesitar cambiar cierta información sobre la base de datos como el nombre de usuario, puerto, etc; puede descargarse o crear un archivo [config.properties](/config.properties) que puede contener cualquiera de los atributos disponibles abajo.


| Nombre  | Descripción de valor |
|--|--|
| `username` | _(Opcional)_ Nombre de usuario para la aplicación. Por defecto es `root`. |
| `password` | _(Opcional)_ Contraseña de usuario para la aplicación. Por defecto es `root`. |
| `dbName` | _(Opcional)_ Nombre de la base de datos. Por defecto es `alura_hotel`. |
| `dbPort` | _(Opcional)_ Puerto de la base de datos. Por defecto es `5432`. |
| `dbUsername` | _(Opcional)_ Nombre de usuario para la base de datos. Se utiliza la cuenta que ya viene por defecto. |
| `dbPassword` | _(Opcional)_ Contraseña de usuario para la base de datos.<br>Se utiliza la cuenta que ya viene por defecto, la cual no necesita contraseña para autenticarse. |
