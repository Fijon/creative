package cn.xuhuiqiang.stock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.xuhuiqiang.stock.domain.FundMesDO;


@Repository
public interface FundMesRepository extends CrudRepository<FundMesDO, Long>, PagingAndSortingRepository<FundMesDO, Long>{

}
