package cn.xuhuiqiang.stock.respository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xuhuiqiang.stock.domain.Company;
import cn.xuhuiqiang.stock.repository.CompanyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRespositoryTest {
	@Autowired
	private CompanyRepository companyRepository;

	@Test
	public void findById() {
		Optional<Company> result = companyRepository.findById(1L);
		System.out.println(result.toString());
	}
}