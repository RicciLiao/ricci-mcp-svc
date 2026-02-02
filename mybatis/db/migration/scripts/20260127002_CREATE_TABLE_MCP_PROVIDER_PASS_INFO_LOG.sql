create table mcp_provider_pass_info_log
(
    provider_info_id bigint      not null,
    pass_key         varchar(16) not null,
    created_dtm      datetime(6) not null,
    updated_dtm      datetime(6) not null,
    version          datetime(6) not null,
    action_cd        varchar(1)  not null comment 'I: Insert| U: Update| D: Delete',
    action_dtm       datetime(6) not null,
    primary key mcp_provider_pass_info_log_id (provider_info_id, action_dtm)
);

-- //@UNDO
drop table mcp_provider_pass_info_log;