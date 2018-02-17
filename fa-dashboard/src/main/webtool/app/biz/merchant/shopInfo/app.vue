<template>

    <div class="layout-content">

        <div class="layout-content-main">

            <i-button type="primary" @click="createShopModal=true">新增店铺</i-button>

            <br>
            <br>
            <br>
            <div><span>------------------------已有店铺-------------------------</span></div>
            <br>
            <br>
            <br>
            <div v-if="shopInfos.length>0">
                <div v-for="item in shopInfos">
                    <Card style="width:350px" @click="showDetail(item)">
                        <p slot="title">
                            <a>{{item.shopName}}</a>
                        </p>
                        <span>{{item.description}}</span>
                    </Card>
                    <br>
                </div>
            </div>
            <div v-else="shopInfos.length=0">
                您还没有店铺，请创建
            </div>

        </div>


        <!--新增商铺页面-->
        <Modal
                :visible.sync="createShopModal"
                title="新增店铺"
                @on-ok="doCreatedShopModal"
                @on-cancel="cancelCreatedShopModal">
            <Row type="flex" justify="center" align="middle" class="code-row-bg">
                <i-col span="3" offset="3">店铺名称：</i-col>
                <i-col span="18"><i-input :value.sync="createShopInfo.shopName" placeholder="请输入..." style="width: 300px"></i-input></i-col>
            </Row>
            <br>
            <Row type="flex" justify="center" align="middle" class="code-row-bg">
                <i-col span="3" offset="3">店铺描述：</i-col>
                <i-col span="18"><i-input :value.sync="createShopInfo.description" placeholder="请输入..." style="width: 300px"></i-input></i-col>
            </Row>
            <br>
            <Row type="flex" justify="start" align="middle" class="code-row-bg">
                <i-col span="2" offset="3">省：</i-col>
                <i-col span="4"><i-input :value.sync="createShopInfo.province" placeholder="浙江省" ></i-input></i-col>
                <i-col span="2" offset="1">市：</i-col>
                <i-col span="4"><i-input :value.sync="createShopInfo.city" placeholder="杭州市"></i-input></i-col>
                <i-col span="2" offset="1">区/县：</i-col>
                <i-col span="4"><i-input :value.sync="createShopInfo.county" placeholder="西湖区"></i-input></i-col>
            </Row>
            <Row type="flex" justify="start" align="middle" class="code-row-bg">

            </Row>
            <br>
            <Row type="flex" justify="start" align="middle" class="code-row-bg">
                <i-col span="3" offset="3">详细地址：</i-col>
                <i-col span="12"><i-input :value.sync="createShopInfo.address" placeholder="如：九和路xxx号" style="width: 250px"></i-input></i-col>
                <i-col span="3" offset="1"><i-button type="primary" size="small" @click="checkAddress">地址校验(点两次)</i-button></i-col>
            </Row>
            <br>
            <Row type="flex" justify="start" align="middle" class="code-row-bg">
                <i-col span="9" offset="3" v-if="createShopInfo.posLongitude">经度：{{createShopInfo.posLongitude}}</i-col>
                <i-col span="9" offset="3" v-if="createShopInfo.posLatitude">纬度：{{createShopInfo.posLatitude}}</i-col>
            </Row>
            <br>
            <Row type="flex" justify="center" align="middle" class="code-row-bg" v-if="createShopInfo.posLongitude && createShopInfo.posLatitude">
                <i-col span="24">
                    <div id="shopBaiduMap" style="height: 300px"></div>
                </i-col>
            </Row>
        </Modal>


    </div>

</template>

<script>
    import api from './api.js'
    import { Modal } from 'iview'


    export default {
        data() {
            return {
                managerCode:"",
                shopInfos:[],

                queryParams:{},

                createShopInfo:{
                    "shopName":"",
                    "description":"",
                    "province":"",
                    "city":"",
                    "county":"",
                    "address":"",
//                    经度
                    "posLongitude":"",
//                    纬度
                    "posLatitude":""

                },

                createShopModal:false,

                isAddressOK:false
            }
        },

        ready() {

            var managerCode=getCurrentMerchantManagerCodeFromCookie()
            this.managerCode=managerCode

            this.queryParams={'managerCode':managerCode}

            this.getAllMerchantShopInfo();
        },

        methods: {

            getAllMerchantShopInfo(){

                console.log(this.queryParams)
                api.getShopInfosByManagerCode(this.queryParams).then((data)=>{
                    if(data.success){
                        this.shopInfos=data.data;
                    }else{
                        alert("获取店铺信息失败:",data.message)
                    }

                }).catch((e)=>{
                    console.log("获取店铺信息异常:",e)
                    alert("获取店铺信息异常:"+ e.statusText,e)
                });
            },

            showDetail(item){
                window.location.href = '/static/merchant/shopInfo/detail.html?shopCode='+item.shopCode;
            },

            showCreateShopInfo(){
                this.generateMap()
            },

            doCreatedShopModal () {

                if(!this.isAddressOK){
                    this.$Message.error('请先验证地址正确性');
                    return;
                }

                api.createShopInfo(this.createShopInfo,this.managerCode).then((data)=>{
                    if(data.success){
                        this.shopInfos.push(data.data);
                        this.$Message.error('创建店铺成功');

                        this.getAllMerchantShopInfo();
                    }else{
                        alert("创建店铺失败:",data.message)
                    }

                }).catch((e)=>{
                    console.log(e)
                    alert("创建店铺异常:",e)
                });

            },

            cancelCreatedShopModal () {
                this.$Message.info('点击了取消');
            },

            generateMap(){
                var map = new BMap.Map("shopBaiduMap");    // 创建Map实例
                this.createShopInfo.posLongitude=120.265096;
                this.createShopInfo.posLatitude=30.31316;
                var point = new BMap.Point(this.createShopInfo.posLongitude, this.createShopInfo.posLatitude);
//                var point = new BMap.Point(120.265096,30.31316);
                map.centerAndZoom(point,17);
                map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
            },

            checkAddress(){
                var shopInfo=this.createShopInfo;

                if( !shopInfo.province || !shopInfo.city || !shopInfo.county || !shopInfo.address){
                    this.isAddressOK=false;
                    this.$Message.error('省市区地址信息不能为空');
                    return;
                }


//                TODO 点两次才行  需要解决
                var map = new BMap.Map("shopBaiduMap");

                var myGeo = new BMap.Geocoder();
                var self=this;
                var fullAddress=shopInfo.province+shopInfo.city+shopInfo.county+shopInfo.address
                myGeo.getPoint(fullAddress, function(point){
                    if (point) {
                        self.createShopInfo.posLongitude=point.lng
                        self.createShopInfo.posLatitude=point.lat

                        map.centerAndZoom(point, 19);
                        map.addOverlay(new BMap.Marker(point));

//                        逻辑有漏洞
                        self.isAddressOK=true;
                    }else{
                        alert("您选择地址没有解析到结果!");
                    }
                }, "北京市");




            }
        },
        components: {

        }
    }
</script>

<style scoped>
    .modal-dialog span {
        padding: 4px 10px;
    }
    .modal-dialog select {
        padding: 2px 0 2px 8px;
    }
    .modal-dialog div.input-group {
        width: 100%;
    }
    .modal-dialog span.input-group-addon {
        width: 110px;
    }
</style>
