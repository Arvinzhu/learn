import path from 'path';

const BASE_PATH = path.join(__dirname, '../');

export default {
    /**
     * 服务器端口
     */
    SERVER_PORT: 8180,

    /**
     * 路径地址
     */
    PATH_SRC: path.join(BASE_PATH, 'websrc'),
    PATH_DIST: path.join(BASE_PATH, 'm2m'),
    PATH_NODE_MODULES: path.join(BASE_PATH, 'node_modules'),


    /**
     * Webpack 配置
     */
    WEBPACK_FRAMEWORK: [ 'babel-polyfill', 'framework_js', 'pasp_js', 'pasp_css' ],
    WEBPACK_ENTRY: [ 'babel-polyfill', path.join(BASE_PATH, 'websrc/app.js') ],
    WEBPACK_ENTRY_ORDER: { 'vendor': 0, framework: 1, app: 2 },

    /**
     * 指定图片转换成BASE64的最大比特数
     * 当图片大小小于指定比特数时转换为BASE64以减少请求数，否则直接使用图片文件
     */
    BASE64_LIMIT: 2048,

    /**
     * CSS Prefix 列表
     */
    CSS_PREFIX_LIST: ['> 1%', 'ie > 6', 'Firefox > 20']
}