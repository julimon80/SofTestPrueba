# Proyecto de Automatización - DemoBlaze 🛒

Este proyecto implementa **pruebas automatizadas** utilizando **Serenity BDD** con el patrón **Screenplay**.  
El objetivo principal es automatizar escenarios de compra en la página [DemoBlaze](https://www.demoblaze.com/),
validando funcionalidades como agregar productos al carrito y verificar el total.

---

## 🚀 Tecnologías utilizadas

- **Java 17+**
- **Gradle**
- **Serenity BDD**
- **Screenplay Pattern**
- **JUnit**
- **Cucumber**
-

---

## 📂 Estructura del Proyecto

src
├── main
│ └── java
│ └── com.demoblaze
│ ├── hooks # Configuración inicial y final (Before/After)
│ ├── interactions # Interacciones personalizadas con la UI
│ ├── questions # Consultas al estado de la aplicación
│ ├── tasks # Acciones principales que ejecutan los actores
│ ├── ui # Mapeo de elementos de interfaz de usuario (Targets)
│ └── utils # Clases utilitarias y helpers
└── test
├── java # Step Definitions y Runners
└── resources # Archivos de configuración y Features (.feature)

## ⚙️ Configuración

### 1. Clonar el proyecto

```bash
git clone https://github.com/tuusuario/https://github.com/julimon80/SofTestPrueba.git
cd SofTestPrueba

```

### 2. Ejecutar el test

Esta Ejecucion se hace con la dependencia de Boni Garcia, se recomienda tener chrome instalado.

```bash
./gradlew clean test aggregate
```
o gradle ya que el build tiene seteado por default las demas task 

```bash
gradle
```

### 📝 Escenarios automatizado -Ejemplo

Esquema del escenario: Comprar productos
Dado el usuario quiere agregar <productos> productos al carrito
Cuando el usuario va al carrito de compras
Entonces el usuario deberia ver los <productos> productos en el carrito
Y el usuario deberia ver el total de los productos en el carrito
Ejemplos:
| productos |
| 2 |

### ✅ Reportes

target/site/serenity/index.html

### 📌 Autor

julian Casafus

QA Automation Engineer

✉️ casafus1995@gmail.com
