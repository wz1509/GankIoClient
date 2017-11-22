package me.threebears.gankio;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        final Connection connect = Jsoup.connect("http://gank.io/xiandu/");
        // 伪装成浏览器抓取
        connect.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0");
        final Document document = connect.get();

        // 获取标题
        String title1 = document.head().select("title").text();
        // 或者
        String title2 = document.title();
        System.out.println(title1);
        System.out.println(title2);

        Elements typoElements = document.body().select("div.typo").select("div.container");
        // 多个select()拼接可以用空格隔开
        Elements liElements = typoElements.select("div#xiandu_cat ul li");
        for (Element element : liElements) {
            String href = element.select("a").attr("href");
            String text = element.text();
            System.out.println(href + "\t" + text);
        }

        Elements itemElements = typoElements.select("div.xiandu_items div.xiandu_item");
        for (Element element : itemElements) {
            Elements leftElements = element.select("div.xiandu_left");
            // 编号，1:
            String no = leftElements.select("span.xiandu_index").text();
            // 跳转网址，http://daily.zhihu.com/story/9657300?utm_source=gank.io%2Fxiandu&utm_medium=website
            String url = leftElements.select("a").attr("href");
            // 内容，想精想怪 90——吃鸡手游化那些事
            String desc = leftElements.select("a").text();
            // 时间，8 分钟前
            String date = leftElements.select("span small").text();

            Elements rightElements = element.select("div.xiandu_right");
            // 来源url，/xiandu/view/zhihu
            String sourceUrl = rightElements.select("a.site-name").attr("href");
            // 来源名称，知乎日报
            String sourceTitle = rightElements.select("a.site-name").attr("title");
            // 来源图标，http://ww4.sinaimg.cn/large/610dc034jw1f9sfzr2gmnj204v04va9y.jpg
            String sourceIcon = rightElements.select("a.site-name img").attr("src");
            System.out.println(no + "\t" + url + "\t" + desc + " \t" + date + "\t" + sourceUrl + "\t" + sourceTitle + "\t" + sourceIcon);
        }
    }

}