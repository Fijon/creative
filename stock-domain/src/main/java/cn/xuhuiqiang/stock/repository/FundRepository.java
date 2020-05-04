package cn.xuhuiqiang.stock.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.xuhuiqiang.stock.domain.FundShareDO;

@Repository
public interface FundRepository extends CrudRepository<FundShareDO, Long>, PagingAndSortingRepository<FundShareDO, Long>{
	
	List<FundShareDO> findByFundCode(String fundCode);
	
	Long countByFundCodeAndBizDate(String fundCode, Date bizDate);


}
