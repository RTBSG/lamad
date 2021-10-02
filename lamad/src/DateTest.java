import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 日期
 * 1期:
 * 日期的操纵
 * TemporalAdjuster:时间校正器。有时我们可能需要获
 * 取例如:将日期调整到“下个周日”等操作。
 * TemporalAdjusters :该类通过静态方法提供了大量的常
 * 用TemporalAdjuster的实现。
 * DateTimeFormatter：格式化时间 / 日期
 * Instant：以 Unix 元年 1970-01-01 00:00:00 到某个时间之间的毫秒值
 * @author bilib
 * @date 2021/09/06
 */
public class DateTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> callable = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20210905",dtf);
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(callable));
        }
        for (Future<LocalDate> future : list) {
            System.out.println(future.get());

        }
    }

    /**
     * Instant：以 Unix 元年 1970-01-01 00:00:00 到某个时间之间的毫秒值
     */
    @Test
    public void test02() {
//        获取当前时间

        Instant now = Instant.now();
//    2021-09-07T02:35:16.740Z  以时间戳的 形式 获取当前时间utc时间
        System.out.println(now);
//    2021-09-07T10:37:47.255+08:00 往后偏移8个小时 回归北京时间
    OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
    System.out.println(offsetDateTime);
//        1630982485360 以 Unix 元年 1970-01-01 00:00:00 到某个时间之间的毫秒值
        long l = now.toEpochMilli();
        System.out.println(l);
//        使用1970-01-01 t00:00:00往后偏移1000ms
        Instant instant = Instant.ofEpochSecond(1000);
        System.out.println(instant);

//        时间校正器 TemporalAdjusters
        LocalDateTime now1 = LocalDateTime.now();
//        2021-09-07T11:06:25.344
        System.out.println(now1);
//        把时间的日位置指定为10号    2021-09-10T11:06:25.344 同理年月都可以
        LocalDateTime localDateTime = now1.withDayOfMonth(10);
        System.out.println(localDateTime);
//        with系列方法就是校正用的里面参数用TemporalAdjusters的各种格式
        LocalDateTime with = now1.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(with);
    }

    /**
     *  DateTimeFormatter 格式时间
     */
    @Test
    public void test03() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

        String format = localDateTime.format(dtf);
        System.out.println(format);
//        自定义格式
        DateTimeFormatter l1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
//        2021|09|07|
        DateTimeFormatter l2 = DateTimeFormatter.ofPattern("yyyy|MM|dd|");
        String format1 = localDateTime.format(l1);
        System.out.println(format1);
//        将字符串解析回时间格式，以指定方式解析
        LocalDateTime parse = localDateTime.parse(format1, l1);
        System.out.println(parse);
    }

}
