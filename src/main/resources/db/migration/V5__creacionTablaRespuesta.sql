create table respuesta(
id bigint primary key auto_increment,
mensaje varchar(80) not null unique,
id_topico bigint not null,
fecha_creacion DATETIME not null,
id_autor bigint not null,
solucion boolean,
foreign key (id_topico) references topico(id),
foreign key (id_autor) references usuario(id)
);