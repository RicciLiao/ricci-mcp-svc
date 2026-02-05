INSERT INTO mcp_provider_info (id, consumer, store, provider, ttl_seconds, active, statical, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (1, 'dummy', 'redis', 1000, 300, 1, 0, 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO mcp_provider_info (id, consumer, store, provider, ttl_seconds, active, statical, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (2, 'dummy', 'mongo', 1001, 300, 1, 0, 0, sysdate(), 0, sysdate(), sysdate());

-- //@UNDO
delete
from mcp_provider_info
where consumer = 'dummy'
  and store = 'redis';
delete
from mcp_provider_info
where consumer = 'dummy'
  and store = 'mongo';