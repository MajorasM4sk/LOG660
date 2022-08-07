drop table location_etoile;
drop table film_etoile;
drop table temps_etoile;
drop table client_etoile;

create table client_etoile (
    id_client integer primary key,
    nom_client varchar(50),
    premiere_location_annee number(4),
    premiere_location_mois number(2),
    premiere_location_jour number(2),
    age0_4 char(1),
    age5_9 char(1),
    age10_14 char(1),
    age15_19 char(1),
    age20_24 char(1),
    age25_29 char(1),
    age30_34 char(1),
    age35_39 char(1),
    age40_44 char(1),
    age45_49 char(1),
    age50_54 char(1),
    age55_59 char(1),
    age60_64 char(1),
    age65_69 char(1),
    age70_74 char(1),
    age75_79 char(1),
    age80_84 char(1),
    age85_89 char(1),
    age90_94 char(1),
    age95_99 char(1),
    age100_104 char(1),
    age105_109 char(1),
    age110_114 char(1),
    age115_119 char(1),
    age120_124 char(1),
    code_postal char(7),
    ville varchar(50),
    province varchar(2)
);

create table temps_etoile(
    id_temps integer primary key,
    date_location_heure number(2),
    date_location_jour_semaine number(1),
    date_location_mois number(2),
    annee_location number(4)
);

create table film_etoile(
    id_film integer primary key,
    titre_film varchar(200),
    annee_film number(4),
    est_usa char(1),
    est_action char(1),
    est_adventure char(1),
    est_comedy char(1),
    est_family char(1),
    est_romance char(1),
    est_drama char(1),
    est_animation char(1),
    est_fantasy char(1),
    est_biography char(1),
    est_thriller char(1),
    est_sci_fi char(1),
    est_crime char(1),
    est_sport char(1),
    est_horror char(1),
    est_film_noir char(1),
    est_mystery char(1),
    est_western char(1),
    est_war char(1),
    est_musical char(1),
    est_documentary char(1),
    est_history char(1),
    est_music char(1)
);

create table location_etoile(
    id_location integer,
    id_film integer,
    id_temps integer,
    id_client integer,
    constraint fk_location_etoile_id_film FOREIGN KEY (id_film) REFERENCES film_etoile (id_film),
    constraint fk_location_etoile_id_temps FOREIGN KEY (id_temps) REFERENCES temps_etoile (id_temps),
    constraint fk_location_etoile_id_client FOREIGN KEY (id_client) REFERENCES client_etoile (id_client)
);

commit;