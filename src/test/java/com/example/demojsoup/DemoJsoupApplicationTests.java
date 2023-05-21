package com.example.demojsoup;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoJsoupApplicationTests {

  @Test
  @SneakyThrows
  void contextLoads() {

    Document document = Jsoup.connect("https://www.baidu.com").get();

    System.out.println(document.body().toString());




  }

}
