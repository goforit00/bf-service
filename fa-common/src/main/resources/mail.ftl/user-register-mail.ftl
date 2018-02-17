<style class="fox_global_style">div.fox_html_content {
    line-height: 1.5;
}

div.fox_html_content {
    font-size: 10pt;
    font-family: 微软雅黑;
    color: rgb(0, 0, 0);
    line-height: 1.5;
}
h2{ font-size: 16pt;
    font-family: 微软雅黑;
    color: rgb(255, 20, 20);
    line-height: 1.5;}
</style>
<div>
<#if emergencyOrderList?? && (emergencyOrderList?size > 0) >
    <h2>异常订单如下,请尽快处理</h2>
    <span style="orphans: 2; widows: 2;">
    <table border="0" cellpadding="0" cellspacing="0" width="672" style="border-collapse: collapse;width:504pt">
        <!--StartFragment-->
        <colgroup>
            <col width="51" span="2" style="mso-width-source:userset;mso-width-alt:1632; width:38pt">
            <col width="72" style="mso-width-source:userset;mso-width-alt:2304;width:54pt">
            <col width="80" style="mso-width-source:userset;mso-width-alt:2560;width:60pt">
            <col width="81" style="mso-width-source:userset;mso-width-alt:2592;width:61pt">
            <col width="80" style="mso-width-source:userset;mso-width-alt:2560;width:60pt">
            <col width="81" span="2" style="mso-width-source:userset;mso-width-alt:2592; width:61pt">
            <col width="95" style="mso-width-source:userset;mso-width-alt:3040;width:71pt">
        </colgroup>
        <tbody>
        <tr height="20" style="height:15.0pt">
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 38pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">包裹单号</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 54pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">订单状态</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 54pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">支付距今</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 60pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">错误类型</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 60pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">报错信息</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 60pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">建议处理方式</td>

        </tr>
            <#list emergencyOrderList! as order>
            <item>
            <tr height="19" style="height:14.25pt">
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${order.shippingNo!}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${order.innerStatus!}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${order.paidSoFar!}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${order.errorType!''}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${order.errorMessage!''}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${order.suggestMeasure!''}</td>
            </tr>
        </item>
            </#list>
        <!--EndFragment-->
        </tbody>
    </table>
  </span>
</#if>

    <h3>近七日订单下传时效合规率统计</h3>
    <span style="orphans: 2; widows: 2;">
    <table border="0" cellpadding="0" cellspacing="0" width="672" style="border-collapse: collapse;width:504pt">
        <!--StartFragment-->
        <colgroup>
            <col width="51" span="2" style="mso-width-source:userset;mso-width-alt:1632; width:38pt">
            <col width="72" style="mso-width-source:userset;mso-width-alt:2304;width:54pt">
            <col width="80" style="mso-width-source:userset;mso-width-alt:2560;width:60pt">
            <col width="81" style="mso-width-source:userset;mso-width-alt:2592;width:61pt">
        </colgroup>
        <tbody>
        <tr height="20" style="height:15.0pt">
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 38pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">支付时间</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 54pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">杭州国内仓下传合规率</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 54pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">金义仓下传合规率</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 54pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">杭州保税仓下传合规率</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 54pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">杭州保税2号仓下传合规率</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 60pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">宁波保税仓下传合规率</td>

        </tr>
        <#list downloadQualifiedList! as record>
        <item>
            <tr height="19" style="height:14.25pt">
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${record.paidDate!}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${record.warehousePercentMap["HZ2"]!''}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${record.warehousePercentMap["JY1"]!''}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${record.warehousePercentMap["HGH1"]!''}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${record.warehousePercentMap["HGH2"]!''}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${record.warehousePercentMap["NGB1"]!''}</td>
            </tr>
        </item>
        </#list>
        <!--EndFragment-->
        </tbody>
    </table>
  </span>

    <h3>昨日订单下传时效分布统计</h3>
    <span style="orphans: 2; widows: 2;">
    <table border="0" cellpadding="0" cellspacing="0" width="672" style="border-collapse: collapse;width:504pt">
        <!--StartFragment-->
        <colgroup>
            <col width="51" span="2" style="mso-width-source:userset;mso-width-alt:1632; width:38pt">
            <col width="72" style="mso-width-source:userset;mso-width-alt:2304;width:54pt">
            <col width="80" style="mso-width-source:userset;mso-width-alt:2560;width:60pt">
        </colgroup>
        <tbody>
        <tr height="20" style="height:15.0pt">
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 38pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">仓库</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 54pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">时间区间</td>
            <td class="xl68" width="80"
                style="border: 0.5pt solid windowtext; width: 54pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">订单数</td>

        </tr>
        <#list downloadTimeRangeList! as record>
        <item>
            <tr height="19" style="height:14.25pt">
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${record.warehouse!}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${record.timeRange!}</td>
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${record.orderNum}</td>
            </tr>
        </item>
        </#list>
        <!--EndFragment-->
        </tbody>
    </table>
  </span>
</div>
