create or replace view v_stockIngredient as
select 
    ing.*, 
    sum(entree)-sum(sortie) as stock 
from 
    mvtStockIngredient as mvt 
join 
    ingredient as ing 
on 
    ing.id = mvt.idIngredient 
group by 
    ing.id;


create or replace view v_stockProduit as
select
    prod.*,
    sum(entree)-sum(sortie) as stock,
    cat.nom as categorie
from   
    mvtStockProduit as mvt
join
    produit as prod
on  
    prod.id = mvt.idProduit
join
    categorie as cat
on  
    prod.idCategorie = cat.id
group by
    cat.nom,
    prod.id;

create or replace view v_rechercheProduction as
select 
    p.*, 
    pf.idProduit,
    prod.id as produit,
    prod.idCategorie,
    rec.idIngredient 
from 
    production p
join 
    productionFille pf 
on 
    p.id = pf.idProduction
left join 
    produit prod 
on 
    pf.idProduit = prod.id
join 
    recette as rec 
on 
    prod.id = rec.idProduit;

create or replace view v_ficheAchat as
select 
    af.*, 
    ing.nom 
from 
    achatFille as af 
join 
    ingredient as ing 
on 
    af.idIngredient = ing.id

create or replace view v_ficheVente as
select 
    vf.*, 
    prod.nom, 
    cat.nom as categorie 
from 
    venteFille as vf 
join 
    produit as prod 
on 
    vf.idProduit = prod.id 
join 
    categorie as cat 
on 
    prod.idCategorie = cat.id; 


create or replace view v_ficheProduction as
select 
    pf.*, 
    prod.nom, 
    cat.nom as categorie 
from 
    productionFille as pf 
join 5
    produit as prod 
on 
    pf.idProduit = prod.id 
join 
    categorie as cat 
on 
    prod.idCategorie = cat.id;

create or replace view v_venteNature as
select 
    v.*, 
    vf.idProduit, 
    prod.idCategorie, 
    prod.estNature 
from 
    venteProduit v 
join 
    venteFille vf 
on 
    v.id = vf.idVente 
join 
    produit prod 
on 
    vf.idProduit = prod.id; 