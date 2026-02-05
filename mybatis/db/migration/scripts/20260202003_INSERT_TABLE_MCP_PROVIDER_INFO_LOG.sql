INSERT INTO mcp_provider_info_log (id, consumer, store, provider, ttl_seconds, active, statical, created_by, created_dtm, updated_by, updated_dtm, version, action_cd, action_dtm, action_by)
VALUES (1, 'dummy', 'redis', 1000, 300, 1, 0, 0, sysdate(), 0, sysdate(), sysdate(), 'I', sysdate(), 0);
INSERT INTO mcp_provider_info_log (id, consumer, store, provider, ttl_seconds, active, statical, created_by, created_dtm, updated_by, updated_dtm, version, action_cd, action_dtm, action_by)
VALUES (2, 'dummy', 'mongo', 1001, 300, 1, 0, 0, sysdate(), 0, sysdate(), sysdate(), 'I', sysdate(), 0);

-- //@UNDO
delete
from mcp_provider_info_log
where consumer = 'dummy'
  and store = 'redis';
delete
from mcp_provider_info_log
where consumer = 'dummy'
  and store = 'mongo';