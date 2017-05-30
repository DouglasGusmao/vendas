create database vendas;

use teste;

create table cliente(id int(6) auto_increment primary key,
nome varchar(50) not null,
email varchar(50) not null,
senha varchar(8) not null
);

create table produto(id int(6) auto_increment primary key,
titulo varchar(100) not null,
descricao text not null,
preco double not null,
nomeImagem varchar(100) not null
);

create table pedido(id int(6) auto_increment primary key,
data_pedido datetime,
total double(6,2) not null,
id_cliente int not null,
CONSTRAINT cliente_pedido_fk FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);

create table item_pedido(id_pedido int(6) not null,
id_produto int(6) not null,
preco_unitario double(6,2) not null,
quantidade int not null,
preco_total double(6,2) not null,
CONSTRAINT produto_item_pedido_fk FOREIGN KEY (id_produto) REFERENCES produto (id),
CONSTRAINT pedido_item_pedido_fk FOREIGN KEY (id_pedido) REFERENCES pedido (id)
);
