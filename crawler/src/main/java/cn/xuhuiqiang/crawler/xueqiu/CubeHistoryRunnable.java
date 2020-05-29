package cn.xuhuiqiang.crawler.xueqiu;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.zip.Checksum;

import org.openqa.selenium.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.xuhuiqiang.crawler.CrawlerRunnable;
import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.stock.domain.XueQiuCubeDynamicDO;
import cn.xuhuiqiang.stock.repository.XueQiuCubeDynamicRepository;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;

/**
 * 雪球历史调仓记录处理
 * @ClassName: CubeHistoryRunnable 
 * @Description: TODO
 * @author huiqiangxu@126.com
 * @date 2020年5月16日 下午5:02:12 
 *
 */
@Component
public class CubeHistoryRunnable extends CrawlerRunnable {

	private final static String URL_FORMAT = "https://xueqiu.com/cubes/rebalancing/show_origin.json?rb_id=";
	@Autowired
	private XueQiuCubeDynamicRepository xueQiuCubeDynamicRepository;

	{
		super.setInitUrl(URL_FORMAT + "72138209");
		super.site.getAcceptStatCode().add(400);
	}

	@Override
	protected void doCrawler(Page page) {
		String url = page.getUrl().toString();
		System.out.println(url);
		Long rebalanceId = parseReblanceId(url);
		JSONObject json = JSONObject.parseObject(page.getRawText()).getJSONObject("rebalancing");
		Long cubeId = json.getLong("cube_id");
		//Date bizDate = new Date(json.getLongValue("created_at"));
		// 本次调仓明细
		JSONArray array = json.getJSONArray("rebalancing_histories");
		List<XueQiuCubeDynamicDO> toDb = new LinkedList<XueQiuCubeDynamicDO>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject tmp = array.getJSONObject(i);
			Long recordId = tmp.getLong("id");
			String code = tmp.getString("stock_symbol");
			String stockName = tmp.getString("stock_name");
			Double price = tmp.getDouble("price");
			Date bizDate = new Date(tmp.getLongValue("created_at"));
			Double preWeight = tmp.getDouble("prev_weight_adjusted");
			Double nowWeight = tmp.getDouble("target_weight");
			XueQiuCubeDynamicDO dynamic = new XueQiuCubeDynamicDO();
			dynamic.setBizDate(bizDate);
			dynamic.setRebalanceId(rebalanceId);
			dynamic.setCubeId(cubeId);
			dynamic.setRecordId(recordId);
			dynamic.setCode(code);
			dynamic.setBizDate(bizDate);
			dynamic.setStockName(stockName);
			if (CheckUtil.isEmpty(price)) {
				dynamic.setPrice(BigDecimal.ZERO);
			} else {
				dynamic.setPrice(new BigDecimal(price.toString()));
			}

			if (CheckUtil.isEmpty(preWeight)) {
				dynamic.setPrePersent(BigDecimal.ZERO);
			} else {
				dynamic.setPrePersent(new BigDecimal(preWeight.toString()));
			}
			dynamic.setNowPersent(new BigDecimal(nowWeight.toString()));
			toDb.add(dynamic);
		}
		try {
		xueQiuCubeDynamicRepository.saveAll(toDb);
		}catch (Exception e) {
			System.out.println(toDb.toString());
		}
		try {
			Random r = new Random();
			int gap = r.nextInt(10) + 5;
			Thread.sleep(gap * 1000);
		} catch (Exception e) {
			
		}
		addNextRequest(page);
	}
	
	

	@Override
	protected void pre() {
		super.pre();
		//71665156
		//72027922  72116577  72138209
		XueQiuCubeDynamicDO lastRebalance = xueQiuCubeDynamicRepository.findTopByOrderByRebalanceIdDesc();
		if(CheckUtil.isEmpty(lastRebalance)) {
			super.setInitUrl(URL_FORMAT + "72138209");
		}else {
			super.setInitUrl(URL_FORMAT + (lastRebalance.getRebalanceId() - 1));
		}
		super.setInitUrl(URL_FORMAT + "72138209");
		
	}



	@Override
	protected void dealCode400(Page page) {
		System.out.println("dealCode400....");
		addNextRequest(page);
	}

	private void addNextRequest(Page page) {
		Long rebalanceId = parseReblanceId(page.getUrl().toString());
		//rebalanceId++;
		rebalanceId--;
		Request targetRequest = new Request(URL_FORMAT + rebalanceId);
		if (CheckUtil.isNotEmpty(cookies)) {
			for (Cookie item : cookies) {
				targetRequest.addCookie(item.getName(), item.getValue());
			}
		}
		page.addTargetRequest(targetRequest);
	}

	private Long parseReblanceId(String url) {
		return Long.valueOf(url.split("=")[1].trim());
	}

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		CubeHistoryRunnable runnable = new CubeHistoryRunnable();
		runnable.setHostUrl("https://xueqiu.com");
		runnable.run();
 
	}

}
