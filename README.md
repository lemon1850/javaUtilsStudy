## java工具包的使用学习

### java
1. regex 正则表达式
    - [wiki](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html) 
    - [demo](https://www.baeldung.com/regular-expressions-java) 
    - [正则测试网站](https://regex101.com/)
2. time 时间库
3. concurrent 并发库
4. lambda表达式
5. stream流处理
6. IO/NIO
7. 泛型
    - [generics使用demo todo read](https://www.baeldung.com/java-generics)

### spring系列
1. spring
    - [spring生态的文档](https://spring.io/projects/spring-framework#overview)
    - [spring wiki](https://docs.spring.io/spring-framework/docs/current/reference/html/index.html)
    - [spring-boot wiki](https://docs.spring.io/spring-boot/docs/current/reference/html/)
### 工具
1.  disruptor 
    - [wiki](https://lmax-exchange.github.io/disruptor/) 
    - [github](https://github.com/LMAX-Exchange/disruptor)
2.  lombok 
    - [wiki](https://projectlombok.org/features/all) 
    - [lombok configuration sys](https://projectlombok.org/features/configuration)
3.  jedis 
    - [github](https://github.com/redis/jedis)
5.  redisson 
    - [github](https://github.com/redisson/redisson/) 
    - [wiki](https://github.com/redisson/redisson/wiki/Table-of-Content)
    - [redisson vs lettuce](https://redisson.org/feature-comparison-redisson-vs-lettuce.html)
6.  guava 
    - [wiki](https://github.com/google/guava/wiki)
7.  HikariCp 
    - [wiki](https://github.com/brettwooldridge/HikariCP)
8.  easyExcel 
    - [github](https://github.com/alibaba/easyexcel) 
    - [wiki](https://www.yuque.com/easyexcel/doc/easyexcel)
9.  opencsv 
    - [wiki](http://opencsv.sourceforge.net/)
10. JsonPath 
    - [github&wiki](https://github.com/json-path/JsonPath) 
    - [demo](https://www.baeldung.com/guide-to-jayway-jsonpath)
11. tink google加密组件 
    - [github](https://github.com/google/tink)
12. validate 
    - [wiki](http://hibernate.org/validator/) 
    - [validator基础使用](https://www.baeldung.com/javax-validation)
    - [hibernate validator](https://www.baeldung.com/hibernate-validator-constraints)
    - [method validator](https://www.baeldung.com/javax-validation-method-constraints)
    - [validator group](https://www.baeldung.com/javax-validation-groups)
13. jackson 
    - [wiki](https://github.com/FasterXML/jackson-databind) 
    - [demo](https://www.baeldung.com/jackson)
    1. jackson-datatype-jsr310 
        - [github](https://github.com/FasterXML/jackson-modules-java8) 
        - [wiki](https://github.com/FasterXML/jackson-modules-java8/tree/master/datetime)
14. log4j2 
    - [wiki](https://logging.apache.org/log4j/2.x/manual/index.html)
15. caffeine 
    - [wiki](https://github.com/ben-manes/caffeine/wiki)
16. hikari
    - [wiki|github](https://github.com/brettwooldridge/HikariCP)
17. apache commons
    1. commons-langs
    2. common-collections4
    3. commons-compress
    4. commons-codec
    5. commons-io
    6. commons-text
    7. commons-beanutils
18. netty
    - [github](https://github.com/netty/netty)
    - [wiki](https://netty.io/wiki/)
    - [官网](https://netty.io/)
19. okhttp
    - [github](https://github.com/square/okhttp/)
21. retrofit
    - [github](https://github.com/square/retrofit)
    - [wiki](https://square.github.io/retrofit/)
20. mapstruct
    - [wiki](https://mapstruct.org/)
    - [快速入门](https://www.baeldung.com/mapstruct)
    - [集合处理](https://www.baeldung.com/java-mapstruct-mapping-collections)
    - [ignore-unmapped-properties](https://www.baeldung.com/mapstruct-ignore-unmapped-properties)
    - [custom-mapper给特定属性定义转换方法](https://www.baeldung.com/mapstruct-custom-mapper)
    - [多个对象映射到一个对象(或者更新)](https://www.baeldung.com/mapstruct-multiple-source-objects)
21. spring reactor
    - [github](https://github.com/reactor/reactor-core)
    - [wiki](https://projectreactor.io/docs)
    - [reactivex规范官网](https://reactivex.io/)
    - [入门demo](https://www.baeldung.com/reactor-core)
    - [reactor-combine-streams demo todo](https://www.baeldung.com/reactor-combine-streams)
    - [flux-sequences-reactor demo todo](https://www.baeldung.com/flux-sequences-reactor)
22. hutools
    - [wiki](https://www.hutool.cn/docs/#/)
    - [github](https://github.com/dromara/hutool)
1. ognl
    - [wiki](http://commons.apache.org/proper/commons-ognl/language-guide.html)
    - [demo todo](https://juejin.cn/post/6844904013859651597)
    - [demo todo](https://blog.hhui.top/hexblog/2019/11/29/191129-Ognl-%E8%AF%AD%E6%B3%95%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B/)

### Test
1. junit5 
    - [wiki](https://junit.org/junit5/docs/current/user-guide/)
2. AssertJ 
    - [wiki](https://assertj.github.io/doc/)
3. Mockito 
    - [wiki](https://site.mockito.org/)
4. JSONassert 
    - [demo](https://www.baeldung.com/jsonassert)
5. awaitility
    - [awaitility](https://github.com/awaitility/awaitility)
    
### 中间件
1. arthas
    - [官网](https://arthas.aliyun.com/zh-cn/)
    - [在线动手教程1](https://arthas.aliyun.com/doc/arthas-tutorials.html?language=cn)
    - [在线动手教程2](https://start.aliyun.com/handson-lab?category=arthas)
    - [wiki](https://arthas.aliyun.com/doc/)
    - [ognl特殊用法参考](https://github.com/alibaba/arthas/issues/71)
    - [ognl wiki](http://commons.apache.org/proper/commons-ognl/language-guide.html)
    - [ognl advice核心表达式](https://arthas.aliyun.com/doc/advice-class.html)

 
