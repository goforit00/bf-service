import Vue from "vue";

let mapper = {

};

for (let key in mapper) {
    Vue.component(key, mapper[key]);
}