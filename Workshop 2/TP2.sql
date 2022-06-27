-- -------------------------------
-- Database name : tp2          --
-- Author 1 : Pablo Sanchez     --
-- -------------------------------

DROP DATABASE IF EXISTS tp2;
CREATE DATABASE tp2;
USE tp2;

-- Tables destructions

drop table if exists Région;
drop table if exists Projet;
drop table if exists Tache;
drop table if exists Division;
drop table if exists Département;
drop table if exists Commune;
drop table if exists Personne;
drop table if exists Salarié;
drop table if exists Materiel;
drop table if exists Chef_de_projet;
drop table if exists Matériel_élémentaire;
drop table if exists accompli;
drop table if exists estConstituePar;

-- Tables creation

CREATE TABLE Région(
   NumeroRegion INT,
   NomRegion VARCHAR(50),
   PRIMARY KEY(NumeroRegion)
)engine = InnoDB;

CREATE TABLE Projet(
   NumeroProjet INT,
   Thème VARCHAR(50),
   DateDébut DATE,
   DateFin DATE,
   PRIMARY KEY(NumeroProjet)
)engine = InnoDB;

CREATE TABLE Tache(
   NumeroTache INT,
   NomSymbolique VARCHAR(50),
   Description VARCHAR(50),
   DatePlanifTot DATE,
   DatePlanifTard VARCHAR(50),
   Durée INT,
   PRIMARY KEY(NumeroTache)
)engine = InnoDB;

CREATE TABLE Division(
   NumeroDivision INT,
   NomDivision VARCHAR(50),
   PRIMARY KEY(NumeroDivision)
)engine = InnoDB;

CREATE TABLE Département(
   NumeroDepartement INT,
   NomDépartement VARCHAR(50),
   NumeroRegion INT NOT NULL,
   PRIMARY KEY(NumeroDepartement),
   FOREIGN KEY(NumeroRegion) REFERENCES Région(NumeroRegion)
)engine = InnoDB;

CREATE TABLE Commune(
   NumeroCommune INT,
   NomCommune VARCHAR(50),
   NumeroDepartement INT NOT NULL,
   PRIMARY KEY(NumeroCommune),
   FOREIGN KEY(NumeroDepartement) REFERENCES Département(NumeroDepartement)
)engine = InnoDB;

CREATE TABLE Personne(
   ID_personne INT,
   Prénom VARCHAR(50),
   Nom VARCHAR(50),
   NumeroCommune INT NOT NULL,
   PRIMARY KEY(ID_personne),
   FOREIGN KEY(NumeroCommune) REFERENCES Commune(NumeroCommune)
)engine = InnoDB;

CREATE TABLE Salarié(
   NumeroMatricule INT,
   compétenceLinguistique VARCHAR(50),
   Salaire DECIMAL(15,2),
   ID_personne INT,
   NumeroDivision INT NOT NULL,
   PRIMARY KEY(NumeroMatricule),
   FOREIGN KEY(ID_personne) REFERENCES Personne(ID_personne),
   FOREIGN KEY(NumeroDivision) REFERENCES Division(NumeroDivision)
)engine = InnoDB;

CREATE TABLE Materiel(
   NumeroMateriel INT,
   NomMateriel VARCHAR(50),
   Type VARCHAR(50),
   Référence INT NOT NULL,
   NumeroMatricule INT NOT NULL,
   PRIMARY KEY(NumeroMateriel),
   FOREIGN KEY(NumeroMatricule) REFERENCES Salarié(NumeroMatricule)
)engine = InnoDB;

CREATE TABLE Chef_de_projet(
   NumeroMatricule INT,
   compétenceLinguistique VARCHAR(50),
   Salaire DECIMAL(15,2),
   NumeroDivision INT NOT NULL,
   ID_personne INT,
   NumeroProjet INT NOT NULL,
   PRIMARY KEY(NumeroMatricule),
   FOREIGN KEY(NumeroDivision) REFERENCES Division(NumeroDivision),
   FOREIGN KEY(ID_personne) REFERENCES Personne(ID_personne),
   FOREIGN KEY(NumeroProjet) REFERENCES Projet(NumeroProjet)
)engine = InnoDB;

CREATE TABLE Matériel_élémentaire(
   NumeroMaterielEl INT,
   NomMateriel VARCHAR(50),
   Type VARCHAR(50),
   Référence INT NOT NULL,
   NumeroMateriel INT NOT NULL,
   PRIMARY KEY(NumeroMaterielEl),
   FOREIGN KEY(NumeroMateriel) REFERENCES Materiel(NumeroMateriel)
)engine = InnoDB;

CREATE TABLE accompli(
   NumeroMatricule INT,
   NumeroTache INT,
   Date_début DATE,
   Date_fin DATE,
   PRIMARY KEY(NumeroMatricule, NumeroTache),
   FOREIGN KEY(NumeroMatricule) REFERENCES Salarié(NumeroMatricule),
   FOREIGN KEY(NumeroTache) REFERENCES Tache(NumeroTache)
)engine = InnoDB;

