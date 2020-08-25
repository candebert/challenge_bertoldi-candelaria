# Challenge/Candelaria-bertoldi

_Examen tecnico realizado para Mavha_

## Comenzando 

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._


### Pre-requisitos 

### Base de datos 

_Asegúrese de tener en correcto funcionamiento su base de datos MySQL. (https://dev.mysql.com/doc/)_

_1 - Cree una nueva conexión con las siguientes especificaciones:_

```
Conexión method: Standar(TCP/IP)
Hostname: localhost
Port: 3306
Username: root
Password: password
```

_SSL está desactivado en el backend. Dejarlo activado no crea problemas. Puede dejar la opción SSL por defecto._

_2 - Importe y sincronice el modelo con la conexión que acaba de crear._

_3 - Puede validar que sea correcto viendo si users, listings & special_prices tienen sus registros por defecto_

```
SELECT * FROM users;
SELECT * FROM listings;
SELECT * FROM special_prices;
```

### Back End

_Asegurese tener en correcto funcionamiento su IDE. Personalmente utilizo **NetBeans v.11**._

 _1 - Importe el proyecto dentro de su IDE_
 
_2 - Limpie y reconstruya el proyecto._

_3 - Ejecute el proyecto._

_El backend ahora estara corriendo en:
http://localhost:8081_

__Todos los endpoints podra encontrarlos en: https://inspector.swagger.io/builder en la pestaña de colección__

**Username:** candebertoldi
**Password:** iYXJH@GXJC.k28k

_Una vez ejecutado el back end podrá acceder a los ABM´s creados para Users, Listings y Special Prices._

#### Users
_Users ya cuenta con dos usuarios por defecto:_

```
{
    "id": "1",
    "name": "Juan Perez",
    "email": "juanperez@gmail.com",
    "password": "$2a$10$8GFX28JdvE5WJzMCLe0CQu5zb/j4RW3TDFT2J18xpNCITjkh/8xRy"
},
{
    "id": "2",
    "name": "Carla Gomez",
    "email": "carlagomez24@gmail.com",
    "password": "$2a$10$HHjbweWP/H3CcfC8N8g.GO9mBdBv5aDquCu7cXkGtKG5zRPu2LwFW"
}

```
_Puede utilizar cualquiera de estos para crear un  nuevo listings._

##### Endpoints
###### Nuevo Usuario

_Si desea crear un nuevo usuario deberá mandar por el cuerpo de la peticion en formato json los siguientes datos:_
_Deberá apuntar a la URL:_
**POST**  http://localhost:8081/api/users

```
{
  "name": "{nombre}",
  "email": "{mail}",
  "password": “{password}”
}

Todos los datos son requeridos
```

###### Obtener un Usuario existente por id

_Puede obtener un usuario por su id a través de la siguiente URL:_
**GET**  http://localhost:8081/api/users/show/{id}

##### Obtener todos los Usuarios existentes
_Puede obtener el listado de todos los usuarios cargados a través de la siguiente URL:_
**GET**  http://localhost:8081/api/users

##### Eliminar un Usuario existente

_Podrá eliminar un usuario a través de su id con la siguiente URL:_
**DELETE**  http://localhost:8081/api/users/{id}

#### Listings
_Listings ya cuenta con un anuncio por defecto:_

```
{
    "id": "1",
    "name": "Departamento en carlos paz",
    "slug": "dpto-en-vcp",
    "description": "Hermoso departamento centrico en villa carlos paz. Cuenta con dos dormitorios, un baño, cocina comedor. Gym, sum y pileta compartidos.",
    "adults": 2,
    "children": 2,
    "isPetsAllowed": true,
    "basePrice": "2500.00",
    "cleaningFee": “500.00”,
    "imageUrl": “https://unsplash.com/photos/gREquCUXQLI”,
    "weeklyDiscount": "500.00",    
    "monthlyDiscount": "2000.00",
    "owner": {
        "id”:1
    }
}
```
_Puede utilizar cualquiera de estos para crear un  nuevo special_price._

##### Endpoints
###### Nuevo Anuncio

_Si desea crear un nuevo anuncio deberá mandar por el cuerpo de la peticion en formato json los siguientes datos:_
_Deberá apuntar a la URL:_
**POST**  http://localhost:8081/api/listings

```
{
    "name": "{nombre}",
    "slug": "´{slug}",
    "description": "{description}",
    "adults": {cantidad de adultos},
    "children": {cantidad de niños},
    "isPetsAllowed": {true si se aceptan, false si no},
    "basePrice": "{precio por noche}",
    "cleaningFee": “{precio de limpieza por noche}”,
    "imageUrl": “{url de la imagen}”,
    "weeklyDiscount": "{descuento por alquilar una semana}",    
    "monthlyDiscount": "{descuento por alquilar un mes}",
    "owner": {
        "id”:{id del usuario creador del anuncio}
    }
}

Todos los datos son requeridos
```

###### Obtener un anuncio existente por id

_Puede obtener un anuncio por su id a través de la siguiente URL:_
**GET**  http://localhost:8081/api/listings/show/{id}

##### Obtener todos los anuncios existentes
_Puede obtener el listado de todos los anuncios cargados a través de la siguiente URL:_
**GET**  http://localhost:8081/api/listings

##### Obtener todos los anuncios existentes por usuario
_Puede obtener el listado de todos los anuncios cargados de un usuario a través de la siguiente URL:_
**GET**  http://localhost:8081/api/listings/owner/{idOwner}

##### Realizar un checkout
_Puede obtener el monto total de lo que se debera abonar por determinada cantidad de dias(más descuentos, total por limpieza, total de días) será a través de la siguiente URL:_
**GET**  http://localhost:8081/api/listings/{idListings}/{dateStart}/{dateEnd}/checkout

Donde: 
_**Idlistings** refiere al id del anuncio a alquilar, **dateStart** refiere al primer día de alquiler y **dateEnd** al último._
_Patrón de fecha:_ yyyy-MM-dd
_(No están contempladas las validaciones de disponibilidad de la propiedad ni si las fechas son incorrectas(el dia de check in posterior al último dia del checkout), pero serán agregadas)_

##### Eliminar un anuncios existente

_Podrá eliminar un anuncios a través de su id con la siguiente URL:_
**DELETE**  http://localhost:8081/api/listings/{id}

#### Special Prices
_Special Prices ya cuenta con un precio especial por defecto:_

```
{
    "id": "1",
    "date": "2020-08-26",
    "price": "2000.00",
    "listings": {
        “id”: 1
    }
}
```

##### Endpoints
###### Nuevo precio especial

_Si desea crear un nuevo precio especial deberá mandar por el cuerpo de la peticion en formato json los siguientes datos:_
_Deberá apuntar a la URL:_
**POST**  http://localhost:8081/api/specialprice

```
{
    "date": "{yyyy-MM-dd}",
    "price": "{price}",
    "listings": {
        “id”: {id del anuncio}
    }
}

Todos los datos son requeridos
```

###### Obtener un precio especial existente por id

_Puede obtener un precio especial por su id a través de la siguiente URL:_
**GET**  http://localhost:8081/api/specialprice/show/{id}

##### Obtener todos los precios especiales existentes
_Puede obtener el listado de todos los precio especial cargados a través de la siguiente URL:_
**GET**  http://localhost:8081/api/specialprice

##### Obtener todos los precios especiales existentes por anuncio
_Puede obtener el listado de todos los precio especial cargados de un anuncio a través de la siguiente URL:_
**GET**  http://localhost:8081/api/listings/listing/{idListing}

##### Eliminar un precio especial existente

_Podrá eliminar un precio especial a través de su id con la siguiente URL:_
**DELETE**  http://localhost:8081/api/specialprice/{id}


---
⌨️ con ❤️ 
