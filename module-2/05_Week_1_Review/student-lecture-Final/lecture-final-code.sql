---------------------------------------------------------------------------------------------------------------------------------------
-- Referential Integrity - Enforcement of the Parent-Dependent relatiohnship
--                         ie. Ensure that every foreign key value has a matching primary key
--
-- Constraints are rules regarding the data that must be complied with when INSERTing and UPDATing:
--
--  NOT NULL - Column must have a value
--
--  UNIQUE   - Column value must be unique with the table 
--
--  PRIMARY KEY - Column is part of the unique identifier for a row in the table
--                UNIQUE and NOT NULL are implied
--
--  FOREIGN KEY - Column is part of a foreign key for the table.  
--                Value must have a matching value in the primary key of the parent table
--                Establishes the parent-dependent relationship
--
--  CHECK  -  Specifies acceptable values for a column - any simple WHERE predicate is allowed
--
--  DEFAULT - Specify a default value for column if no value is supplied on INSER
---------------------------------------------------------------------------------------------------------------------------------------
-- Unit Of Work (UOW) - A recoverable sequence of operations within an application process
--                      all steps to have a completed piece of work have been completed
-- 
-- BEGIN TRANSACTION - Mark the start of a unit of work
-- 
-- COMMIT - End a unit of work and save changes - automatically done if no errors
-- 
-- ROLLBACK - End a unit of work and undo changes - automatically done if errors
---------------------------------------------------------------------------------------------------------------------------------------
--  INSERT - add a row to a table
--
--  Format 1: INSERT INTO table-name 
--            (column-list)           -- must contain all non-null columns
--            VALUES(value-list)      -- order of values must match order of columns specified
--
--
--  Format 2: INSERT INTO table-name 
--            VALUES(value-list)      -- order of values must match order of columns as defined in the table
--
--
--  Format 3: INSERT INTO table-name 
--            (SELECT statement)      -- order of columns in the SELECT must match the order of columns as defined in the table
--                                    -- () are optional
--                                            
---------------------------------------------------------------------------------------------------------------------------------------
--  UPDATE - change data in a table
--
--  UPDATE table-name
--  SET column = new-value
--  WHERE condition            -- scope of update - if omitted, all rows are updated                                         
---------------------------------------------------------------------------------------------------------------------------------------
--  DELETE - Remove rows from a table
--
--  DELETE FROM table-name
--  WHERE condition            -- scope of delete - if omitted, all rows are deleted                                        
---------------------------------------------------------------------------------------------------------------------------------------
-- INSERT

-- 1. Add Klingon as a spoken language in the USA


Begin transaction;  -- start a unit of work - in case something goes wrong, we undo/rollback and start over

-- The safest (fewer opportunty for errors) format, is to include the column-list in the INSERT

insert into countrylanguage                     -- add a row to the country language table
(countrycode, language, isofficial, percentage) -- list of columns we are providing values for - all non-null columns  
values('USA', 'Klingon', true, 16)              -- values for the columns in the order of list above
;

--insert into countrylanguage                     -- add a row to the country language table
--(isofficial, countrycode, percentage, language) -- list of columns we are providing values for - all non-null columns  
--values(true, 'USA', 16, 'Klingon')               -- values for the columns in the order of list above--
--;

--insert into countrylanguage             -- add a row to the country language table
--values('USA', 'Klingon', true, 16)      -- values for the columns in the order of definition for the table
;
-- optionally - we do a select to make sure insert worked
-- since we are doing  a tollback we cannot look at the table after we run to see if the insert work
select * from countrylanguage where countrycode = 'USA';

Rollback;  -- undo any changes in this unit of work until we are sure they were done correctly

-- 2. Add Klingon as a spoken language in Great Britain

begin transaction;
insert into countrylanguage                     -- add a row to the country language table
(countrycode, language, isofficial, percentage) -- list of columns we are providing values for - all non-null columns  
values('GBR', 'Klingon', true, 36)              -- values for the columns in the order of list above
;
select * from countrylanguage where language = 'Klingon';

rollback;

-- UPDATE

-- 1. Update the capital of the USA to Houston
begin transaction;

