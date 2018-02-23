var path = require('path');

module.exports = {
    entry: './src/main/js/app.js',
    devtool: 'sourcemaps',
    cache: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/js/bundle.js'
    },

    module: {
        rules: [
            {
                use: [
                    {
                        loader: 'babel-loader'
                    }
                ]
            }
            
        ],
    }
};