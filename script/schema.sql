create database boulangerie;

\c boulangerie;

create sequence seq_unite start with 1 increment by 1;
create sequence seq_ingredient start with 1 increment by 1;
create sequence seq_categorie start with 1 increment by 1;
create sequence seq_produit start with 1 increment by 1;
create sequence seq_mvtStockIngredient start with 1 increment by 1;
create sequence seq_mvtStockProduit start with 1 increment by 1;
create sequence seq_recette start with 1 increment by 1;
create sequence seq_achat start with 1 increment by 1;
create sequence seq_achatFille start with 1 increment by 1;
create sequence seq_venteProduit start with 1 increment by 1;
create sequence seq_venteFille start with 1 increment by 1;
create sequence seq_production start with 1 increment by 1;
create sequence seq_productionFille start with 1 increment by 1;

create table unite (
    id varchar(255) primary key,
    nom varchar(255),
    initiale varchar(255)
);


create table ingredient(
    id varchar(255) primary key,
    nom varchar(255),
    "description" varchar(255),
    idUnite varchar(255),
    foreign key (idUnite) references unite(id)
);


create table categorie (
    id varchar(255) primary key,
    nom varchar(255)
);


create table produit (
    id varchar(255) primary key,
    nom varchar(255),
    "description" varchar(255),
    idCategorie varchar(255),
    prix decimal(11,2),
    estNature int,
    foreign key (idCategorie) references categorie(id)
);


create table mvtStockIngredient (
    id varchar(255) primary key,
    idIngredient varchar(255),
    dateMvtI date,
    entree int,
    sortie int,
    prix decimal(11,2),
    foreign key (idIngredient) references ingredient(id) 
);


create table mvtStockProduit (
    id varchar(255) primary key,
    idProduit varchar(255),
    dateMvtP date,
    entree int,
    sortie int,
    prix decimal(11,2),
    foreign key (idProduit) references produit(id)
);


create table recette (
    id varchar(255) primary key,
    idProduit varchar(255),
    idIngredient varchar(255),
    quantite int,
    foreign key (idProduit) references produit(id),
    foreign key (idIngredient) references ingredient(id)
);


create table achat (
    id varchar(255) primary key,
    dateAchat date,
    "description" varchar(255)
);


create table achatFille (
    id varchar(255) primary key,
    idAchat varchar(255),
    idIngredient varchar(255),
    prix decimal(11,2),
    quantite int,
    foreign key (idAchat) references achat(id),
    foreign key (idIngredient) references ingredient(id)
);


create table venteProduit (
    id varchar(255) primary key,
    dateVente date,
    "description" varchar(255),
    idClient varchar(255),
    idVendeur varchar(255),
    commission decimal(11,2)
    foreign key(idClient) references client(id),
    foreign key(idVendeur) references vendeur(id)
);

create table commission(
    valeur decimal(11,2)
);

create table venteFille (
    id varchar(255) primary key,
    idVente varchar(255),
    idProduit varchar(255),
    prix decimal(11,2),
    quantite int,
    foreign key (idVente) references venteProduit(id),
    foreign key (idProduit) references produit(id)
);


create table production (
    id varchar(255) primary key,
    dateProduction date,
    "description" varchar(255)
);


create table productionFille (
    id varchar(255) primary key,
    idProduction varchar(255),
    idProduit varchar(255),
    quantite int,
    foreign key (idProduction) references production(id),
    foreign key (idProduit) references produit(id)
);

create sequence seq_conseil start with 1 increment by 1;
create table conseil (
    id varchar(255) primary key,
    idProduit varchar(255),
    mois int,
    annee int,
    foreign key(idProduit) references produit(id)
);

create sequence seq_client start with 1 increment by 1;
create table client (
    id varchar(255) primary key,
    nom varchar(255)
);

create sequence seq_vendeur start with 1 increment by 1;
create table vendeur (
    id varchar(255) primary key,
    nom varchar(255),
    dateEmbauche date,
    idGenre varchar(255),
    foreign key (idGenre) references genre(id)
);

insert into vendeur values('VEND0000'||nextval('seq_vendeur'), 'Vendeur 1', '2024-01-19');
insert into vendeur values('VEND0000'||nextval('seq_vendeur'), 'Vendeur 2', '2024-01-19');
insert into vendeur values('VEND0000'||nextval('seq_vendeur'), 'Vendeur 3', '2024-01-19');

create sequence seq_genre start with 1 increment by 1;
create table genre (
  id varchar(255) primary key ,
  nom varchar(255)
);

insert into genre values ('GEN0000' || nextval('seq_genre'), 'Homme');
insert into genre values ('GEN0000' || nextval('seq_genre'), 'Femme');

create sequence seq_historiquePrix start with 1 increment by 1;
create table historiquePrix(
    id varchar(255) primary key,
    idProduit varchar(255),
    dateChangement date,
    prix decimal(11,2),
    foreign key (idProduit) references produit (id)
);