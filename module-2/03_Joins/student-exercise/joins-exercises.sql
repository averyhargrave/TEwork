-- The following queries utilize the "dvdstore" database.

-- 1. All of the films that Nick Stallone has appeared in
-- (30 rows)
select title
  from actor
       inner join
       film_actor
    on actor.actor_id = film_actor.actor_id
       inner join
       film
    on film.film_id = film_actor.film_id
 where first_name = 'NICK'
   and last_name = 'STALLONE'
;

-- 2. All of the films that Rita Reynolds has appeared in
-- (20 rows)
select title
  from actor
       inner join
       film_actor
    on actor.actor_id = film_actor.actor_id
       inner join
       film
    on film.film_id = film_actor.film_id
 where first_name = 'RITA'
   and last_name = 'REYNOLDS'
;

-- 3. All of the films that Judy Dean or River Dean have appeared in
-- (46 rows)
select title
  from actor
       inner join
       film_actor
    on actor.actor_id = film_actor.actor_id
       inner join
       film
    on film.film_id = film_actor.film_id
 where first_name = 'JUDY'
       or first_name = 'RIVER'
   and last_name = 'DEAN'
;

-- 4. All of the "Documentary" films
-- (68 rows)
select title
  from film
       inner join
       film_category
    on film.film_id = film_category.film_id
       inner join
       category
    on category.category_id = film_category.category_id
 where category.name = 'Documentary'
;

-- 5. All of the â€˜Comedyâ€™ films
-- (58 rows)
select title
  from film
       inner join
       film_category
    on film.film_id = film_category.film_id
       inner join
       category
    on category.category_id = film_category.category_id
 where category.name = 'Comedy'
;

-- 6. All of the Children™ films that are rated G™
-- (10 rows)
select title
  from film
       inner join
       film_category
    on film.film_id = film_category.film_id
       inner join
       category
    on category.category_id = film_category.category_id
 where category.name = 'Children'
   and film.rating = 'G'
;

-- 7. All of the Family™ films that are rated â€˜Gâ€™ and are less than 2 hours in length
-- (3 rows)
select title
  from film
       inner join
       film_category
    on film.film_id = film_category.film_id
       inner join
       category
    on category.category_id = film_category.category_id
 where category.name = 'Family'
   and film.rating = 'G'
   and film.length < 120
;

-- 8. All of the films featuring actor Matthew Leigh that are rated â€˜Gâ€™
-- (9 rows)
select title
  from actor
       inner join
       film_actor
    on actor.actor_id = film_actor.actor_id
       inner join
       film
    on film.film_id = film_actor.film_id
 where first_name = 'MATTHEW'
   and last_name = 'LEIGH'
   and film.rating = 'G'
;

-- 9. All of the â€˜Sci-Fiâ€™ films released in 2006
-- (61 rows)
select title
  from film
       inner join
       film_category
    on film.film_id = film_category.film_id
       inner join
       category
    on category.category_id = film_category.category_id
 where category.name = 'Sci-Fi'
   and film.release_year = 2006
;

-- 10. All of the â€˜Actionâ€™ films starring Nick Stallone
-- (2 rows)
select title
  from actor
       inner join
       film_actor
    on actor.actor_id = film_actor.actor_id
       inner join
       film
    on film.film_id = film_actor.film_id
       inner join
       film_category
    on film.film_id = film_category.film_id
       inner join
       category
    on category.category_id = film_category.category_id
 where first_name = 'NICK'
   and last_name = 'STALLONE'
   and category.name = 'Action'
;

-- 11. The address of all stores, including street address, city, district, and country
-- (2 rows)
select address, city.city, address.district, country.country
  from store
       inner join address
   on store.address_id = address.address_id
       inner join
       city
    on address.city_id = city.city_id
       inner join
       country
    on country.country_id = city.country_id
;

-- 12. A list of all stores by ID, the storeâ€™s street address, and the name of the storeâ€™s manager
-- (2 rows)
select address, staff.first_name, staff.last_name
  from store
        inner join address
    on store.address_id = address.address_id 
       inner join
       staff
    on staff.store_id = store.store_id
 ;

