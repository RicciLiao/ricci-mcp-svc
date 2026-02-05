create table mcp_provider_info_log
(
    id          bigint      not null,
    consumer    varchar(25) not null,
    store       varchar(25) not null,
    provider   bigint     not null,
    ttl_seconds bigint      null,
    active     tinyint(1) not null,
    statical   tinyint(1) not null,
    created_by bigint     not null,
    created_dtm datetime(6) not null,
    updated_by bigint     not null,
    updated_dtm datetime(6) not null,
    version     datetime(6) not null,
    action_cd   varchar(1)  not null comment 'I: Insert| U: Update| D: Delete',
    action_dtm  datetime(6) not null,
    action_by  bigint     not null,
    primary key cache_provider_info_id (id, action_dtm)
);

-- //@UNDO
drop table mcp_provider_info_log;