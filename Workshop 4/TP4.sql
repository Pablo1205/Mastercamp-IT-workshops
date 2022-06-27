-- -------------------------------
-- Database name : tp4          --
-- Author 1 : Pablo Sanchez     --
-- -------------------------------

DROP DATABASE IF EXISTS tp4;
CREATE DATABASE tp4;
USE tp4;

-- Tables destructions

drop table if exists Category ;
drop table if exists Topic ;
drop table if exists Tag ;
drop table if exists Document ;
drop table if exists Porte ;

-- Tables creation


CREATE TABLE Category(
   CategoryID INT,
   Name VARCHAR(50),
   PRIMARY KEY(CategoryID)
)engine = InnoDB;

CREATE TABLE Topic(
   TopicID INT,
   Topic VARCHAR(50),
   PRIMARY KEY(TopicID)
)engine = InnoDB;

CREATE TABLE Tag(
   TagID INT,
   Tag VARCHAR(50),
   PRIMARY KEY(TagID)
)engine = InnoDB;

CREATE TABLE Document(
   DocumentID INT,
   DocumentName VARCHAR(50),
   DocumentDate DATE,
   StorageAddress INT,
   TopicID INT NOT NULL,
   CategoryID INT NOT NULL,
   PRIMARY KEY(DocumentID),
   FOREIGN KEY(TopicID) REFERENCES Topic(TopicID),
   FOREIGN KEY(CategoryID) REFERENCES Category(CategoryID)
)engine = InnoDB;

CREATE TABLE Porte(
   DocumentID INT,
   TagID INT,
   PRIMARY KEY(DocumentID, TagID),
   FOREIGN KEY(DocumentID) REFERENCES Document(DocumentID),
   FOREIGN KEY(TagID) REFERENCES Tag(TagID)
);

-- Fill tables 

INSERT INTO `Category` (`CategoryID`, `Name`) VALUES 
('1', 'policy'), 
('2', 'plan'),
('3', 'report'),
('4', 'receipt'),
('5', 'order');

INSERT INTO `Topic` (`TopicID`, `Topic`) VALUES 
('1', 'CS240 Course Files in Fall 2019'),
('2', 'CS241 Course Files in Spring 2020'),
('3', 'CS242 Course Files SummerCamp 2020'),
('4', 'CS240 Course Files in Fall 2020'),
('5', 'CS241 Course Files in Spring 2021'),
('6', 'CS242 Course Files SummerCamp 2021'),
('7', 'CS240 Course Files in Fall 2021'),
('8', 'CS241 Course Files in Spring 2022'),
('9', 'CS242 Course Files SummerCamp 2022');

INSERT INTO `Tag` (`TagID`, `Tag`) VALUES 
('1', 'legal'),
('2', 'medical'),
('3', 'administrative'),
('4', 'technical'),
('5', '2022'),
('6', 'reporting');

COMMIT;
