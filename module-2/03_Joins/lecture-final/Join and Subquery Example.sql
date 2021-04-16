--
-- Show the country name, city name, district
-- 
-- We need data from more than one table - country table and city table
--
-- Whenever you need data from more than one table - code a join
--
-- A join matches rows between tables based on a join condition
--
-- join condition defines how rows between table should be matched
--
-- If names betweens are duplicated you must make uniques using tablename.column-name
--
-- Two formats for coding a join:
--
--    1. CLassic syntax - code all the tables on the from and and join condition on the WHERE clause
--    2. Modern syntax  - code the type of join between the table names and the join condition on an ON clause
--
---------------------------------------------------------------------------------------------------------------------
-- Classic Syntax Join
---------------------------------------------------------------------------------------------------------------------
select country.name, city.name, district  -- columns you want in the result
  from country, city                      -- table(s) with the columns you want
 where country.code = city.countrycode    -- join condition - we want the rows that match between city and country tables by country code
   and country.code = 'USA'               -- filtering conditions are allowed on the WHERE too
 ;

---------------------------------------------------------------------------------------------------------------------
-- Modern Syntax Join
---------------------------------------------------------------------------------------------------------------------
select country.name, city.name, district  -- columns you want in the result
  from country                            -- code one of tables in the join
       inner join                         -- type join - inner join shows matches bewteen tables based on join condition\
       city                               -- code the other table in the join
    on country.code = city.countrycode    -- join condition - we want the rows that match between city and country tables by country code
 where country.code = 'USA'               -- filtering conditions the WHERE as usual
;

-- Subquery Example -----------------------------------------------------------------------------------------------
--
-- The names of the countries that speak English
--
-- The country language table has the country codes that speak each language
--
--   1. Get a list of country code that speak English from the countrylanguage table
--   2. Get the name of the country from the country table if the code in the country table is in the list from #1
--
-- A subquery is where the result of one select is used in another select
-- Used when data from one table is needed to search another table or data from one table is needed another
-- 
-- a subquery may only return one column (a list)
--
-- Usually link the queries with an IN due to a list being returned from the subquery
--
-- The subquery is run first and the list returned from it is plugged into the IN clause of the outer SELECT
--
-- You nest up to generally 32,766 subqueries (more than you ever need)
--
-- we needed the countrycodes from countrylanguage that spoke Eglish to search country table for the names of those countries
--
select name
  from country
 where code in (select countrycode             -- list of countrycodes
                   from countrylanguage        -- from the countrylanguage table 
                 where language = 'English')   -- where the language os English
 ;
 
 
  