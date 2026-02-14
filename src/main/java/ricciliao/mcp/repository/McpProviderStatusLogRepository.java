package ricciliao.mcp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.mcp.pojo.po.McpProviderStatusLogId;
import ricciliao.mcp.pojo.po.McpProviderStatusLogPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface McpProviderStatusLogRepository extends JpaRepository<McpProviderStatusLogPo, McpProviderStatusLogId> {

    @Override
    void flush();

    @Override
    <S extends McpProviderStatusLogPo> S saveAndFlush(S entity);

    @Override
    <S extends McpProviderStatusLogPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<McpProviderStatusLogPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<McpProviderStatusLogId> mcpProviderStatusLogIds);

    @Override
    void deleteAllInBatch();

    @Override
    McpProviderStatusLogPo getReferenceById(McpProviderStatusLogId mcpProviderStatusLogId);

    @Override
    <S extends McpProviderStatusLogPo> List<S> findAll(Example<S> example);

    @Override
    <S extends McpProviderStatusLogPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends McpProviderStatusLogPo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<McpProviderStatusLogPo> findAll();

    @Override
    List<McpProviderStatusLogPo> findAllById(Iterable<McpProviderStatusLogId> mcpProviderStatusLogIds);

    @Override
    <S extends McpProviderStatusLogPo> S save(S entity);

    @Override
    Optional<McpProviderStatusLogPo> findById(McpProviderStatusLogId mcpProviderStatusLogId);

    @Override
    boolean existsById(McpProviderStatusLogId mcpProviderStatusLogId);

    @Override
    long count();

    @Override
    void deleteById(McpProviderStatusLogId mcpProviderStatusLogId);

    @Override
    void delete(McpProviderStatusLogPo entity);

    @Override
    void deleteAllById(Iterable<? extends McpProviderStatusLogId> mcpProviderStatusLogIds);

    @Override
    void deleteAll(Iterable<? extends McpProviderStatusLogPo> entities);

    @Override
    void deleteAll();

    @Override
    List<McpProviderStatusLogPo> findAll(Sort sort);

    @Override
    Page<McpProviderStatusLogPo> findAll(Pageable pageable);

    @Override
    <S extends McpProviderStatusLogPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends McpProviderStatusLogPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends McpProviderStatusLogPo> long count(Example<S> example);

    @Override
    <S extends McpProviderStatusLogPo> boolean exists(Example<S> example);

    @Override
    <S extends McpProviderStatusLogPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
