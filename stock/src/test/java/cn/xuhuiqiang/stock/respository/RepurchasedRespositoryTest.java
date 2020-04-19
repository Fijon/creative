package cn.xuhuiqiang.stock.respository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xuhuiqiang.stock.domain.RepurchasedDO;
import cn.xuhuiqiang.stock.repository.RepurchasedRepository; 

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepurchasedRespositoryTest {
	@Autowired
	private RepurchasedRepository repurchasedRepository;

	@Test
	public void findById() {
		Optional<RepurchasedDO> result = repurchasedRepository.findById(1L);
		Assert.assertNotNull(result.get());
		RepurchasedDO db = result.get();
		Assert.assertEquals(1L, db.getId().longValue());
	}
}