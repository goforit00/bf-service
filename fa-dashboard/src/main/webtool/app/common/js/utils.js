import Vue from "vue";
import {menuList} from "app/common/js/constants";

Object.assign(Array.prototype, {
    repeat(time) {
        let arr = this.slice(0);
        for (let i = 0; i < time; i++) {
            this.splice(this.length, 0, ...JudyUtils.deepCopy(arr));
        }
        return this;
    },
    clear() {
        this.splice(0);
        return this;
    },
    remove(target, prop = "id") {
        let index = -1;
        if (~(index = this.findIndex(e => (typeof e === "object" ? e[prop] : e) === target))) {
            this.splice(index, 1);
        }
    },
    sum() {
        let num = 0;
        for (let i = 0; i < this.length; i++) {
            num += parseFloat(this[i]);
        }
        return num;
    }
});

Object.assign(Number.prototype, {
    signify(decimals) {
        let num = this.valueOf();
        return (num > 0 ? "+" : "") + (num && Number.isInteger(decimals) && decimals.isNaturalNumber() ? num.toFixedNumber(decimals) : num);
    },
    isNaturalNumber() {
        let num = this.valueOf();
        return Number.isInteger(num) && num >= 0;
    },
    isPositiveInteger() {
        let num = this.valueOf();
        return Number.isInteger(num) && num > 0;
    },
    isNegativeInteger() {
        let num = this.valueOf();
        return Number.isInteger(num) && num < 0;
    },
    toFixedNumber() {
        return +this.toFixed(...arguments);
    },
    beautify() {
        let integerPart = String(this), restPart = "", dpIndex = integerPart.indexOf(".");
        if (~dpIndex) {
            restPart = integerPart.substring(dpIndex);
            integerPart = integerPart.substring(0, dpIndex);
        }
        integerPart.split("").reverse().forEach((digit, index) => {
            if (digit === "-" || !index || (index % 3)) {
                restPart = `${digit}${restPart}`;
            } else {
                restPart = `${digit},${restPart}`;
            }
        });
        return restPart;
    },
    beautifyAndSignify() {
        let sign = this > 0 ? "+" : "";
        return `${sign}${this.beautify()}`;
    }
});

export let JudyUtils = {
    isFilledArray: input => Array.isArray(input) && input.length,
    getTreeDepth(node, prop) {
        let depth = 1, offset = 0;

        if (this.isFilledArray(node)) {
            node = {[prop]: node};
            offset = 1;
        }

        if (this.isFilledArray(node[prop])) {
            depth = Math.max(...node[prop].map(subNode => this.getTreeDepth(subNode, prop))) + 1;
        }
        return depth - offset;
    },
    getTreeLeafNodes(node, prop) {
        let leafNodes, isFilledArray = this.isFilledArray;

        if (isFilledArray(node)) {
            node = {[prop]: node};
        }

        if (isFilledArray(node[prop])) {
            leafNodes = node[prop].reduce(function getLeafNodes(leafNodes, subNode) {
                if (isFilledArray(subNode[prop])) {
                    subNode[prop].reduce(getLeafNodes, leafNodes);
                } else {
                    leafNodes.push(subNode);
                }
                return leafNodes;
            }, []);
        }

        return leafNodes;
    },
    generateMapper(arr) {
        let mapper = {};
        arr.forEach(item => mapper[item.key] = item.name);
        return mapper;
    },
    deepCopy: input => JSON.parse(JSON.stringify(input)),
    keepDecimals: (input, num = 2) => ("0".repeat(num) + input).slice(-num),
    getQueryStr(obj, props) {
        props = Array.isArray(props) ? props : Object.keys(obj);
        return props.map(p => {
            let v = obj[p];
            if (typeof v === "object") v = JSON.stringify(v);
            return `${p}=${v}`;
        }).join("&");
    },
    updatePerfectScrollbar({scrollLeft = true, scrollTop = true} = {scrollLeft: true, scrollTop: true}) {
        Vue.nextTick(() => {
            let $psb = $(".ps-container").perfectScrollbar("update");
            scrollLeft && $psb.scrollLeft(0);
            scrollTop && $psb.scrollTop(0);
        });
    },
    mixin: {
        ready() {// 默认在app.ready之前执行
            // 初始化bootstrap-select组件
            $(".selectpicker").selectpicker();
            $(".bootstrap-select-wrapper").each(function () {
                let phrase = this.dataset.phrase;
                phrase && $(this).find(".filter-option").text(`全部${phrase}`);
            });
            Vue.nextTick(() => {// 在app.ready之后执行的操作，全部放在Vue.nextTick的回调函数里
                this.$broadcast("appReady");
            });
        },
        methods: {
            exportToResCenter(url) {
                let redirectUrl = menuList.find(m => m.id === "resource-center").children.find(c => c.id === "task-list").href;
                JudyUtils.loadingMask.show();
                fireGetJSON(url, () => {
                    window.location = redirectUrl;
                    JudyUtils.loadingMask.hide();
                });
            }
        }
    },
    loadingMask: {
        show: () => $(".loading-mask").show(),
        hide: () => $(".loading-mask").hide()
    },
    getPagingInfo($this) {
        let carrier = {};
        $this.$broadcast("getPagingInfo", carrier);
        return carrier;
    },
    getRandomID: (seed = 5) => `${Date.now()}${JudyUtils.keepDecimals(Math.floor(seed * 10 * Math.random()), seed)}`,
    doConfirm(data, $this) {
        swal({
            title: data.title || "确定删除选中的这些数据吗？",
            html: true,
            showCancelButton: true,
            closeOnConfirm: false,
        }, () => {
            firePostData({
                url: data.apiPath || "/api/delete",
                data: data.data || {},
                success: () => {$this.query();}
            });
        });
    },
    getOriginData(object) {
        return JSON.parse(JSON.stringify(object));
    },
    handleKeyMapping(arr, val, field_in="key", field_out="name") {
        for (let i = 0, len = arr.length; i < len; i++) {
            if (arr[i][field_in] == val) {
                return arr[i][field_out];
            }
        }
        // 找不到映射返回原值
        return val;
    },
    gotoNewPage(path) {
        swal({
            title: "页面迁移",
            text: "3s后自动跳往新页面",
            confirmButtonText: "知道了",
            type: "warning"
        });
        setTimeout(function() {
            window.location.href = path;
        }, 2500);
    }
};

