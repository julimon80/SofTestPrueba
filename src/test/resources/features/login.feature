#language:es
Característica: Login - Inicion de sesión

  Antecedentes:
    Dado el usuario accede a la pagina web de la tienda

  Esquema del escenario: Login exitoso
    Dado el usuario ingresa en la opcion inicio de sesion Log in
    Cuando el usuario ingresa el usuario y contraseña validos
      | username   | password   |
      | <username> | <password> |
    Entonces el usuario deberia ver el mensaje del home "Welcome"
    Ejemplos:
      | username | password |
      | z        | z        |

  @loginIncorrecto
  Escenario: Login Incorrecto
    Dado el usuario ingresa en la opcion inicio de sesion Log in
    Cuando el usuario ingresa el usuario y contraseña
    Entonces el usuario deberia ver el mensaje de error "User does not exist."