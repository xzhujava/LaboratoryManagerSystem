function gotoRegister() {
    $('.login').animate({width:'toggle'},350);
    $('.register').animate({width:'toggle'},350);
}
function gotoLogin() {
    $('.register').animate({width:'toggle'},350);
    $('.login').animate({width:'toggle'},350);
}
function loginSubmit() {
    let userNo = $('#inputUserNo').val();
    let userPassword = $('#inputPassword').val();
    var isUserNo = /^\d+$/.test(userNo);
    if(userNo.trim().length<6 && isUserNo){
        alert("学号格式错误！");
        return;
    }
}
function registerSubmit() {
    let userName = $('#inputUserName_register').val();
    let userNo = $('#inputUserNo_register').val();
    let userPassword = $('#inputPassword_register').val();
    let userSex = $('input[name="sex"]:checked').val();
    let remark = $('#remark').val();
    var isUserNo = /^\d+$/.test(userNo);
    if(userName.trim().length<1){
        alert("用户名不应为空！");
        return;
    }
    if(isUserNo){
        alert("学号格式错误！");
        return;
    }
    if(userPassword.trim().length<6){
        alert("请保证密码在6位及以上！");
        return;
    }
}