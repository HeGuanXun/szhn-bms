# szhn-bms
数字海南-图书馆管理系统-测试题目

#前后端架构
    bms-serve 是后端工程   
    1.技术架构：springboot+ mybatis+ h2+ ehcache + spring security                   
    2.提供对图书的增、删、改、查功能，需要有用户登录控制
    3.内置了登录、权限控制、分层、日志、缓存、数据库、基于环境的配置分离、swagger 还有统一异常处理
    4.用户需要登录 -> 登录成功 -> 返回token
    5.用户增删改查需要携带token才能进行才做   
    6.可以使用postman测试，接口测试本人都已经被调试过，不过已经提供前端页面，可以直接跑起页面查看效果
    7.为了方便部署数据库使用了H2,缓存使用ehcache做缓存
#关于后端部署
    1.git地址：https://github.com/HeGuanXun/szhn-bms.git
    2.使用idea

    bms-vue 是前端工程





