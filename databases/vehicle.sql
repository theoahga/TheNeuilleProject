create table "emergency"."vehicle"
(
    immat         varchar(255) not null
        primary key,
    description   varchar(255),
    name          varchar(255) not null,
    places_number integer,
    type          smallint
        constraint vehicle_type_check
            check ((type >= 0) AND (type <= 3))
);

alter table "emergency"."vehicle"
    owner to postgres;

INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-011', 'Camion de pompier standard', 'Camion 11', 5, 2, 3, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-006', 'Camion de pompier standard', 'Camion 6', 5, 2, 3, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('CMD-038', 'Véhicule de commandement mobile', 'Commandement 3', 4, 3, 1, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('SRCH-033', 'Véhicule de recherche et de sauvetage', 'Recherche 3', 6, 2, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-015', 'Camion de pompier standard', 'Camion 15', 5, 2, 3, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('TNKR-026', 'Camion-citerne pour le transport d''eau', 'Citerne 1', 3, 0, 4, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('TNKR-030', 'Camion-citerne pour le transport d''eau', 'Citerne 5', 3, 0, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('TNKR-027', 'Camion-citerne pour le transport d''eau', 'Citerne 2', 3, 0, 1, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('SRCH-034', 'Véhicule de recherche et de sauvetage', 'Recherche 4', 6, 2, 3, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('SRCH-032', 'Véhicule de recherche et de sauvetage', 'Recherche 2', 6, 2, 1, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-014', 'Camion de pompier standard', 'Camion 14', 5, 2, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-016', 'Camion de pompier standard', 'Camion 16', 5, 2, 4, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-001', 'Camion de pompier standard', 'Camion 1', 5, 2, 4, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-003', 'Camion de pompier standard', 'Camion 3', 5, 2, 3, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-004', 'Camion de pompier standard', 'Camion 4', 5, 2, 1, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-005', 'Camion de pompier standard', 'Camion 5', 5, 2, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-002', 'Camion de pompier standard', 'Camion 2', 5, 2, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-023', 'Véhicule de secours et d''incendie', 'Secours 3', 6, 0, 1, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-008', 'Camion de pompier standard', 'Camion 8', 5, 2, 4, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('SRCH-035', 'Véhicule de recherche et de sauvetage', 'Recherche 5', 6, 2, 4, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('CMD-037', 'Véhicule de commandement mobile', 'Commandement 2', 4, 3, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-018', 'Camion de pompier standard', 'Camion 18', 5, 2, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('TNKR-028', 'Camion-citerne pour le transport d''eau', 'Citerne 3', 3, 0, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-013', 'Camion de pompier standard', 'Camion 13', 5, 2, 1, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('SRCH-031', 'Véhicule de recherche et de sauvetage', 'Recherche 1', 6, 2, 4, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-007', 'Camion de pompier standard', 'Camion 7', 5, 2, 4, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-012', 'Camion de pompier standard', 'Camion 12', 5, 2, 4, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-010', 'Camion de pompier standard', 'Camion 10', 5, 2, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-022', 'Véhicule de secours et d''incendie', 'Secours 2', 6, 0, 3, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-009', 'Camion de pompier standard', 'Camion 9', 5, 2, 1, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-024', 'Véhicule de secours et d''incendie', 'Secours 4', 6, 0, 2, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('TNKR-029', 'Camion-citerne pour le transport d''eau', 'Citerne 4', 3, 0, 1, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-025', 'Véhicule de secours et d''incendie', 'Secours 5', 6, 0, 3, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-021', 'Véhicule de secours et d''incendie', 'Secours 1', 6, 0, 4, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-017', 'Camion de pompier standard', 'Camion 17', 5, 2, 1, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-019', 'Camion de pompier standard', 'Camion 19', 5, 2, 3, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('CMD-036', 'Véhicule de commandement mobile', 'Commandement 1', 4, 3, 3, true);
INSERT INTO "emergency"."vehicle" (immat, description, name, places_number, type, station_id, is_available) VALUES ('FTRK-020', 'Camion de pompier standard', 'Camion 20', 5, 2, 1, true);
