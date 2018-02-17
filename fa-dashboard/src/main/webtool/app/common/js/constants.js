import {Utils} from "./utils";

let menuUrlPrefix = "/static";


//export let menuList = [{
//    title: "用户详情",
//    icon: "rocket",
//    children: [{
//        title: "我的资料",
//        href: `${menuUrlPrefix}/user/detail.html`,
//    }, {
//        title: "我的店铺",
//        href: `${menuUrlPrefix}/user/company.html`,
//        pin: true
//    }]
//}];

var userMenuList=[]
getUserMenu();
function getUserMenu(){

    var userType=getLoginUserType();

    if(userType=='NORMAL'){
        userMenuList= [{
            title: "用户详情",
            icon: "rocket",
            children: [{
                title: "我的店铺",
                href: `${menuUrlPrefix}/user/detail.html`,
                pin: true
            }]
        }];

    }else if(userType=='MERCHANT'){
        userMenuList= [{
            title: "商家详情",
            icon: "rocket",
            children: [{
                title: "店铺管理者信息",
                href: `${menuUrlPrefix}/merchant/detail.html`,
                pin: true
            },{
                title: "我的店铺",
                href: `${menuUrlPrefix}/merchant/shopInfo.html`,
                pin: true
            }]
        }];
    }
};


export let menuList=userMenuList;




