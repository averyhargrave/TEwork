--------------------------------------------------------------------------------------------------------
-- Ordering, Grouping Basic Functions Lecture Code
--------------------------------------------------------------------------------------------------------
--
-- ORDER BY -  Sequence of Rows in Result
--
--    ORDER BY          -- Ascending Sequence (low-high)
--    ORDER BY ASC      -- Ascending Sequence (low-high)
--    ORDER BY DESC     -- Descending Sequence (high-low)
--
-- The is no guarantee of the order of the rows in the result without an ORDER BY clause
--     You can run the same query 1000 times without an order by and get the same result order
--       the 1001st time might be a different result order
--
-- IF THE ORDER OF THE ROWS IN THE RESULT MATTER, CODE AN ORDER BY
--
-- In standard SQL, the ORDER BY is always last in the query
--   (postgreSQL) unless there is LIMIT clause which goes last
--

--
-- Show Populations of all countries in acscending order
select name, population
  from country
;  -- without an ORDER BY the rous in the result are in whatever order the database gives it to you - may not be consistent

select name, population
  from country
 order by population -- if the order of the rows in the results matter - code an order by
; 

select name, population
  from country
 order by population asc; -- if the order of the rows in the results matter - code an order by
; 


-- Show Populations of all countries in descending order
select name, population
  from country
 order by population desc;   
; 

-- Show  the names of countries and continents in ascending order
select name, continent
  from country
 order by continent, name    -- to order by multiple columns, code column names separated by , in the sequence you want them sort
;
select name, continent
  from country
 order by name, continent    -- to order by multiple columns, code column names separated by , in the sequence you want them sort
;
select name, continent
  from country
 order by continent desc, name    -- to order by multiple columns, code column names separated by , in the sequence you want them sort
                                  -- if you want to mix asc and desc for columns, code it after the column name and before the ,
;         
select name, continent
  from country
 order by continent, name desc    -- to order by multiple columns, code column names separated by , in the sequence you want them sort
                                  -- if you want to mix asc and desc for columns, code it after the column name and before the ,
;      
select name, continent
  from country
 order by continent desc, name desc    -- to order by multiple columns, code column names separated by , in the sequence you want them sort
                                  -- if you want to mix asc and desc for columns, code it after the column name and before the ,
;                            
--------------------------------------------------------------------------------------------------------
-- Limiting the number of rows in the result
--
-- LIMIT n   - Limit the number of rows in the result - always goes at the end of the SELECT
--
-- LIMIT is postgreSQL extentsion to standard SQL (ANSI - American National Standard Institute, ISO - International Standards Organization) 
-- other data base managers limit rows, but call it something other than LIMIT
--       and have you code it somewhere else in the SELECT
--
--LIMIT is always last in the SELECT
-- 
-- Show the name and life expectancy of the countries with the 10 highest life expectancies.
select name, lifeexpectancy
from country
where lifeexpectancy is not null
order by lifeexpectancy desc -- show the life expectancy from highest to lowest
limit 10   -- only return the first 10 rows in the result
;

-- Show the name and life expectancy of the countries with the 10 lowest life expectancies.
select name, lifeexpectancy
from country
where lifeexpectancy is not null
order by lifeexpectancy  -- show the life expectancy from lowest to highest
limit 10   -- only return the first 10 rows in the result
;

--------------------------------------------------------------------------------------------------------
-- Concatenating string/character values 
--
-- the concat operator (||) may be used to concatenate character (string) values in a result
--

-- Show the name & state in the format: "city-name, state"
-- of all cities in California, Oregon, or Washington.
-- sorted by state then city
select name || ', ' || district  as city_state -- contatenate a ', ' between the values
  from city
 where district = 'California'
    or district = 'Oregon'     -- the entire predicate must be coded when using OR / AND (column relational value)
    or district = 'Washington' -- the entire predicate must be coded when using OR / AND (column relational value)
-- where district In('California', 'Oregon','Washington') -- IN is an alternative to a series of =/or
order by district, city
;


