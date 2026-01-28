create table mcp_provider_info
(
    id          bigint auto_increment primary key,
    consumer    varchar(25) not null,
    store       varchar(25) not null,
    provider    bigint      not null comment '0: Redis| 1. MongoDB',
    ttl_seconds bigint      null,
    is_active   tinyint(1)  not null,
    is_static   tinyint(1)  not null,
    created_dtm datetime(6) not null,
    updated_dtm datetime(6) not null,
    version     datetime(6) not null,
    constraint cache_provider_info_uk
        unique (consumer, store)
) auto_increment = 1000;

-- //@UNDO
drop table mcp_provider_info;