--------------------------------------------------------------------------------------------------------
-- Basic SELECT Lecture Code
--------------------------------------------------------------------------------------------------------
-- -- indicates a comment - anything following on the line is ignored
--
-- SQL SELECT statement - retrieve values form the database (Read)
--
-- A SELECT statement is often referred to as a query
--
-- Basic syntax: (the order in which these are specified is important/required)
--
--      SELECT   - columns to include in the result (seperate mutiple column reqeusts with commas)
--      FROM     - table containing rows used in the query 
--      WHERE    - rows to include in the result - WHERE preicates are similar to Java predicates/conditions
--      ORDER BY - sequence of rows in the result
--                 without an ORDER BY the sequence of the rows in the result is not predictable
--                 if the sequence of teh rows in teh result matter - code an ORDER BY
--
-- WHERE predicates:
--
--        =  <>  !=  >  >=  <  <= -- <> and != both mean Not Equal
--        IN(list-of-values)      -- alternative to a series of = OR
--        NOT IN(list-of-values)  -- alternative to a series of != AND
--        BETWEEN value AND value
--        IS NULL
--        IS NOT NULL
--        LIKE    (use wildcards: % means 0 to any number of any characters
--                               'word%'  - starts with test
--                               '%word'  - ends with test
--                               '%word%'  - contains test
--                                _ means exactly any one character
--        ILIKE   (case insensivtive LIKE - Postgres extension)
--
-- predicates may be combined using AND and OR
--
-- use parentheses to make your multi-predicate condition clear

-- The DISTINCT clause on a SELECT removes duplicate values from the result
-- based on the all columns that follow
--
-- The DISTINCT ON(columns/expression) clause on a SELECT removes duplicate values from the result
-- based on the all columns/expression inside the parentheses that follow (Postgres extension)
------------------------------------------------------------------------------------------------------

-- Selecting the names for all countries
Select name              -- list the column(s) you want to see in the result on the SELECT
  from country           -- specify the table with the column(s) you want on the FROM
;                        -- ; marks the end of an SQL statement
-- Selecting the name and population of all countries
SELECT name, population  -- column(s) you want to see in the result
  FROM country           -- table with the column(s)
 ;
 select population, name -- the order of the columns in the SELECT will be the order of the columns in the result
   from country
 ;
-- Selecting all columns from the city table
select *                 -- SELECT * - lists ALL columns from the table in the order in which they are defined
 fROm city
 ;
 SELECT * FROM city;     -- this also works
-- SELECT ... FROM ... WHERE
-- Selecting the cities in Ohio
Select name, district    -- district is the name of the column in the city table for what we call a state
  from city
 where district = 'Ohio'; -- in SQL you put single quotes around strings instead of double quotes

-- Selecting countries that gained independence in the year 1776
Select name
  from country
 where indepyear = 1776   -- numerics are not coded in quotes
;
-- Selecting countries not in Asia
select name
  from country
 where continent != 'Asia' -- <> also means not equals
;
-- Selecting countries that do not have an independence year
SELECT name, indepyear
  FROM country
 WHERE indepyear IS NULL  -- null represents an unknown or missing value so = and != doesn't work
 ;

-- Selecting countries that do have an independence year
SELECT name, indepyear
  FROM country
 WHERE indepyear IS NOT NULL
 ;

-- Selecting countries that have a population greater than 5 million
select name, population
  from country
 where population > 5000000
;

-- SELECT ... FROM ... WHERE ... AND/OR
-- Selecting cities in Ohio and Population greater than 400,000
Select name, district, population 
  from city
 where district = 'Ohio'
   AND population > 400000
 ;

-- Selecting country names on the continent North America or South America
select name, continent
  from country
 where continent = 'North America' OR continent = 'South America'
 ;
 
 select name, continent
  from country
 where continent IN ('North America', 'South America')
 ;
 
 select name, continent
  from country
 where continent LIKE '%America'
 ;
 
 -- List the countries that gained independence in the 20th century (1900 - 1999)
 select name, indepyear
   from country
  where indepyear >= 1900
    and indepyear <= 1999
 ;
 
 select name, indepyear
   from country
  where indepyear between 1900 and 1999 -- between will select data in an inclusive range
 ;
 
-- SELECTING DATA w/arithmetic - you can code an arithmetic expression on SELECT
-- Selecting the population, life expectancy, and population per area
select name, population, lifeexpectancy, population / surfacearea -- a calculated column/derived column has no name
  from country
;

select name, population as how_many_people_lived_there    -- AS will overwrite a name of an existing column
           , lifeexpectancy as how_long_they_usually_live -- use _ between parts of the AS name, not spaces
           , population / surfacearea as pop_per_area     -- AS will assign a name to a column
  from country
;
