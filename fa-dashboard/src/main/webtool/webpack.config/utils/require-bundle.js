var camelcase = require("./camelcase");
var ArrayUtils = require("./array-utils");

module.exports = packages => {
    return ArrayUtils.toArray(packages).map(packageName => {
        var variableName = packageName;
        if (packageName.includes("!")) {
            variableName = packageName.split("!")[1];
            packageName = packageName.split("!")[0];
        } else if (packageName.startsWith(".")) {
            variableName = camelcase(packageName.match(/[^/]+$/)[0]);
        } else if (packageName.includes("-")) {
            variableName = camelcase(packageName, true);
        }
        return `var ${variableName} = require("${packageName}")`;
    }).join(";");
};