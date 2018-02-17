<template lang="pug">
    header.main-header
        a.logo(href="{{ headerRef }}")
            //- mini logo for sidebar mini 50x50 pixels
            span.logo-mini FirstApple
            //- logo for regular state and mobile devices
            span.logo-lg First Apple系统
        nav.navbar.navbar-static-top(role="navigation")
            a.sidebar-toggle(href="#", data-toggle="offcanvas", role="button", title="开关导航栏")
                span.sr-only 开关导航栏
            p.main-title {{ mainTitle }}
            .right-box
                .user-info
                    span.prefix 当前登录：
                    span.name(v-text="user.name")
                .logout(@click="logout")
                    span 注销
</template>

<style scoped>
.right-box {line-height: 50px; float: right;}
.right-box > * {display: inline-block;}
.user-info {margin-right: 10px;}
.user-info .prefix {color: white;}
.user-info .name {color: yellow;}
.logout {color: white; padding: 0 15px; margin-right: 10px; cursor: pointer;}
.logout a {color: white;}
.logout:hover {background-color: #008d4c;}
.logout:hover span {text-decoration: underline;}
</style>

<script>
export default {
    props: ["mainTitle"],
    data() {
        return {
            user: {
                name: "登录用户"
            }
        }
    },
    ready() {
//    调用后端或者从cookie拿到用户信息
        var pageLoginType=getLoginUserType()

        var username;
        if(pageLoginType=='NORMAL'){
            username=getCurrentUsernameFromCookie()
        }else{
            username=getCurrentMerchantManagerCodeFromCookie();
        }

        if(username){
            this.user.name=username
        }else{
            this.user.name="登录用户"
        }
    },
    methods: {
        logout() {
            cleanCookie()
            window.location = "/static/login.html";
            return;
        }
    }
}
</script>