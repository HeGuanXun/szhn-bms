 let server = "/apiproxy";
 let server1 = "http://localhost:8080";
let urls = {
    //登录,登出
    login: `${server1}/login`,
    /*
    *图书：
    */
    book_create: `${server}/insert`,
    book_update: `${server}/update`,
    book_list: `${server1}/getBookByPage`,
    book_delete: `${server}/deleteById`,
};

export default urls;
