import Vue from "vue";
import {} from "./constants";

let mapper = {

};

for (let key in mapper) {
    Vue.filter(key, mapper[key]);
}