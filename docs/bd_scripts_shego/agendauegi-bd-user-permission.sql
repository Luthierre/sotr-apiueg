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

/*Data for the table `permissao` */

insert  into `permissao`(`codigo`,`descricao`) values (1,'ROLE_CADASTRAR_CONTATO'),(2,'ROLE_PESQUISAR_CONTATO'),(3,'ROLE_REMOVER_CONTATO'),(4,'ROLE_CADASTRAR_DEPARTAMENTO'),(5,'ROLE_REMOVER_DEPARTAMENTO'),(6,'ROLE_PESQUISAR_DEPARTAMENTO'),(7,'ROLE_CADASTRAR_USUARIO'),(8,'ROLE_REMOVER_USUARIO'),(9,'ROLE_PESQUISAR_USUARIO'),(10,'ROLE_MENU_USUARIO'),(11,'ROLE_MENU_CONTATO'),(12,'ROLE_MENU_DEPARTAMENTO');

/*Data for the table `usuario` */

insert  into `usuario`(`codigo`,`nome`,`email`,`senha`) values (1,'Ti-UEG','ti.santahelena@ueg.br','$2a$10$kBLnIqLFe62/1alsjRWtFuWbOw5sDMbAstwhMNdUQKomsl2POEP3a'),(2,'Luthierre','luthierre.silva@ueg.br','$2a$10$mdras66GxjRbe684T6TMOeXeJzppX3YqprfBCm3h6BQvb4GWiSmUW'),(3,'Secretaria','sec.santahelena@ueg.br','$2a$10$oRZwBShnoDe5swb1GkeOSehregn1Nsg9p2vuXlJSN7SsuQx1P077.');

/*Data for the table `usuario_permissao` */

insert  into `usuario_permissao`(`codigo_usuario`,`codigo_permissao`) values (1,1),(2,1),(3,1),(1,2),(2,2),(3,2),(1,3),(2,3),(3,3),(1,4),(3,4),(1,5),(3,5),(1,6),(3,6),(1,7),(1,8),(1,9),(1,10),(1,11),(2,11),(3,11),(1,12),(3,12);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