-- 13. The first and last name of the top ten customers ranked by number of rentals
-- (#1 should be â€œELEANOR HUNTâ€? with 46 rentals, #10 should have 39 rentals)
  select customer.first_name, customer.last_name, count(rental.customer_id)
    from customer
         inner join
         rental
      on rental.customer_id = customer.customer_id
group by first_name, last_name
order by 3 desc
limit 10
 ;

-- 14. The first and last name of the top ten customers ranked by dollars spent
-- (#1 should be â€œKARL SEALâ€? with 221.55 spent, #10 should be â€œANA BRADLEYâ€? with 174.66 spent)
  select customer.first_name, customer.last_name, sum(payment.amount)
    from customer
         inner join
         payment
      on customer.customer_id = payment.customer_id
group by first_name, last_name
order by 3 desc
limit 10
;

-- 15. The store ID, street address, total number of rentals, total amount of sales (i.e. payments), and average sale of each store.
-- (NOTE: Keep in mind that while a customer has only one primary store, they may rent from either store.)
-- (Store 1 has 7928 total rentals and Store 2 has 8121 total rentals)
  select store.store_id, address, count(rental.*) as number_of_rentals, sum(payment.amount) as money_earned, avg(payment.amount) as average_price_per_rental
    from store
         inner join
         address
      on store.address_id = address.address_id
         inner join
         customer
      on customer.store_id = store.store_id
         inner join
         payment
      on payment.customer_id = customer.customer_id
         inner join
         rental
      on rental.rental_id = payment.rental_id
group by store.store_id, address
;

-- 16. The top ten film titles by number of rentals
-- (#1 should be â€œBUCKET BROTHERHOODâ€? with 34 rentals and #10 should have 31 rentals)
  select title, count(inventory.film_id)
    from rental
         inner join
         inventory
      on rental.inventory_id = inventory.inventory_id
         inner join
         film
      on inventory.film_id = film.film_id
group by title
order by 2 desc
limit 10
 ;

-- 17. The top five film categories by number of rentals
-- (#1 should be â€œSportsâ€? with 1179 rentals and #5 should be â€œFamilyâ€? with 1096 rentals)
  select name, count(rental.inventory_id)
    from category
         inner join
          film_category
      on category.category_id = film_category.category_id
         inner join
         inventory
      on inventory.film_id = film_category.film_id
         inner join
         rental
      on rental.inventory_id = inventory.inventory_id
group by name
order by 2 desc
limit 5
 ;

-- 18. The top five Action film titles by number of rentals
-- (#1 should have 30 rentals and #5 should have 28 rentals)
  select title , count(rental.*)
    from film
         inner join
         inventory
      on inventory.film_id = film.film_id
         inner join
         rental
      on rental.inventory_id = inventory.inventory_id
         inner join
         film_category
      on film_category.film_id = film.film_id
         inner join
         category 
      on category.category_id = film_category.category_id
   where category.name='Action'
group by film.title
order by 2 desc
limit 5
;

-- 19. The top 10 actors ranked by number of rentals of films starring that actor
-- (#1 should be â€œGINA DEGENERESâ€? with 753 rentals and #10 should be â€œSEAN GUINESSâ€? with 599 rentals)
  select first_name, last_name, count(rental.*)
    from actor
         inner join
         film_actor
      on film_actor.actor_id = actor.actor_id
         inner join
         film
      on film.film_id = film_actor.film_id
         inner join
         inventory
      on inventory.film_id = film.film_id
         inner join
         rental
      on rental.inventory_id = inventory.inventory_id
group by actor.actor_id
order by 3 desc
limit 10
;

-- 20. The top 5 â€œComedyâ€? actors ranked by number of rentals of films in the â€œComedyâ€? category starring that actor
-- (#1 should have 87 rentals and #5 should have 72 rentals)
  select first_name, last_name, count(rental.*)
    from actor
         inner join
         film_actor
      on film_actor.actor_id = actor.actor_id
         inner join
         film
      on film.film_id = film_actor.film_id
         inner join
         inventory
      on inventory.film_id = film.film_id
         inner join
         rental
      on rental.inventory_id = inventory.inventory_id
         inner join
         film_category
      on film_category.film_id = film.film_id
         inner join
         category 
      on category.category_id = film_category.category_id
   where category.name = 'Comedy'
group by actor.actor_id
order by 3 desc
limit 5
;
