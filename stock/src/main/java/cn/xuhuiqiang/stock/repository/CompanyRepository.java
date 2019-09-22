package cn.xuhuiqiang.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xuhuiqiang.stock.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
