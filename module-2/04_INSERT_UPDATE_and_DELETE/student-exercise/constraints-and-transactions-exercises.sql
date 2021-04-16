-- Write queries to return the following:
-- The following changes are applied to the "dvdstore" database.**

-- 1. Add actors, Hampton Avenue, and Lisa Byway to the actor table.

begin transaction;

insert into actor
(first_name, last_name)
values('HAMPTON', 'AVENUE'),('LISA', 'BYWAY');
select * from actor where last_name in ('AVENUE', 'BYWAY');

-- 2. Add "Euclidean PI", "The epic story of Euclid as a pizza delivery boy in
-- ancient Greece", to the film table. The movie was released in 2008 in English.
-- Since its an epic, the run length is 3hrs and 18mins. There are no special
-- features, the film speaks for itself, and doesn't need any gimmicks.

insert into film
(title, description, release_year, language_id, length)
values('Euclidean PI', 'The epic story of Euclid as a pizza delivery boy in ancient Greece', 2008, 1, 198);

select * from film where title = 'Euclidean PI';

-- 3. Hampton Avenue plays Euclid, while Lisa Byway plays his slightly
-- overprotective mother, in the film, "Euclidean PI". Add them to the film.

insert into film_actor
(actor_id, film_id)
values((select actor_id from actor where first_name = 'HAMPTON' and last_name = 'AVENUE'), (select film_id from film where title = 'Euclidean PI'));

insert into film_actor
(actor_id, film_id)
values((select actor_id from actor where first_name = 'LISA' and last_name = 'BYWAY'), (select film_id from film where title = 'Euclidean PI'));

select * from film_actor;


-- 4. Add Mathmagical to the category table.

insert into category
(name)
values('Mathmagical');

select * from category where name = 'Mathmagical';

-- 5. Assign the Mathmagical category to the following films, "Euclidean PI",
-- "EGG IGBY", "KARATE MOON", "RANDOM GO", and "YOUNG LANGUAGE"

insert into film_category
(film_id,category_id)
values((select film_id from film where title = 'Euclidean PI'),(select category_id from category where name = 'Mathmagical'))
;
insert into film_category
(film_id,category_id)
values((select film_id from film where title = 'EGG IGBY'),(select category_id from category where name = 'Mathmagical'))
;
insert into film_category
(film_id,category_id)
values((select film_id from film where title = 'KARATE MOON'),(select category_id from category where name = 'Mathmagical'))
;
insert into film_category
(film_id,category_id)
values((select film_id from film where title = 'RANDOM GO'),(select category_id from category where name = 'Mathmagical'))
;
insert into film_category
(film_id,category_id)
values((select film_id from film where title = 'YOUNG LANGUAGE'),(select category_id from category where name = 'Mathmagical'))
;
select * from film_category where category_id = 17;

-- 6. Mathmagical films always have a "G" rating, adjust all Mathmagical films
-- accordingly.
-- (5 rows affected)

update film
set rating = 'G'
where film_id in(select film_id from film_category where category_id = (select category_id from category where name = 'Mathmagical'));

-- 7. Add a copy of "Euclidean PI" to all the stores.

insert into inventory
(film_id, store_id)
values((select film_id from film where title = 'Euclidean PI'),1)
;
insert into inventory
(film_id, store_id)
values((select film_id from film where title = 'Euclidean PI'),2)
;

select * from inventory
order by film_id desc;

-- 8. The Feds have stepped in and have impounded all copies of the pirated film,
-- "Euclidean PI". The film has been seized from all stores, and needs to be
-- deleted from the film table. Delete "Euclidean PI" from the film table.
-- (Did it succeed? Why?)

-- <It doesn't work because it is used as a foreign key on another table>

delete from film
where title = 'Euclidean PI';

select * from film where title = 'Euclidean PI';

-- 9. Delete Mathmagical from the category table.
-- (Did it succeed? Why?)

-- <This is also used as a foreign key on another table>

delete from category
where name = 'Mathmagical';

select * from category where name = 'Mathmagical';

-- 10. Delete all links to Mathmagical in the film_category tale.
-- (Did it succeed? Why?)

-- <This delete succeeded because it was the only table it was used as a foreign key on.>

delete from film_category
where category_id = 17;

select * from film_category where category_id = 17;

-- 11. Retry deleting Mathmagical from the category table, followed by retrying
-- to delete "Euclidean PI".
-- (Did either deletes succeed? Why?)

-- <Mathmagical deleted from the category table because it was no longer used as a foreign key on another table.
--     Euclidean PI did not delete from the film table because it is still used as a foreign key in the film_actor table.>

delete from category
where name = 'Mathmagical';

select * from category where name = 'Mathmagical';

delete from film
where title = 'Euclidean PI';

select * from film where title = 'Euclidean PI';

-- 12. Check database metadata to determine all constraints of the film id, and
-- describe any remaining adjustments needed before the film "Euclidean PI" can
-- be removed from the film table.

rollback;