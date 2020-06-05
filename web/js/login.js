/*
* 切换登录注册div
* */
$(function () {
    $("#gotoRegister").click(function () {
        $('.login').animate({width:'toggle'},350);
        $('.register').animate({width:'toggle'},350);
    });
})
$(function () {
    $("#gotoLogin").click(function () {
        $('.register').animate({width:'toggle'},350);
        $('.login').animate({width:'toggle'},350);
    });
})