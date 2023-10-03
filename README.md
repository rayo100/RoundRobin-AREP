# PATRONES DE ARQUITECTURA

La aplicación es un sitio web donde el usuario puede introducir mensajes y/o cadenas en un formulario sencillo. Se envían dichas cadenas y la página web muestra una lista de las últimas 10 cadenas enviadas y la respectiva fecha en que se enviaron.

## ARQUITECTURA

![image](https://github.com/rayo100/LogService-AREP/assets/89558695/3f4081ed-7f9d-4dc2-82b7-171e2fb2e785)

### MongoDB

El servicio MongoDB es una instancia de una base de datos MongoDB, almacena los mensajes enviados por el servicio de registro y la fecha en que se almacenaron.

### Log Service

LogService es un servicio REST que recibe una cadena, la almacena en la base de datos y responde con un objeto JSON que contiene las últimas 10 cadenas almacenadas en la base de datos junto con la fecha en que se almacenaron.

### APP-LB-RoundRobin

La aplicación web APP-LB-RoundRobin consta de un cliente web y un servicio REST. El cliente web tiene un campo y un botón, y cada vez que el usuario envía un mensaje, lo envía al servicio REST y actualiza la pantalla con la información devuelta en formato JSON. El servicio REST recibe la cadena e implementa un algoritmo de equilibrio de carga Round Robin, delegando el procesamiento del mensaje y la devolución de la respuesta a cada una de las tres instancias del servicio LogService.

# CÓMO INSTALAR

Para instalar el programa, sigue los pasos:

Clone ambos repositorios que componen la aplicación:

APP-LB-RoundRobin
```
git clone https://github.com/rayo100/RoundRobin-AREP.git
```
Log Service
```
https://github.com/rayo100/LogService-AREP.git
```
Para cada uno de los proyectos, ubíquese en el directorio correcto y ejecute el comando:
```
mvn clean install
```

# CREACIÓN IMÁGENES EN DOCKER

Para compilar las imágenes de Docker, recuerde que debe estar ubicado en el directorio del proyecto, ejecute el siguiente comando en para las partes del proyecto respectivamente. 

APP-LB-RoundRobin
```
docker build --tag roundrobin . -f Dockerfile
```
Log Service
```
docker build --tag logservice . -f Dockerfile
```

# EJECUCIÓN DEL PROYECTO

Para ejecutar el proyecto, corra los siguientes comandos teniendo en cuenta los directorios:
```
docker network create my_network
```
```
docker run -d -p 34000:4567 --name roundrobin1 --network my_network roundrobin
```
```
docker run -d -p 34001:4568 --name logservice1 --network my_network logservice
```
```  
docker run -d -p 34002:4569 --name logservice2 --network my_network logservice
```
```
docker run -d -p 34003:4570 --name logservice3 --network my_network logservice
```
```  
docker run -d -p 27017:27017 -v mongodb:/data/db -v mongodb_config:/data/configdb --name db --network my_network 
```
Después, debería poder ingresar la página web en la siguiente url
```
http://localhost:36000/app.html
```
# VIDEO

  

# HERRAMIENTAS

  * [Git](https://git-scm.com/) - Controlador de versiones
  * [Java](https://www.java.com/) - El lenguaje de programación utilizado
  * [JavaScript](https://www.javascript.com/) - El lenguaje de programación utilizado para la lógica de la página principal
  * [HTML](https://html.com/document/) - El lenguaje de marcado utilizado para la estructura de la página de inicio
  * [Maven](https://maven.apache.org/) - Gestión de dependencias
  * [Docker](https://www.docker.com/) - Container Management

# AUTOR

  * Cesar Vásquez [GitHub](https://github.com/rayo100)
