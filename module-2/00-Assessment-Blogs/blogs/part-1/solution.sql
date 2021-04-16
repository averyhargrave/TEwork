-- Select all columns from posts that are published
Select *
from posts
where published = true;

-- Select posted_by and body from comments that have a body and were created after Oct. 15, 2019
select posted_by, body
from comments
where created > '10/15/2019';
-- **** I was going to order by created as well to make the results sorted by the date, and I was going to exclude
--      comments that had a (null) body, but I didn't want to add code that the question didn't call for. ****


-- Select the name and published states from posts ordered by when they were created, earliest first
select name, published
from posts
order by created asc;

-- Select the created date and the count of all the comments created on that date
select created, count(created)
from comments
group by created;
-- **** I was going to order by created as well to make the results sorted by the date, and I was going to exclude
--      comments that had a (null) body, but I didn't want to add code that the question didn't call for. ****


-- Select the post name, comment posted_by and comment body for all posts created after Oct. 1st, 2019
select posts.name, comments.posted_by, comments.body
from posts
inner join
comments
on posts.id = comments.post_id
where posts.created > '10/01/2019';

