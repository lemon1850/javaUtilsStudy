package cn.catgod.java;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/17
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.*;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/1/9
 */

public class DateTimeDemo {


    public static void main(String[] args) {

        //整个time包类的概览
//        overviewTimeClass();
//        studyLocalDate();
        //clock instant demo
//        studyInstant();
        //period duration demo
        studyPeriodAndDuration();

    }

    /**
     * Period[year,month,day组成] 处理date的时间段[概念上的周期，会考虑daylight savings time等]
     * Duration[seconds,nanos] 处理time的时间段[精准的周期]
     */
    private static void studyPeriodAndDuration() {

        System.out.println(String.format("%-20s, %-30s", "Period 40d", Period.ofDays(40)));
        System.out.println(String.format("%-20s, %-30s", "Period 10m", Period.ofMonths(10)));
        System.out.println(String.format("%-20s, %-30s", "Period 10y", Period.ofYears(10)));

        System.out.println(String.format("%-20s, %-30s", "Period 10d", Duration.ofDays(3)));
        System.out.println(String.format("%-20s, %-30s", "Period 7200s", Duration.ofSeconds(7200)));
        System.out.println(String.format("%-20s, %-30s", "Period 10m", Duration.ofMinutes(3)));


    }

    private static void studyInstant() {

        Clock clock = Clock.systemDefaultZone();
        Instant now = clock.instant();
        System.out.println(String.format("%-20s, %-30s", "Instant", now));
        System.out.println(String.format("%-20s, %-30s", "Instant", now.atZone(ZoneId.systemDefault())));
        System.out.println(String.format("%-20s, %-30s", "EpochMilli", now.toEpochMilli()));
        System.out.println(String.format("%-20s, %-30s", "EpochMilli", System.currentTimeMillis()));
        System.out.println(String.format("%-20s, %-30s", "OffsetDateTime 8h", now.atOffset(ZoneOffset.ofHours(8))));
    }

    /**
     * java.time包核心类串讲
     */
    private static void overviewTimeClass() {

        ZonedDateTime now = ZonedDateTime.now();

        System.out.println("人类习惯的日期时间");
        System.out.println(String.format("%-20s, %-30s", "LocalDate", now.toLocalDate()));
        System.out.println(String.format("%-20s, %23s", "LocalTime", now.toLocalTime()));
        System.out.println(String.format("%-20s, %29s", "OffsetTime", now.toOffsetDateTime().toOffsetTime()));
        System.out.println(String.format("%-20s, %-30s", "LocalDateTime", now.toLocalDateTime()));
        System.out.println(String.format("%-20s, %-30s", "OffsetDateTime", now.toOffsetDateTime()));
        System.out.println(String.format("%-20s, %-30s", "ZonedDateTime", now));
        System.out.println(String.format("%-20s, %-30s", "Instant", now.toInstant()));
        System.out.println(String.format("%-20s, %-30s", "Instant", now.getZone()));

        System.out.println("机器习惯的秒毫秒");
        Instant instant = Instant.now();
        Clock clock = Clock.system(ZoneId.systemDefault());
        System.out.println(String.format("%-20s, %-30s", "Instant", instant));
        System.out.println(String.format("%-20s, %-30s", "epochMilli", instant.toEpochMilli()));

        System.out.println("时区");
        System.out.println(String.format("%-20s, %-30s", "ZoneId", ZoneId.systemDefault()));
        System.out.println(String.format("%-20s, %-30s", "ZoneId", ZoneId.of("Asia/Shanghai")));
        System.out.println(String.format("%-20s, %-30s", "ZoneOffset", now.getOffset()));

    }

    private static void studyLocalDate() {

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDate of = LocalDate.of(2021, 1, 1);
        LocalDate parse = LocalDate.parse("2020-01-01");

        localDate.plusDays(1);
        localDate.plusWeeks(1);
        localDate.plus(1, DAYS);
        localDate.plus(Period.ofDays(1));
        localDate.minus(1, DAYS);
        localDate.minusDays(1);
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());
        System.out.println(localDateTime);
        localDate.atTime(OffsetTime.now());
//        localDate.plus(Duration.ofDays(1));
    }
}
