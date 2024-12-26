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