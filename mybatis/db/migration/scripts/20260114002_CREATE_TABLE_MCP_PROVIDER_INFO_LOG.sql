create table mcp_provider_info_log
(
    id          bigint      not null,
    consumer    varchar(25) not null,
    store       varchar(25) not null,
    provider    bigint      not null comment '0: Redis| 1. MongoDB',
    ttl_seconds bigint      null,
    is_active   tinyint(1)  not null,
    is_static   tinyint(1)  not null,
    created_dtm datetime(6) not null,
    updated_dtm datetime(6) not null,
    action_cd   varchar(1)  not null comment 'I: Insert| U: Update| D: Delete',
    action_dtm  datetime(6) not null,
    primary key cache_provider_info_id (id, action_dtm)
) auto_increment = 1000;

-- //@UNDO
drop table mcp_provider_info_log;