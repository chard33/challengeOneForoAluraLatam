create table curso(
id bigint primary key auto_increment,
nombre varchar(80) not null unique,
id_categoria bigint not null,
foreign key (id_categoria) references categoria(id)
);