create table usuario(
id bigint primary key auto_increment,
nombre varchar(80) not null,
email varchar(80) not null unique,
contrasena varchar(80) not null
);