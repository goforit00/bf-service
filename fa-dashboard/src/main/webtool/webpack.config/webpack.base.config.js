var RequireBundle = require("./utils/require-bundle");
eval(RequireBundle(["webpack", "glob", "path", "fs",
                    "./app.config",
                    "./utils/console.log!clog",
                    "copy-webpack-plugin",
                    "extract-text-webpack-plugin!ExtractTextPlugin"]));

var bizPath = "./app/biz/", entry = getEntry(`${bizPath}**/index.js`, bizPath);

entry.vendors = ["vue"];

var chunksObject = Object.keys(entry).map(pathname => {
    var templatePath = "app/app.tpl.html";

    try {
        var appTplPath = `${bizPath}${pathname}/index.html`;
        var s = fs.statSync(appTplPath);
        if (s.isFile()) {
            templatePath = appTplPath;
        }
    } catch (err) {
        if (err.code !== "ENOENT") {
            throw err;
        }
    }

    return {pathname, templatePath};
});

module.exports = {
    chunksObject,// 仅作中转之用
    entry,
    output: {
        path: appConfig.outputPath || "./build",
        publicPath: appConfig.publicPath || "/bui/",
        filename: "static/[name].js",
        chunkFilename: "static/[id].js"
    },
    resolve: {
        root: path.resolve("./"),
        extensions: ["", ".js", ".vue"]
    },
    resolveLoader: {
        fallback: [path.join(__dirname, '../node_modules')]
    },
    module: {
        loaders: [
            {
                test: /\.(png|jpg|gif|svg|woff2?|eot|ttf)(\?.*)?$/,
                loader: "url",
                query: {
                    limit: 100000,
                    name: "fonts/[name].[ext]?[hash:7]"
                }
            },
            {
                test: /\.scss$/,
                loader: ExtractTextPlugin.extract('style', 'css!sass'),// No Hot Module Replacement!!
                //loader: "style!css!sass"
            },
            {
                test: /\.js$/,
                loader: "babel",
                exclude: /node_modules/
            },
            {
                test: /\.vue$/,
                loader: "vue"
            },
            {
                test: /\.pug$/,
                loader: "pug"
            },
            {
                //test: /\.css$/,
                //loader: 'style!css'
                test: /\.css/,
                loader: ExtractTextPlugin.extract("style-loader", "css-loader")
            }
        ]
    },
    vue:{
        loaders:cssLoaders()
    },
    plugins: [
        //new webpack.ProvidePlugin({ //加载jq
        //    $: "jquery"
        //}),
        new webpack.optimize.CommonsChunkPlugin("vendors", "vendors.js"),
        // This plugin optimizes chunks and modules by
        // how much they are used in your app
        new webpack.optimize.OccurenceOrderPlugin(),
        new CopyWebpackPlugin([
            "lib/font-awesome/css/font-awesome.min.css",
            "lib/font-awesome/fonts",
            "lib/admin-lte-2.3.5/css/AdminLTE.min.css",
            "lib/admin-lte-2.3.5/css/skins/skin-green.min.css",
            "lib/admin-lte-2.3.5/js/app.min.js",
            "lib/bootstrap",
            "lib/perfect-scrollbar",
            "lib/jquery",
            "lib/highcharts-4.2.5",
            "lib/sweetalert",
            "common/css/base.css",
            "common/js/base.js",
            "common/js/common.js",
        ].map(path => ({from: `app/${path}`, to: path})))
    ]
};

function getEntry(globPath, basePath) {
    var files = glob.sync(globPath), entries = {};
    for (var entry of files) {
        var dirname = path.dirname(entry);
        var pathname = dirname.replace(new RegExp("^" + basePath), "");
        entries[pathname] = entry;
    }
    return entries;
}

function cssLoaders(options){
    options = options || {}
    // generate loader string to be used with extract text plugin
    function generateLoaders (loaders) {
        var sourceLoader = loaders.map(function (loader) {
            var extraParamChar
            if (/\?/.test(loader)) {
                loader = loader.replace(/\?/, '-loader?')
                extraParamChar = '&'
            } else {
                loader = loader + '-loader'
                extraParamChar = '?'
            }
            return loader + (options.sourceMap ? extraParamChar + 'sourceMap' : '')
        }).join('!')

        if (options.extract) {
            return ExtractTextPlugin.extract('vue-style-loader', sourceLoader)
        } else {
            return ['vue-style-loader', sourceLoader].join('!')
        }
    }

    // http://vuejs.github.io/vue-loader/configurations/extract-css.html
    return {
        css: generateLoaders(['css']),
        postcss: generateLoaders(['css']),
        less: generateLoaders(['css', 'less']),
        sass: generateLoaders(['css', 'sass?indentedSyntax']),
        scss: generateLoaders(['css', 'sass']),
        stylus: generateLoaders(['css', 'stylus']),
        styl: generateLoaders(['css', 'stylus'])
    }
}