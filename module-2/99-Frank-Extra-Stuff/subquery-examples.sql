-- Show the country name, city name, district
--
-- We need data from more than one table - country and city tables
-- Whenever you need data from more than one table - code a JOIN
--
-- A JOIN matches rows between tables based on a JOIN condition
-- The JOIN condition defines how rows between tables should be matched
--
-- If names between columns are duplicated you must make them unique by using table-name.column-name
-- Two formats for doing a join
--       1. Classic syntax - code all the tables on the FROM and the JOIN condition on the where clause
--       2. Modern syntax - code the type of JOIN between the table names and the JOIN condition on an ON clause
--

-- Classic Syntax JOIN ---------------------------------------------------------------------------------------------------------------------------
select country.name, city.name, district -- columns you want to see in the result
from country, city                                     -- the table(s) with the columns you want
where country.code = city.countrycode   -- we want the rows to match the data between city and country by country code
and country.code = 'USA'                         -- filtering conditions are allowed on the WHERE as well
;

-- Modern Syntax JOIN --------------------------------------------------------------------------------------------------------------------------
select country.name, city.name, district
   from country                                            -- code one of the tables in the JOIN
            inner join                                       -- type of JOIN - inner join shows matches between the tables based on the join condition
            city                                                   -- code the other table in the join
       on country.code = city.countrycode
where country.code = 'USA'
;

-- Subquery Example -----------------------------------------------------------------------------------------------------------------------------
--
-- Names of the countries that speak English
--
-- The country language table has the country codes that speak each language
--
--     1. Get a list of country codes that speak English from the countrylanguage table
--     2. Get the name of the country from the country table if the code in the country table is in the list you created in #1
--
-- A subquery is where the result of one select is used in another select
-- Used when data from one table is needed to search another table or data from one table is needed in another
-- A subquery may only return one column (a list)
--
-- Usually link the queries with an IN due to a list being returned from the subquery
-- The subquery is run first, the list returned from it is plugged into the IN
--
-- You can nest up to generally 32,766 subqueries (more than you'll ever need)
--
-- We needed the countrycodes from countrylanguage that spoke English to search the country table for the names of those countries

select name
from country
where code in (select countrycode
                          from countrylanguage
                          where language = 'English')
;