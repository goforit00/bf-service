<template lang="pug">
    aside.main-sidebar: section.sidebar: ul.sidebar-menu
        li(v-for="menu of menuList", :class="['treeview', {active: menu.active}]", v-show="!menu.hide")
            a(href="#")
                i.fa(class="fa-{{* menu.icon }}")
                span {{* menu.title }}
                i.fa.fa-angle-left.pull-right
            ul.treeview-menu
                li(v-for="submenu in menu.children", :class="{active: submenu.active}", v-show="!submenu.hide")
                    a(href="{{* submenu.href }}")
                        i.fa.fa-link
                        span {{* submenu.title }}
</template>

<script>
import {menuList} from "app/common/js/constants";

export default {
    data() {
        return {menuList};
    },
    created() {
        var pathname = window.location.pathname;

        this.menuList.some(menu => {
            menu.active = false;
            menu.children.some(submenu => {
                return menu.active = submenu.active = submenu.href === pathname;
            });
            return menu.active;
        });
    }
}
</script>