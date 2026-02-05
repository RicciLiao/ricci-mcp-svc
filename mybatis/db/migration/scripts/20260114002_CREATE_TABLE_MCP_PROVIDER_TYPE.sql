create table mcp_provider_type
(
    id          bigint auto_increment primary key,
    provider    varchar(25) not null,
    created_by  bigint      not null,
    created_dtm datetime(6) not null,
    updated_by  bigint      not null,
    updated_dtm datetime(6) not null,
    version     datetime(6) not null
) auto_increment = 1000;

-- //@UNDO
drop table mcp_provider_type;