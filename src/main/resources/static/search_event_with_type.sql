select *
from app
where external_data #> '{event,type}' = '"update"'::jsonb;

select external_data
from app
where external_data -> 'event' ? 'type';