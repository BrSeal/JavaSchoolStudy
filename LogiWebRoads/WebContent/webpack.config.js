const path = require("path");

module.exports = {
    mode: "development",
    entry: {
        app: path.join(__dirname, "resources/js/src/App.js")
    },
    output: {
        path: path.join(__dirname, "resources/js"),
        filename: 'main.js'
    },
    devtool: 'source-map',
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: '/node_modules/',
                loader: 'babel-loader'
            }
        ]
    }
};