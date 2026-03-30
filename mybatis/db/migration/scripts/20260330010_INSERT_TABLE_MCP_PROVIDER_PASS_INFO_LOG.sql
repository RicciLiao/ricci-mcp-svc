INSERT INTO mcp_provider_pass_info_log (provider_info_id, pass_key, created_by, created_dtm, updated_by, updated_dtm, version, action_cd, action_dtm, action_by)
VALUES (1, 'dummy', 0, sysdate(), 0, sysdate(), 0, 'I', sysdate(), 0);
INSERT INTO mcp_provider_pass_info_log (provider_info_id, pass_key, created_by, created_dtm, updated_by, updated_dtm, version, action_cd, action_dtm, action_by)
VALUES (2, 'dummy', 0, sysdate(), 0, sysdate(), 0, 'I', sysdate(), 0);

-- //@UNDO
delete
from mcp_provider_pass_info_log
where id = 1;
delete
    delete
from mcp_provider_pass_info_log
where id = 2;