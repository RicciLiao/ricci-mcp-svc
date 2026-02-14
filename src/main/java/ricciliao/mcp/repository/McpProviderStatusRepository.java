package ricciliao.mcp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.mcp.pojo.po.McpProviderStatusPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface McpProviderStatusRepository extends JpaRepository<McpProviderStatusPo, Long> {

    @Override
    void flush();

    @Override
    <S extends McpProviderStatusPo> S saveAndFlush(S entity);

    @Override
    <S extends McpProviderStatusPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<McpProviderStatusPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    McpProviderStatusPo getReferenceById(Long aLong);

    @Override
    <S extends McpProviderStatusPo> List<S> findAll(Example<S> example);

    @Override
    <S extends McpProviderStatusPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends McpProviderStatusPo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<McpProviderStatusPo> findAll();

    @Override
    List<McpProviderStatusPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends McpProviderStatusPo> S save(S entity);

    @Override
    Optional<McpProviderStatusPo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(McpProviderStatusPo entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends McpProviderStatusPo> entities);

    @Override
    void deleteAll();

    @Override
    List<McpProviderStatusPo> findAll(Sort sort);

    @Override
    Page<McpProviderStatusPo> findAll(Pageable pageable);

    @Override
    <S extends McpProviderStatusPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends McpProviderStatusPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends McpProviderStatusPo> long count(Example<S> example);

    @Override
    <S extends McpProviderStatusPo> boolean exists(Example<S> example);

    @Override
    <S extends McpProviderStatusPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
