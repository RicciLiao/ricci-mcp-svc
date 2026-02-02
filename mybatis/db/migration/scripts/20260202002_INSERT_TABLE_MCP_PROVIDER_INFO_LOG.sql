INSERT INTO mcp.mcp_provider_info_log (id, consumer, store, provider, ttl_seconds, is_active, is_static, created_dtm, updated_dtm, version, action_cd, action_dtm)
VALUES (1, 'dummy', 'redis', 0, null, 1, 1, sysdate(), sysdate(), sysdate(), 'I', sysdate());
INSERT INTO mcp.mcp_provider_info_log (id, consumer, store, provider, ttl_seconds, is_active, is_static, created_dtm, updated_dtm, version, action_cd, action_dtm)
VALUES (2, 'dummy', 'mongo', 1, null, 1, 1, sysdate(), sysdate(), sysdate(), 'I', sysdate());

-- //@UNDO
delete
from mcp_provider_info_log
where consumer = 'dummy'
  and store = 'redis';
delete
from mcp_provider_info_log
where consumer = 'dummy'
  and store = 'mongo';