--------------------------------------------------------------------------------------------------------
-- Basic SELECT Lecture Code
--------------------------------------------------------------------------------------------------------
-- -- indicates a comment - anything following on the line is ignored
--
-- SQL SELECT statement - retrieve values from the database (Read)
--
-- A SELECT statement is often referred to as a query
--
-- Basic syntax: (the order in which these is important/required)
--
--      SELECT   - columns to include in the result (seperate multiple column reqeusts with commas)
--      FROM     - table containing rows used in the query 
--      WHERE    - rows to include in the result - WHERE predicates are similar to Java predicate (a predicate is conditions)
--      ORDER BY - sequence of rows in the result
--                 without an ORDER BY the sequence of the rows in the result is not predictable
--                 if the sequence of teh rows in teh result matter - code an ORDER BY
--
-- WHERE predicates:
--
--        =  <>  !=  >  >=  <  <= -- not equal can be specified two ways:  <>   !=
--        IN(list-of-values)      -- alterative to a series of = OR
--        NOT IN(list-of-values)  -- alterative to a series of != AND
--        BETWEEN value AND value
--        IS NULL          -- special predicate for checking to see if column is null 
--        IS NOT NULL      -- special predicate for checking to see if column is null 
--        LIKE    (use wildcards: % means 0 to any number of any characters
--                                _ means exactly any one character
--                'word%'  - starts with test
--                '%word'  - ends with test
--                '%word%' - contains test
--
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
Select name                -- list the columns(s) you want to see in the result on the SELECT
  from country             -- specify the table with the column(s) you want on the FROM
;                          -- ; marks the end of SQL statement
select name from country;


-- Selecting the name and population of all countries
SELECT name, population    -- List the column(s) you want see in the result on the SELECT
  FROM country             -- specify the table with columns you want on the FROM
;
select name, population from country;

SELECT population, name    -- The order of the columns in the SELECT will be the order of columns in the result
  FROM country
;
-- Selecting all columns from the city table
Select *     -- * - List all the columns from the table in the order in which they are defined
  from country
;
-- SELECT ... FROM ... WHERE
-- Selecting the cities in Ohio
select name, district   -- district is the name of colunn in city table for what we call a state in USA
  from city
 where district = 'Ohio'-- In SQL you put single quotes around string not double quotes as in Java
                        -- The case of teh literal matters: 'Ohio' is diffrent tha 'OHIO' - know your data base
;

-- Selecting countries that gained independence in the year 1776
Select name, indepyear
  from country
 where indepyear = 1776   -- numeric values are NOT coded in single quotes
;
-- Selecting countries not in Asia
select name, continent
  from country
 where continent != 'Asia'  -- use <> in place of <> for not equal
;

-- Selecting countries that do not have an independence year
Select name, indepyear
  from country
 where indepyear is null       -- null represents an unknown or missing value so = and != doesn't work
;

-- Selecting countries that do have an independence year
Select name, indepyear
  from country
 where indepyear is not null   -- null represents an unknown or missing value so = and != doesn't work
;

-- Selecting countries that have a population greater than 5 million
select name, population
  from country
 where population > 5000000
;

-- SELECT ... FROM ... WHERE ... AND/OR
-- Selecting cities in Ohio and Population greater than 400,000
select name, district, population
  from city
 where district = 'Ohio' 
   and population > 400000
;

-- Selecting country names on the continent North America or South America

select name, continent
  from country
 where continent = 'North America'
    or continent = 'South America'
;

select name, continent
  from country
 where continent IN ('North America', 'South America')  -- IN can replace a serices of =/or
;

select name, continent
  from country
 where continent LIKE '%America'  -- continent ends with teh word 'America'
;
select name, continent
  from country
 where continent LIKE '%America%'  -- continent contains the word 'America'
;

-- List the countries that gained independence in the 20th Century (1900-1999)
select name, indepyear
  from country
 where indepyear >= 1900 
   and indepyear <= 1999
;

select name, indepyear
  from country
 where indepyear between 1900 and 1999  -- bewteen will select data in a inclusive range
;

-- SELECTING DATA w/arithmetic - you can code arithmetic expression on SELECT

-- Selecting the population, life expectancy, and population per area
select name, population, lifeexpectancy, population / surfacearea  -- calculated/drived column has no name
  from country
;

select name as country_name
     , population as How_Many_People_Live_There    -- Use _ bewteen parts of the AS name NOT spaces
     , lifeexpectancy as How_Long_They_Usually_Live
     , population / surfacearea as pop_per_area  -- as will assign a name to a column
from country
;


