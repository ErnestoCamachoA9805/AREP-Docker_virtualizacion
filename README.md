# AREP-Docker_virtualizacion
> ## Autor:
>> Ernesto Camacho Arroyave 
>>
> ## Fecha:
>> 30/9/2021
> ## Diseño
>> Se mantuvo acorde a la solicitud y descripcion del trabajo, existe un contenedor en el que esta la base de datos, 3 contenedores del servicio de logedo que le hacen solicitudes a la base de datos y un contenedor al que le llegan las solicitudes y recibe las respuestas de los servicios, hace rotacion entre los contenedores .
>>
>> Imagenes montadas en la maquina aws:
>>
>> ![] imagen 
>>
>> El proyecto se probo de manera local 
>>
>> ![] imagen
>>
>> El proyecto en la maquina de aws
>>
>> ![] imagen
>>
>> El proyecto recibe una palabra y responde las 10 ultimas cadenas de la base de datos.
>>
> ## Requerimnientos
>> - maven 
>> - java 
>> - docker 
>>
> ## Javadoc
>> el javadoc se encuentra en la carpeta de recursos
> ## Links
>> Dockerhub: 
>> - https://hub.docker.com/repository/docker/ernestoca9805/arep_logservicedocker
>>
>> maquina aws: 
>> - http://ec2-34-227-27-85.compute-1.amazonaws.com/