CREATE TABLE estConstituePar(
   NumeroProjet INT,
   NumeroTache INT,
   PRIMARY KEY(NumeroProjet, NumeroTache),
   FOREIGN KEY(NumeroProjet) REFERENCES Projet(NumeroProjet),
   FOREIGN KEY(NumeroTache) REFERENCES Tache(NumeroTache)
)engine = InnoDB;

-- Fill tables 

INSERT INTO `Région` (`NumeroRegion`, `NomRegion`) VALUES 
('11', 'Île-de-France');

INSERT INTO `Département` (`NumeroDepartement`, `NomDépartement`, `NumeroRegion`) VALUES 
('75', 'Paris', '11'), 
('92', 'Hauts-de-Seine', '11'), 
('93', 'Seine-Saint-Denis', '11'), 
('94', 'Val-de-Marne', '11'), 
('95', 'Val-d Oise', '11'), 
('91', 'Essonne', '11');

INSERT INTO `Commune` (`NumeroCommune`, `NomCommune`, `NumeroDepartement`) VALUES 
('75056', 'Paris','75'), 
('92064', 'Saint-Cloud','92'), 
('92004', 'Asnières','92'), 
('93048', 'Montreuil','93'), 
('92071', 'Sceaux','92'), 
('92075', 'Vanves','92'), 
('95018', 'Argenteuil','95'), 
('94003', 'Arcueil','94'), 
('94076', 'Villejuif','94'), 
('91377', 'Massy','91'), 
('93006', 'Bagnolet','93');

INSERT INTO `Division` (`NumeroDivision`, `NomDivision`) VALUES 
('0', 'informatique'),
('1', 'marketing'),
('2', 'comptabilité');

INSERT INTO `Personne` (`ID_personne`, `Prénom`, `Nom`, `NumeroCommune`) VALUES 
('1', 'Tristan', 'Fresnes', '75056'), 
('2', 'Yves', 'Bonenfant', '92064'), 
('3', 'Heloise', 'Aubin', '92004'), 
('4', 'Pascal', 'Hébert', '93048'), 
('5', 'Thibaut', 'Parenteau', '75056'), 
('6', 'Francis', 'Durand', '92071'), 
('7', 'Cécile', 'Primeau', '92075'), 
('8', 'Gaetane', 'Paimboeuf', '95018'), 
('9', 'Ruby', 'Fréchette', '75056'), 
('10', 'Octave', 'Saindon', '94003'), 
('11', 'Juliette', 'Trudeau', '75056'), 
('12', 'Gill', 'Labbé', '94076'), 
('13', 'Dominic', 'Chassé', '91377'), 
('14', 'Joseph', 'Lasindona', '75056'), 
('15', 'Denis', 'Clément', '93006');

INSERT INTO `Projet` (`NumeroProjet`, `Thème`,`DateDébut`, `DateFin`) VALUES 
('0', 'informatique','2022-05-24', '2022-06-06'),
('1', 'marketing' ,'2022-03-13', '2022-07-23'),
('2', 'comptabilité' ,'2022-04-02', '2022-06-30');

INSERT INTO `Chef_de_projet` (`NumeroMatricule`, `compétenceLinguistique`,`Salaire`, `NumeroDivision`, `ID_personne`, `NumeroProjet`) VALUES 
('01', 'français','4500', '0', '1', '0'),
('11', 'français' ,'4500', '1', '6', '1'),
('21', 'français' ,'4500', '2', '11', '2');

INSERT INTO `Salarié` (`NumeroMatricule`, `compétenceLinguistique`,`Salaire`, `NumeroDivision`, `ID_personne`) VALUES 
('02', 'français','3000', '0','2'),
('03', 'français','3000', '0', '3'),
('04', 'français','3000', '0', '4'),
('05', 'français','3000', '0', '5'),
('12', 'français','3000', '1','7'),
('13', 'français','3000', '1', '8'),
('14', 'français','3000', '1', '9'),
('15', 'français','3000', '1', '10'),
('22', 'français','3000', '2','12'),
('23', 'français','3000', '2', '13'),
('24', 'français','3000', '2', '14'),
('25', 'français','3000', '2', '15');

INSERT INTO `Tache` (`NumeroTache`, `NomSymbolique`,`Description`,`DatePlanifTot`, `DatePlanifTard`, `Durée`) VALUES 
('0', 'plannification', 'plannification du projet' ,'2022-05-24', '2022-05-26','2'),
('1', 'plannification', 'plannification du projet' ,'2022-03-13', '2022-03-14','1'),
('2', 'plannification', 'plannification du projet' ,'2022-04-02', '2022-04-03','1'),
('3', 'compte rendu oral', 'explication du projet au client' ,'2022-06-06', '2022-06-06','1'),
('4', 'compte rendu  oral', 'explication du projet au client' ,'2022-07-23', '2022-07-23','1'),
('5', 'compte rendu oral', 'explication du projet au client' ,'2022-06-30', '2022-06-30','1'),
('6', 'avancement', 'avancer le projet' ,'2022-05-27', '2022-06-05','10'),
('7', 'avancement', 'avancer le projet' ,'2022-03-15', '2022-07-22','130'),
('8', 'avancement', 'avancer le projet' ,'2022-04-04', '2022-06-29','87');

