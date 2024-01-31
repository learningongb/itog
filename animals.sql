CREATE DATABASE `animals`;
USE `animals`;

-- Создать таблицы для хранения данных

-- Таблица для хранения типов животных

CREATE TABLE `animal_types` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `type_name` varchar(50) NOT NULL
);

CREATE TABLE `horses` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  `animal_type` int NOT NULL,
  FOREIGN KEY (`animal_type`) REFERENCES `animal_types` (`id`) ON DELETE NO ACTION
);

CREATE TABLE `camels` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  `animal_type` int NOT NULL,
  FOREIGN KEY (`animal_type`) REFERENCES `animal_types` (`id`) ON DELETE NO ACTION
);

CREATE TABLE `donkeys` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  `animal_type` int NOT NULL,
  FOREIGN KEY (`animal_type`) REFERENCES `animal_types` (`id`) ON DELETE NO ACTION
);

CREATE TABLE `dogs` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  `animal_type` int NOT NULL,
  FOREIGN KEY (`animal_type`) REFERENCES `animal_types` (`id`) ON DELETE NO ACTION
);

CREATE TABLE `cats` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  `animal_type` int NOT NULL,
  FOREIGN KEY (`animal_type`) REFERENCES `animal_types` (`id`) ON DELETE NO ACTION
);

CREATE TABLE `hamsters` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  `animal_type` int NOT NULL,
  FOREIGN KEY (`animal_type`) REFERENCES `animal_types` (`id`) ON DELETE NO ACTION
);

-- Первоначальное наполнение таблиц

INSERT INTO `animal_types`(`type_name`) VALUES
("PackAnimals"),
("PetAninals");

set @PackAnimals = (SELECT MAX(id) FROM animal_types WHERE type_name = "PackAnimals");
set @PetAninals = (SELECT MAX(id) FROM animal_types WHERE type_name = "PetAninals");

INSERT INTO `horses`(`name`, `birthday`, `animal_type`) VALUES
("Варяг", '20210110', @PackAnimals),
("Ясный", '20210202', @PackAnimals);

INSERT INTO `camels`(`name`, `birthday`, `animal_type`) VALUES
("Бомбей", '20230131', @PackAnimals),
("Зефир", '20230502', @PackAnimals);

INSERT INTO `donkeys`(`name`, `birthday`, `animal_type`) VALUES
("Мерлин", '20220102', @PackAnimals),
("Грей", '20240102', @PackAnimals);

INSERT INTO `dogs`(`name`, `birthday`, `animal_type`) VALUES
("Белка", '20200102', @PetAninals),
("Стрелка", '20210502', @PetAninals);

INSERT INTO `cats`(`name`, `birthday`, `animal_type`) VALUES
("Мурка", '20230607', @PetAninals),
("Машка", '20200102', @PetAninals);

INSERT INTO `hamsters`(`name`, `birthday`, `animal_type`) VALUES
("Борис", '20200102', @PetAninals),
("Зинаида", '20200102', @PetAninals);

-- Удалить всех верблюдов

DELETE FROM `camels`;

-- Поместить молодых животных в дополнительную таблицу

CREATE TABLE `young_animals` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  `animal_type` int NOT NULL,
  FOREIGN KEY (`animal_type`) REFERENCES `animal_types` (`id`) ON DELETE NO ACTION
);

SET @now = date(now());
SET @begin_date = DATE_ADD(DATE_ADD(@now, INTERVAL -1 DAY), INTERVAL -3 YEAR);
SET @end_date = DATE_ADD(@now, INTERVAL -1 YEAR);

INSERT INTO young_animals (name, birthday, animal_type)
SELECT name, birthday, animal_type FROM horses WHERE birthday >= @begin_date and birthday <= @end_date
UNION ALL 
SELECT name, birthday, animal_type FROM donkeys WHERE birthday >= @begin_date and birthday <= @end_date
UNION ALL 
SELECT name, birthday, animal_type FROM dogs WHERE birthday >= @begin_date and birthday <= @end_date
UNION ALL 
SELECT name, birthday, animal_type FROM cats WHERE birthday >= @begin_date and birthday <= @end_date
UNION ALL 
SELECT name, birthday, animal_type FROM hamsters WHERE birthday >= @begin_date and birthday <= @end_date
;

-- Объединить все таблицы в одну

-- Таблица для хранения вида животного, чтобы сохранить принадлежность к исходной таблице
CREATE TABLE `animal_kinds` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(50) NOT NULL
);

-- Новая общая таблица

CREATE TABLE `animals` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  `animal_type` int NOT NULL,
  `animal_kind` int NOT NULL,
  FOREIGN KEY (`animal_type`) REFERENCES `animal_types` (`id`) ON DELETE NO ACTION,
  FOREIGN KEY (`animal_kind`) REFERENCES `animal_kinds` (`id`) ON DELETE NO ACTION
);

SET @kind_name = "horse";
INSERT INTO animal_kinds (name) VALUES (@kind_name);
INSERT INTO animals (name, birthday, animal_type, animal_kind)
SELECT horses.name, birthday, animal_type, animal_kinds.id
FROM horses, animal_kinds
WHERE animal_kinds.name = @kind_name;

SET @kind_name = "donkey";
INSERT INTO animal_kinds (name) VALUES (@kind_name);
INSERT INTO animals (name, birthday, animal_type, animal_kind)
SELECT donkeys.name, birthday, animal_type, animal_kinds.id
FROM donkeys, animal_kinds
WHERE animal_kinds.name = @kind_name;

SET @kind_name = "dog";
INSERT INTO animal_kinds (name) VALUES (@kind_name);
INSERT INTO animals (name, birthday, animal_type, animal_kind)
SELECT dogs.name, birthday, animal_type, animal_kinds.id
FROM dogs, animal_kinds
WHERE animal_kinds.name = @kind_name;

SET @kind_name = "cat";
INSERT INTO animal_kinds (name) VALUES (@kind_name);
INSERT INTO animals (name, birthday, animal_type, animal_kind)
SELECT cats.name, birthday, animal_type, animal_kinds.id
FROM cats, animal_kinds
WHERE animal_kinds.name = @kind_name;

SET @kind_name = "hamster";
INSERT INTO animal_kinds (name) VALUES (@kind_name);
INSERT INTO animals (name, birthday, animal_type, animal_kind)
SELECT hamsters.name, birthday, animal_type, animal_kinds.id
FROM hamsters, animal_kinds
WHERE animal_kinds.name = @kind_name;

SELECT animals.id AS id, animals.name, birthday, animal_types.type_name as type, animal_kinds.name as kind
FROM animals
LEFT JOIN animal_types ON animals.animal_type = animal_types.id
LEFT JOIN animal_kinds ON animals.animal_kind = animal_kinds.id
;