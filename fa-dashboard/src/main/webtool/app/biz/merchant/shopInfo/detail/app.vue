<template>

    <div class="layout-content">

        <div class="layout-content-main">

            <Row type="flex" justify="start" align="top" class="code-row-bg">
                <i-col span="10">
                    <br>
                    <Row>
                        <i-col span="8">店铺唯一标识：</i-col>
                        <i-col span="16">{{shopInfo.shopCode}}</i-col>
                    </Row>
                    <br>
                    <Row>
                        <i-col span="8">店铺名称：</i-col>
                        <i-col span="16">{{shopInfo.shopName}}</i-col>
                    </Row>
                    <br>
                    <Row v-if="shopInfo.companyCode">
                        <i-col span="8">所属公司编码：</i-col>
                        <i-col span="16">{{shopInfo.companyCode}}</i-col>
                    </Row>
                    <br>
                    <Row>
                        <i-col span="8">店铺地址：</i-col>
                        <i-col span="16">{{shopInfo.province}}-{{shopInfo.city}}-{{shopInfo.county}}-{{shopInfo.address}}</i-col>
                    </Row>
                    <br>
                    <Row>
                        <i-col span="8">状态：</i-col>
                        <i-col span="16">{{shopInfo.status}}</i-col>
                    </Row>
                    <br>
                    <Row>
                        <i-col span="8">描述：</i-col>
                        <i-col span="16">{{shopInfo.description}}</i-col>
                    </Row>
                    <br>
                    <Row>
                        <i-col span="8">地址位置经度：</i-col>
                        <i-col span="16">{{shopInfo.posLongitude}}</i-col>
                    </Row>
                    <br>
                    <Row>
                        <i-col span="8">地理位置维度：</i-col>
                        <i-col span="16">{{shopInfo.posLatitude}}</i-col>
                    </Row>
                </i-col>
                <i-col span="14" >
                    <div id="shopBaiduMap" style="height: 300px"></div>
                </i-col>
            </Row>
            <br>
            <Row type="flex" justify="start" align="middle" class="code-row-bg">
                <i-col span="3" offset="1"><i-button type="primary" @click="updateShopModal=true">修改</i-button></i-col>
            </Row>


            <!--修改商铺页面-->
            <Modal
                    :visible.sync="updateShopModal"
                    title="修改店铺"
                    @on-ok="doUpdateShopModal"
                    @on-cancel="cancelUpdateShopModal">
                <Row type="flex" justify="center" align="middle" class="code-row-bg">
                    <i-col span="3" offset="3">店铺名称：</i-col>
                    <i-col span="18"><i-input :value.sync="updateShopInfo.shopName" placeholder="请输入..." style="width: 300px"></i-input></i-col>
                </Row>
                <Row type="flex" justify="center" align="middle" class="code-row-bg">
                    <i-col span="3" offset="3">店铺描述：</i-col>
                    <i-col span="18"><i-input :value.sync="updateShopInfo.description" placeholder="请输入..." style="width: 300px"></i-input></i-col>
                </Row>
                <Row type="flex" justify="center" align="middle" class="code-row-bg">
                    <i-col span="3" offset="3">省：</i-col>
                    <i-col span="3"><i-input :value.sync="updateShopInfo.province" placeholder="浙江省" style="width: 300px"></i-input></i-col>
                    <i-col span="3">市：</i-col>
                    <i-col span="3"><i-input :value.sync="updateShopInfo.city" placeholder="杭州市" style="width: 300px"></i-input></i-col>
                    <i-col span="3">区/县：</i-col>
                    <i-col span="6"><i-input :value.sync="updateShopInfo.county" placeholder="西湖区" style="width: 300px"></i-input></i-col>
                </Row>
                <Row type="flex" justify="center" align="middle" class="code-row-bg">
                    <i-col span="3" offset="3">详细地址：</i-col>
                    <i-col span="18"><i-input :value.sync="updateShopInfo.address" placeholder="如：九和路xxx号" style="width: 300px"></i-input></i-col>
                </Row>
                <Row type="flex" justify="center" align="middle" class="code-row-bg">
                    <i-col span="24">
                        <div id="updateShopBaiduMap" style="height: 300px"></div>
                    </i-col>
                </Row>
            </Modal>
        </div>

    </div>

</template>

<script>
    import api from '../api.js'
    import { Modal } from 'iview'
    import {getUrlParse} from 'app/common/js/utils.js'
    import {MP} from 'app/common/js/map.js'


    export default {
        data() {
            return {
                shopCode:"",
                shopInfo:{},
                updateShopInfo:{},

                updateShopModal:false
            }
        },

        ready() {
            var params=getUrlParse(location)
            this.shopCode=params.shopCode

            this.getShopInfoDetail();
        },

        methods: {

            getShopInfoDetail(){

                api.getShopInfoByShopCode(this.shopCode).then((data)=>{
                    if(data.success){
                        this.shopInfo=data.data;
                        this.generateMap()

                    }else{
                        alert("获取店铺详情失败:",data.message)
                    }

                }).catch((e)=>{
                    console.log("获取店铺详情异常:",e)
                    alert("获取店铺详情异常:"+ e.statusText,e)
                });
            },

            generateMap(){
                var map = new BMap.Map("shopBaiduMap");    // 创建Map实例
                var point = new BMap.Point(this.shopInfo.posLongitude, this.shopInfo.posLatitude);
//                var point = new BMap.Point(120.265096,30.31316);
                map.centerAndZoom(point,17);
                map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
            },

            initUpdateShopModal(){
                $.extend(this.updateShopInfo,this.shopInfo)
                var map = new BMap.Map("updateShopBaiduMap");    // 创建Map实例
                var point = new BMap.Point(this.updateShopInfo.posLongitude, this.updateShopInfo.posLatitude);
                map.centerAndZoom(point,17);
                map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

            },

            doUpdateShopModal(){
                this.$Message.info('点击了确定');
            },

            cancelUpdateShopModal(){
                this.$Message.info('点击了取消');

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
