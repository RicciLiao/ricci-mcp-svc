create table mcp_provider_status
(
    provider_info_id bigint primary key,
    status           tinyint(1)  not null comment '0: started| 1: stopped',
    created_by       bigint      not null,
    created_dtm      datetime(6) not null,
    updated_by       bigint      not null,
    updated_dtm      datetime(6) not null,
    version          datetime(6) not null
);

-- //@UNDO
drop table mcp_provider_status;