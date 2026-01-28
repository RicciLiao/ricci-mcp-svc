create table mcp_provider_passkey
(
    provider_info_id bigint primary key,
    pass_key         varchar(16) not null,
    created_dtm      datetime(6) not null,
    updated_dtm      datetime(6) not null,
    version          datetime(6) not null
) auto_increment = 1000;

-- //@UNDO
drop table mcp_provider_passkey;