create table tb_pessoa (
	id_pessoa int8 NOT NULL,
	dt_inclusao timestamp NOT NULL,
	dt_atualizacao timestamp NOT NULL,
	vl_nome varchar(150) NOT NULL,
	vl_sexo varchar(10) ,
	vl_naturalidade varchar(50) ,
	vl_nacionalidade varchar(50) ,
	dt_nascimento date,
	vl_email varchar(150)
);