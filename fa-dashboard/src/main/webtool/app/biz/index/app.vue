<template lang="pug">
    .landing-page
        h3 欢迎使用First Apple系统！
        p 请在左侧菜单中选择你所需要的功能。
        hr
        p 快速入口链接：
        nav: ul: li(v-for="menu in pinnedMenuList")
            a(href="{{* menu.href}}") {{* menu.title }}
</template>

<script>
import {menuList} from "app/common/js/constants";

export default {
    data: function () {
        var pinnedMenuList = [];
        menuList.forEach(menu => pinnedMenuList = pinnedMenuList.concat(menu.children.filter(submenu => submenu.pin)));
        return {pinnedMenuList};
    }
}
</script>