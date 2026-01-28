package ricciliao.mcp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface McpProviderInfoPoRepository extends JpaRepository<McpProviderInfoPo, Long> {
    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    void deleteAllInBatch(Iterable<McpProviderInfoPo> entities);

    @Override
    <S extends McpProviderInfoPo> List<S> findAll(Example<S> example);

    @Override
    <S extends McpProviderInfoPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    void flush();

    @Override
    McpProviderInfoPo getReferenceById(Long aLong);

    @Override
    <S extends McpProviderInfoPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    <S extends McpProviderInfoPo> S saveAndFlush(S entity);

    @Override
    List<McpProviderInfoPo> findAll();

    @Override
    List<McpProviderInfoPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends McpProviderInfoPo> List<S> saveAll(Iterable<S> entities);

    @Override
    long count();

    @Override
    void delete(McpProviderInfoPo entity);

    @Override
    void deleteAll();

    @Override
    void deleteAll(Iterable<? extends McpProviderInfoPo> entities);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Optional<McpProviderInfoPo> findById(Long aLong);

    @Override
    <S extends McpProviderInfoPo> S save(S entity);

    @Override
    List<McpProviderInfoPo> findAll(Sort sort);

    @Override
    Page<McpProviderInfoPo> findAll(Pageable pageable);

    @Override
    <S extends McpProviderInfoPo> long count(Example<S> example);

    @Override
    <S extends McpProviderInfoPo> boolean exists(Example<S> example);

    @Override
    <S extends McpProviderInfoPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends McpProviderInfoPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    <S extends McpProviderInfoPo> Optional<S> findOne(Example<S> example);

    @Query("select new McpProviderInfoPo(po, passkey) " +
            "from McpProviderInfoPo po " +
            "left join McpProviderPasskeyPo passkey on passkey.providerInfoId = po.id " +
            "where po.isActive = true ")
    List<McpProviderInfoPo> initialize();
}
