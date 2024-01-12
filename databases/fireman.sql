create table "emergency"."fireman"
(
    registration_number bigint not null
        primary key,
    name                varchar(255),
    second_name         varchar(255),
    unit_id             bigint
        constraint fkurd1uimng1k2vwgq6vbuqy22
            references unit
);

alter table "emergency"."fireman"
    owner to postgres;

INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (33, 'Michel', 'Léon', 1);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (50, 'Renard', 'Océane', 1);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (71, 'Lefevre', 'Augustin', 6);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (5, 'Robert', 'Louis', 4);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (36, 'Lefevre', 'Nolan', 3);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (16, 'Lemoine', 'Raphaël', 16);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (1, 'Martin', 'Lucas', 14);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (3, 'Dubois', 'Léo', 10);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (10, 'Leroy', 'Lina', 6);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (24, 'Lefevre', 'Mathis', 8);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (30, 'Roussel', 'Clara', 14);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (15, 'Leclerc', 'Mia', 9);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (35, 'Lefevre', 'Capucine', 2);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (66, 'Morel', 'Hugo', 1);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (42, 'Bertrand', 'Timéo', 9);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (48, 'Bourgeois', 'Camille', 15);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (76, 'Lefevre', 'Cassandra', 11);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (45, 'Lefevre', 'Louna', 12);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (65, 'Brun', 'Axelle', 16);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (56, 'Guillaume', 'Lucas', 7);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (53, 'Rivière', 'Mathilde', 4);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (21, 'Dufour', 'Zoé', 5);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (46, 'Garnier', 'Evan', 13);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (11, 'Moreau', 'Hugo', 13);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (7, 'Petit', 'Gabriel', 8);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (55, 'Lefevre', 'Mélina', 6);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (63, 'Philippe', 'Mila', 14);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (77, 'Dumas', 'Oscar', 12);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (22, 'Girard', 'Milan', 6);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (69, 'Lefevre', 'Clémence', 4);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (12, 'Simon', 'Inès', 15);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (78, 'Guérin', 'Mael', 13);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (23, 'Faure', 'Louise', 7);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (47, 'Lefevre', 'Ambre', 14);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (52, 'Perrin', 'Lilou', 3);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (13, 'Laurent', 'Maëlys', 7);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (2, 'Bernard', 'Eva', 5);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (17, 'Roux', 'Jade', 14);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (68, 'Rivière', 'Liam', 3);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (37, 'David', 'Célia', 4);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (28, 'Dumas', 'Lucie', 12);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (62, 'Lefevre', 'Noé', 13);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (75, 'Robin', 'Samuel', 10);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (38, 'Morel', 'Axel', 5);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (18, 'Renard', 'Enzo', 2);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (72, 'Collet', 'Mia', 7);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (41, 'Martinez', 'Sarah', 8);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (32, 'Perrin', 'Manon', 16);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (67, 'Lemoine', 'Manon', 2);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (57, 'Roy', 'Lou', 8);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (6, 'Richard', 'Chloé', 12);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (44, 'Lefevre', 'Alexis', 11);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (29, 'Brun', 'Nathan', 13);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (14, 'Lefevre', 'Tom', 11);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (73, 'Perrin', 'Léo', 8);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (25, 'Barbier', 'Jules', 9);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (27, 'Garcia', 'Noémie', 11);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (20, 'Dupont', 'Noah', 4);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (60, 'Giraud', 'Nael', 11);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (9, 'Lefevre', 'Arthur', 1);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (54, 'Hubert', 'Gabin', 5);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (64, 'Lefevre', 'Lydia', 15);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (49, 'Leclerc', 'Antoine', 16);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (34, 'Leclercq', 'Mael', 1);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (8, 'Durand', 'Alice', 3);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (51, 'Lefevre', 'Soan', 2);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (4, 'Thomas', 'Emma', 2);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (59, 'Blanc', 'Lina', 10);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (70, 'Roussel', 'Maelle', 5);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (39, 'Fournier', 'Anaïs', 6);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (74, 'Lefevre', 'Estelle', 9);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (31, 'Vincent', 'Théo', 15);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (43, 'Caron', 'Juliette', 10);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (40, 'Lefevre', 'Elliot', 7);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (58, 'Lefevre', 'Isaac', 9);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (19, 'Marchand', 'Léa', 3);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (26, 'Lemoine', 'Rose', 10);
INSERT INTO "emergency"."fireman" (registration_number, name, second_name, unit_id) VALUES (61, 'Lemoine', 'Elya', 12);
