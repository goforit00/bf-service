var RequireBundle = require("./utils/require-bundle");
eval(RequireBundle([
    "webpack",
    "webpack-merge",
    "./webpack.base.config!baseConfig",
    "./utils/vue-css-loaders",
    "./utils/console.log!clog",
    "extract-text-webpack-plugin!ExtractTextPlugin",
    "html-webpack-plugin",
    "copy-webpack-plugin"
]));

var proxyMapper = {
    dev: {
        "/*": {
            target: "http://127.0.0.1:8080/",
            secure: false
        }
    }
};

var devConfig = WebpackMerge(baseConfig, {
    devtool: "#eval-source-map",
    vue: {
        loaders: vueCssLoaders({
            sourceMap: false,
            extract: false
        })
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin("vendors", "static/vendors.js"),
        new ExtractTextPlugin("static/[name].css"),

        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin()
    ],
    devServer: {
        contentBase: "./", // 相对于publicPath
        host: "0.0.0.0",
        port: 6060,
        inline: true,
        historyApiFallback: true,
        hot: true,
        proxy: proxyMapper.dev
    }
});

devConfig.chunksObject.forEach(item => {
    devConfig.plugins.push(new HtmlWebpackPlugin({
        filename: `./${item.pathname}.html`,
        template: item.templatePath,
        inject: "body",
        chunks: ["vendors", item.pathname]
    }));
});

module.exports = devConfig;