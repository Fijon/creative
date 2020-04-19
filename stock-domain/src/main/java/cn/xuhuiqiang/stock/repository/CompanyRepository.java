package cn.xuhuiqiang.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.xuhuiqiang.stock.domain.CompanyDO;
 
@Repository
public interface CompanyRepository extends JpaRepository<CompanyDO, Long>{

}
