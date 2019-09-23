package cn.xuhuiqiang.stock.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.xuhuiqiang.stock.domain.Repurchased;

public interface RepurchasedRepository extends JpaRepository<Repurchased, Long> {
	
	Repurchased findByCode(String code);
	
	@Query(value = "SELECT * FROM repurchased WHERE name like = %?1%",
		    countQuery = "SELECT count(*) FROM USERS WHERE name like = %?1%",
		    nativeQuery = true)
	List<Repurchased> findByName(String name, Pageable pageable);
	
	

}
