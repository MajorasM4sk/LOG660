drop table realisateur_film;
drop table role_film;
drop table location_film;
drop table copie_film;
drop table bande_annonce_film;
drop table pays_film;
drop table pays;
drop table genre_film;
drop table film;
drop table genre;
drop table employe;
drop table personne;
drop table carte_credit;
drop table client;
drop table type_forfait;

create table type_forfait(
    code_forfait char(1) not null primary key,
    type_forfait varchar2(15) not null,
    cout number(2) not null,
    location_max number(2) not null,
    duree number(2) not null
);

create table client(
    id_client number(7) not null primary key,
    courriel varchar2(50) not null unique,
    mot_de_passe varchar2(20) not null check (length(mot_de_passe) > 4),
    telephone number(10) not null,
    nom varchar2(25) not null,
    prenom varchar2(25) not null,
    date_naissance date not null,
    code_forfait char(1),
    constraint fk_client_code_forfait FOREIGN key (code_forfait) REFERENCES type_forfait (code_forfait)
);

create table carte_credit(
    no_carte char(16) not null primary key,
    type_carte varchar2(30) not null,
    annee_expiration number(4) not null,
    mois_expiration number(2) not null,
    cvv number(3) not null,
    nom_proprio varchar2(50) not null,
    id_client number(7) not null,
    CONSTRAINT fk_carte_credit_id_client
        FOREIGN KEY (id_client)
        REFERENCES client (id_client)
);

create table personne (
    id_personne integer not null primary key,
    nom varchar2(50) not null,
    date_naissance varchar2(50) not null,
    lieu_naissance varchar2(50) not null,
    photo varchar2(100) not null,
    biographie CLOB not null
);

create table employe(
    matricule number(7) not null primary key,
    courriel varchar2(50) not null,
    mot_de_passe varchar2(20) not null,
    telephone number(10) not null,
    nom varchar2(25) not null,
    prenom varchar2(25) not null,
    adresse_civique varchar2(50) not null,
    date_naissance date not null
);

create table genre(
    no_genre integer not null primary key,
    nom varchar2(25) not null
);

create table film(
    code_film integer not null primary key,
    titre varchar (200) not null,
    annee number(4) not null,
    langue varchar2(25) not null,
    resume_film varchar2(500) not null,
    affiche varchar2(100) not null
);

create table genre_film(
    code_film integer not null,
    no_genre integer not null,
    constraint pk_genre_film primary key(code_film, no_genre),
    constraint fk_genre_film_code_film FOREIGN key (code_film) REFERENCES film (code_film),
    constraint fk_genre_film_no_genre FOREIGN key (no_genre) REFERENCES genre (no_genre)
);

create table pays(
    code_pays char(3) not null primary key,
    nom varchar(60) not null
);

create table pays_film(
    code_pays char(3) not null,
    code_film integer not null,
    constraint pk_pays_film primary key(code_pays, code_film),
    constraint fk_pays_film_code_pays FOREIGN key (code_pays) REFERENCES pays (code_pays),
    constraint fk_pays_film_code_film FOREIGN key (code_film) REFERENCES film (code_film)
);

create table bande_annonce_film(
    lien_bande_annonce varchar(50) not null,
    code_film integer not null,
    constraint pk_bande_annonce_film primary key (lien_bande_annonce, code_film),
    constraint fk_bande_annonce_film_code_film FOREIGN key (code_film) REFERENCES film (code_film)
);

create table copie_film(
    no_copie_film number(7) not null primary key,
    est_disponible char(1) not null check (est_disponible in ('1', '0')),
    code_film integer not null,
    constraint fk_copie_film_code_film FOREIGN key (code_film) REFERENCES film (code_film)
);

create table location_film(
    no_location number(10) not null primary key,
    date_pret date not null,
    date_retour date,
    id_client number(7) not null,
    no_copie_film number(7) not null,
    constraint fk_location_film_code_film FOREIGN key (no_copie_film) REFERENCES copie_film (no_copie_film),
    constraint fk_location_film_id_client FOREIGN key (id_client) REFERENCES client (id_client)
);

create table role_film(
    personnage varchar2(50) not null,
    id_personne integer not null,
    code_film integer not null,
    constraint pk_role_film primary key (personnage, code_film, id_personne),
    constraint fk_role_film_id_personne FOREIGN KEY (id_personne) REFERENCES personne (id_personne),
    constraint fk_role_film_code_film FOREIGN KEY (code_film) REFERENCES film (code_film)
);

create table realisateur_film(
    id_personne integer not null,
    code_film integer not null,
    constraint pk_realisateur_film primary key (id_personne, code_film),
    constraint fk_realisateur_film_id_personne FOREIGN key (id_personne) REFERENCES personne (id_personne),
    constraint fk_realisateur_film_code_film FOREIGN key (code_film) REFERENCES film (code_film)
);