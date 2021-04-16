---------------------------------------------------------------------------------------------------------------------------------------
-- Referential Integrity - Enforcement of the Parent-Dependent relatiohnship
--                         ie. Ensure that every foreign key value has a matching primary key
--
-- Constraints are rules regarding the data that must be complied with:
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
begin transaction; -- start a Unit Of Work - in case something goes wrong, we can undo (rollback) and start over

-- the safest (fewest opportunities for errors) format is to include the column-list in the INSERT
insert into countrylanguage                     -- add a row to the countrylanguage table
(countrycode, language, isofficial, percentage) -- list of the columns we're providing values for
values('USA', 'Klingon', false, 69)             -- values for the columns in the order of the list above
;

--insert into countrylanguage                     -- add a row to the countrylanguage table
--(isofficial, percentage, language, countrycode) -- list of the columns we're providing values for
--values(false, 69, 'Klingon', 'USA')             -- values for the columns in the order of the list above
--;

--insert into countrylanguage                     -- add a row to the countrylanguage table
--values('USA', 'Klingon', false, 69)             -- values for the columns in the order of the list above
--; -- you can do it without adding the list of columns but they must be in the same order as in the table

-- optionally - we do a select to make sure the insert worked
-- since we are doing a rollback, we cannot look at the table after we run this
select * from countrylanguage where countrycode = 'USA';

rollback; -- undo changes in this UOW until we are sure they were done correctly
-- 2. Add Klingon as a spoken language in Great Britain
begin transaction;

insert into countrylanguage
(countrycode, language, isofficial, percentage)
values('GBR', 'Klingon', true, 50)
;
select * from countrylanguage where countrycode = 'GBR';

rollback;

-- UPDATE

-- 1. Update the capital of the USA to Houston
begin transaction;

update country
   set capital = (select id from city where name = 'Houston') -- look up id for Houston in city table and use it to set the capital
 where code = 'USA' 
;

select * from country where code = 'USA';

rollback;

-- 2. Update the capital of the USA to Washington DC and the head of state
begin transaction;

update country
   set capital = (select id from city where name = 'Washington'), -- code a column name when there is another column to update
       headofstate = 'Luke'                                       -- do not repeat the set, just code the column-name =
 where code = 'USA'
;
 
select * from country where code = 'USA';
 
rollback;

-- DELETE

-- 1. Delete English as a spoken language in the USA
begin transaction;
delete from countrylanguage
where countrycode = 'USA'
  and language = 'English'
;
select * from countrylanguage where countrycode = 'USA';

rollback;

-- 2. Delete all occurrences of the Klingon language 


-- REFERENTIAL INTEGRITY

-- 1. Try just adding Elvish to the country language table.

-- 2. Try inserting English as a spoken language in the country ZZZ. What happens?

-- 3. Try deleting the country USA. What happens?


-- CONSTRAINTS

-- 1. Try inserting English as a spoken language in the USA

-- 2. Try again. What happens?

-- 3. Let's relocate the USA to the continent - 'Outer Space'


-- How to view all of the constraints

SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
SELECT * FROM INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE
SELECT * FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS


-- TRANSACTIONS

-- 1. Try deleting all of the rows from the country language table and roll it back.

-- 2. Try updating all of the cities to be in the USA and roll it back
