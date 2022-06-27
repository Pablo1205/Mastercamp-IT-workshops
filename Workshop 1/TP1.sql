-- -------------------------------
-- Database name : tp1          --
-- Author 1 : Pablo Sanchez     --
-- -------------------------------

DROP DATABASE IF EXISTS tp1;
CREATE DATABASE tp1;
USE tp1;

-- Tables destructions

drop table if exists Région ;
drop table if exists Projet ;
drop table if exists Tache ;
drop table if exists Materiel ;
drop table if exists Divisions ;
drop table if exists Département ;
drop table if exists Entreprise ;
drop table if exists Matériel_élémentaire ;
drop table if exists Commune ;
drop table if exists Personne ;
drop table if exists Salarié ;
drop table if exists Chef_de_projet ;
drop table if exists Constitué_de ;
drop table if exists Travaille_à_ ;
drop table if exists Divisé_en_ ;

-- Tables creation

CREATE TABLE Région(
   ID_region INT,
   Nom VARCHAR(50),
   PRIMARY KEY(ID_region)
)engine = InnoDB;

CREATE TABLE Projet(
   ID_projet INT,
   Thème VARCHAR(50),
   Début DATE,
   Fin DATE,
   PRIMARY KEY(ID_projet)
)engine = InnoDB;

CREATE TABLE Tache(
   ID_tache INT,
   Nom VARCHAR(50),
   Description VARCHAR(50),
   Date_au_plus_tôt DATE,
   Date_au_plus_tard VARCHAR(50),
   Durée DATE,
   PRIMARY KEY(ID_tache)
)engine = InnoDB;

CREATE TABLE Materiel(
   ID_materiel INT,
   Nom VARCHAR(50),
   Type VARCHAR(50),
   Référence INT NOT NULL,
   ID_projet INT NOT NULL,
   PRIMARY KEY(ID_materiel),
   FOREIGN KEY(ID_projet) REFERENCES Projet(ID_projet)
)engine = InnoDB;

CREATE TABLE Divisions(
   ID_division INT,
   Nom VARCHAR(50),
   PRIMARY KEY(ID_division)
)engine = InnoDB;

CREATE TABLE Département(
   ID_departement INT,
   Nom VARCHAR(50),
   ID_region INT NOT NULL,
   PRIMARY KEY(ID_departement),
   FOREIGN KEY(ID_region) REFERENCES Région(ID_region)
)engine = InnoDB;

CREATE TABLE Entreprise(
   ID_entreprise INT,
   Nom VARCHAR(50),
   PRIMARY KEY(ID_entreprise)
)engine = InnoDB;

CREATE TABLE Matériel_élémentaire(
   ID_materiel_el INT,
   Nom VARCHAR(50),
   Type VARCHAR(50),
   Référence INT NOT NULL,
   ID_materiel INT NOT NULL,
   PRIMARY KEY(ID_materiel_el),
   FOREIGN KEY(ID_materiel) REFERENCES Materiel(ID_materiel)
)engine = InnoDB;

CREATE TABLE Commune(
   ID_commune INT,
   Nom VARCHAR(50),
   ID_departement INT NOT NULL,
   PRIMARY KEY(ID_commune),
   FOREIGN KEY(ID_departement) REFERENCES Département(ID_departement)
)engine = InnoDB;

CREATE TABLE Personne(
   ID_personne INT,
   Nom VARCHAR(50),
   Prénom VARCHAR(50),
   ID_commune INT,
   PRIMARY KEY(ID_personne),
   FOREIGN KEY(ID_commune) REFERENCES Commune(ID_commune)
)engine = InnoDB;

CREATE TABLE Salarié(
   Matricule_salarié INT,
   Nom VARCHAR(50),
   Prénom VARCHAR(50),
   Langue_Principale VARCHAR(50),
   Salaire DECIMAL(15,2),
   ID_personne INT,
   ID_division INT NOT NULL,
   PRIMARY KEY(Matricule_salarié),
   FOREIGN KEY(ID_personne) REFERENCES Personne(ID_personne),
   FOREIGN KEY(ID_division) REFERENCES Divisions(ID_division)
)engine = InnoDB;

CREATE TABLE Chef_de_projet(
   Matricule_chef_de_projet INT,
   Nom VARCHAR(50),
   Prénom VARCHAR(50),
   Langue_Principale VARCHAR(50),
   Salaire DECIMAL(15,2),
   ID_division INT NOT NULL,
   ID_personne INT,
   ID_projet INT NOT NULL,
   PRIMARY KEY(Matricule_chef_de_projet),
   FOREIGN KEY(ID_division) REFERENCES Divisions(ID_division),
   FOREIGN KEY(ID_personne) REFERENCES Personne(ID_personne),
   FOREIGN KEY(ID_projet) REFERENCES Projet(ID_projet)
)engine = InnoDB;

CREATE TABLE Constitué_de_(
   ID_division INT,
   ID_entreprise INT,
   PRIMARY KEY(ID_division, ID_entreprise),
   FOREIGN KEY(ID_division) REFERENCES Divisions(ID_division),
   FOREIGN KEY(ID_entreprise) REFERENCES Entreprise(ID_entreprise)
)engine = InnoDB;

CREATE TABLE Travaille_à_(
   Matricule_salarié INT,
   ID_tache INT,
   Date_début DATE,
   Date_fin DATE,
   PRIMARY KEY(Matricule_salarié, ID_tache),
   FOREIGN KEY(Matricule_salarié) REFERENCES Salarié(Matricule_salarié),
   FOREIGN KEY(ID_tache) REFERENCES Tache(ID_tache)
)engine = InnoDB;

CREATE TABLE Divisé_en_(
   ID_projet INT,
   ID_tache INT,
   PRIMARY KEY(ID_projet, ID_tache),
   FOREIGN KEY(ID_projet) REFERENCES Projet(ID_projet),
   FOREIGN KEY(ID_tache) REFERENCES Tache(ID_tache)
)engine = InnoDB;


COMMIT;
