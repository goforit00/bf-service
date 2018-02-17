var RequireBundle = require("./utils/require-bundle");
eval(RequireBundle([
    "webpack",
    "webpack-merge",
    "./webpack.base.config!baseConfig",
    "./utils/vue-css-loaders",
    "extract-text-webpack-plugin!ExtractTextPlugin",
    "html-webpack-plugin"
]));

var hashLen = 8;

var proConfig = WebpackMerge(baseConfig, {
    devtool: false,
    output: {
        filename: `static/[name].[chunkhash:${hashLen}].js`,
        chunkFilename: `static/[id].[chunkhash:${hashLen}].js`
    },
    vue: {
        loaders: vueCssLoaders({
            sourceMap: false,
            extract: true
        })
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin("vendors", `static/vendors.[hash:${hashLen}].js`),
        new ExtractTextPlugin(`static/[name].[contenthash:${hashLen}].css`),

        // This plugin looks for similar chunks and files
        // and merges them for better caching by the user
        new webpack.optimize.DedupePlugin(),

        // This plugin prevents Webpack from creating chunks
        // that would be too small to be worth loading separately
        new webpack.optimize.MinChunkSizePlugin({
            minChunkSize: 51200, // ~50kb
        }),

        // This plugin minifies all the javascript code of the final bundle
        new webpack.optimize.UglifyJsPlugin({
            mangle: true,
            compress: {
                warnings: false // Suppress uglification warnings
            }
        }),

        // This plugin defines various variables that we can set to false
        // in production to avoid code related to them from being compiled
        // in our final bundle
        new webpack.DefinePlugin({
            __SERVER__:      false,
            __DEVELOPMENT__: false,
            __DEVTOOLS__:    false,
            "process.env":   {
                NODE_ENV: "'production'"
            },
        })
    ]
});

proConfig.chunksObject.forEach(item => {
    proConfig.plugins.push(new HtmlWebpackPlugin({
        filename: `./${item.pathname}.html`,
        template: item.templatePath,
        inject: "body",
        minify: { //压缩HTML文件
            removeComments: true, //移除HTML中的注释
            collapseWhitespace: true //删除空白符与换行符
        },
        chunks: ["vendors", item.pathname]
    }));
});

module.exports = proConfig;