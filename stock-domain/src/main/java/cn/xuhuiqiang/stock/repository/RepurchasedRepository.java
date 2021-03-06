package cn.xuhuiqiang.stock.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.xuhuiqiang.stock.domain.RepurchasedDO;

@Repository
public interface RepurchasedRepository
		extends CrudRepository<RepurchasedDO, Long>, PagingAndSortingRepository<RepurchasedDO, Long> {

	RepurchasedDO findByCode(String code);

	@Query(value = "SELECT * FROM repurchased WHERE name like = %?1%", countQuery = "SELECT count(*) FROM USERS WHERE name like = %?1%", nativeQuery = true)
	List<RepurchasedDO> findByName(String name, Pageable pageable);

}
