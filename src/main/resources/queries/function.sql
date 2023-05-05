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