package ricciliao.mcp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.mcp.pojo.po.McpProviderPasskeyLogId;
import ricciliao.mcp.pojo.po.McpProviderPasskeyLogPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface McpProviderPasskeyLogRepository extends JpaRepository<McpProviderPasskeyLogPo, McpProviderPasskeyLogId> {

    @Override
    void deleteAllByIdInBatch(Iterable<McpProviderPasskeyLogId> mcpProviderPasskeyLogIds);

    @Override
    void deleteAllInBatch();

    @Override
    void deleteAllInBatch(Iterable<McpProviderPasskeyLogPo> entities);

    @Override
    <S extends McpProviderPasskeyLogPo> List<S> findAll(Example<S> example);

    @Override
    <S extends McpProviderPasskeyLogPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    void flush();

    @Override
    McpProviderPasskeyLogPo getReferenceById(McpProviderPasskeyLogId mcpProviderPasskeyLogId);

    @Override
    <S extends McpProviderPasskeyLogPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    <S extends McpProviderPasskeyLogPo> S saveAndFlush(S entity);

    @Override
    List<McpProviderPasskeyLogPo> findAll();

    @Override
    List<McpProviderPasskeyLogPo> findAllById(Iterable<McpProviderPasskeyLogId> mcpProviderPasskeyLogIds);

    @Override
    <S extends McpProviderPasskeyLogPo> List<S> saveAll(Iterable<S> entities);

    @Override
    long count();

    @Override
    void delete(McpProviderPasskeyLogPo entity);

    @Override
    void deleteAll();

    @Override
    void deleteAll(Iterable<? extends McpProviderPasskeyLogPo> entities);

    @Override
    void deleteAllById(Iterable<? extends McpProviderPasskeyLogId> mcpProviderPasskeyLogIds);

    @Override
    void deleteById(McpProviderPasskeyLogId mcpProviderPasskeyLogId);

    @Override
    boolean existsById(McpProviderPasskeyLogId mcpProviderPasskeyLogId);

    @Override
    Optional<McpProviderPasskeyLogPo> findById(McpProviderPasskeyLogId mcpProviderPasskeyLogId);

    @Override
    <S extends McpProviderPasskeyLogPo> S save(S entity);

    @Override
    List<McpProviderPasskeyLogPo> findAll(Sort sort);

    @Override
    Page<McpProviderPasskeyLogPo> findAll(Pageable pageable);

    @Override
    <S extends McpProviderPasskeyLogPo> long count(Example<S> example);

    @Override
    <S extends McpProviderPasskeyLogPo> boolean exists(Example<S> example);

    @Override
    <S extends McpProviderPasskeyLogPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends McpProviderPasskeyLogPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    <S extends McpProviderPasskeyLogPo> Optional<S> findOne(Example<S> example);
}