--------------------------------------------------------------------------------------------------------
-- Aggregate functions - produce one row in result for each group specified
--                       rather than one row in the result for each row that matches the WHERE clause
--
-- The group used by the aggregate functions is determined by the GROUP BY clause
-- if no GROUP BY clause is specified, the group is the set of rows in the result
--
-- When you code a GROUP BY the only columns allowed on the SELECT are aggregate functions and columns listed in the GROUP BY
--
--     AVG(column-expression)   - arithmentic average for group of non-NULL values in expression 
--     SUM(column-expression)   - arithmentic sum for group of a non-NULL values in expression 
--     MIN(column-expression)   - lowest value for group of non-NULL values in expression 
--     MAX(column-expression)   - highest value for group of non-NULL values in expression 
--     COUNT(*)                 - number of rows in the group
--     COUNT(column-expression) - number of rows for the group of non-NULL values in expression 
--
--
-- AVG(), SUM() may only be used with numeric data types
-- MIN(), MAX() may be used with numeric, character, date, time datatypes
--
-- COUNT() is applied to rows (not columns)
--
--
-- Show average life expectancy in the world
select avg(lifeexpectancy)  -- one row in the result with the average lifeexpectancy across all countries
  from country
;
-- Show average life expectancy in the for each continent
select continent, avg(lifeexpectancy) as Avg_Life_Expectancy -- one row in the result for each unique continent the average lifeexpectancy for that continent
  from country
  group by continent   -- specify the groups we want for the aggregate funtion
;
-- Show the total population in Ohio
select sum(population) as Total_People_In_Ohio
  from city
 where district = 'Ohio'
;
-- Show the surface area of the smallest country in the world
select name, surfacearea
  from country
 order by surfacearea
 limit 1
 ;

select name, min(surfacearea)
  from country
  group by name
  order by min(surfacearea)   -- order by derived value
  limit 1
;
select name, min(surfacearea)
  from country
  group by name
  order by 2   -- order by column 2
               -- the column of the column in the select may be used in the order by when you have derived columns
  limit 1
;
-- Show The 10 largest countries (by surface area) in the world
select name, surfacearea
  from country
 order by surfacearea desc
 limit 10
 ;

-- Show the number of countries who declared independence in 1991

select count(*) as Number_Countries_Indepyear_1991
  from country
 where indepyear = 1991
;

-- We want to see the country names
-- we can't add a Group BT - it's country only has 1 indepyear
-- If want the names of the countries and the count - come back tomorrow

--select name, count(*) as Number_Countries_Indepyear_1991
--  from country
-- where indepyear = 1991
--;
--------------------------------------------------------------------------------------------------------
-- GROUP BY  - Specify the group to which the aggregate functions apply
--
--      GROUP BY column-expression
--
-- When using a GROUP BY the SELECT is limited ot aggreggate functions or columns in the GROUP BY
--
--

-- Show the number of countries where each language is spoken, order show them from most countries to least
--     number of - count(*)
--     each whatever - group by whatever
select language, count(*)
  from countrylanguage
  group by language
  order by Count(*) desc
;
-- Show the average life expectancy of each continent ordered from highest to lowest
select continent, avg(lifeexpectancy)
  from country
 group by continent
 order by 2 desc   -- order by column 2 in the select
 ;


-- Exclude Antarctica from consideration for average life expectancy
select continent, avg(lifeexpectancy)
  from country
 where lifeexpectancy is not null
 group by continent
 order by 2 desc   -- order by column 2 in the select
 ;

-- What is the sum of the population of cities in each state in the USA ordered by state name
select district, sum(population) as population
  from city
 where countrycode = 'USA'
 group by district
 order by district 
;

-- What is the average population of cities in each state in the USA ordered by state name
select district, avg(population) as avg_population
  from city
 where countrycode = 'USA'
 group by district
 order by district 
;

--------------------------------------------------------------------------------------------------------
-- SUBQUERIES - Using the result from one query (inner query) in another query (outer query)
--
-- Frequently used in a WHERE clause with an IN predicate:
--
--       WHERE column-name IN (SELECT column-name FROM some-table WHERE some-predicate)
--
-- Any WHERE predicate may be used to connect the subquery in a WHERE clause, but you must
-- be sure a single value is returned from the subquery. 
--
-- Subqueries may also be used in a SELECT as a column-specification or a FROM as a table
-- (These are advanced concepts we will discuss later, if there is time)
--
-- Show the cities under the same given government leader


-- Show countries with the same independece year


-- Show the cities cities whose country has not yet declared independence yet


--------------------------------------------------------------------------------------------------------
--
-- Additional samples
--
-- You may alias column and table names to provide more descriptive names
--
SELECT name AS CityName 
  FROM city AS cities

-- Ordering allows columns to be displayed in ascending order, or descending order (Look at Arlington)
SELECT name
     , population 
  FROM city 
 WHERE countryCode='USA' 
 ORDER BY name ASC, population DESC
;
-- Limiting results allows rows to be returned in 'limited' clusters where LIMIT says how many, 
-- and an optional OFFSET specifies number of rows to skip
SELECT name
     , population 
  FROM city 
  LIMIT 10 OFFSET 10
;