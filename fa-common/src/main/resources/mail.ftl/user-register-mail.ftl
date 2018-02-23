<style class="fox_global_style">div.fox_html_content {
    line-height: 1.5;
}

div.fox_html_content {
    font-size: 10pt;
    font-family: 微软雅黑;
    color: rgb(0, 0, 0);
    line-height: 1.5;
}

h2 {
    font-size: 16pt;
    font-family: 微软雅黑;
    color: rgb(255, 20, 20);
    line-height: 1.5;
}
</style>
<div>
<#if xxx?? && (xxx?size > 0) >
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
                style="border: 0.5pt solid windowtext; width: 38pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 宋体; vertical-align: bottom; white-space: nowrap; background-color: rgb(141, 180, 226);">xxx</td>

        </tr>
            <#list xxx! as xxx>
            <item>
            <tr height="19" style="height:14.25pt">
                <td class="xl66" align="left"
                    style="border: 0.5pt solid windowtext; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 微软雅黑; vertical-align: bottom; white-space: nowrap;">${xxx!}</td>
            </tr>
        </item>
            </#list>
        <!--EndFragment-->
        </tbody>
    </table>
  </span>
</#if>
</div>
