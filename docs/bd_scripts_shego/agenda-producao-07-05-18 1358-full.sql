/*
SQLyog Ultimate v9.62 
MySQL - 5.5.56-MariaDB : Database - agendaueg
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`agendaueg` /*!40100 DEFAULT CHARACTER SET latin1 */;

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
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;

/*Data for the table `contato` */

insert  into `contato`(`codigo`,`nome`,`email`,`observacao`,`codigo_departamento`) values (1,'Luthierre Same','luthierre.silva@ueg.br','Teste para software em produção',11),(2,'Haroldo Reimer','reitor@ueg.br','',2),(3,'Centro de Comunicação Institucional','comunicacao@ueg.br','relacoespublicas@ueg.br\njornalismo@ueg.br\npublicidade@ueg.br',2),(4,'Chefia de Gabinete','gabinete@ueg.br',NULL,2),(7,'Assessoria dos Órgãos Colegiados','colegiados@ueg.br',NULL,2),(8,'Filipe de Jesus Barros','filipebarros15@hotmail.com',NULL,11),(9,'Ouvidoria','ouvidoria@ueg.br',NULL,2),(10,'Comissão Central de Estágio Probatório','probatorio@ueg.br',NULL,2),(11,'Gerência Jurídica','gejur@ueg.br',NULL,2),(12,'Centro de Comunicação Institucional','comunicacao@ueg.br','relacoespublicas@ueg.br\njornalismo@ueg.br\npublicidade@ueg.br',2),(13,'Assessoria de Relações Externas','arex@ueg.br',NULL,2),(14,'Gerência de Convênios Acadêmicos de Captação de Recursos','convenios@ueg.br',NULL,2),(15,'Núcleo de Seleção','dir.ns@ueg.br',NULL,3),(16,'Coordenação do EVV','gabinete.evv@ueg.br',NULL,2),(17,'Coordenação Central de Bolsas','centraldebolsas@ueg.br',NULL,2),(18,'Gabinete PrE','pre@ueg.br',NULL,4),(19,'Coordenação de Extensão','extensao@ueg.br',NULL,4),(20,'Gerência de Assuntos Estudantis e Egressos','assuntosestudantis@ueg.br',NULL,4),(21,'Coordenação de Cultura','cultura@ueg.br',NULL,4),(22,'Coordenação Programa de Incubadoras da UEG','proin@ueg.br',NULL,4),(23,'Pronatec','pronatec@ueg.br',NULL,4),(24,'Mediotec','mediotec@ueg.br',NULL,4),(25,'Gabinet PrG','prg@ueg.br',NULL,5),(26,'Recepção PrG','recepcao.prg@ueg.br',NULL,5),(27,'Câmara de Graduação','camara.prg@ueg.br',NULL,5),(28,'Coordenação Geral PrG','coordgeral.prg@ueg.br',NULL,5),(29,'Coordenação Acadêmica PrG','coordacademica.prg@ueg.br',NULL,5),(30,'Assessoria de Expedição de Diplomas','diploma.prg@ueg.br',NULL,5),(31,'Assessoria de Sistemas Acadêmicos - Fenix','fenix@ueg.br',NULL,5),(32,'Coordenação de Ensino Prg','coordensino.prg@ueg.br',NULL,5),(33,'Assessoria Sistema Integrado de Bibliotecas-Sibre','sibre.prg@ueg.br',NULL,5),(34,'Coordenação de Programas e Projetos ','programaseprojetos.prg@ueg.br',NULL,5),(35,'Coordenação de Direitos Humanos e Diversidade','direitoshumanos.prg@ueg.br',NULL,5),(36,'Gabinete PrP','prp@ueg.br',NULL,9),(37,'Coordenação de Pesquisa e Inovação','pesquisa@ueg.br',NULL,9),(38,'Coordenação de Iniciação e Tecnológica','ict@ueg.br',NULL,9),(39,'Coordenação de Projetos e Publicações','revista.prp@ueg.br',NULL,9),(40,'Gerência de Pós-Graduação','latosensu@ueg.br',NULL,9),(41,'Coordenação Stricto Sensu','strictosensu@ueg.br',NULL,9),(42,'Gabinete - PrGF','prgf@ueg.br',NULL,6),(43,'Gerência de Contratos','contratos@ueg.br',NULL,6),(44,'Comissão Permanente de Licitação','licitacao@ueg.br',NULL,6),(45,'Gerência de Finanças','gerfin@ueg.br',NULL,6),(49,'Coordenação de Diárias','diarias@ueg.br',NULL,6),(50,'Coordenação de Contabilidade','contabilidade@ueg.br',NULL,6),(51,'Coordenação de Fundo Rotativo','fundorotativo@ueg.br',NULL,6),(52,'Gerência de Apoio Logistico e de Suprimentos','geals@ueg.br',NULL,6),(53,'Coordenação de Almoxarifado','almoxarifado@ueg.br',NULL,6),(54,'Coordenação de Arquivo','arquivo@ueg.br',NULL,6),(55,'Coordenação de Compras','compras@ueg.br',NULL,6),(56,'Coordenação de Conservação e Limpeza','conservacao.limpeza@ueg.br',NULL,6),(57,'Coordenação de Patrimônio','patrimonio@ueg.br',NULL,6),(58,'Coordenação de Protocolo','protocolo@ueg.br',NULL,6),(59,'Coordenação de Transportes','transporte@ueg.br',NULL,6),(60,'Gerência de Infraestrutura','infraestrutura@ueg.br',NULL,6),(61,'Gabinete - PrDI','prdi@ueg.br',NULL,7),(62,'Gerência de Inovação Tecnológica - GIT','git@ueg.br',NULL,7),(63,'Gerência de Planejamento','planejamento@ueg.br',NULL,7),(64,'Gerência de Gestão de Pessoas','gegp@ueg.br',NULL,7),(65,'Coordenação de Assistência e Beneficios','cab@ueg.br',NULL,7),(66,'Coordenação de Desenvolvimento Humano','cdh@ueg.br',NULL,7),(67,'Folha de Pagamento','fopag.rh@ueg.br',NULL,7),(68,'Coordenação de Avaliação e Gestão de Pessoas','cagp@ueg.br',NULL,7),(69,'Comissão de Sindicância','sindicancia@ueg.br',NULL,7),(70,'Comissão de Processo Administrativo Disciplinar - PAD','cppad@ueg.br',NULL,7),(71,'Câmpus Anápolis de Ciências Socioeconômicas e Humanas','dir.unucseh@ueg.br',NULL,8),(72,'Câmpus Anápolis de Ciências Exatas e Tecnológicas','direcao.unucet@ueg.br',NULL,8),(73,'Câmpus Aparecida de Goiânia','dir.aparecida@ueg.br',NULL,8),(74,'Câmpus Caldas Novas','dir.caldas@ueg.br',NULL,8),(75,'Câmpus Campos Belos','dir.campos@ueg.br',NULL,8),(76,'Câmpus Ceres','dir.ceres@ueg.br',NULL,8),(77,'Câmpus Crixás','dir.crixas@ueg.br',NULL,8),(78,'Câmpus Edeia','dir.edeia@ueg.br',NULL,8),(79,'Câmpus Formosa','dir.formosa@ueg.br',NULL,8),(80,'Câmpus Goianésia','dir.goianesia@ueg.br',NULL,8),(81,'Câmpus Goiânia ESEFEGO','dir.goiania@ueg.br',NULL,8),(82,'Câmpus Laranjeiras','dir.laranjeiras@ueg.br',NULL,8),(83,'Câmpus Goiás','dir.goias@ueg.br',NULL,8),(84,'Câmpus Inhumas','dir.inhumas@ueg.br',NULL,8),(85,'Câmpus Ipameri','dir.ipameri@ueg.br',NULL,8),(86,'Câmpus Iporá','dir.ipora@ueg.br',NULL,8),(87,'Câmpus Itaberai','dir.itaberai@ueg.br',NULL,8),(88,'Câmpus Itapuranga','dir.itapuranga@ueg.br',NULL,8),(89,'Câmpus Itumbiara','dir.itumbiara@ueg.br',NULL,8),(90,'Câmpus Jaraguá','dir.jaragua@ueg.br',NULL,8),(91,'Câmpus Jataí','dir.jatai@ueg.br',NULL,8),(92,'Câmpus Jussara','dir.jussara@ueg.br',NULL,8),(93,'Câmpus Luziânia','dir.luziania@ueg.br',NULL,8),(94,'Câmpus Minaçu','dir.minacu@ueg.br',NULL,8),(95,'Câmpus Mineiros','dir.mineiros@ueg.br',NULL,8),(96,'Câmpus Morrinhos','dir.morrinhos@ueg.br',NULL,8),(97,'Câmpus Niquelândia','dir.niquelandia@ueg.br',NULL,8),(98,'Câmpus Palmeiras de Goiás','dir.palmeiras@ueg.br',NULL,8),(99,'Câmpus Pires do Rio','dir.pires@ueg.br',NULL,8),(100,'Câmpus Pirenópolis','dir.pirenopolis@ueg.br',NULL,8),(101,'Câmpus Porangatu','dir.porangatu@ueg.br',NULL,8),(102,'Câmpus Posse','dir.posse@ueg.br',NULL,8),(103,'Câmpus Quirinopolis','dir.quirinopolis@ueg.br',NULL,8),(104,'Câmpus São Miguel do Araguaia','dir.saomiguel@ueg.br',NULL,8),(105,'Câmpus Sanclerlândia','dir.sanclerlandia@ueg.br',NULL,8),(106,'Câmpus Santa Helena','dir.santahelena@ueg.br',NULL,8),(107,'Câmpus São Luis de Montes Belos','dir.saoluis@ueg.br',NULL,8),(108,'Câmpus Senador Canedo','dir.senadorcanedo@ueg.br',NULL,8),(109,'Câmpus Silvânia','dir.silvania@ueg.br',NULL,8),(110,'Câmpus Trindade','dir.trindade@ueg.br',NULL,8),(111,'Câmpus Uruaçu','dir.uruacu@ueg.br',NULL,8),(112,'Centro de Ensino e Aprendizagem em Rede - CEAR','dir.cear@ueg.br',NULL,8),(113,'Adriana Rodolfo da Costa','adriana.costa@ueg.br',NULL,10),(114,'Adriano Ferraz','adriano.ferraz@ueg.br',NULL,10),(115,'Adriele Dias Marques','adrielediasmarques@yahoo.com.br',NULL,11),(116,'Aldeci Rufino de Queiroz','aldeci.queiroz@ueg.br',NULL,10),(117,'Alexandro Leonel Lunas','alexandrolunas@gmail.com',NULL,10),(118,'Ana Paula Pereira Jorge','ana_pjorge@hotmail.com',NULL,10),(119,'Andrea Nunes de Almeida Frias','andrea.frias@ueg.br',NULL,10),(120,'Ana Clara Araujo Gomes da Silva','araujo.anaclara@gmail.com',NULL,10),(121,'Aline Martins da Silva','tamenila@yahoo.com.br',NULL,10),(122,'Angelina Maria Marcomini Giongo','ammarcomini@yahoo.com.br',NULL,10),(123,'Benedito Hoffmann Filho','coordadm.santahelena@ueg.br',NULL,11),(124,'Carla Cristina Rodrigues Leal','carla-leal@hotmail.com',NULL,10),(125,'Carla Patricia Silva da Silveira','dir.santahelena@ueg.br',NULL,11),(126,'Cássia Teles de Almeida Teixeira','cassia.ta@hotmail.com',NULL,10),(127,'Cleuber de Jesus Pereira','cleuberengenheiro@hotmail.com',NULL,10),(128,'Divino Allancaster','dallancaster@hotmail.com',NULL,10),(129,'Deisy dos Santos Moreira','deisy_moreira93@hotmail.com',NULL,11),(130,'Daniel Otavio Alves Pinto','daniel.otavio.alves@hotmail.com',NULL,10),(131,'Danilo Martins da Silva','danilomartinssilva@r7.com',NULL,10),(132,'Edmar Augusto Yokome','edmaryokome@ueg.br',NULL,10),(133,'Eleonilda Franscisca Vinhal Cabral','eleonildavinhal@gmail.com',NULL,10),(134,'Elismar Alves Cabral','sememail@gmail.com',NULL,11),(135,'Erivelton de Oliveira Alves','erivelton.alves@ueg.br',NULL,10),(136,'Euripedes Pereira Guimarães Filho','euripedes.filho@ueg.br',NULL,10),(137,'Elaine Cristina de Carvalho Taveira','elaine.taveira@ueg.br',NULL,11),(138,'Fernando Massao','fernandomassao@yahoo.com',NULL,10),(139,'Francione Resende Sousa','francioneresende@gmail.com',NULL,10),(140,'Géssica Fernandes','gessicafernandes95@outlook.com',NULL,11),(141,'Gilmar Teixeira Junior','gilmarjnr@gmail.com',NULL,10),(142,'Glauco Vitor Pedrosa','glauco.pedrosa@ueg.br',NULL,10),(143,'Jakeline Magna Dias Ferreira','jakelinemagna@gmail.com',NULL,11),(144,'João Pedro de Freitas','sememail@gmail.com',NULL,11),(145,'José Dionisio Orlandini','diorlandini@hotmail.com',NULL,10),(146,'Josue Gomes Delmond','josue.delmond@ueg.br',NULL,10),(147,'Jozilaine Borges Mendonça Frias','jozialt@yahoo.com.br',NULL,10),(148,'José Henrique da Silva Taveira','jose.taveira@ueg.br',NULL,10),(149,'Lucelene Bueno Branquinho','lucelene.bueno@ueg.br',NULL,10),(150,'Marcial Rocha da Silva','marcial.silva@ueg.br','Assistente administrativo da coordenação administrativa',11),(151,'Maria Bezerra Lopes de Araujo','sememail@gmail.com',NULL,11),(152,'Maria José de Oliveira','sememail@gmail.com',NULL,11),(153,'Massako Saiki Alves Ferreira','sememail@gmail.com',NULL,11),(154,'Marta Maria de Sousa','sememail@gmail.com',NULL,11),(155,'Nilcyneia Domingos Silva de Queiroz','nilcyneia.queiroz@ueg.br',NULL,10),(156,'Patricia Costa Silva','patricia.costa@ueg.br',NULL,10),(157,'Pedro Rogerio Giongo','pedro.giongo@ueg.br',NULL,10),(158,'Pollyana de Queiroz Ribeiro','pollyana.ribeiro@ueg.br',NULL,10),(159,'Roberto Couto de Oliveira Filho','robertocoutorv@hotmail.com',NULL,10),(160,'Simone Pereira Silva Bastos','simone.bastos@ueg.br',NULL,10),(161,'Telismar Vitorino de Souza','professorvitorino@gmail.com',NULL,10),(162,'Rafaela Arantes Marquez Queiroz','rafaela.queiroz@ueg.br',NULL,11),(163,'Patricia Gonçalves Correa','pathiago30@gmail.com',NULL,11),(164,'Mariana Oliveira Soldera','mariana.soldera@hotmail.com',NULL,11),(165,'Sandra Rodrigues Venancio','sandrarodriguesvenancio2013@gmail.com',NULL,10);

