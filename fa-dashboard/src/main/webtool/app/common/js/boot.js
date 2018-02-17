import Vue from "vue";

import vHeader from "../components/layout/header";
import vSidebar from "../components/layout/sidebar";

import "./components";
import "./filters";
import "../css/app.scss";

import iView from 'iview';
import 'iview/dist/styles/iview.css';

Vue.use(iView);

export default function (vApp, data) {
    return new Vue({
        el: "body",
        data: data,
        components: {
            vHeader,
            vSidebar,
            vApp
        }
    })
}