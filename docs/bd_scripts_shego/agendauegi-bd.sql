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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `contato` */

insert  into `contato`(`codigo`,`nome`,`email`,`observacao`,`codigo_departamento`) values (1,'Maria da Gloria','maria.gloria@ueg.br','assistente administrativa do campus de Santa Helena de Goiás',1),(2,'Nincineia','dir.santahelena@ueg.br','Direção do campus Santa Helena',3),(3,'Jaqueline','secretaria.santahelena@ueg.br','secretaria do campus de Santa Helena de Goiás',3),(4,'Mariana','bib.santahelena@ueg.br','bibliotecaria do campus de Santa Helena de Goiás',1),(5,'Benedito','benedito.hoofmann@ueg.br','Coordenador administrativo do campus de Santa Helena de Goiás',3),(6,'Rafaela','rafaela.queiroz@ueg.br','Assistente administrativo do departamento de compras do campus de Santa Helena de Goiás',1),(7,'Carla Patricia','dir.sahtahelena@ueg.br','Assistente administrativo da direção do campus de Santa Helena de Goiás',1),(8,'Géssica Silva','gessica.silva@ueg.br','Assistente administrativo da coordenação de cursos do campus de Santa Helena de Goiás',3),(9,'Elivelton de Souza','elivelton.souza@ueg.br','Coordenador pedagogico do campus de Santa Helena de Goiás',1);

/*Table structure for table `departamento` */

DROP TABLE IF EXISTS `departamento`;

CREATE TABLE `departamento` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `departamento` */

insert  into `departamento`(`codigo`,`descricao`) values (1,'Diretoria'),(2,'UEG-Transportes'),(3,'UEG-Edéia'),(4,'UEG-Santa Helena');

/*Table structure for table `permissao` */

DROP TABLE IF EXISTS `permissao`;

CREATE TABLE `permissao` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permissao` */

insert  into `permissao`(`codigo`,`descricao`) values (1,'ROLE_CADASTRAR_CONTATO'),(2,'ROLE_PESQUISAR_CONTATO'),(3,'ROLE_REMOVER_CONTATO'),(4,'ROLE_CADASTRAR_DEPARTAMENTO'),(5,'ROLE_REMOVER_DEPARTAMENTO'),(6,'ROLE_PESQUISAR_PESSOA'),(7,'ROLE_CADASTRAR_USUARIO'),(8,'ROLE_REMOVER_USUARIO'),(9,'ROLE_PESQUISAR_USUARIO');

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

insert  into `schema_version`(`version_rank`,`installed_rank`,`version`,`description`,`type`,`script`,`checksum`,`installed_by`,`installed_on`,`execution_time`,`success`) values (1,1,'01','criar tabelas departamento contato telefones','SQL','V01__criar_tabelas_departamento_contato_telefones.sql',-1537142455,'root','2018-04-10 10:04:15',1278,1),(2,2,'02','criar e registrar usuarios e premissoes','SQL','V02__criar_e_registrar_usuarios_e_premissoes.sql',1736975475,'root','2018-04-11 13:05:07',951,1);

/*Table structure for table `telefones` */

DROP TABLE IF EXISTS `telefones`;

CREATE TABLE `telefones` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(30) DEFAULT NULL,
  `area` varchar(2) DEFAULT NULL,
  `numero` varchar(14) DEFAULT NULL,
  `codigo_contato` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_contato` (`codigo_contato`),
  CONSTRAINT `telefones_ibfk_1` FOREIGN KEY (`codigo_contato`) REFERENCES `contato` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `telefones` */

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(150) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `usuario` */

insert  into `usuario`(`codigo`,`nome`,`email`,`senha`) values (1,'Suporte','ti.santahelena@ueg.br','$2a$10$OVfTgA9P6rha79iS8hhpfekzNIsSq.HujSoOn8ZqOv1Nzlvty/DbG'),(2,'Luthierre','luthierre.silva@gmail.com','$2a$10$jjQ.3aYeO.WR/FXkSg3BrOFOZcjFxOKd.3a7NmVjf39tgSUhZQkPW');

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

insert  into `usuario_permissao`(`codigo_usuario`,`codigo_permissao`) values (1,1),(1,2),(2,2),(1,3),(1,4),(2,4),(1,5),(1,6),(1,7),(1,8),(1,9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
