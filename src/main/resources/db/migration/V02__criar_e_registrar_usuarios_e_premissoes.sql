CREATE TABLE 02a_usuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	codigo_campus BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_campus) REFERENCES 01a_campus(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE 02b_permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE 02c_usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES 02a_usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES 02b_permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO 02a_usuario (codigo, nome, email, senha, codigo_campus) values (1, 'Suporte', 'ti.santahelena@ueg.br', '$2a$10$OVfTgA9P6rha79iS8hhpfekzNIsSq.HujSoOn8ZqOv1Nzlvty/DbG', 1);
INSERT INTO 02a_usuario (codigo, nome, email, senha, codigo_campus) values (2, 'Ana', 'ana@ueg.br', '$2a$10$C00ehTfnUTWcfvCnhMf7ju1cuIgQco29H49MnN9d4dyFbA78uMuQq', 42);

INSERT INTO 02b_permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_CONTATO');
INSERT INTO 02b_permissao (codigo, descricao) values (2, 'ROLE_PESQUISAR_CONTATO');
INSERT INTO 02b_permissao (codigo, descricao) values (3, 'ROLE_REMOVER_CONTATO');
INSERT INTO 02b_permissao (codigo, descricao) values (4, 'ROLE_CADASTRAR_DEPARTAMENTO');
INSERT INTO 02b_permissao (codigo, descricao) values (5, 'ROLE_REMOVER_DEPARTAMENTO');
INSERT INTO 02b_permissao (codigo, descricao) values (6, 'ROLE_PESQUISAR_DEPARTAMENTO');

INSERT INTO 02b_permissao (codigo, descricao) values (20, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO 02b_permissao (codigo, descricao) values (21, 'ROLE_REMOVER_USUARIO');
INSERT INTO 02b_permissao (codigo, descricao) values (22, 'ROLE_PESQUISAR_USUARIO');
INSERT INTO 02b_permissao (codigo, descricao) values (23, 'ROLE_BUSCAR_USUARIO');



INSERT INTO 02b_permissao (codigo, descricao) values (30, 'ROLE_CADASTRAR_ORDEM');
INSERT INTO 02b_permissao (codigo, descricao) values (31, 'ROLE_REMOVER_ORDEM');
INSERT INTO 02b_permissao (codigo, descricao) values (32, 'ROLE_PESQUISAR_ORDEM');
INSERT INTO 02b_permissao (codigo, descricao) values (33, 'ROLE_CADASTRAR_VEICULOS_CONDUTOR');
INSERT INTO 02b_permissao (codigo, descricao) values (34, 'ROLE_REMOVER_VEICULOS_CONDUTOR');
INSERT INTO 02b_permissao (codigo, descricao) values (35, 'ROLE_PESQUISAR_VEICULOS_CONDUTOR');

INSERT INTO 02b_permissao (codigo, descricao) values (60, 'ADMINISTRADOR');
INSERT INTO 02b_permissao (codigo, descricao) values (61, 'USUARIO');

INSERT INTO 02b_permissao (codigo, descricao) values (80, 'ROLE_DASHBOARD_AGENDA');
INSERT INTO 02b_permissao (codigo, descricao) values (81, 'ROLE_DASHBOARD_VIAGEM');

INSERT INTO 02b_permissao (codigo, descricao) values (101, 'ROLE_CADASTRAR_CAMPUS');
INSERT INTO 02b_permissao (codigo, descricao) values (102, 'ROLE_REMOVER_CAMPUS');
INSERT INTO 02b_permissao (codigo, descricao) values (103, 'ROLE_PESQUISAR_CAMPUS');
INSERT INTO 02b_permissao (codigo, descricao) values (104, 'ROLE_BUSCAR_CAMPUS');

-- suporte
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 20);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 21);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 22);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 23);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 30);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 31);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 32);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 33);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 34);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 35);

INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 60);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 61);


INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 80);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 81);

INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 101);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 102);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 103);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (1, 104);

-- ana
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 1);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 2);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 3);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 4);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 5);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 6);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 20);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 21);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 22);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 23);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 30);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 31);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 32);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 33);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 34);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 35);

INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 60);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 61);


INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 80);
INSERT INTO 02c_usuario_permissao (codigo_usuario, codigo_permissao) values (2, 81);



