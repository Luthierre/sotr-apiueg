/*
SQLyog Ultimate v9.62 
MySQL - 5.7.18-log : Database - algamoneyapi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`algamoneyapi` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `algamoneyapi`;

/*Data for the table `categoria` */

insert  into `categoria`(`codigo`,`nome`) values (1,'Lazer'),(2,'Alimentação'),(3,'Supermercado'),(4,'Farmácia'),(5,'Outros');

/*Data for the table `contato` */

/*Data for the table `lancamento` */

insert  into `lancamento`(`codigo`,`descricao`,`data_vencimento`,`data_pagamento`,`valor`,`observacao`,`tipo`,`codigo_categoria`,`codigo_pessoa`) values (1,'Salário mensal','2018-06-10',NULL,'6500.00','Distribuição de lucros','RECEITA',1,1),(2,'Supermercado','2018-02-10',NULL,'100.32',NULL,'DESPESA',2,2),(3,'Academia','2018-06-10',NULL,'120.00',NULL,'DESPESA',3,3),(4,'Conta de luz','2018-02-10',NULL,'110.44',NULL,'DESPESA',3,4),(5,'Conta de água','2018-06-10',NULL,'200.30',NULL,'DESPESA',3,5),(6,'Restaurante','2018-03-10',NULL,'1010.32',NULL,'DESPESA',4,6),(7,'Venda vídeo game','2018-06-10',NULL,'500.00',NULL,'RECEITA',1,7),(8,'Clube','2018-03-10',NULL,'400.32',NULL,'DESPESA',4,8),(9,'Impostos','2018-06-10',NULL,'123.64','Multas','DESPESA',3,9),(10,'Multa','2018-04-10',NULL,'665.33',NULL,'DESPESA',5,10),(11,'Padaria','2018-06-10',NULL,'8.32',NULL,'DESPESA',1,5),(12,'Papelaria','2018-04-10',NULL,'2100.32',NULL,'DESPESA',5,4),(13,'Almoço','2018-06-10',NULL,'1040.32',NULL,'DESPESA',4,3),(14,'Café','2018-04-10','2018-04-10','4.32',NULL,'DESPESA',4,2),(15,'Lanche','2018-06-10',NULL,'10.20',NULL,'DESPESA',4,1),(16,'Mensalidade Sistema','2018-05-10',NULL,'156820.36',NULL,'RECEITA',5,8);

/*Data for the table `permissao` */

insert  into `permissao`(`codigo`,`descricao`) values (1,'ROLE_CADASTRAR_CATEGORIA'),(2,'ROLE_PESQUISAR_CATEGORIA'),(3,'ROLE_CADASTRAR_PESSOA'),(4,'ROLE_REMOVER_PESSOA'),(5,'ROLE_PESQUISAR_PESSOA'),(6,'ROLE_CADASTRAR_LANCAMENTO'),(7,'ROLE_REMOVER_LANCAMENTO'),(8,'ROLE_PESQUISAR_LANCAMENTO');

/*Data for the table `pessoa` */

insert  into `pessoa`(`codigo`,`nome`,`logradouro`,`numero`,`complemento`,`bairro`,`cep`,`cidade`,`estado`,`ativo`) values (1,'João Silva','Rua do Abacaxi','10',NULL,'Brasil','38.400-121','Uberlândia','MG',1),(2,'Maria Rita','Rua do Sabiá','110','Apto 101','Colina','11.400-121','Ribeirão Preto','SP',1),(3,'Pedro Santos','Rua da Bateria','23',NULL,'Morumbi','54.212-121','Goiânia','GO',1),(4,'Ricardo Pereira','Rua do Motorista','123','Apto 302','Aparecida','38.400-12','Salvador','BA',1),(5,'Josué Mariano','Av Rio Branco','321',NULL,'Jardins','56.400-121','Natal','RN',1),(6,'Pedro Barbosa','Av Brasil','100',NULL,'Tubalina','77.400-121','Porto Alegre','RS',1),(7,'Henrique Medeiros','Rua do Sapo','1120','Apto 201','Centro','12.400-121','Rio de Janeiro','RJ',1),(8,'Carlos Santana','Rua da Manga','433',NULL,'Centro','31.400-121','Belo Horizonte','MG',1),(9,'Leonardo Oliveira','Rua do Músico','566',NULL,'Segismundo Pereira','38.400-00','Uberlândia','MG',1),(10,'Isabela Martins','Rua da Terra','1233','Apto 10','Vigilato','99.400-121','Manaus','AM',1);

/*Data for the table `schema_version` */

insert  into `schema_version`(`version_rank`,`installed_rank`,`version`,`description`,`type`,`script`,`checksum`,`installed_by`,`installed_on`,`execution_time`,`success`) values (1,1,'01','criar e registrar categorias','SQL','V01__criar_e_registrar_categorias.sql',-1283744520,'root','2018-04-03 15:33:45',225,1),(2,2,'02','criar tabela pessoa','SQL','V02__criar_tabela_pessoa.sql',1975717565,'root','2018-04-03 15:33:45',196,1),(3,3,'03','criar tabela e registrar lancamentos','SQL','V03__criar_tabela_e_registrar_lancamentos.sql',-1827997622,'root','2018-04-03 15:33:45',305,1),(4,4,'04','criar e registrar usuarios e permissoes','SQL','V04__criar_e_registrar_usuarios_e_permissoes.sql',-1228387442,'root','2018-04-03 15:33:46',699,1),(5,5,'05','criar tabela contato','SQL','V05__criar_tabela_contato.sql',-188361352,'root','2018-04-05 09:26:32',648,0);

/*Data for the table `usuario` */

insert  into `usuario`(`codigo`,`nome`,`email`,`senha`) values (1,'Administrador','luthierre.silva@ueg.br','$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.'),(2,'Maria Silva','luthierre.silva@gmail.com','$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

/*Data for the table `usuario_permissao` */

insert  into `usuario_permissao`(`codigo_usuario`,`codigo_permissao`) values (1,1),(1,2),(2,2),(1,3),(1,4),(1,5),(2,5),(1,6),(1,7),(1,8),(2,8);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
