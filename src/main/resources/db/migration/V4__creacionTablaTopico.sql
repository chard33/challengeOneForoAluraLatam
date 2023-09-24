create table topico(
id bigint primary key auto_increment,
titulo varchar(80) not null unique,
mensaje varchar(80) not null unique,
fecha_creacion DATETIME not null,
status varchar(80) not null,
id_autor bigint not null,
id_curso bigint not null,
foreign key (id_autor) references usuario(id),
foreign key (id_curso) references curso(id)
);