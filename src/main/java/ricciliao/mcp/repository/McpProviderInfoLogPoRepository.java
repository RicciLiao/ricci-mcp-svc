package ricciliao.mcp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.mcp.pojo.po.McpProviderInfoLogPo;
import ricciliao.mcp.pojo.po.McpProviderInfoLogPoId;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface McpProviderInfoLogPoRepository extends JpaRepository<McpProviderInfoLogPo, McpProviderInfoLogPoId> {
    @Override
    void deleteAllByIdInBatch(Iterable<McpProviderInfoLogPoId> mcpProviderInfoLogPoIds);

    @Override
    void deleteAllInBatch();

    @Override
    void deleteAllInBatch(Iterable<McpProviderInfoLogPo> entities);

    @Override
    <S extends McpProviderInfoLogPo> List<S> findAll(Example<S> example);

    @Override
    <S extends McpProviderInfoLogPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    void flush();

    @Override
    McpProviderInfoLogPo getReferenceById(McpProviderInfoLogPoId mcpProviderInfoLogPoId);

    @Override
    <S extends McpProviderInfoLogPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    <S extends McpProviderInfoLogPo> S saveAndFlush(S entity);

    @Override
    List<McpProviderInfoLogPo> findAll();

    @Override
    List<McpProviderInfoLogPo> findAllById(Iterable<McpProviderInfoLogPoId> mcpProviderInfoLogPoIds);

    @Override
    <S extends McpProviderInfoLogPo> List<S> saveAll(Iterable<S> entities);

    @Override
    long count();

    @Override
    void delete(McpProviderInfoLogPo entity);

    @Override
    void deleteAll();

    @Override
    void deleteAll(Iterable<? extends McpProviderInfoLogPo> entities);

    @Override
    void deleteAllById(Iterable<? extends McpProviderInfoLogPoId> mcpProviderInfoLogPoIds);

    @Override
    void deleteById(McpProviderInfoLogPoId mcpProviderInfoLogPoId);

    @Override
    boolean existsById(McpProviderInfoLogPoId mcpProviderInfoLogPoId);

    @Override
    Optional<McpProviderInfoLogPo> findById(McpProviderInfoLogPoId mcpProviderInfoLogPoId);

    @Override
    <S extends McpProviderInfoLogPo> S save(S entity);

    @Override
    List<McpProviderInfoLogPo> findAll(Sort sort);

    @Override
    Page<McpProviderInfoLogPo> findAll(Pageable pageable);

    @Override
    <S extends McpProviderInfoLogPo> long count(Example<S> example);

    @Override
    <S extends McpProviderInfoLogPo> boolean exists(Example<S> example);

    @Override
    <S extends McpProviderInfoLogPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends McpProviderInfoLogPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    <S extends McpProviderInfoLogPo> Optional<S> findOne(Example<S> example);
}
