select *
from app
where external_data #> '{event,type}' = '"update"'::jsonb;