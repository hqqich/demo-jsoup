package com.example.demojsoup;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

/**
 *
 * 利用Jsoup获取HTML页面的各分页中的标题信息
 *
 * Created by Administrator on 2022/6/9 is 13:09.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/9
 */

public class JsoupTest {

  Elements elements;

  @SneakyThrows
  @Test
  public void getAllPageTitle() {

    int page =1 ;

    while(true) {

      //Document document = Jsoup.connect("https://blog.csdn.net/deng214/article/month/2018/05/"+page)
      //    .data("query", "Java")
      //    .userAgent("Mozilla")
      //    .cookie("auth", "token")
      //    .timeout(3000)
      //    .post();

      Document document = Jsoup.connect("https://blog.csdn.net/deng214/article/month/2018/05/"+page)
          .header("a", "a")  //添加header
          .header("b", "b")
          .get();

      elements = document.select("div.article-list");  ///找到循环的父集
      elements = elements.first().children().select("div:not(div[style=display: none;])").remove();  //删除不显示的
      elements = elements.select("h4>a");  //找到h4下面的a标签
      int size = elements.size();

      System.out.println("-------------------- 第" + page + "页,显示"+size+"条数据-------------------- ");

      if (size == 0) {
        break;
      }
      if (size < 20 && size > 0) {
        getElement();
        break;
      } else {
        getElement();
        page++;
      }

    }
  }

  public void getElement(){
    for (int i = 0; i < elements.size(); i++) {
      Element element = elements.get(i);
      String href = element.attr("href");
      String text = element.text();
      System.out.println("标题："+text);
      System.out.println("链接："+href);
    }
  }

  @Test
  @SneakyThrows
  public void test02() {
    OkHttpClient client = new OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    Request request = new Request.Builder()
        .url("https://blog.csdn.net/deng214/article/month/2018/05/")
        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.61 Safari/537.36")
        .get()
        .build();

    Response execute = client.newCall(request).execute();

    System.out.println(execute.body().string());
  }

}
