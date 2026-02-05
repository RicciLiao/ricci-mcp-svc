INSERT INTO mcp_provider_type (id, provider, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (1000, 'redis', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO mcp_provider_type (id, provider, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (1001, 'mongo', 0, sysdate(), 0, sysdate(), sysdate());

-- //@UNDO
delete
from mcp_provider_type
where id = 1000;
delete
from mcp_provider_type
where id = 1001;