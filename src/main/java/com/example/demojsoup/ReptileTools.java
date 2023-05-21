package com.example.demojsoup;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.logging.Level;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * Jsoup配合 htmlunit 爬取异步加载的网页
 *
 * Created by Administrator on 2022/6/9 is 11:58.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/9
 */

public class ReptileTools {

    /**
     * 使用htmlunit模拟Chrome并获取全部网页信息
     * @param cookie
     * @param DownloadUrl
     * @return
     */
    public static String searchMobile2(String cookie,String DownloadUrl) {

        String title="";
        Document doc = null;
        try {
            //构造一个webClient 模拟Chrome 浏览器
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            //屏蔽日志信息
            java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
            //支持JavaScript
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setActiveXNative(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(5000);
            HtmlPage rootPage = webClient.getPage(DownloadUrl);
            //设置一个运行JavaScript的时间
            webClient.waitForBackgroundJavaScript(5000);
            String html = rootPage.asXml();
            doc = Jsoup.parse(html);
            System.out.println("==========="+doc);
            System.out.println("===========");
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
        return title;
    }

    public static void main(String[] args) {
        ReptileTools.searchMobile2(null, "http://www.baidu.com");
    }
}

