create database boulangerie;

\c boulangerie;

create sequence seq_test start with 1 increment by 1;

create table test(
    id varchar(255),
    nom varchar(255),
    prenom varchar(255)
);