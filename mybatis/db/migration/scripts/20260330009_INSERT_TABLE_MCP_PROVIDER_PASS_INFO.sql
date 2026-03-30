INSERT INTO mcp_provider_pass_info (provider_info_id, pass_key, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (1, 'dummy', 0, sysdate(), 0, sysdate(), 0);
INSERT INTO mcp_provider_pass_info (provider_info_id, pass_key, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (2, 'dummy', 0, sysdate(), 0, sysdate(), 0);

-- //@UNDO
delete
from mcp_provider_pass_info
where id = 1;
delete
from mcp_provider_pass_info
where id = 2;