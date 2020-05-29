package cn.xuhuiqiang.stock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.xuhuiqiang.stock.domain.XueQiuCubeDO;

@Repository
public interface XueQiuCubeRepository extends CrudRepository<XueQiuCubeDO, Long>, PagingAndSortingRepository<XueQiuCubeDO, Long> {

}
