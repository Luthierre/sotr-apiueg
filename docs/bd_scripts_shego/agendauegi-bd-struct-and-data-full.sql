/*
SQLyog Ultimate v9.62 
MySQL - 5.7.18-log : Database - agendaueg
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`agendaueg` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `agendaueg`;

/*Table structure for table `contato` */

DROP TABLE IF EXISTS `contato`;

CREATE TABLE `contato` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `observacao` mediumtext,
  `codigo_departamento` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_departamento` (`codigo_departamento`),
  CONSTRAINT `contato_ibfk_1` FOREIGN KEY (`codigo_departamento`) REFERENCES `departamento` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `contato` */

insert  into `contato`(`codigo`,`nome`,`email`,`observacao`,`codigo_departamento`) values (1,'Maria da Gloria','maria.gloria@ueg.br','assistente administrativa do campus de Santa Helena de Goiás',1),(2,'Nincineia','dir.santahelena@ueg.br','Direção do campus Santa Helena',3),(4,'Mariana','bib.santahelena@ueg.br','bibliotecaria do campus de Santa Helena de Goiás',1),(6,'Leandro José da Silva','leandrojose@gmail.com','Encarregado da lanchonete da UEG',4),(8,'Géssica Silva','gessica.silva@ueg.br','Assistente administrativo da coordenação de cursos do campus de Santa Helena de Goiás',3),(11,'Filipe Alves','filipe@gmail.com','Assistente na area de informatica',4),(12,'Erico Montana de Souza','erico@gmail.com',NULL,2),(13,'Luthierre Same Pereira Silva','luthierre23same@gmail.com',NULL,6),(14,'Gelzieny Rezende Martins','gelzieny@gmail.com','Estudante de sistemas de informação e membro CEDSI - Shego',6),(15,'Leandro Cabral','leandro@gmail.com','Testando',1),(16,'Testando aplicação','teste@ueg.br','Testando a aplicação',1);

/*Table structure for table `departamento` */

DROP TABLE IF EXISTS `departamento`;

CREATE TABLE `departamento` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `departamento` */

insert  into `departamento`(`codigo`,`descricao`) values (1,'Diretoria'),(2,'UEG-Transportes'),(3,'UEG-Edéia'),(4,'UEG-Santa Helena'),(5,'Secretaria'),(6,'Técnologia da Informação');

/*Table structure for table `permissao` */

DROP TABLE IF EXISTS `permissao`;

CREATE TABLE `permissao` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permissao` */

insert  into `permissao`(`codigo`,`descricao`) values (1,'ROLE_CADASTRAR_CONTATO'),(2,'ROLE_PESQUISAR_CONTATO'),(3,'ROLE_REMOVER_CONTATO'),(4,'ROLE_CADASTRAR_DEPARTAMENTO'),(5,'ROLE_REMOVER_DEPARTAMENTO'),(6,'ROLE_PESQUISAR_DEPARTAMENTO'),(7,'ROLE_CADASTRAR_USUARIO'),(8,'ROLE_REMOVER_USUARIO'),(9,'ROLE_PESQUISAR_USUARIO'),(10,'ROLE_MENU_USUARIO'),(11,'ROLE_MENU_CONTATO'),(12,'ROLE_MENU_DEPARTAMENTO');

/*Table structure for table `schema_version` */

DROP TABLE IF EXISTS `schema_version`;

CREATE TABLE `schema_version` (
  `version_rank` int(11) NOT NULL,
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`version`),
  KEY `schema_version_vr_idx` (`version_rank`),
  KEY `schema_version_ir_idx` (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `schema_version` */

insert  into `schema_version`(`version_rank`,`installed_rank`,`version`,`description`,`type`,`script`,`checksum`,`installed_by`,`installed_on`,`execution_time`,`success`) values (1,1,'01','criar tabelas departamento contato telefones','SQL','V01__criar_tabelas_departamento_contato_telefones.sql',-1537142455,'root','2018-04-10 10:04:15',1278,1),(2,2,'02','criar e registrar usuarios e premissoes','SQL','V02__criar_e_registrar_usuarios_e_premissoes.sql',2027552337,'root','2018-04-11 13:05:07',951,1);

/*Table structure for table `telefones` */

DROP TABLE IF EXISTS `telefones`;

CREATE TABLE `telefones` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(30) DEFAULT NULL,
  `area` varchar(4) DEFAULT NULL,
  `numero` varchar(14) DEFAULT NULL,
  `codigo_contato` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_contato` (`codigo_contato`),
  CONSTRAINT `telefones_ibfk_1` FOREIGN KEY (`codigo_contato`) REFERENCES `contato` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `telefones` */

insert  into `telefones`(`codigo`,`tipo`,`area`,`numero`,`codigo_contato`) values (1,'PESSOAL','64','992002691',1),(2,'PESSOAL','64','922101201',1),(3,'PESSOAL','61','981252121',2),(4,'PESSOAL','(64)','99200-2691',13),(5,'RESIDENCIAL','(64)','36452-124',13),(9,'PESSOAL','64','9922-68460',14),(10,'TRABALHO','64','3611-0201',14),(11,'CORPORATIVO','62','2412-1311',15),(12,'DEPARTAMENTO','64','2512-1211',16);

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(150) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `usuario` */

insert  into `usuario`(`codigo`,`nome`,`email`,`senha`) values (1,'Ti-UEG','ti.santahelena@ueg.br','$2a$10$kBLnIqLFe62/1alsjRWtFuWbOw5sDMbAstwhMNdUQKomsl2POEP3a'),(2,'Luthierre','luthierre.silva@ueg.br','$2a$10$mdras66GxjRbe684T6TMOeXeJzppX3YqprfBCm3h6BQvb4GWiSmUW'),(3,'Secretaria','sec.santahelena@ueg.br','$2a$10$oRZwBShnoDe5swb1GkeOSehregn1Nsg9p2vuXlJSN7SsuQx1P077.');

/*Table structure for table `usuario_permissao` */

DROP TABLE IF EXISTS `usuario_permissao`;

CREATE TABLE `usuario_permissao` (
  `codigo_usuario` bigint(20) NOT NULL,
  `codigo_permissao` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo_usuario`,`codigo_permissao`),
  KEY `codigo_permissao` (`codigo_permissao`),
  CONSTRAINT `usuario_permissao_ibfk_1` FOREIGN KEY (`codigo_usuario`) REFERENCES `usuario` (`codigo`),
  CONSTRAINT `usuario_permissao_ibfk_2` FOREIGN KEY (`codigo_permissao`) REFERENCES `permissao` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usuario_permissao` */

insert  into `usuario_permissao`(`codigo_usuario`,`codigo_permissao`) values (1,1),(2,1),(3,1),(1,2),(2,2),(3,2),(1,3),(2,3),(3,3),(1,4),(3,4),(1,5),(3,5),(1,6),(3,6),(1,7),(1,8),(1,9),(1,10),(1,11),(2,11),(3,11),(1,12),(3,12);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
