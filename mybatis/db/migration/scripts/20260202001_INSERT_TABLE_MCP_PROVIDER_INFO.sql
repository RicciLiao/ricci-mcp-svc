INSERT INTO mcp.mcp_provider_info (id, consumer, store, provider, ttl_seconds, is_active, is_static, created_dtm, updated_dtm, version)
VALUES (1, 'dummy', 'redis', 0, null, 1, 1, sysdate(), sysdate(), sysdate());
INSERT INTO mcp.mcp_provider_info (id, consumer, store, provider, ttl_seconds, is_active, is_static, created_dtm, updated_dtm, version)
VALUES (2, 'dummy', 'mongo', 1, null, 1, 1, sysdate(), sysdate(), sysdate());

-- //@UNDO
delete
from mcp_provider_info
where consumer = 'dummy'
  and store = 'redis';
delete
from mcp_provider_info
where consumer = 'dummy'
  and store = 'mongo';