# szhn-bms
数字海南-图书馆管理系统-测试题目

#前后端架构

#bms-serve 是后端工程   
    1.技术架构：springboot+ mybatis+ h2+ ehcache + spring security                   
    2.提供对图书的增、删、改、查功能，需要有用户登录控制
    3.内置了登录、权限控制、分层、日志、缓存、数据库、基于环境的配置分离、swagger 还有统一异常处理等功能
    4.为了方便部署数据库使用了H2
    其他：
        5.关于环境隔离
            5.1  使用maven的<profiles>标签定义dev、test、prod环境，在整合application.yml指定spring.profiles.active=@profileActive@
            5.2  日志隔离也是基于maven的<profiles>配置 根据环境使用不同的文件路径LOG_PATH 具体可以看resources\logback\logback-spring.xml
        6.关于日志
            6.1  springboot内置的日志使用的是logback，logback性能是最好的，这里也是使用logback实现日志相关功能
        7.关于分层
            7.1 系统分层结构都是业界经典的方式，具体可以看源码架构
        8.关于登录、权限
            8.1  因为采用前后端架构，所以系统采用spring security+JWT 并基于JwtAuthenticationProvider 提供自定义的认证授权机制
            8.2  用户需要登录 -> 登录成功 -> 返回token
            8.3  用户增删改查需要携带token才能进行才做 
                  可以使用postman测试，接口测试本人都已经被调试过，不过已经提供前端页面，可以直接跑起页面查看效果
        9.关于缓存
            9.1  为了方便部署采用ehcache做缓存，提提供ehcache.xml配置 
        10.关于swagger
            10.1 swgger使用最新版本的UI 登录ui地址：localhost:8080/doc.html  
        11.其他...               
    #关于后端部署运行
        1.git地址：https://github.com/HeGuanXun/szhn-bms.git
        2.打开idea File -> Open - >找到刚刚拉去的szhn-bms目录并选择 bms-serve (注意：原则上会一并打开bms-vue工程  我已经配置好了)
        3.配置maven  最好使用阿里云镜像
        4.run BmsApplication  这样子项目就起球来了


#bms-vue 是前端工程
      1.技术架构：vue + vuex + router + element ui + axio
      2.实现了登录页面
        2.1用户需要登录后才能访问其他页面 
      3.实现了首页功能
        3.1 首页功能包括 主页，图书 一二级菜单 用户名展示等等
      4.实现了图书管理功能
        4.1 增删改查 数据检验，分页功能等等
      5.其他...
      
      #关于前端部署  
        1. 1.git地址：https://github.com/HeGuanXun/szhn-bms.git
            1.1 推荐使用idea家族专门提供前端开发的功能Webstorm，
                原则上如果用idea打开bms-serve后端项目的话 会会默认带出bms-vue项目
                直接在idea上编译运行
        2. 安装node
        
        3.安装淘宝镜像 npm --registry https://registry.npm.taobao.org install express
        
        4.cnpm i
        
        5.cnpm run serve
        
        note:系统内置了超级管理员用户：szhn/szhn
                       普通用户：hegx/hegx
                                
       





