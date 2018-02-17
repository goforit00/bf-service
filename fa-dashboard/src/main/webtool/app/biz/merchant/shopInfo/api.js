export default{

    //获取用户下的店铺
    getShopInfosByManagerCode(data){
        return new Promise((resolve, reject)=> {
            window.FA.useAjax('/restful/merchant/shopInfo/getByManagerCode',
                {
                data:data,
                type: 'GET',
                contentType:'application/json',
                success: (data)=> {
                    resolve(data)
                },
                error: reject
            });
        });
    },

    getShopInfoByShopCode(shopCode){
        return new Promise((resolve, reject)=> {
            window.FA.useAjax('/restful/merchant/shopInfo/'+shopCode,
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

    createShopInfo(data,managerCode){
        return new Promise((resolve, reject)=> {
            window.FA.useAjax('/restful/merchant/shopInfo/'+managerCode+'/create',
                {
                    data,
                    type: 'POST',
                    //contentType:'application/json',
                    success: (data)=> {
                        resolve(data)
                    },
                    error: reject
                });
        });
    }

}