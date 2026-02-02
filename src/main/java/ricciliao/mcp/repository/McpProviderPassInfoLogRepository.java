package ricciliao.mcp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.mcp.pojo.po.McpProviderPassInfoLogPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoLogPoId;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface McpProviderPassInfoLogRepository extends JpaRepository<McpProviderPassInfoLogPo, McpProviderPassInfoLogPoId> {

    @Override
    void deleteAllByIdInBatch(Iterable<McpProviderPassInfoLogPoId> mcpProviderPasskeyLogIds);

    @Override
    void deleteAllInBatch();

    @Override
    void deleteAllInBatch(Iterable<McpProviderPassInfoLogPo> entities);

    @Override
    <S extends McpProviderPassInfoLogPo> List<S> findAll(Example<S> example);

    @Override
    <S extends McpProviderPassInfoLogPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    void flush();

    @Override
    McpProviderPassInfoLogPo getReferenceById(McpProviderPassInfoLogPoId mcpProviderPasskeyLogId);

    @Override
    <S extends McpProviderPassInfoLogPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    <S extends McpProviderPassInfoLogPo> S saveAndFlush(S entity);

    @Override
    List<McpProviderPassInfoLogPo> findAll();

    @Override
    List<McpProviderPassInfoLogPo> findAllById(Iterable<McpProviderPassInfoLogPoId> mcpProviderPasskeyLogIds);

    @Override
    <S extends McpProviderPassInfoLogPo> List<S> saveAll(Iterable<S> entities);

    @Override
    long count();

    @Override
    void delete(McpProviderPassInfoLogPo entity);

    @Override
    void deleteAll();

    @Override
    void deleteAll(Iterable<? extends McpProviderPassInfoLogPo> entities);

    @Override
    void deleteAllById(Iterable<? extends McpProviderPassInfoLogPoId> mcpProviderPasskeyLogIds);

    @Override
    void deleteById(McpProviderPassInfoLogPoId mcpProviderPasskeyLogId);

    @Override
    boolean existsById(McpProviderPassInfoLogPoId mcpProviderPasskeyLogId);

    @Override
    Optional<McpProviderPassInfoLogPo> findById(McpProviderPassInfoLogPoId mcpProviderPasskeyLogId);

    @Override
    <S extends McpProviderPassInfoLogPo> S save(S entity);

    @Override
    List<McpProviderPassInfoLogPo> findAll(Sort sort);

    @Override
    Page<McpProviderPassInfoLogPo> findAll(Pageable pageable);

    @Override
    <S extends McpProviderPassInfoLogPo> long count(Example<S> example);

    @Override
    <S extends McpProviderPassInfoLogPo> boolean exists(Example<S> example);

    @Override
    <S extends McpProviderPassInfoLogPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends McpProviderPassInfoLogPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    <S extends McpProviderPassInfoLogPo> Optional<S> findOne(Example<S> example);
}