/*Table structure for table `departamento` */

DROP TABLE IF EXISTS `departamento`;

CREATE TABLE `departamento` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `departamento` */

insert  into `departamento`(`codigo`,`descricao`) values (1,'Secretaria - Santa Helena'),(2,'Reitoria'),(3,'Núcleo de Seleção'),(4,'Pró-Reitoria de Extensão, Cultura e Assuntos Estudantis-PrE'),(5,'Pró-Reitoria de Graduação - PrG'),(6,'Pró-Reitoria de Planejamento e Gestão de Finanças - PrGF'),(7,'Pró-Reitoria Planejamento Desenvolvimento Institucional-PrDI'),(8,'Câmpus Universitários'),(9,'Pró-Reitoria de Pesquisa e Pós-Graduação-PrP'),(10,'Docente Santa Helena'),(11,'Técnico Administrativo de Santa Helena');

/*Table structure for table `permissao` */

DROP TABLE IF EXISTS `permissao`;

CREATE TABLE `permissao` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permissao` */

insert  into `permissao`(`codigo`,`descricao`) values (1,'ROLE_CADASTRAR_CONTATO'),(2,'ROLE_PESQUISAR_CONTATO'),(3,'ROLE_REMOVER_CONTATO'),(4,'ROLE_CADASTRAR_DEPARTAMENTO'),(5,'ROLE_REMOVER_DEPARTAMENTO'),(6,'ROLE_PESQUISAR_DEPARTAMENTO'),(7,'ROLE_CADASTRAR_USUARIO'),(8,'ROLE_REMOVER_USUARIO'),(9,'ROLE_PESQUISAR_USUARIO'),(10,'ROLE_MENU_CONTATO'),(11,'ROLE_MENU_DEPARTAMENTO'),(12,'ROLE_MENU_USUARIO');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `schema_version` */

insert  into `schema_version`(`version_rank`,`installed_rank`,`version`,`description`,`type`,`script`,`checksum`,`installed_by`,`installed_on`,`execution_time`,`success`) values (1,1,'01','criar tabelas departamento contato telefones','SQL','V01__criar_tabelas_departamento_contato_telefones.sql',-1537142455,'root','2018-05-07 13:34:09',87,1),(2,2,'02','criar e registrar usuarios e premissoes','SQL','V02__criar_e_registrar_usuarios_e_premissoes.sql',2044355053,'root','2018-05-07 13:34:09',113,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8;

/*Data for the table `telefones` */

insert  into `telefones`(`codigo`,`tipo`,`area`,`numero`,`codigo_contato`) values (1,'DEPARTAMENTO','64','3641-3053',1),(4,'DEPARTAMENTO','62','3328-1192',2),(5,'DEPARTAMENTO','62','3328-1403',3),(6,'DEPARTAMENTO','62','3328-1435',3),(7,'DEPARTAMENTO','62','3328-1433',4),(12,'DEPARTAMENTO','62','3328-1176',7),(13,'PESSOAL','64','9928-73220',8),(14,'DEPARTAMENTO','62','3328-1178',9),(15,'DEPARTAMENTO','62','3328-1436',10),(16,'DEPARTAMENTO','62','3328-1178',10),(17,'DEPARTAMENTO','62','3328-1167',11),(18,'DEPARTAMENTO','62','3328-1403',12),(19,'DEPARTAMENTO','62','3328-1435',12),(20,'DEPARTAMENTO','62','3328-1423',13),(21,'DEPARTAMENTO','62','3328-1438',14),(22,'DEPARTAMENTO','62','3328-1137',14),(23,'DEPARTAMENTO','62','3328-1122',15),(24,'DEPARTAMENTO','62','3328-1107',15),(25,'DEPARTAMENTO','62','3328-1108',15),(26,'DEPARTAMENTO','62','3328-1194',15),(27,'DEPARTAMENTO','62','3286-8846',16),(28,'DEPARTAMENTO','62','3274-2045',16),(29,'DEPARTAMENTO','62','3328-1202',17),(30,'DEPARTAMENTO','62','3328-1110',18),(31,'DEPARTAMENTO','62','3328-1184',18),(32,'DEPARTAMENTO','62','3328-1103',19),(33,'DEPARTAMENTO','62','3328-1184',20),(34,'DEPARTAMENTO','62','3328-1184',21),(35,'DEPARTAMENTO','62','3328-1170',22),(36,'DEPARTAMENTO','62','3328-1429',23),(37,'DEPARTAMENTO','62','3328-1184',24),(38,'DEPARTAMENTO','62','3328-1172',24),(39,'DEPARTAMENTO','62','3328-1444',25),(40,'DEPARTAMENTO','62','3328-1140',25),(41,'DEPARTAMENTO','62','3328-1168',26),(42,'DEPARTAMENTO','62','3328-1158',27),(43,'DEPARTAMENTO','62','3328-1158',28),(44,'DEPARTAMENTO','62','3328-1135',29),(45,'DEPARTAMENTO','62','3328-1152',30),(46,'DEPARTAMENTO','62','3328-1402',31),(47,'DEPARTAMENTO','62','3328-1113',32),(48,'DEPARTAMENTO','62','3328-1165',33),(49,'DEPARTAMENTO','62','3328-1175',34),(50,'DEPARTAMENTO','62','3328-1114',35),(51,'DEPARTAMENTO','62','3328-1153',36),(52,'DEPARTAMENTO','62','3328-1181',37),(53,'DEPARTAMENTO','62','3328-1442',38),(54,'DEPARTAMENTO','62','3328-1181',39),(55,'DEPARTAMENTO','62','3328-1149',40),(56,'DEPARTAMENTO','62','3328-1104',41),(57,'DEPARTAMENTO','62','3328-1405',42),(58,'DEPARTAMENTO','62','3328-1169',42),(59,'DEPARTAMENTO','62','3328-1136',43),(60,'DEPARTAMENTO','62','3328-1121',44),(61,'DEPARTAMENTO','62','3328-1150',45),(62,'DEPARTAMENTO','62','3328-1187',45),(63,'DEPARTAMENTO','62','3328-1180',45),(67,'DEPARTAMENTO','62','3328-1180',49),(68,'DEPARTAMENTO','62','3328-1187',50),(69,'DEPARTAMENTO','62','3328-1150',51),(70,'DEPARTAMENTO','62','3328-1431',52),(71,'DEPARTAMENTO','62','3328-1422',53),(72,'DEPARTAMENTO','62','3328-1432',54),(73,'DEPARTAMENTO','62','3328-1191',55),(74,'DEPARTAMENTO','62','3328-1125',55),(75,'DEPARTAMENTO','62','3328-1401',56),(76,'DEPARTAMENTO','62','3328-1166',57),(77,'DEPARTAMENTO','62','3328-1154',58),(78,'DEPARTAMENTO','62','3328-1185',59),(79,'DEPARTAMENTO','62','3328-1400',60),(80,'DEPARTAMENTO','62','3328-1102',61),(81,'DEPARTAMENTO','62','3328-1440',62),(82,'DEPARTAMENTO','62','3328-1163',62),(83,'DEPARTAMENTO','62','3328-1164',62),(84,'DEPARTAMENTO','62','3328-1120',62),(85,'DEPARTAMENTO','62','3328-1186',63),(86,'DEPARTAMENTO','62','3328-1183',64),(87,'DEPARTAMENTO','62','3328-1173',65),(88,'DEPARTAMENTO','62','3328-1171',66),(89,'DEPARTAMENTO','62','3328-1111',67),(90,'DEPARTAMENTO','62','3328-1123',68),(91,'DEPARTAMENTO','62','3328-1174',68),(92,'DEPARTAMENTO','62','3328-1189',69),(93,'DEPARTAMENTO','62','3328-1189',70),(94,'DEPARTAMENTO','62','3328-1128',71),(95,'DEPARTAMENTO','62','3328-1112',71),(96,'DEPARTAMENTO','62','3328-1105',71),(97,'DEPARTAMENTO','62','3328-1161',72),(98,'DEPARTAMENTO','62','3328-1139',72),(99,'DEPARTAMENTO','62','3277-2989',73),(100,'DEPARTAMENTO','62','3277-3048',73),(101,'DEPARTAMENTO','62','3453-2310',74),(102,'DEPARTAMENTO','62','3453-2310',74),(103,'DEPARTAMENTO','62','3451-1409',75),(104,'DEPARTAMENTO','62','3451-2109',75),(105,'DEPARTAMENTO','62','3925-8023',76),(106,'DEPARTAMENTO','62','3925-8021',76),(107,'DEPARTAMENTO','62','3365-1800',77),(108,'DEPARTAMENTO','64','3492-2829',78),(109,'DEPARTAMENTO','64','3492-1059',78),(110,'DEPARTAMENTO','61','3631-8569',79),(111,'DEPARTAMENTO','61','3631-1187',79),(112,'DEPARTAMENTO','62','3353-4544',80),(113,'DEPARTAMENTO','62','3353-2132',80),(114,'DEPARTAMENTO','62','3522-3506',81),(115,'DEPARTAMENTO','62','3522-3500',81),(116,'DEPARTAMENTO','62','3522-5622',82),(117,'DEPARTAMENTO','62','3522-5603',82),(118,'DEPARTAMENTO','62','3522-5601',82),(119,'DEPARTAMENTO','62','3371-4971',83),(120,'DEPARTAMENTO','62','3514-1345',84),(121,'DEPARTAMENTO','62','3514-3122',84),(122,'DEPARTAMENTO','62','3491-1556',85),(123,'DEPARTAMENTO','64','3674-1651',86),(124,'DEPARTAMENTO','64','3603-1489',86),(125,'DEPARTAMENTO','62','3375-4876',87),(126,'DEPARTAMENTO','62','3355-1112',88),(127,'DEPARTAMENTO','62','3312-2030',88),(128,'DEPARTAMENTO','64','3404-5136',89),(129,'DEPARTAMENTO','62','3326-4296',90),(130,'DEPARTAMENTO','62','3326-4128',90),(131,'DEPARTAMENTO','64','3636-2848',91),(132,'DEPARTAMENTO','62','3373-2124',92),(133,'DEPARTAMENTO','62','3373-2335',92),(134,'DEPARTAMENTO','61','3620-6315',93),(135,'DEPARTAMENTO','61','3620-6330',93),(136,'DEPARTAMENTO','62','3904-1570',94),(137,'DEPARTAMENTO','64','3661-2350',95),(138,'DEPARTAMENTO','64','3661-1613',95),(139,'DEPARTAMENTO','64','3413-1358',96),(140,'DEPARTAMENTO','62','3354-1630',97),(141,'DEPARTAMENTO','62','3354-1571',97),(142,'DEPARTAMENTO','64','3571-1173',98),(143,'DEPARTAMENTO','64','3571-1198',98),(144,'DEPARTAMENTO','64','3461-2031',99),(145,'DEPARTAMENTO','62','3331-2904',100),(146,'DEPARTAMENTO','62','3362-4200',101),(147,'DEPARTAMENTO','62','3367-1033',101),(148,'DEPARTAMENTO','62','3481-3510',102),(149,'DEPARTAMENTO','62','3481-2413',102),(150,'DEPARTAMENTO','64','3651-2285',103),(151,'DEPARTAMENTO','62','3364-1199',104),(152,'DEPARTAMENTO','64','3679-1052',105),(153,'DEPARTAMENTO','64','3641-3053',106),(154,'DEPARTAMENTO','64','3641-4714',106),(155,'DEPARTAMENTO','64','3671-1427',107),(156,'DEPARTAMENTO','62','3532-4100',108),(157,'DEPARTAMENTO','62','3332-2483',109),(158,'DEPARTAMENTO','62','3332-3034',109),(159,'DEPARTAMENTO','62','3505-0439',110),(160,'DEPARTAMENTO','62','3357-6734',111),(161,'DEPARTAMENTO','62','3357-6744',111),(162,'DEPARTAMENTO','62','3328-1410',112),(163,'DEPARTAMENTO','62','3328-1469',112),(164,'PESSOAL','64','9124-8395',113),(165,'PESSOAL','61','8199-3748',113),(166,'DEPARTAMENTO','66','9605-0353',114),(167,'PESSOAL','64','9209-9713',115),(168,'PESSOAL','64','9238-7441',116),(169,'RESIDENCIAL','64','3647-1369',116),(170,'PESSOAL','64','9204-4880',117),(171,'PESSOAL','64','8409-2406',118),(172,'PESSOAL','64','9236-2556',119),(173,'PESSOAL','64','9641-4240',120),(174,'PESSOAL','64','9289-4151',121),(175,NULL,'64','9905-1295',121),(176,'PESSOAL','64','8100-3330',122),(177,'PESSOAL','64','9926-88387',123),(178,'PESSOAL','64','9930-98230',124),(179,'PESSOAL','64','9926-74139',125),(180,'PESSOAL','64','9926-06767',126),(181,'PESSOAL','64','9921-30185',127),(182,'PESSOAL','64','9929-14092',128),(183,'PESSOAL','64','9812-52561',128),(184,'PESSOAL','64','9931-58229',129),(185,'PESSOAL','64','9920-77378',130),(186,'PESSOAL','64','9924-13149',131),(187,'PESSOAL','64','9921-98041',132),(188,'PESSOAL','64','9962-39392',133),(189,'PESSOAL','64','9925-21854',134),(190,'PESSOAL','62','9848-83703',135),(191,'PESSOAL','64','9995-82738',136),(192,'PESSOAL','64','9997-99981',137),(193,'PESSOAL','64','9922-91403',138),(194,'PESSOAL','64','9962-57115',139),(195,'PESSOAL','64','9928-69311',139),(196,'PESSOAL','64','9927-16531',140),(197,'PESSOAL','64','9901-5814',141),(198,'PESSOAL','16','9813-56512',142),(199,'PESSOAL','64','9922-19283',143),(200,'PESSOAL','64','9240-2624',144),(201,'PESSOAL','64','9921-79251',145),(202,'PESSOAL','64','9810-65677',146),(203,'PESSOAL','64','9921-58544',146),(204,'PESSOAL','64','9922-71189',147),(205,'PESSOAL','64','9979-9969',148),(206,'PESSOAL','64','9920-62958',149),(207,'PESSOAL','64','9933-45750',150),(208,'RESIDENCIAL','64','3641-2318',150),(209,'PESSOAL','64','9920-02691',1),(210,'PESSOAL','64','9922-62003',151),(211,'PESSOAL','64','9921-39954',152),(212,'PESSOAL','64','3641-2473',153),(213,'PESSOAL','64','9967-54076',153),(214,'PESSOAL','64','9922-47433',154),(215,'PESSOAL','64','9922-54125',155),(216,'PESSOAL','64','9928-41384',156),(217,'PESSOAL','64','9811-41606',157),(218,'PESSOAL','64','9844-85683',157),(219,'PESSOAL','64','9921-73607',158),(220,'PESSOAL','64','9813-89663',159),(221,'PESSOAL','64','9996-16600',160),(222,'PESSOAL','64','9928-19683',161),(223,'PESSOAL','64','9961-15037',162),(224,'PESSOAL','64','9930-57162',163),(225,'PESSOAL','64','9932-05555',164),(226,'PESSOAL','64','9921-38328',165);

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(150) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `usuario` */

insert  into `usuario`(`codigo`,`nome`,`email`,`senha`) values (1,'Suporte','ti.santahelena@ueg.br','$2a$10$OVfTgA9P6rha79iS8hhpfekzNIsSq.HujSoOn8ZqOv1Nzlvty/DbG'),(2,'Secretaria','sec.santahelena@ueg.br','$2a$10$8A0D.1vRmTwobX5bAwJjqub1aVHJpbRSLczxJrfBkhGRSwDcV3yQ2'),(3,'Marcial Rocha','marcial.silva@ueg.br','$2a$10$GLrdEaqx1JvBuwXgfAaIn.S6Mm7nO.PySj2uNwqngfkBGGPj4ePqy'),(5,'Nilcyneia','dir.santahelena@ueg.br','$2a$10$Ml/NsUKLfOE2.LQ89Obdruy0uz1Ih5quEQdSAs//4YUzDMUVez92C');

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

insert  into `usuario_permissao`(`codigo_usuario`,`codigo_permissao`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,10),(2,11),(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,11),(3,12),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,10),(5,11);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