INSERT INTO `estConstituePar` (`NumeroProjet`, `NumeroTache`) VALUES 
('0', '0'),
('1', '1'),
('2','2'),
('0','3'),
('1', '4'),
('2','5'),
('0', '6'),
('1','7'),
('2','8');

INSERT INTO `accompli` (`NumeroMatricule`, `NumeroTache`,`Date_début`, `Date_fin`) VALUES 
('02','0','2022-05-24', '2022-05-26'),
('12','1','2022-03-13', '2022-03-14'),
('22','2','2022-04-02', '2022-04-03'),
('03','3','2022-06-06', '2022-06-06'),
('04','3','2022-06-06', '2022-06-06'),
('13','4', '2022-07-23', '2022-07-23'),
('14','4', '2022-07-23', '2022-07-23'),
('23','5' ,'2022-06-30', '2022-06-30'),
('24','5' ,'2022-06-30', '2022-06-30'),
('05','6', '2022-05-27', '2022-06-05'),
('15','7','2022-03-15', '2022-07-22'),
('25','8', '2022-04-04', '2022-06-29');

INSERT INTO `Materiel` (`NumeroMateriel`, `NomMateriel`,`Type`, `Référence`, `NumeroMatricule`) VALUES 
('1', 'ordinateur fixe','informatique', '01','02'),
('2', 'ordinateur fixe','informatique', '01','03'),
('3', 'ordinateur fixe','informatique', '01','04'),
('4', 'ordinateur fixe','informatique', '01','05'),
('5', 'ordinateur fixe','informatique', '01','12'),
('6', 'ordinateur fixe','informatique', '01','13'),
('7', 'ordinateur fixe','informatique', '01','14'),
('8', 'ordinateur fixe','informatique', '01','15'),
('9', 'ordinateur fixe','informatique', '01','22'),
('10', 'ordinateur fixe','informatique', '01','23'),
('11', 'ordinateur fixe','informatique', '01','24'),
('12', 'ordinateur fixe','informatique', '01','25');

INSERT INTO `Matériel_élémentaire` (`NumeroMaterielEl`, `NomMateriel`,`Type`, `Référence`, `NumeroMateriel`) VALUES 
('0', 'souris','informatique', '01','1'),
('1', 'écran','informatique', '02','1'),
('2', 'tour','informatique', '03','1'),
('3', 'clavier','informatique', '04','1'),
('4', 'souris','informatique', '01','2'),
('5', 'écran','informatique', '02','2'),
('6', 'tour','informatique', '03','2'),
('7', 'clavier','informatique', '04','2'),
('8', 'souris','informatique', '01','3'),
('9', 'écran','informatique', '02','3'),
('10', 'tour','informatique', '03','3'),
('11', 'clavier','informatique', '04','3'),
('12', 'souris','informatique', '01','4'),
('13', 'écran','informatique', '02','4'),
('14', 'tour','informatique', '03','4'),
('15', 'clavier','informatique', '04','4'),
('16', 'souris','informatique', '01','5'),
('17', 'écran','informatique', '02','5'),
('18', 'tour','informatique', '03','5'),
('19', 'clavier','informatique', '04','5'),
('20', 'souris','informatique', '01','6'),
('21', 'écran','informatique', '02','6'),
('22', 'tour','informatique', '03','6'),
('23', 'clavier','informatique', '04','6'),
('24', 'souris','informatique', '01','7'),
('25', 'écran','informatique', '02','7'),
('26', 'tour','informatique', '03','7'),
('27', 'clavier','informatique', '04','7'),
('28', 'souris','informatique', '01','8'),
('29', 'écran','informatique', '02','8'),
('30', 'tour','informatique', '03','8'),
('31', 'clavier','informatique', '04','8'),
('32', 'souris','informatique', '01','9'),
('33', 'écran','informatique', '02','9'),
('34', 'tour','informatique', '03','9'),
('35', 'clavier','informatique', '04','9'),
('36', 'souris','informatique', '01','10'),
('37', 'écran','informatique', '02','10'),
('38', 'tour','informatique', '03','10'),
('39', 'clavier','informatique', '04','10'),
('40', 'souris','informatique', '01','11'),
('41', 'écran','informatique', '02','11'),
('42', 'tour','informatique', '03','11'),
('43', 'clavier','informatique', '04','11'),
('44', 'souris','informatique', '01','12'),
('45', 'écran','informatique', '02','12'),
('46', 'tour','informatique', '03','12'),
('47', 'clavier','informatique', '04','12');

COMMIT;