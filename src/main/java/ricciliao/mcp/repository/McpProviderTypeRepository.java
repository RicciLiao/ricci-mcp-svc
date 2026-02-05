package ricciliao.mcp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.mcp.pojo.po.McpProviderTypePo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface McpProviderTypeRepository extends JpaRepository<McpProviderTypePo, Long> {

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    void deleteAllInBatch(Iterable<McpProviderTypePo> entities);

    @Override
    <S extends McpProviderTypePo> List<S> findAll(Example<S> example);

    @Override
    <S extends McpProviderTypePo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    void flush();

    @Override
    McpProviderTypePo getReferenceById(Long aLong);

    @Override
    <S extends McpProviderTypePo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    <S extends McpProviderTypePo> S saveAndFlush(S entity);

    @Override
    List<McpProviderTypePo> findAll();

    @Override
    List<McpProviderTypePo> findAllById(Iterable<Long> longs);

    @Override
    <S extends McpProviderTypePo> List<S> saveAll(Iterable<S> entities);

    @Override
    long count();

    @Override
    void delete(McpProviderTypePo entity);

    @Override
    void deleteAll();

    @Override
    void deleteAll(Iterable<? extends McpProviderTypePo> entities);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Optional<McpProviderTypePo> findById(Long aLong);

    @Override
    <S extends McpProviderTypePo> S save(S entity);

    @Override
    List<McpProviderTypePo> findAll(Sort sort);

    @Override
    Page<McpProviderTypePo> findAll(Pageable pageable);

    @Override
    <S extends McpProviderTypePo> long count(Example<S> example);

    @Override
    <S extends McpProviderTypePo> boolean exists(Example<S> example);

    @Override
    <S extends McpProviderTypePo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends McpProviderTypePo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    <S extends McpProviderTypePo> Optional<S> findOne(Example<S> example);
}
