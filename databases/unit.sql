create table "emergency"."unit"
(
    id           bigint not null
        primary key,
    is_available boolean
);

alter table "emergency"."unit"
    owner to postgres;

INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (6, true, 2);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (2, true, 2);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (8, true, 4);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (15, true, 2);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (16, true, 3);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (4, true, 4);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (13, true, 4);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (5, true, 1);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (12, true, 4);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (11, true, 3);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (10, true, 2);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (3, true, 3);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (7, true, 3);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (1, true, 1);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (9, true, 1);
INSERT INTO "emergency"."unit" (id, is_available, station_id) VALUES (14, true, 1);
