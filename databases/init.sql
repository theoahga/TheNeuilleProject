CREATE TABLE "emergency"."ville" (
    id_ville SERIAL PRIMARY KEY,
    nom VARCHAR(255)
);

INSERT INTO "emergency"."ville" (nom) VALUES
    ('Lyon'),
    ('Villeurbanne');

CREATE TABLE "emergency"."sdis" (
    id_sdis SERIAL PRIMARY KEY,
    nom VARCHAR(255),
    id_ville INT REFERENCES "emergency"."ville"(id_ville)
);

INSERT INTO "emergency"."sdis" (nom, id_ville) VALUES
    ('SDIS de Lyon',1),
    ('SDIS de Villeurbanne',2);

CREATE TABLE "emergency"."sensors" (
    cid SERIAL PRIMARY KEY,
    lat VARCHAR(255),
    lon VARCHAR(255),
    adresse VARCHAR(500),
    alias VARCHAR(20),
    id_ville INT REFERENCES "emergency"."ville"(id_ville)
);

INSERT INTO "emergency"."sensors" (lat,lon,adresse,alias,id_ville) VALUES
    ('45.78067985908081','4.860780835221825','Tennis Club de Lyon, Boulevard de la Bataille de Stalingrad, 69100 Villeurbanne, France', '' , 2),
    ('45.78156077846428','4.861144909028607','UFR Sciences et techniques des activités physiques et sportives, 3-5 Avenue Claude Bernard, 69100 Villeurbanne, France', '' , 2),
    ('45.780046562813865','4.862795941409859','', '' , 2),
    ('45.77974172183641','4.863642624682312','27 Boulevard du 11 Novembre 1918, 69100 Villeurbanne, France', '' , 2),
    ('45.78122494573686','4.864091366816643','Sapin, 3-13 Avenue Claude Bernard, 69100 Villeurbanne, France', '' , 2),
    ('45.78281441309416','4.864303037634756','', '' , 2),
    ('45.783674984981864','4.864286079278317','3 Avenue Pierre de Coubertin, 69100 Villeurbanne, France', '' , 2),
    ('45.78022143601035','4.865263663153967','37 Avenue Pierre de Coubertin, 69100 Villeurbanne, France', '' , 2),
    ('45.78085379700507','4.866331611925823','33 Avenue Pierre de Coubertin, 69100 Villeurbanne, France', '' , 2),
    ('45.77952210658161','4.86908363376096','Complexe sportif SIUAPS, 21-23 Boulevard André Latarjet, 69100 Villeurbanne, France', '' , 2),
    ('45.780560176144114','4.867662440395328','16 Rue Enrico Fermi, 69100 Villeurbanne, France', '' , 2),
    ('45.78000690779821','4.868309764228145','', '' , 2),
    ('45.78159595562129','4.867336453110682','Braconnier, 21 Avenue Claude Bernard, 69100 Villeurbanne, France', '' , 2),
    ('45.783045212595624','4.866148441011466','15 Avenue Pierre de Coubertin, 69100 Villeurbanne, France', '' , 2),
    ('45.78385966225946','4.867658504583332','6 Rue Enrico Fermi, 69100 Villeurbanne, France', '' , 2),
    ('45.7826156799646','4.868545935308037','14 Rue Enrico Fermi, 69100 Villeurbanne, France', '' , 2),
    ('45.78190290428074','4.869025434167336','16 Rue Enrico Fermi, 69100 Villeurbanne, France', '' , 2),
    ('45.7804773255751','4.86942620933335','26 Avenue Gaston Berger, 69100 Villeurbanne, France', '' , 2),
    ('45.78240574741664','4.870814609015572','16 Avenue Gaston Berger, 69100 Villeurbanne, France', '' , 2),
    ('45.78388087335168','4.869046904265515','46 Boulevard Niels Bohr, 69100 Villeurbanne, France', '' , 2),
    ('45.78350749456184','4.870614221432525','6 Avenue Gaston Berger, 69100 Villeurbanne, France', '' , 2),
    ('45.78209599408573','4.8712225408809395','Avenue Gaston Berger, 69100 Villeurbanne, France', '' , 2),
    ('45.78142314028668','4.87184517372814','22 Avenue Gaston Berger, 69100 Villeurbanne, France', '' , 2),
    ('45.78060054442959','4.872095658206899','19 Avenue Gaston Berger, 69100 Villeurbanne, France', '' , 2),
    ('45.780985796560024','4.873705915570308','59 Boulevard du 11 Novembre 1918, 69100 Villeurbanne, France', '' , 2),
    ('45.779743933796645','4.8737560124660595','47 Boulevard du 11 Novembre 1918, 69100 Villeurbanne, France', '' , 2),
    ('45.78290421895226','4.872718291054099','7 Avenue Jean Capelle, 69100 Villeurbanne, France', '' , 2),
    ('45.78389834130405','4.874070907239356','14 Avenue des Arts, 69100 Villeurbanne, France', '' , 2),
    ('45.78520187180224','4.875366269829469','25 Avenue des Arts, 69100 Villeurbanne, France', '' , 2),
    ('45.78375897687704','4.878624943699412','3 Avenue Jean Capelle, 69100 Villeurbanne, France', '' , 2),
    ('45.783661612954624','4.882648289228788','Croix-Luizet, Avenue Jean Capelle, 69100 Villeurbanne, France', '' , 2),
    ('45.78535001537445','4.883206753608169','Gymnase B, Boulevard Niels Bohr, 69100 La Feyssine, France', '' , 2),
    ('45.78684574382899','4.882047789896129','98 Boulevard Niels Bohr, 69100 Villeurbanne, France', '' , 2),
    ('45.78436828272266','4.884221597480587','Résidence B, Boulevard Niels Bohr, 69100 La Feyssine, France', '' , 2),
    ('45.780745427631295','4.879824599119016','13 Rue Chateaubriand, 69100 Villeurbanne, France', 'Chez Jb' , 2),
    ('45.775543730089005','4.873757955287803','14 Rue Colin, 69100 Villeurbanne, France', 'Chez Aymeric' , 2),
    ('45.77802462291498','4.862921206753397','Athena I, Promenade du Lys Orangé, 69100 Villeurbanne, France', 'Chez Théo' , 2),
    ('45.777849979264204','4.866867155973651','10 Avenue Condorcet, 69100 Villeurbanne, France', '' , 2),
    ('45.77654915279619','4.864959778984899','37 Rue du Tonkin, 69100 Villeurbanne, France', '' , 2),
    ('45.77629003967249','4.867351569177001','16 Avenue Galline, 69100 Villeurbanne, France', '' , 2),
    ('45.77618577819474','4.869662623835405','Solutec, Avenue Galline, 69100 Villeurbanne, France', '' , 2),
    ('45.77533540198569','4.8699754740293555','26 Avenue Roger Salengro, 69100 Villeurbanne, France', '' , 2),
    ('45.778398543249885','4.870106669272025','1 Rue Bonnet, 69100 Villeurbanne, France', '' , 2),
    ('45.77794236010477','4.872296620629721','31 Rue Marguerite, 69100 Villeurbanne, France', '' , 2),
    ('45.77923171671906','4.874748962472477','130 Boulevard du 11 Novembre 1918, 69100 Villeurbanne, France', '' , 2),
    ('45.776692126415135','4.8719131268435945','20 Rue Marteret, 69100 Villeurbanne, France', '' , 2),

    ('45.77780223560963','4.8372573750629755','8 Rue Louis Thévenet, 69004 Lyon, France', '' ,1),
    ('45.77509667677609','4.836746995583936','4 Rue Lebrun, 69004 Lyon, France', '' ,1),
    ('45.775393646565185','4.832419865218314','22 Place de la Croix-Rousse, 69004 Lyon 4e Arrondissement, France', '' ,1),
    ('45.77417383548198','4.8363031873413','Place Bellevue, 69001 Lyon, France', '' ,1),
    ('45.774662343520085','4.8282036869133504','137 Boulevard de la Croix-Rousse, 69004 Lyon 4e Arrondissement, France', '' ,1),
    ('45.77712610003166','4.830045491120258','32 Rue de Cuire, 69004 Lyon 4e Arrondissement, France', '' ,1),
    ('45.7752562861427','4.824386936026728','13 Rue Denfert-Rochereau, 69004 Lyon, France', '' ,1),
    ('45.77679141472354','4.820681137200764','24 Rue Bournes, 69004 Lyon 4e Arrondissement, France', '' ,1),
    ('45.77420962730528','4.815599532822676','21 Montée des Esses, 69004 Lyon 4e Arrondissement, France', '' ,1),
    ('45.77320648020565','4.821613134510252','55 Boulevard de la Croix-Rousse, 69004 Lyon 4e Arrondissement, France', '' ,1),
    ('45.770036339376375','4.816842195901994','12 Cours Général Giraud, 69001 Lyon 1er Arrondissement, France', '' ,1),
    ('45.769714188034705','4.827959592379765','7 Rue de l''Annonciade, 69001 Lyon 1er Arrondissement, France', '' ,1),
    ('45.768865750523126','4.831265963787308','25 Rue Sergent Blandan, 69001 Lyon, France', '' ,1),
    ('45.768264967696','4.827915211555469','2 Rue Pareille, 69001 Lyon 1er Arrondissement, France', '' ,1),
    ('45.76738554928071','4.831621010381313','2 Rue Lanterne, 69001 Lyon, France', '' ,1),
    ('45.768951852523735','4.835060524261663','8 Rue Romarin, 69001 Lyon, France', '' ,1),
    ('45.76773190054256','4.837079851765593','1 Rue Alexandre Luigini, 69001 Lyon, France', '' ,1),
    ('45.76719302348732','4.832885863872738','14 Place des Terreaux, 69001 Lyon 1er Arrondissement, France', '' ,1),
    ('45.76594207337822','4.830910917193023','Rue de la Platière, 69001 Lyon, France', '' ,1),
    ('45.766579645423285','4.834439192721884','5 Rue du Président Édouard Herriot, 69001 Lyon, France', '' ,1),
    ('45.76520484138271','4.831798533678255','2 Rue Longue, 69001 Lyon, France', '' ,1),
    ('45.76506842337046','4.835104905085799','12 Rue Neuve, 69002 Lyon 2e Arrondissement, France', '' ,1),
    ('45.76545832644874','4.838189372372064','13 Quai Jean Moulin, 69002 Lyon, France', '' ,1),
    ('45.767550985486906','4.836702614759241','1 Rue du Garet, 69001 Lyon 1er Arrondissement, France', '' ,1),
    ('45.76295632037532','4.833129958405964','7 Rue Tupin, 69002 Lyon, France', '' ,1),
    ('45.762293550548016','4.835726236625337','30 Rue de la République, 69002 Lyon, France', '' ,1),
    ('45.761073452949525','4.836991090116802','2174, Rue Jussieu, 69002 Lyon, France', '' ,1),
    ('45.76099894969413','4.832930244696754','9 Rue de l''Ancienne Préfecture, 69002 Lyon 2e Arrondissement, France', '' ,1),
    ('45.75934533884599','4.834594525606599','17 Rue des Archers, 69002 Lyon, France', '' ,1),
    ('45.75756782186073','4.832131389859993','Place Bellecour, 69002 Lyon, France', '' ,1),
    ('45.75647148075754','4.834550144782304','5 Place Antonin Poncet, 69002 Lyon 2e Arrondissement, France', '' ,1),
    ('45.758192910500036','4.828625304743199','7 Quai Tilsitt, 69002 Lyon, France', '' ,1),
    ('45.763131646570045','4.835968341150405','Grand Bazar, Rue Tupin, 69002 Lyon, France', '' ,1),
    ('45.76426323088166','4.831365670480849','1 Quai Saint-Antoine, 69002 Lyon 2e Arrondissement, France', '' ,1),
    ('45.768822548660935','4.826805915155532','11 Rue de la Vieille, 69001 Lyon, France', '' ,1);



