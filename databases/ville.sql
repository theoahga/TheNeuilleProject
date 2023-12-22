create table emergency.ville
(
    id_ville serial
        primary key,
    nom      varchar(255)
);

alter table emergency.ville
    owner to postgres;

INSERT INTO emergency.ville (id_ville, nom) VALUES (1, 'Lyon');
INSERT INTO emergency.ville (id_ville, nom) VALUES (2, 'Villeurbanne');
