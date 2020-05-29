package cn.xuhuiqiang.stock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.xuhuiqiang.stock.domain.XueQiuCubeDynamicDO;
@Repository
public interface XueQiuCubeDynamicRepository extends CrudRepository<XueQiuCubeDynamicDO, Long>, PagingAndSortingRepository<XueQiuCubeDynamicDO, Long> {
	XueQiuCubeDynamicDO findTopByOrderByRebalanceIdDesc();
}
