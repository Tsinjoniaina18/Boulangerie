create database boulangerie;

\c boulangerie;

create sequence seq_ingredient start with 1 increment by 1;

create table ingredient(
    id varchar(255) primary key,
    nom varchar(255),
    "description" varchar(255),
    prix decimal(11,2)
);

create sequence seq_categorie start with 1 increment by 1;

create table categorie (
    id varchar(255) primary key,
    nom varchar(255)
);

create sequence seq_produit start with 1 increment by 1;

create table produit (
    id varchar(255) primary key,
    nom varchar(255),
    "description" varchar(255),
    idCategorie varchar(255),
    prix decimal(11,2),
    foreign key (idCategorie) references categorie(id)
);

create sequence seq_mvtStockIngredient start with 1 increment by 1;

create table mvtStockIngredient (
    id varchar(255) primary key,
    idIngredient varchar(255),
    dateMvtI date,
    entree int,
    sortie int,
    prix decimal(11,2),
    foreign key (idIngredient) references ingredient(id) 
);

create sequence seq_mvtStockProduit start with 1 increment by 1;

create table mvtStockProduit (
    id varchar(255),
    idProduit varchar(255),
    dateMvtP date,
    entree int,
    sortie int,
    prix decimal(11,2),
    foreign key (idProduit) references produit(id)
);

create sequence seq_correspondance start with 1 increment by 1;

create table recette (
    id varchar(255),
    idProduit varchar(255),
    idIngredient varchar(255),
    quantite int,
    foreign key (idProduit) references produit(id),
    foreign key (idIngredient) references ingredient(id)
);

create sequence seq_achat start with 1 increment by 1;

create table achat (
    id varchar(255),
    dateAchat date;
    "description" varchar(255),
);

create sequence seq_achatFille start with 1 increment by 1;

create table achatFille (
    id varchar(255),
    idAchat varchar(255),
    idIngredient varchar(255),
    prix decimal(11,2),
    quantite int,
    foreign key (idAchat) references achat(id),
    foreign key (idIngredient) references ingredient(id)
);

create sequence vente start with 1 increment by 1;

create table vente (
    id varchar(255),
    dateVente date,
    "description" varchar(255)
);

create sequence seq_venteFille start with 1 increment by 1;

create table venteFille (
    id varchar(255),
    idVente varchar(255),
    idProduit varchar(255),
    prix decimal(11,2),
    quantite int,
    foreign key (idVente) references vente(id),
    foreign key (idProduit) references produit(id)
);