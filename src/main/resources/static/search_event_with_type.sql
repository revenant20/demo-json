select
    *
from
    apps
where
    external_data #>
    '{event,type}' = '"update"';

select
    *
from
    apps
where
    external_data ->
    'event' ? 'type';