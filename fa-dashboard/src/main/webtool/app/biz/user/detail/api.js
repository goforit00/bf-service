export default{

    //获取用户详细信息
    getUserDetailInfo(nickName){
        return new Promise((resolve, reject)=> {
            window.FA.useAjax('/restful/user/getDetail/'+nickName,
                {
                data:{},
                type: 'GET',
                contentType:'application/json',
                success: (data)=> {
                    resolve(data)
                },
                error: reject
            });
        });
    },

    //修改用户基本信息
    changeUserBaseInfo(nickName,userInfo){
        return new Promise((resolve, reject)=> {
            window.FA.useAjax('/restful/user/changeBase/'+nickName,
                {
                    data:userInfo,
                    type: 'POST',
                    success: (data)=> {
                        resolve(data)
                    },
                    error: reject
                });
        });
    },

    //修改用户实名信息
    changeUserRealInfo(nickName,realUserInfo){
        return new Promise((resolve, reject)=> {
            window.FA.useAjax('/restful/user/changeReal/'+nickName,
                {
                    data:realUserInfo,
                    type: 'POST',
                    success: (data)=> {
                        resolve(data)
                    },
                    error: reject
                });
        });
    }

}