package cn.xuhuiqiang.stock.respository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xuhuiqiang.stock.domain.CompanyDO;
import cn.xuhuiqiang.stock.repository.CompanyRepository; 

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRespositoryTest {
	@Autowired
	private CompanyRepository companyRepository;

	@Test
	public void findById() {
		Optional<CompanyDO> result = companyRepository.findById(1L);
		//Assert.assertNotNull(result.get());
		//CompanyDO db = result.get();
		//Assert.assertEquals(1L, db.getId().longValue());
	}
}