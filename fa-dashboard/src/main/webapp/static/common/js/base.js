
//进行是否登录检查

function checkLogin() {

    var loginUserType=getLoginUserType()

    if( !loginUserType ){
        //默认为普通用户登录
        loginUserType='NORMAL';
    }

    var username;
    var token;
    var loginUrl;
    var checkTokenUrl;
    var checkTokenKey;
    if( loginUserType==='NORMAL'){
        username=$.cookie('fa_username')
        token=$.cookie('fa_token')
        loginUrl='/static/login.html';
        checkTokenUrl='/restful/user/token?'
        checkTokenKey='userNickName';

    }else if(loginUserType==='MERCHANT'){
        username=$.cookie('fa_manager_code')
        token=$.cookie('fa_manager_token')
        loginUrl='/static/merchantlogin.html'
        checkTokenUrl='/restful//merchant/manager/token?'
        checkTokenKey='managerCode';
    }

    //未检查到token时跳转
    if( !username || !token ){
        alert("您未登录,请先登录");
        window.location.href=loginUrl;
        return;
    }

    //进行token后台验证
    $.ajax({
        type: "GET",
        url: checkTokenUrl+checkTokenKey+"="+username+"&token="+token,
        data: {
            "username":username,
            "token":token
        },
        contentType:"application/json",
        success: function (data) {
            if (data.success == true) {
                if(data.data==true){
                    return
                }else{
                    alert("您的登录出现问题，请重新登录")
                    window.location.href=loginUrl;
                    return;
                }
            } else {
                alert("登录异常:"+data.message);
                window.location.href=loginUrl;
                return;
            }
        },
        error:function (data) {
            alert("/restful/user/token接口出现异");
            window.location.href=loginUrl;
            return
        }
    });

}

function getLoginUserType(){
    var loginUserType=$.cookie('fa_login_user_type')
    return loginUserType;
}

function setCurrentUserCookie(username,token){
    $.cookie('fa_username', username,{"path":'/static'});
    $.cookie('fa_token',token,{"path":'/static'});
}

function getCurrentUsernameFromCookie(){
    var username=$.cookie('fa_username')
    return username;
}

function getCurrentMerchantManagerCodeFromCookie(){
    var managerCode=$.cookie('fa_manager_code')
    return managerCode;
}

function cleanCookie(){
    //清除cookie
    $.cookie('fa_username', "",{"path":'/static'});
    $.cookie('fa_token',"",{"path":'/static'});
}

checkLogin();

window.isDevEnv = location.port === "6060";

$(window).on("resize.perfect-scrollbar", () => {
    $(".ps-container").perfectScrollbar("update");
});

$.datetimepicker.setLocale("ch");

swal.setDefaults({
    confirmButtonText: "确定",
    cancelButtonText: "取消"
});


