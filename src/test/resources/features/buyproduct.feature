#language: es
Característica: Comprar productos en la tienda

  Como usuario
  Quiero comprar productos en la tienda


  @buyproduct
  Esquema del escenario: Comprar productos
    Dado el usuario quiere agregar <productos> productos al carrito
    Cuando el usuario va al carrito de compras
    Entonces el usuario deberia ver los <productos> productos en el carrito
    Y el usuario deberia ver el total de los productos en el carrito
    Ejemplos:
      | productos |
      | 2         |
