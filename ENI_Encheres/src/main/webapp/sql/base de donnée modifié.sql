-- modification
alter table articles_vendus ALTER COLUMN date_debut_encheres Datetime not null
alter table articles_vendus ALTER COLUMN date_fin_encheres Datetime not null
alter table encheres ALTER COLUMN date_enchere Datetime not null
alter table utilisateurs ALTER COLUMN email varchar(120) not null
alter table utilisateurs ALTER COLUMN rue varchar(120) not null
alter table retraits ALTER COLUMN rue varchar(120) not null