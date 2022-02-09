create table if not exists tb_banco
(
	id_banco integer generated by default as identity
		constraint tb_banco_pkey
			primary key,
	dt_alter timestamp,
	dt_cria timestamp,
	email_banco varchar(255),
	morada_banco varchar(255),
	nif_banco varchar(255),
	nome_banco varchar(255)
);

alter table tb_banco owner to postgres;

create table if not exists tb_grupo
(
	id_grupo integer generated by default as identity
		constraint tb_grupo_pkey
			primary key,
	nome_grupo varchar(255)
);

alter table tb_grupo owner to postgres;

create table if not exists tb_permissao
(
	id_permissao integer generated by default as identity
		constraint tb_permissao_pkey
			primary key,
	nome_permissao varchar(255)
);

alter table tb_permissao owner to postgres;

create table if not exists tb_grupo_permissao
(
	grupo_id integer not null
		constraint fkc35tefcxk6t0b4u5qaenlv63e
			references tb_grupo,
	permissao_id integer not null
		constraint fkebkjqmqseopi0eipijfo06vh8
			references tb_permissao
);

alter table tb_grupo_permissao owner to postgres;

create table if not exists tb_pessoa
(
	id_pessoa integer generated by default as identity
		constraint tb_pessoa_pkey
			primary key,
	dt_alter timestamp,
	dt_cria timestamp,
	data_nascimento date,
	email_pessoa varchar(255),
	morada_pessoa varchar(255),
	numero_conta varchar(255),
	nif_pessoa varchar(255),
	nome_pessoa varchar(255),
	telemovel_pessoa varchar(255),
	id_banco integer
		constraint fks0wm1w2cj2yhkds3ml4ehtj0d
			references tb_banco,
	codigo_pessoa varchar not null,
	saldo_pessoa numeric default 0 not null
);

alter table tb_pessoa owner to postgres;

create table if not exists tb_cliente
(
	id_cliente integer generated by default as identity
		constraint tb_cliente_pkey
			primary key,
	dt_alter timestamp,
	dt_cria timestamp,
	id_pessoa integer
		constraint fk84n2v0pnhf49kc025k6mdrqmk
			references tb_pessoa
);

alter table tb_cliente owner to postgres;

create table if not exists tb_empresa
(
	id_empresa integer generated by default as identity
		constraint tb_empresa_pkey
			primary key,
	dt_alter timestamp,
	dt_cria timestamp,
	id_pessoa integer
		constraint fkiutrtg8o0mir2qhvf5cd7exsy
			references tb_pessoa
);

alter table tb_empresa owner to postgres;

create table if not exists tb_transferencia
(
	id_transferencia integer generated by default as identity
		constraint tb_transferencia_pkey
			primary key,
	dt_cria timestamp,
	extensao_doc varchar(255),
	nome_doc varchar(255),
	tamanho_doc varchar(255),
	is_lido boolean default false not null
);

alter table tb_transferencia owner to postgres;

create table if not exists tb_usuario
(
	id_usuario integer generated by default as identity
		constraint tb_usuario_pkey
			primary key,
	dt_alter timestamp,
	dt_cria timestamp,
	nome_usuario varchar(255),
	senha_usuario varchar(255),
	cliente_id integer
		constraint fkkbqinbe0ibflouklll4j1bqgi
			references tb_cliente,
	empresa_id integer
		constraint tb_usuario_tb_empresa_id_empresa_fk
			references tb_empresa
);

alter table tb_usuario owner to postgres;

create table if not exists tb_usuario_grupo
(
	usuario_id integer not null
		constraint fkos1k9g527yqpdorasypy0h72d
			references tb_usuario,
	grupo_id integer not null
		constraint fkfox4y8jslkfybem54i4jyndnj
			references tb_grupo
);

alter table tb_usuario_grupo owner to postgres;

create table if not exists tb_pedido_saldo
(
	id_pedido_saldo serial not null,
	codigo_emitente varchar not null,
	email_remitente varchar not null,
	quantidade_saldo numeric not null,
	is_accepted boolean,
	dt_cria timestamp not null,
	dt_alter timestamp not null,
	descricao_pedido_saldo varchar not null,
	nome_emitente varchar not null
);

alter table tb_pedido_saldo owner to postgres;

create table if not exists tb_contacto
(
	contacto_id integer not null
		constraint tb_contacto_tb_pessoa_id_pessoa_fk
			references tb_pessoa,
	usuario_id integer not null
		constraint tb_contacto_tb_usuario_id_usuario_fk_2
			references tb_usuario,
	dt_cria timestamp,
	dt_alter timestamp,
	constraint tb_contacto_pk
		primary key (contacto_id, usuario_id)
);

alter table tb_contacto owner to postgres;

create table if not exists tb_movimentacao
(
	id_movimentacao serial not null
		constraint tb_movimentacao_pk
			primary key,
	quantidade_saldo numeric not null,
	usuario_id integer not null
		constraint tb_movimentacao_tb_pessoa_id_pessoa_fk
			references tb_pessoa,
	cliente_id integer
		constraint tb_movimentacao_tb_pessoa_id_pessoa_fk_2
			references tb_pessoa,
	descricao_movimentacao varchar,
	tipo_movimentacao varchar,
	estilo varchar,
	dt_cria timestamp,
	dt_alter timestamp
);

alter table tb_movimentacao owner to postgres;

