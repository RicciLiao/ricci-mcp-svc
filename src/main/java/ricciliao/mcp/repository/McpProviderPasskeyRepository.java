package ricciliao.mcp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.mcp.pojo.po.McpProviderPasskeyPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface McpProviderPasskeyRepository extends JpaRepository<McpProviderPasskeyPo, Long> {

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    void deleteAllInBatch(Iterable<McpProviderPasskeyPo> entities);

    @Override
    <S extends McpProviderPasskeyPo> List<S> findAll(Example<S> example);

    @Override
    <S extends McpProviderPasskeyPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    void flush();

    @Override
    McpProviderPasskeyPo getReferenceById(Long aLong);

    @Override
    <S extends McpProviderPasskeyPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    <S extends McpProviderPasskeyPo> S saveAndFlush(S entity);

    @Override
    List<McpProviderPasskeyPo> findAll();

    @Override
    List<McpProviderPasskeyPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends McpProviderPasskeyPo> List<S> saveAll(Iterable<S> entities);

    @Override
    long count();

    @Override
    void delete(McpProviderPasskeyPo entity);

    @Override
    void deleteAll();

    @Override
    void deleteAll(Iterable<? extends McpProviderPasskeyPo> entities);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Optional<McpProviderPasskeyPo> findById(Long aLong);

    @Override
    <S extends McpProviderPasskeyPo> S save(S entity);

    @Override
    List<McpProviderPasskeyPo> findAll(Sort sort);

    @Override
    Page<McpProviderPasskeyPo> findAll(Pageable pageable);

    @Override
    <S extends McpProviderPasskeyPo> long count(Example<S> example);

    @Override
    <S extends McpProviderPasskeyPo> boolean exists(Example<S> example);

    @Override
    <S extends McpProviderPasskeyPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends McpProviderPasskeyPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    <S extends McpProviderPasskeyPo> Optional<S> findOne(Example<S> example);
}
