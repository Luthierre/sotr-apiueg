CREATE TABLE 01a_campus (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,	
	nome VARCHAR(60),
	principal BOOLEAN	
 )ENGINE = InnoDB CHARSET=utf8;
 
CREATE TABLE 01b_departamento (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,	
	descricao VARCHAR(60),
	codigo_campus BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_campus) REFERENCES 01a_campus(codigo)	
 )ENGINE = InnoDB CHARSET=utf8;
 
CREATE TABLE 01c_contato (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(80),
	email VARCHAR(50),
	observacao MEDIUMTEXT,
	codigo_departamento BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_departamento) REFERENCES 01b_departamento(codigo)
) ENGINE = InnoDB CHARSET=utf8;
 
CREATE TABLE 01d_telefones (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	tipo VARCHAR(30),
	area VARCHAR(10),
	numero VARCHAR(14),
	codigo_contato BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_contato) REFERENCES 01c_contato(codigo)
 ) ENGINE = InnoDB CHARSET=utf8 ;

INSERT INTO 01a_campus (codigo, nome, principal) values (1, 'Administracao Central', 1);

INSERT INTO 01a_campus (codigo, nome, principal) values (2, 'Anápolis CCET', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (3, 'Anápolis CSEH', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (4, 'Aparecida de Goiânia', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (5, 'Caldas Novas', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (6, 'Campos Belos', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (7, 'Ceres', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (8, 'Cora Coralina', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (9, 'Crixás', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (10, 'Edéia', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (12, 'Eseffego', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (13, 'Formosa', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (14, 'Goianésia', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (15, 'Inhumas', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (16, 'Ipameri', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (17, 'Iporá', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (18, 'Itaberaí', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (19, 'Itapuranga', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (20, 'Itumbiara', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (21, 'Jaraguá', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (22, 'Jataí', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (23, 'Jussara', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (24, 'Laranjeiras', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (25, 'Luziânia', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (26, 'Minaçu', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (27, 'Mineiros', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (28, 'Niquelândia', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (29, 'Palmeiras De Goiás', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (30, 'Pirenópolis', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (31, 'Pires do Rio', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (32, 'Porangatu', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (33, 'Posse', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (34, 'Quirinópolis', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (35, 'Sanclerlândia', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (36, 'Santa Helena de Goiás', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (37, 'São Luís de Montes Belos', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (38, 'São Miguel do Araguaia', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (39, 'Senador Canedo', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (40, 'Silvânia', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (41, 'Trindade', 0);
INSERT INTO 01a_campus (codigo, nome, principal) values (42, 'Uruaçu', 0);