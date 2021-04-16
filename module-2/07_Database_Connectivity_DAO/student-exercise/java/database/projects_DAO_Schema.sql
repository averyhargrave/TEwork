begin transaction;

delete employee_id from project_employee
where employee_id = 5,
and project_id = 3
;

rollback;