export function getFullPathFN(props = []) {
    return (prefix, obj) => {
        let fullUrl = `${prefix}?`;
        if (JudyUtils.isFilledArray(props)) {
            fullUrl = `${fullUrl}&${JudyUtils.getQueryStr(this, props)}`
        }
        if (obj) {
            fullUrl = `${fullUrl}&${JudyUtils.getQueryStr(obj)}`
        }
        fullUrl = fullUrl.replace("?&", "?").replace(/\?$/, "");
        return fullUrl;
    }
}

export function fireQueryFN(getFullPath, cb) {
    return () => {
        fireGetJSON(getFullPath(), data => {
            this.$broadcast("broadcastTableData", data);
            $.isFunction(cb) && cb(data);
            Vue.nextTick(() => $(".ps-container").perfectScrollbar().scrollTop(0).scrollLeft(0));
        })
    };
}

export function fireGetJSON(url, success) {
    JudyUtils.loadingMask.show();
    $.getJSON(url, resData => {
        if (resData.error) {
            swal({
                title: "出错啦！",
                text: resData.error_msg,
                type: "warning"
            });
        } else {
            $.isFunction(success) && success(resData);
        }
        JudyUtils.loadingMask.hide();
    });
}

export function firePostData({url, data, success, successTitle = "操作成功！", contentType="application/x-www-form-urlencoded"}) {
    JudyUtils.loadingMask.show();
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        contentType: contentType,
        success: resData => {
            if (resData.error) {
                swal({
                    title: "很抱歉，操作失败了。",
                    text: resData.error_msg,
                    type: "warning"
                });
            } else {
                $.isFunction(success) && success(resData);
                swal({
                    title: successTitle,
                    type: "success"
                });
            }
            JudyUtils.loadingMask.hide();
        }
    });
}


// location查询字符串转对象
export function getUrlParse(location) {
    var queryObj = {};
    var queryStr = location.search.substr(1);

    if (queryStr && queryStr.length) {
        var queryArr = queryStr.split('&');
        var key, value;
        queryArr.forEach((item) => {
            key = item.split('=')[0];
            value = item.split('=')[1] !== 'false' ? decodeURI(item.split('=')[1]) : false;
            queryObj[key] = value;
        });
    }
    return queryObj;
}

export let DTPUtils = {
    init({selector = ".date-time-picker", value = "", allowBlank = true} = {selector: ".date-time-picker", value: "", allowBlank: true}) {
        $(selector).datetimepicker({
            timepicker: false,
            format: "Y-m-d",
            closeOnDateSelect: true,
            value: value,
            defaultDate: value,
            allowBlank: allowBlank,
        });
        return this;
    },
    initDates({target, fromID = "from_date", toID = "to_date"}) {
        let toDate = new Date(), fromDate = new Date(toDate.valueOf() - 30 * 24 * 3600 * 1000);
        this.init({selector: `#${fromID}`, value: fromDate, allowBlank: false});
        this.init({selector: `#${toID}`, value: toDate, allowBlank: false});
        target[fromID] = this.format(fromDate);
        target[toID] = this.format(toDate);
        return this;
    },
    format(date) {
        return `${date.getFullYear()}-${JudyUtils.keepDecimals(date.getMonth() + 1)}-${JudyUtils.keepDecimals(date.getDate())}`;
    },
    parse(dateStr) {
        let [year, month, day] = dateStr.split("-");
        return new Date(year, month - 1, day);
    },
    setAsToday({target, props, allowBlank = false}) {
        let today = new Date(), todayStr = this.format(today);
        props = Array.isArray(props) ? props : [props];
        props.forEach(p => {
            target[p] = todayStr;
            this.init({selector: `#${p}`, value: today, allowBlank});
        });
        return this;
    }
};

export let EHub = (() => {
    let eHub = new Vue(), arr = [];
    return {
        on(name, fn) {
            if (~arr.indexOf(name)) {
                console.warn(`[Judy Warn]: ${name} already registered.`);
            } else {
                arr.push(name);
                eHub.$on(name, fn);
            }
            return this;
        },
        emit(name, ...args) {
            eHub.$emit(name, ...args);
            return this;
        }
    }
})();