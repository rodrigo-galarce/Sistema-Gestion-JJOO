# Sistema-Gestion-JJOO
Trabajo final para la materia “Programación Orientada a Objetos”

## Supuestos realizados
* Se asumió que cada país posee una única delegación deportiva.
* Se asumió que los DNI de los atletas y los entrenadores son únicos dentro del sistema.
* Se asumió que los nombres de deportes, disciplinas, competencias y ceremonias son suficientes para identificarlos dentro de las operaciones de búsqueda.
* Se asumió que las medallas se asignan automáticamente a los tres primeros puestos de una competencia.
* Se asumió que los récords se actualizan automáticamente al registrar resultados en una competencia.
* Se asumió que las personas que participan en ceremonias deben estar previamente registradas en el sistema como atletas o entrenadores, pudiendo desempeñarse con distintos roles.

## Decisiones relevantes de implementación
* Se implementó una estructura basada en paquetes separando:
    * Excepciones personalizadas (`excepciones`)
    * Main (`main`)
    * Modelo (`modelo`)
    * Persistencia (`persistencia`)
    * Servicios (`servicio`)
    * Interfaz de usuario (`ui`)
* La lógica de negocio fue centralizada en las clases de servicio para evitar que los menús contengan reglas del dominio.
* El menú principal fue dividido en submenús especializados para mejorar la organización y mantenibilidad del sistema:
    * Gestión Organizativa
    * Gestión Deportiva
    * Gestión de Inscripciones
    * Gestión de Resultados
    * Consultas
* Se implementó persistencia mediante serialización de objetos utilizando archivos `.dat`, permitiendo conservar el estado completo del sistema entre ejecuciones.
* Los datos persistidos se almacenan en el directorio `datos`.
* Se implementaron excepciones personalizadas para controlar situaciones específicas del dominio, como inscripciones duplicadas y errores de persistencia.
* Se incorporaron validaciones de entrada y manejo de excepciones para evitar interrupciones durante la ejecución del programa.
* Durante la implementación se agregaron clases y métodos auxiliares necesarios para completar la funcionalidad del sistema y mejorar la organización del código respecto del modelo inicial del Diagrama de Clases.

## Aspectos de diseño destacados
* Se utilizó herencia para modelar las personas participantes del sistema, tomando a `Persona` como clase base y especializando su comportamiento mediante las clases `Atleta` y `Entrenador`.
* Se utilizó polimorfismo en la gestión de marcas y resultados deportivos, permitiendo trabajar de manera uniforme con distintos tipos de marcas a través de una abstracción común.
* Se procuró una clara separación de responsabilidades:
    * Las clases del paquete `modelo` representan las entidades del dominio.
    * Las clases del paquete `servicio` contienen la lógica de negocio.
    * Las clases del paquete `ui` gestionan la interacción con el usuario mediante menús.
    * Las clases del paquete `persistencia` administran el almacenamiento y recuperación de información.
    * Las clases del paquete `excepciones` encapsulan errores específicos del dominio.
* Para optimizar búsquedas por clave se utilizaron estructuras `HashMap` en entidades como países, atletas y entrenadores.
* Para colecciones donde resulta importante mantener el orden de inserción y realizar recorridos secuenciales se utilizaron estructuras `ArrayList`, por ejemplo para disciplinas, competencias, ceremonias y participaciones.
* El sistema cuenta con una clase principal ejecutable (`Main`) que actúa como punto de entrada de la aplicación, inicializando la persistencia, cargando el sistema y delegando la interacción al menú principal.