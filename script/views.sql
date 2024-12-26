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