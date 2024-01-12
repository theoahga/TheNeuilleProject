create table "emergency"."station"
(
    id      bigint not null
        primary key,
    address varchar(255),
    lat     double precision,
    lon     double precision,
    name    varchar(255)
);

alter table "emergency"."station"
    owner to postgres;

INSERT INTO "emergency"."station" (id, address, lat, lon, name, sdis_id_sdis) VALUES (4, '3 Avenue Jean Capelle, 69100 Villeurbanne, France', 45.78351016802518, 4.877747779545341, 'Caserne Insa', 2);
INSERT INTO "emergency"."station" (id, address, lat, lon, name, sdis_id_sdis) VALUES (3, '12 Rue Enrico Fermi, 69100 Villeurbanne, France', 45.78353444202694, 4.868465497060774, 'Caserne Cpe', 2);
INSERT INTO "emergency"."station" (id, address, lat, lon, name, sdis_id_sdis) VALUES (2, 'Le bassin central de la place, Place de la République, 69002 Lyon, France', 45.76066862832485, 4.835709583359651, 'Caserne République', 1);
INSERT INTO "emergency"."station" (id, address, lat, lon, name, sdis_id_sdis) VALUES (1, '9 Rue de Condé, 69002 Lyon, France', 45.75223304888803, 4.826356465032533, 'Caserne Perrache', 1);
