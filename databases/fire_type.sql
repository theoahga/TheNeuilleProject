create table "emergency"."fire_type"
(
    name   varchar(255) not null
        primary key,
    number integer
);

alter table "emergency"."fire_type"
    owner to postgres;

INSERT INTO "emergency"."fire_type" (name, number) VALUES ('GAZ', 1);
INSERT INTO "emergency"."fire_type" (name, number) VALUES ('SOLIDE', 2);
INSERT INTO "emergency"."fire_type" (name, number) VALUES ('LIQUID', 3);
INSERT INTO "emergency"."fire_type" (name, number) VALUES ('METALS', 4);
