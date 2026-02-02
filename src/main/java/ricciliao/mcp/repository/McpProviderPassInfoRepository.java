package ricciliao.mcp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface McpProviderPassInfoRepository extends JpaRepository<McpProviderPassInfoPo, Long> {

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    void deleteAllInBatch(Iterable<McpProviderPassInfoPo> entities);

    @Override
    <S extends McpProviderPassInfoPo> List<S> findAll(Example<S> example);

    @Override
    <S extends McpProviderPassInfoPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    void flush();

    @Override
    McpProviderPassInfoPo getReferenceById(Long aLong);

    @Override
    <S extends McpProviderPassInfoPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    <S extends McpProviderPassInfoPo> S saveAndFlush(S entity);

    @Override
    List<McpProviderPassInfoPo> findAll();

    @Override
    List<McpProviderPassInfoPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends McpProviderPassInfoPo> List<S> saveAll(Iterable<S> entities);

    @Override
    long count();

    @Override
    void delete(McpProviderPassInfoPo entity);

    @Override
    void deleteAll();

    @Override
    void deleteAll(Iterable<? extends McpProviderPassInfoPo> entities);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Optional<McpProviderPassInfoPo> findById(Long aLong);

    @Override
    <S extends McpProviderPassInfoPo> S save(S entity);

    @Override
    List<McpProviderPassInfoPo> findAll(Sort sort);

    @Override
    Page<McpProviderPassInfoPo> findAll(Pageable pageable);

    @Override
    <S extends McpProviderPassInfoPo> long count(Example<S> example);

    @Override
    <S extends McpProviderPassInfoPo> boolean exists(Example<S> example);

    @Override
    <S extends McpProviderPassInfoPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends McpProviderPassInfoPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    <S extends McpProviderPassInfoPo> Optional<S> findOne(Example<S> example);
}
