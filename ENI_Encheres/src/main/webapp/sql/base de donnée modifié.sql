-- modification
alter table articles_vendus ALTER COLUMN date_debut_encheres Datetime not null
alter table articles_vendus ALTER COLUMN date_fin_encheres Datetime not null
alter table encheres ALTER COLUMN date_enchere Datetime not null
alter table utilisateurs ALTER COLUMN email varchar(120) not null
alter table utilisateurs ALTER COLUMN rue varchar(120) not null
alter table retraits ALTER COLUMN rue varchar(120) not null
alter table articles_vendus add etatVente varchar(80) not null

--supprime tous les articles
delete from RETRAITS
delete from encheres
delete from ARTICLES_VENDUS

--insert pour l'admin
insert into UTILISATEURS (pseudo,nom,prenom,email,rue,code_postal,ville,mot_de_passe,credit,administrateur)values('admin','admin','admin','admin@gmail.com','rue','cpo','ville','adminmdp',0,'true')