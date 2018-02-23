const HtmlWebpackPlugin = require('html-webpack-plugin');

const path = require('path');

module.exports = {
  devtool: 'cheap-module-eval-source-map',
  entry: {
    server: path.resolve(__dirname, 'app', 'js', 'server.js'),
    client: path.resolve(__dirname, 'app', 'js', 'client.js'),
  },
  output: {
    path: path.join(__dirname, 'public'),
    filename: './[name].js',
  },
  resolve: {
    extensions: ['.js', '.css'],
    alias: {
      components: path.resolve(__dirname, 'app', 'js', 'components'),
    },
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: [
          {
            loader: 'babel-loader',
            options: {
              presets: ['es2015', 'react'],
            },
          },
        ],
      },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      hash: true,
      template: path.resolve(__dirname, 'app', 'index.html'),
      filename: 'index.html',
      minify: {
        collapseWhitespace: false,
      },
      excludeChunks: ['server'],
    }),
  ],
};