update country
   set capital = (select id from city where name = 'Houston')  -- look up the id for Houston in the city table abd use to set capital
 where code = 'USA'   -- limit the update to just rows with the countrycode USA
;

select * from country where code = 'USA'
;

rollback;

-- 2. Update the capital of the USA to Washington DC and the head of state
begin transaction;

update country
   set capital = (Select id from city where name = 'Washington'),   -- code a comma when there is another column to update
       headofstate = 'Luke'                                         -- just code column-name = new-vale -- do not repeat SET
  where code = 'USA'
;
select * from country where capital =  (Select id from city where name = 'Washington')
;
rollback;

-- DELETE

-- 1. Delete English as a spoken language in the USA
begin transaction;
delete from countrylanguage
 where countrycode = (select code from country where name = 'United States')
   and language = 'English'
;
select * from countrylanguage where countrycode = (select code from country where name = 'United States');

rollback;

-- 2. Delete all occurrences of the Klingon language 
begin transaction;
delete from countrylanguage where language = 'Klingon'; 
rollback;

-- REFERENTIAL INTEGRITY

-- 1. Try just adding Elvish to the country language table.
--   in order to add a language need to know the country, isOfficial, percentage because they are all required (not null)
--   we can;t just add just a language without everything else

--begin transaction;
--insert into countrylanguage                     -- add a row to the country language table
--(countrycode, language, isofficial, percentage) -- list of columns we are providing values for - all non-null columns  
--values(???, 'Elvish', ???, ??)              -- values for the columns in the order of list above
--;
--select * from countrylanguage where language = 'Elvish';
--rollback;

-- 2. Try inserting English as a spoken language in the country ZZZ. What happens?
--
--    countrylanguage table is a dependent of country table 
--         it's foreign key must match a primary key value that already exists in the country table
--              countrycode value in countrylanguage must match an existing code value in country table

--    When we try to insert a language for  countrycode that is NOT in the country table
--         we get a foreign key contraint violation error
--
--    Be sure any value you specify as a forgeign key value has a match to primary key in the parent
--
--    Either look at the data in the parent table or insert parent rows before dependent rows
--
--   In this case if the country code was for a contry that did not exist in country
--      add the country to the country table first

begin transaction;

-- This insert will fail because countrycode 'ZZZ' is not an existing promary key value in country table

insert into countrylanguage                     -- add a row to the country language table
(countrycode, language, isofficial, percentage) -- list of columns we are providing values for - all non-null columns  
values('ZZZ', 'English', false, 10)              -- values for the columns in the order of list above
;
select * from countrylanguage where language = 'English';

rollback;
-- 3. Try deleting the country USA. What happens?
--    
--  The country table has two dependent tables - city and countrylanguage  
--
--  When deleting a row from a parent table it may be retristed 
--  so that the row may only be deleted it has no dependent rows
--   (ie. no foreign matches to the primary key of the row) 
--
--  You must delete all dependents rows before parents if delete of parent row is restricted by Referential Integrity
--
begin transaction;

-- this delete will fail if there are any entries in the dependent tables with a countrycode of 'USA'
-- (this is why #8 in the exercise should fail)

delete from country where code = 'USA';

rollback;

-- CONSTRAINTS

-- 1. Try inserting English as a spoken language in the USA
begin transaction;

-- This fails with a unique violation due to there already entry for 'USA' and 'English' in the countrylanguage table

insert into countrylanguage                     -- add a row to the country language table
(countrycode, language, isofficial, percentage) -- list of columns we are providing values for - all non-null columns  
values('USA', 'English', false, 90)              -- values for the columns in the order of list above
;

rollback;
-- 2. Try again. What happens?

-- 3. Let's relocate the USA to the continent - 'Outer Space'
begin transaction;
--
-- This update will fail due to 'Outer Space' not being an allowable continent value
-- There is CHECK constraint on continent that limits teh values

update country
   set continent = 'Outer Space'
 where code = 'USA'
 ;

rollback;

-- Constraints are stored in database tables - postgreSQL tables
--
-- How to view all of the constraints on all tables

SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS;
SELECT * FROM INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE;
SELECT * FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS;


-- TRANSACTIONS

-- 1. Try deleting all of the rows from the country language table and roll it back.

-- 2. Try updating all of the cities to be in the USA and roll it back
