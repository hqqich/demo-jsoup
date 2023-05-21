package com.example.demojsoup;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * Created by Administrator on 2022/6/9 is 11:50.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/9
 */

public class HtmlutilTest {

    @Test
    @SneakyThrows
    public void test01() {

        WebClient webClient = new WebClient();
        // 启动 js 解释器
        webClient.getOptions().setJavaScriptEnabled(true);
        // 禁用 css 支持
        webClient.getOptions().setCssEnabled(false);
        HtmlPage page = webClient.getPage("http://www.baidu.com");

         //以 xml 的形式获取响应文本
        String pageXml = page.asXml();
        // 以 文本 的形式获取响应文本
        String pageText = page.toString();

        // 获取当前 Url (跳转后的最终Url)
        String url = page.getUrl().toString();


        System.out.println(page.getBody());
        int a = 1;
    }

}
