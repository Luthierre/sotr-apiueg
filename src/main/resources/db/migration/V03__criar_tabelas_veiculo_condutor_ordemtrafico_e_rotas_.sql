 CREATE TABLE 03a_veiculo (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	modelo VARCHAR(80),
	fabricante VARCHAR(80),
	placa VARCHAR(10),
	ano VARCHAR(10),
	observacao MEDIUMTEXT,
	codigo_campus BIGINT(20) NOT NULL,	
	FOREIGN KEY (codigo_campus) REFERENCES 01a_campus(codigo)	
) ENGINE = InnoDB CHARSET=utf8;

CREATE TABLE 03b_condutor (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100),
	cpf VARCHAR(15),
	numero_cnh VARCHAR(20),
	categoria_cnh VARCHAR(10),
	data_cadastro DATE NOT NULL,
	data_nascimento DATE NOT NULL,
	data_vencimento_cnh DATE NOT NULL,
	codigo_campus BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_campus) REFERENCES 01a_campus(codigo)
) ENGINE = InnoDB CHARSET=utf8;

CREATE TABLE 03c_ordem_de_trafego (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,	
	solicitante VARCHAR(100),	
	data_cadastro DATE NOT NULL,
	data_solicitacao DATE NOT NULL,
	observacao MEDIUMTEXT,
	codigo_veiculo BIGINT(20) NOT NULL,
	codigo_condutor BIGINT(20) NOT NULL,
	codigo_usuario BIGINT(20) NOT NULL,
	total_km DECIMAL(10,2) NOT NULL,
	codigo_campus BIGINT(20) NOT NULL,	
	ativo BOOLEAN ,
	FOREIGN KEY (codigo_veiculo) REFERENCES 03a_veiculo(codigo),
	FOREIGN KEY (codigo_condutor) REFERENCES 03b_condutor(codigo),
	FOREIGN KEY (codigo_usuario) REFERENCES 02a_usuario(codigo),
	FOREIGN KEY (codigo_campus) REFERENCES 01a_campus(codigo)	
) ENGINE = InnoDB CHARSET=utf8;

CREATE TABLE 03d_rotas (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	local_saida VARCHAR(50),
	data_saida DATE NOT NULL,
	hora_saida VARCHAR(10),
	km_saida DECIMAL(10,2) NOT NULL,
	local_chegada VARCHAR(50),
	data_chegada DATE NOT NULL,
	hora_chegada VARCHAR(10),
	km_chegada DECIMAL(10,2) NOT NULL,
	km_percorrido DECIMAL(10,2) NOT NULL,	
	nome VARCHAR(50),
	servico VARCHAR(50),
	observacao MEDIUMTEXT,
	codigo_ordem BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_ordem) REFERENCES 03c_ordem_de_trafego(codigo)	
) ENGINE = InnoDB CHARSET=utf8;