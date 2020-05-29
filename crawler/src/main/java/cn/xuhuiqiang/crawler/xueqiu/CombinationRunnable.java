/**
 * 
 */
package cn.xuhuiqiang.crawler.xueqiu;

import java.io.File;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.xuhuiqiang.crawler.CrawlerRunnable;
import cn.xuhuiqiang.stock.domain.XueQiuCubeDO;
import cn.xuhuiqiang.stock.repository.XueQiuCubeRepository;
import us.codecraft.webmagic.Page;

/** 
 * @ClassName: CombinationRunnable 
 * @Description: 雪球组合信息爬取
 * @author huiqiangxu@126.com
 * @date 2020年5月6日 下午11:46:40 
 *  
 */
@Component
public class CombinationRunnable extends CrawlerRunnable {

	@Autowired
	private XueQiuCubeRepository xueQiuCubeRepository;

	// 调仓记录

	private final static String INIT_URL = "https://xueqiu.com/cubes/discover/rank/cube/list.json?category=14&page=1&count=100";

	{
		super.setInitUrl(INIT_URL);
	}

	@Override
	protected void doCrawler(Page page) {
		String url = page.getUrl().toString();
		if (Objects.equals(url, INIT_URL)) {
			String rawText = page.getRawText();
			JSONObject json = JSONObject.parseObject(rawText);
			JSONArray list = json.getJSONArray("list");
			for (int i = 0; i < list.size(); i++) {
				JSONObject tmp = list.getJSONObject(i);
				Long cubeId = tmp.getLong("id");
				String name = tmp.getString("name");
				String symbol = tmp.getString("symbol");
				String ownerId = tmp.getLong("owner_id") + "";
				String ownerName = tmp.getJSONObject("style").getString("name");
				System.out.println(name + "  " + symbol);
				XueQiuCubeDO entity = new XueQiuCubeDO();
				entity.setCubeId(cubeId);
				entity.setName(name);
				entity.setSymbol(symbol);
				entity.setOwnerId(ownerId);
				entity.setOwnerName(ownerName);

				System.out.println(entity);
				//xueQiuCubeRepository.save(entity);
			}
		}
	}

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		CombinationRunnable runnable = new CombinationRunnable();
		runnable.setHostUrl("https://xueqiu.com");
		runnable.run();

		File file = new File("./");
		System.out.println(file.getAbsolutePath());

	}
}
