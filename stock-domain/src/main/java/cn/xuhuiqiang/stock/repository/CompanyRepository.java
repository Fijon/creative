package cn.xuhuiqiang.stock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.xuhuiqiang.stock.domain.CompanyDO;
 
@Repository
public interface CompanyRepository extends CrudRepository<CompanyDO, Long>, PagingAndSortingRepository<CompanyDO, Long>{

}
