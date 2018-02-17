

export function MP() {
    return new Promise(function (resolve, reject) {
        window.init = function () {
            resolve(BMap)
        }
        //var baiduScriptElement=document.getElementById("baiduMapScript");
        //baiduScriptElement.type = "text/javascript";
        //baiduScriptElement.src="http://api.map.baidu.com/api?v=2.0&ak=CGULzN8TaVGgtxirprOTBKS1";
        //baiduScriptElement.onerror=reject;
        //
        //baiduScriptElement.appendChild()

        var script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "http://api.map.baidu.com/api?v=2.0&ak=CGULzN8TaVGgtxirprOTBKS1";
        script.onerror = reject;
        document.head.appendChild(script);
    })
}