-- Keys, Joins and Unions
--
-- Primary Key - a column or set of columns that uniquely identify a row in a table (Parent table)
-- Foreigh Key - a column or set of columns that match the primary key of another table (Dependent table)
--
-- Natural Key - Value(s) that exist for a table that can be used as primary keys
-- Surrogate Key - Artificially generated keys to use as a primary key
--
--  Joins are used when columns from multiple tables are required in a query
--
-- INNER JOIN - Matching rows between tables 
-- 
-- OUTER JOIN - All rows from both tables - the full set      
------------------------------------------------------------------------------------------------
-- Inner Join syntax - 2 forms
-- 
--  SELECT columns
--    FROM table1, table2  
--  WHERE table1.column = table2.column  
--
-- If you omit join conditions the result is Cartersian Product
--    each from one table with each row in the other - IT'S NOT A JOIN
--
-- minimum # conditions on WHERE = # tables - 1
--
--
-- Modern syntax you CANNOT forget the join condition - it coded in ON clause
--
--  SELECT columns
--    FROM table1
--         INNER JOIN
--         table2
--      on table1.column = table2.column
--
-- Additional WHERE predicates may be added to either syntax to filter rows
------------------------------------------------------------------------------------------------
-- -- OUTER JOIN syntax 
--
--  SELECT columns
--    FROM table1
--         FULL OUTER JOIN
--         table2
--      on table1.column = table2.column
--
-- Additional WHERE predicates may be added to either syntax to filter rows
------------------------------------------------------------------------------------------------
-- UNION - Merging rows between two tables
--
-- Each SELECT must have the same number of columns in the same order and be compatible datatypes 
--      use literals if necessary to make the UNION work
--
-- SELECT columns
--   FROM table1
-- WHERE predicate
--
-- UNION
--
-- SELECT columns
--   FROM table2
-- WHERE predicate
------------------------------------------------------------------------------------------------
--
-- UNION All - Concatenating rows between two tables
--
-- Each SELECT must have the same number of columns in the same order and be compatible datatypes 
--      use literals if necessary to make the UNION work
--
-- SELECT columns
--   FROM table1
-- WHERE predicate
--
-- UNION ALL
--
-- SELECT columns
--   FROM table2
-- WHERE predicate
------------------------------------------------------------------------------------------------
-- ********* INNER JOIN ***********

-- Show the last_name of all the actors in the movie FINDING ANACONDA
--
-- last_name is in actor table
-- film title is in the film table
-- no direct link between actor and film
-- so we used the indirect link the film_actor table provided
--
select last_name -- columns in the result
  from actor
       inner join
       film_actor
     on actor.actor_id = film_actor.actor_id  
       join   -- coding just the word join is the same as inner join
       film
     on film_actor.film_id = film.film_id
 where title = 'FINDING ANACONDA'
;
-- ********* INNER JOIN ***********

-- Let's find out who made payment 16666:
SELECT *
FROM payment
WHERE payment_id = 16666

-- Ok, that gives us a customer_id, but not the name. We can use the customer_id to get the name FROM the customer table
SELECT *
FROM payment
JOIN customer ON payment.customer_id = customer.customer_id
WHERE payment_id = 16666

-- We can see that the * pulls back everything from both tables. We just want everything from payment and then the first and last name of the customer:
SELECT payment.*, customer.first_name, customer.last_name
FROM payment
JOIN customer ON payment.customer_id = customer.customer_id
WHERE payment_id = 16666

-- But when did they return the rental? Where would that data come from? From the rental table, so let’s join that.
SELECT payment.*, customer.first_name, customer.last_name, rental.return_date
FROM payment
JOIN customer ON payment.customer_id = customer.customer_id
JOIN rental ON rental.rental_id = payment.rental_id
WHERE payment_id = 16666

-- What did they rent? Film id can be gotten through inventory.
SELECT payment.*, customer.first_name, customer.last_name, rental.return_date, film.title
FROM payment
JOIN customer ON payment.customer_id = customer.customer_id
JOIN rental ON rental.rental_id = payment.rental_id
JOIN inventory ON inventory.inventory_id = rental.inventory_id
JOIN film ON film.film_id = inventory.film_id
WHERE payment_id = 16666

-- What if we wanted to know who acted in that film?
SELECT film.title, ARRAY_AGG(actor.first_name || ' ' || actor.last_name) AS actors
FROM film
JOIN film_actor ON film_actor.film_id = film.film_id
JOIN actor ON actor.actor_id = film_actor.actor_id
WHERE film.film_id = 948
GROUP BY film.film_id

-- What if we wanted a list of all the films and their categories ordered by film title
SELECT f.title, c.name
FROM film f
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON c.category_id = fc.category_id
ORDER BY f.title

-- Show all the 'Comedy' films ordered by film title
SELECT f.title, c.name
FROM film f
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON c.category_id = fc.category_id
WHERE c.name = 'Comedy'
ORDER BY f.title

-- Finally, let's count the number of films under each category
SELECT c.name, COUNT(c.name)
FROM film f
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON c.category_id = fc.category_id
GROUP BY c.name
ORDER BY c.name


-- ********* LEFT JOIN ***********

-- (There aren't any great examples of left joins in the "dvdstore" database, so the following queries are for the "world" database)

-- A Left join, selects all records from the "left" table and matches them with records from the "right" table if a matching record exists.

-- Let's display a list of all countries and their capitals, if they have some.
SELECT c.name, ci.name
FROM country c
JOIN city ci ON c.capital = ci.id

-- Only 232 rows
-- But we’re missing entries:
SELECT COUNT(*)
FROM country

-- There are 239 countries. So how do we show them all even if they don’t have a capital?
-- That’s because if the rows don’t exist in both tables, we won’t show any information for it. If we want to show data FROM the left side table everytime, we can use a different join:
SELECT c.name, ci.name
FROM country c
LEFT JOIN city ci ON c.capital = ci.id

-- *********** UNION *************

-- Back to the "dvdstore" database...

-- Gathers a list of all first names used by actors and customers
-- By default removes duplicates
SELECT first_name
FROM actor
UNION
SELECT first_name
FROM customer
ORDER BY first_name

-- Gather the list, but this time note the source table with 'A' for actor and 'C' for customer
SELECT first_name, 'A' AS source
FROM actor
UNION
SELECT first_name, 'C' AS source
FROM customer
ORDER BY first_name
