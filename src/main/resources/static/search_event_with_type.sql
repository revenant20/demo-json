select *
from apps
where external_data #> '{event,type}' = '"update"'::jsonb;

select external_data
from apps
where external_data -> 'event' ? 'type';