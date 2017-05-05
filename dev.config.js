import path from 'path';

const BASE_PATH = path.join(__dirname, '../');

export default {
    /**
     * �������˿�
     */
    SERVER_PORT: 8180,

    /**
     * ·����ַ
     */
    PATH_SRC: path.join(BASE_PATH, 'websrc'),
    PATH_DIST: path.join(BASE_PATH, 'm2m'),
    PATH_NODE_MODULES: path.join(BASE_PATH, 'node_modules'),


    /**
     * Webpack ����
     */
    WEBPACK_FRAMEWORK: [ 'babel-polyfill', 'framework_js', 'pasp_js', 'pasp_css' ],
    WEBPACK_ENTRY: [ 'babel-polyfill', path.join(BASE_PATH, 'websrc/app.js') ],
    WEBPACK_ENTRY_ORDER: { 'vendor': 0, framework: 1, app: 2 },

    /**
     * ָ��ͼƬת����BASE64����������
     * ��ͼƬ��СС��ָ��������ʱת��ΪBASE64�Լ���������������ֱ��ʹ��ͼƬ�ļ�
     */
    BASE64_LIMIT: 2048,

    /**
     * CSS Prefix �б�
     */
    CSS_PREFIX_LIST: ['> 1%', 'ie > 6', 'Firefox > 20']
}