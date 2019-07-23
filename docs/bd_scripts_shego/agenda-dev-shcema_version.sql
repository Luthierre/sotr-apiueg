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

/*Data for the table `schema_version` */

insert  into `schema_version`(`version_rank`,`installed_rank`,`version`,`description`,`type`,`script`,`checksum`,`installed_by`,`installed_on`,`execution_time`,`success`) values (1,1,'01','criar tabelas departamento contato telefones','SQL','V01__criar_tabelas_departamento_contato_telefones.sql',-1537142455,'root','2018-05-04 13:23:06',715,1),(2,2,'02','criar e registrar usuarios e premissoes','SQL','V02__criar_e_registrar_usuarios_e_premissoes.sql',-1849291918,'root','2018-05-04 13:23:07',820,1),(3,3,'03','criar tabelas veiculo ambiente reservas recursos','SQL','V03__criar_tabelas_veiculo_ambiente_reservas_recursos.sql',1047254733,'root','2018-05-04 13:23:09',1638,1),(4,4,'04','criar tabelas cidade e estados e registar estados e cidades e reserva viagem','SQL','V04__criar_tabelas_cidade_e_estados_e_registar_estados_e_cidades_e_reserva_viagem.sql',1592741038,'root','2018-05-04 13:23:10',1455,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
