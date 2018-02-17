
// 公用的API请都通过这个SC变量来开放
window.FA = {
    USER_STORAGE: '_user_'
};


window.FA.useAjax=(url, {
        data,
        success =(data)=>{},
        type ='GET',
        contentType='application/x-www-form-urlencoded',
        error=(err)=>{}
    }={})=>{
    $.ajax({
        url:url,
        type,
        data,
        contentType,
        dataType:'json',
        success:(data)=>{
            if(data,success === '302'){
                let u = data.url.split('?');
                let done = window.SC.getParameter('?' + u[1], 'done');
                let redirect_url = window.SC.getParameter('?' + u[1], 'redirect_url');
                let href = u[0];
                if (done) {
                    href += '?done=' + encodeURIComponent(location.href)
                }
                if (redirect_url) {
                    href += '?redirect_url=' + encodeURIComponent(location.href)
                }
                return location.href = href;
            }

            success(data);
        },
        error
    });
};
