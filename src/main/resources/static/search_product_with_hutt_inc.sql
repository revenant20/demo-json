select *
from product_connections
where external_ids ? 'HUTT_INC';

select *
from product_connections
where external_ids @> '{"HUTT_INC":"SUPER_ZAIM_140"}';