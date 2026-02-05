create table mcp_provider_info
(
    id          bigint auto_increment primary key,
    consumer    varchar(25) not null,
    store       varchar(25) not null,
    provider   bigint     not null comment 'reference to mcp_provider_type',
    ttl_seconds bigint      null,
    active     tinyint(1) not null,
    statical   tinyint(1) not null,
    created_by bigint     not null,
    created_dtm datetime(6) not null,
    updated_by bigint     not null,
    updated_dtm datetime(6) not null,
    version     datetime(6) not null,
    constraint cache_provider_info_uk
        unique (consumer, store),
    constraint cache_provider_info_statical_check
        check ((statical <> 0) or (ttl_seconds is not null))
) auto_increment = 1000;

-- //@UNDO
drop table mcp_provider_info;