package cn.xuhuiqiang.crawler.util;

import java.io.InputStream; 
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class UserAgent {
	private static List<String> userAgent;
	private static List<String> agentIP = new ArrayList<>();
	private  static int size ;
	private static Random random = new Random(); 
	
	static {
		String[] userAgentArray = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 OPR/26.0.1656.60&Opera/8.0 (Windows NT 5.1; U; en)&Mozilla/5.0 (Windows NT 5.1; U; en; rv:1.8.1) Gecko/20061208 Firefox/2.0.0 Opera 9.50&Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; en) Opera 9.50&Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0&Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10&Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2&Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36&Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11&Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.133 Safari/534.16&Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36&Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko&Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11&Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER&Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; LBBROWSER)&Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E; LBBROWSER)&Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; QQBrowser/7.0.3698.400)&Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)&Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0&Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; SE 2.X MetaSr 1.0)&Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Maxthon/4.4.3.4000 Chrome/30.0.1599.101 Safari/537.36&Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 UBrowser/4.0.3214.0 Safari/537.36".split("&");
		userAgent = Arrays.asList(userAgentArray);
		size = userAgent.size();
		String[] ip  = "118.212.105.95:9999,182.138.238.127:9999,113.195.224.133:9999,110.243.6.226:9999,125.108.71.30:9000,110.243.8.110:9999,110.243.23.78:9999,113.232.9.239:9000,171.35.169.4:9999,39.105.28.28:8118,121.232.148.214:9000,60.13.42.75:9999,110.189.152.86:36484,113.195.230.87:9999,120.83.109.147:9999".split(",");
		for(String item: ip) {
			agentIP.add(item);
		}
	}
	
	public static void removeIP(String item) {
		Iterator<String> it = agentIP.iterator();
		while (it.hasNext()) {
			String tmp  = it.next();
			if(tmp.equals(item)){
				agentIP.remove(tmp);
			}
		}
	}
	
	public static String getIp() {
		
		if(agentIP.size() > 0) {
			agentIP.remove(0);
			return agentIP.get(0);
		}
		return null;
		
	}
	
	
	
	public static void initIP() {
		String string = null;
        try {
            Document document = Jsoup.connect("https://www.xicidaili.com/nn").timeout(3000).get();
            System.out.println("get...");
            List<String> tmpList = new ArrayList<>();
            Elements tags = document.select("#ip_list > tbody > tr");
            for (Element element : tags) {
                //取得ip地址节点
                Elements tdChilds = element.select("tr > td:nth-child(2)");
                //取得端口号节点
                Elements tcpd = element.select("tr > td:nth-child(3)");
                if (StringUtils.isNotBlank(tdChilds.text()) && StringUtils.isNotBlank(tcpd.text())) {
                    string = tdChilds.text() + ":" + tcpd.text();
                    System.out.println(string);
                    if (!ifUseless(string)) {
                    	tmpList.add(string);
                    }
                }
            }
            userAgent = tmpList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    //无效的ip 返回true 有效的ip返回false
    static boolean ifUseless(String ip) {
        String[] split = ip.split(":");
        URL url = null;
        try {
            url = new URL("http://www.baidu.com");
            InetSocketAddress addr = new InetSocketAddress(split[0], Integer.parseInt(split[1]));
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
            InputStream in = null;
            try {
                URLConnection conn = url.openConnection(proxy);
                conn.setConnectTimeout(2000);
                in = conn.getInputStream();
            } catch (Exception e) {
                return true;
            }
            String s = IOUtils.toString(in);
            if (s.indexOf("baidu") > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
	}
	
	public static String getViewAgent() {
	        int s = random.nextInt(size)%(size+1);
	        return userAgent.get(s);
	}
	
	public static int getIpSize(){
		return agentIP.size();
	}
}
