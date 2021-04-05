# API Spring - Prueba técnica Accenture

Este repositorio es mi solución a la prueba técnica realizada en el proceso de selección de Accenture.

## APIs

### POST /login
Iniciar sesión con usuario y contraseña. Nota: Los unicos usuarios registrados para el test son fede@gmail.com y juan@gmail.com, ambos con clave 123456. Por cuestiones de tiempo, no se usa JWT, el login solo devuelve el id del usuario.

Body:
```json
{
  "email": string,
  "clave": string
}
```

Response:
```json
{
    "userID": number
}
```
statusCode: 200 | 400 si la autenticacion es incorrecta

### GET /carrito/{idUsuario}
Obtener el carrito de compras del usuario con ID <idUsuario>
  
Response:
```json
{
    "id": number,
    "fechaCreacion": number, // milisegundos desde epoch
    "completado": boolean,
    "precioBruto": number,
    "iva": number,
    "recargoDomicilio": number,
    "precioFinal": number,
    "subpedidos": [
        {
            "id": number,
            "producto": {
                "id": number,
                "nombre": string,
                "descripcion": string,
                "precio": number
            },
            "cantidad": number,
            "subtotal": number,
            "fkIdProducto": number
        }
    ],
    "fkIdUsuario": number,
    "cancelado": boolean
}
```

statusCode: 200

### PUT /carrito/{idUsuario}
Agregar un elemento al carrito de compras del usuario. En caso de que el usuario no tenga un carrito de compras, este se creará

Body: 
```json
{
  "idProducto": number,
  "cantidad": number
}
```

statusCode: 200 | 400 si el producto no existe

Empty Response

### POST /carrito/{idUsuario}/completar
Cerrar el carrito de compras y descontar su valor del saldo del usuario

Empty Body

Empty Response

statusCode: 200 | 400 si no hay saldo suficiente o no hay productos en el carrito

### DELETE /carrito/{idUsuario}/{idSubpedido}
Eliminar un subpedido (producto + cantidad) del carrito de compras del usuario

Empty Response

statusCode: 200

### GET /pedidos/{idUsuario}
Obtener los pedidos realizados en el pasado por un usuario

Response:
```json
[
  {
    "id": number,
    "fechaCreacion": number, // milisegundos desde epoch
    "completado": boolean,
    "precioBruto": number,
    "iva": number,
    "recargoDomicilio": number,
    "precioFinal": number,
    "subpedidos": [
      {
        "id": number,
        "producto": {
          "id": number,
          "nombre": string,
          "descripcion": string,
          "precio": number
        },
        "cantidad": number,
        "subtotal": number,
        "fkIdProducto": number
      }
    ],
    "fkIdUsuario": number,
    "cancelado": boolean
  }
]
```

statusCode: 200

### GET /pedidos/{idUsuario}/{idPedido}
Obtener el pedido especificado

Response:
```json
{
  "id": number,
  "fechaCreacion": number, // milisegundos desde epoch
  "completado": boolean,
  "precioBruto": number,
  "iva": number,
  "recargoDomicilio": number,
  "precioFinal": number,
  "subpedidos": [
    {
      "id": number,
      "producto": {
        "id": number,
        "nombre": string,
        "descripcion": string,
        "precio": number
      },
      "cantidad": number,
      "subtotal": number,
      "fkIdProducto": number
    }
  ],
  "fkIdUsuario": number,
  "cancelado": boolean
}
```

statusCode: 200

### PUT /pedidos/{idUsuario}/{idPedido}
Editar el pedido, si no han pasado más de 5 horas

Body:
```json
[
  {
    "idProducto": number,
    "cantidad": number
  }
]
```
No response

statusCode: 200 | 400 si el pedido no existe o ya pasaron más de 5 horas desde su creación

### GET /usuarios/{idUsuario}/saldo
Obtener el saldo del usuario especificado. Para el test, ambas cuentas tienen un saldo inicial de 1M

Response:
```json
{
    "saldo": number
}
```
statusCode: 200

### GET /productos
Obtener todos los productos de la tienda

Response:
```json
[
    {
        "id": number,
        "nombre": string,
        "descripción": string,
        "precio": number
    }
]
```

statusCode: 200

### DELETE /pedidos/{idUsuario}/{idPedido}
Elimina el pedido especificado, descontar el 10% si han pasado mas de 12 horas desde su creación

No response

statusCode: 200 | 400 si el pedido no existe
