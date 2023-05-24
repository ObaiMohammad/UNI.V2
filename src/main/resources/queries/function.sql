CREATE or replace TRIGGER update_user_task_updated_on
    BEFORE insert
    ON
        users
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_on_users();

CREATE or replace FUNCTION update_updated_on_users()
    RETURNS TRIGGER
    language plpgsql
    AS $$
BEGIN
    NEW.guid = uuid_generate_v4();
RETURN NEW;
END;
$$ ;
-------

create or replace  FUNCTION get_enrolled_students (course_id int)
RETURNS  json
LANGUAGE plpgsql
As $$
DECLARE c_id INTEGER;
begin
    select course_id into c_id;
     return (select COALESCE(array_to_json(array_agg(row_to_json(t)) ),'[]'::json)
    from (
   select u.id,u.first_name,u.last_name,u.email,u.role,u.guid,u.created_at,u.updated_at
     from course_student cs join users u on u.id = cs.student_id
     where cs.course_id = c_id
    ) t);
end;
$$;

select get_enrolled_students(14);
---------------

create or replace  FUNCTION get_enrolled_students (course_id int)
    RETURNS  json
    LANGUAGE plpgsql
As $$
DECLARE c_id INTEGER;
DECLARE   d json;
begin
    select course_id into c_id;
    select (select COALESCE(array_to_json(array_agg(row_to_json(t)) ),'[]'::json)
            from (
                     select u.id,u.first_name,u.last_name,u.email,u.role,u.guid,u.created_at,u.updated_at
                     from course_student cs join users u on u.id = cs.student_id
                     where cs.course_id = c_id
                 ) t) into d;
    return d;
end;
$$;
---------

create or replace  FUNCTION get_enrolled_courses (student_id int)
    RETURNS  varchar
    LANGUAGE plpgsql
As $$
DECLARE s_id INTEGER;
begin
    select student_id into s_id;
    return (select COALESCE(array_to_json(array_agg(row_to_json(t)) ),'[]'::json)
            from (
                     select c.id,c.title,c.credits,c.updated_at,c.created_at
                     from course_student cs join courses c on c.id = cs.course_id
                     where cs.student_id = s_id
                 ) t);
end;
$$;

select* from get_enrolled_courses( 18);



insert into course_student (student_id, course_id) values (22,15);


drop function get_enrolled_courses;
drop function get_enrolled_students;

create or replace FUNCTION get_students (course_id int)
returns json
language plpgsql
as $$
    DECLARE c_id int;
    begin

    end;
    $$;

select json_agg(row_to_json(t))
from (select u.id,u.first_name,u.last_name,u.email,u.guid,u.role,u.created_at,u.updated_at
from course_student
    join users u on u.id = course_student.student_id
where course_id = 14)t;

