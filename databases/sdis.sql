create table sdis
(
    id_sdis  serial
        primary key,
    nom      varchar(255),
    id_ville integer
        references ville
);

alter table sdis
    owner to postgres;

INSERT INTO emergency.sdis (id_sdis, nom, id_ville) VALUES (1, 'SDIS de Lyon', 1);
INSERT INTO emergency.sdis (id_sdis, nom, id_ville) VALUES (2, 'SDIS de Villeurbanne', 2);
