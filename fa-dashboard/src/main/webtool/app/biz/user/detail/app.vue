<template>

    <div class="layout-content">
        <Row>
            <h3><i-col span="24">用户基本信息</i-col></h3>
        </Row>
        <br>
        <br>
        <Row>
            <i-col span="3">用户名：</i-col>
            <i-col span="3">{{userInfo.userNickName}}</i-col>
        </Row>
        <br>
        <Row>
            <i-col span="3">手机：</i-col>
            <i-col span="3">{{userInfo.phone}}</i-col>
        </Row>
        <br>
        <Row>
            <i-col span="3">邮箱：</i-col>
            <i-col span="3">{{userInfo.email}}</i-col>
        </Row>
        <br>
        <Row>
            <i-col span="3">性别：</i-col>
            <i-col span="3">{{userInfo.sex}}</i-col>
        </Row>
        <br>
        <Row>
            <i-col span="3">默认地址：</i-col>
            <i-col span="3">{{userInfo.defaultAddress}}</i-col>
            <i-col span="3" offset="10"><i-button type="primary" @click="baseInfoModal = true" shape="circle" size="small">基本信息修改</i-button></i-col>
        </Row>


        <br>
        <br>
        <span>---------------------------------------------------------------------------------------------------------------</span>
        <br>
        <br>
        <Row>
            <h3><i-col span="24">用户实名信息</i-col></h3>
        </Row>
        <br>
        <br>
        <Row>
            <i-col span="3">真实姓名：</i-col>
            <i-col span="3">{{userRealInformation.realName}}</i-col>
        </Row>
        <br>
        <Row>
            <i-col span="3">身份证号：</i-col>
            <i-col span="3">{{userRealInformation.idCardNum}}</i-col>
            <i-col span="3" offset="10"><i-button type="primary" @click="realInfoModal = true" shape="circle" size="small">实名信息修改</i-button></i-col>
        </Row>


    </div>

    <!--基本信息修改-->
    <Modal
            :visible.sync="baseInfoModal"
            title="用户基本信息修改"
            @on-ok="changeBaseInfo"
            @on-cancel="cancelChange">
        <Row type="flex" justify="center" align="middle" class="code-row-bg">
            <i-col span="3" offset="3">手机：</i-col>
            <i-col span="18"><i-input :value.sync="userInfo.phone" placeholder="请输入..." style="width: 300px"></i-input></i-col>
        </Row>
        <Row type="flex" justify="center" align="middle" class="code-row-bg">
            <i-col span="3" offset="3">性别：</i-col>
            <i-col span="18">
                <Radio-group :model.sync="userInfo.sex">
                    <Radio value="man">
                        <Icon type="man"></Icon>
                        <span>男</span>
                    </Radio>
                    <Radio value="woman">
                        <Icon type="woman"></Icon>
                        <span>女</span>
                    </Radio>

                </Radio-group>
            </i-col>
        </Row>
        <Row type="flex" justify="center" align="middle" class="code-row-bg">
            <i-col span="3" offset="3">邮箱：</i-col>
            <i-col span="18"><i-input :value.sync="userInfo.email" placeholder="请输入..." style="width: 300px"></i-input></i-col>
        </Row>
        <Row type="flex" justify="center" align="middle" class="code-row-bg">
            <i-col span="3" offset="3">默认地址：</i-col>
            <i-col span="18"><i-input :value.sync="userInfo.defaultAddress" placeholder="请输入..." style="width: 300px"></i-input></i-col>
        </Row>
    </Modal>


    <!--实名信息修改-->
    <Modal
            :visible.sync="realInfoModal"
            title="用户实名信息修改"
            @on-ok="changeRealInfo"
            @on-cancel="cancelChange">
        <Row type="flex" justify="center" align="middle" class="code-row-bg">
            <i-col span="3" offset="3">真实姓名：</i-col>
            <i-col span="18"><i-input :value.sync="userRealInformation.realName" placeholder="请输入..." style="width: 300px"></i-input></i-col>
        </Row>
        <Row type="flex" justify="center" align="middle" class="code-row-bg">
            <i-col span="3" offset="3">身份证号：</i-col>
            <i-col span="18"><i-input :value.sync="userRealInformation.idCardNum" placeholder="请输入..." style="width: 300px"></i-input></i-col>
        </Row>
    </Modal>


</template>

<script>
    import api from './api.js'
    import { Modal } from 'iview'


    export default {
        data() {
            return {
                userInfo:{
                    userNickName:"",
                    email:"",
                    realName:"",
                    phone:"",
                    sex:"",
                    defaultAddress:""
                },
                userRealInformation:{
                    idCardNum:"",
                    realName:""
                },
                baseInfoModal:false,
                realInfoModal:false
            }
        },

        ready() {
            this.getUserInfo();
        },

        methods: {
            getUserInfo(){
                var username=getCurrentUsernameFromCookie()
                api.getUserDetailInfo(username).then((data)=>{
                    if(data.success){
                        this.userInfo=data.data;
                        this.userRealInformation=data.data.realInformation;
                    }else{
                        alert("调用获取用户详细信息失败:",data.message)
                    }

                }).catch((e)=>{
                    console.log("调用获取用户详细信息异常:",e)
                    alert("调用获取用户详细信息异常:"+ e.statusText,e)
                });
            },

            changeBaseInfo(){
                var username=getCurrentUsernameFromCookie()
                api.changeUserBaseInfo(username,this.userInfo).then((data)=>{
                    if(data.success){
                        this.userInfo=data.data;
                        this.$Message.success('修改成功');
                    }else{
                        alert("修改用户基本信息失败:",data.message)
                    }
                }).catch((e)=>{
                    console.log(e);
                    alert("调用修改用户基本信息异常:"+ e.statusText)
                });
            },

            changeRealInfo(){
                var username=getCurrentUsernameFromCookie()
                api.changeUserRealInfo(username,this.userRealInformation).then((data)=>{
                    if(data.success){
                        this.userRealInformation=data.data;
                        this.$Message.success('修改成功');
                    }else{
                        alert("调用修改用户实名信息失败:",data.message)
                    }
                }).catch((e)=>{
                    console.log(e);
                    alert("调用修改用户实名信息异常:"+ e.statusText)
                });
            },

            cancelChange(){

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
