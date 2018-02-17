# 系统前端

## 准备工作

> 安装node (v4.x.x)
  下载地址：`https://nodejs.org/dist/latest-v4.x/`

> 安装Node国内包管理工具`cnpm`
  命令行执行：`npm install -g cnpm  --registry=http://registry.npm.taobao.org`
  
> 克隆源码包
  代码仓库：`git clone xxx`
  
> 安装项目依赖包
  项目根目录命令行执行：`cnpm install`

> 启动工程
  项目根目录命令行执行：`npm run wds`，然后打开`127.0.0.1:6060/static/index.html`，正常访问就可以进行开发了

## 项目结构

> app 项目业务代码

> app/biz 页面代码，每一个最子集的文件夹对应一个页面

> app/common/components 页面公用Vue组件(下面有详细说明)

> app/common/css 页面样式

> app/common/js 页面公用js

> app/lib 页面上已引用的第三方库

> app/mock-data 页面使用的模拟数据

> app/app.tpl.html 项目页面布局文件(一般不需要修改)

> webpack.config 项目脚手架配置(一般不需要做修改)

## 项目组件

> app/common/components下组件分类：layout(页面布局组件)、common(公用元素组件,可被其他组件再调用)、form(搜索表单组件)、table(表格和分页组件)、dialog(弹窗业务组件)

> Vue组件简要介绍：props(接收传给该组件的值)、data(定义组件中要使用的变量)、created(组件创建时执行)、methods(组件中定义的方法，通过this来调用)、
  computed(需要动态计算的变量，不能在data中定义)、events(组件中自定义事件)、watch(变量监控，当变化时执行下面的代码)

## 常见说明

> 如需修改项目启动端口，请修改webpack.config/webpack.dev.config.js中105行`port: 6060`

> 如需添加新的代理接口，请依照webpack.config/webpack.dev.config.js中16行后`dev`格式添加好之后，并在89行将`proxy: proxyMapper.jiandong`中`jiandong`改为你添加的接口对象名称

> 在app/common/components下新增的组件，注意需要在app/common/js/components.js文件中进行引入方可在页面中使用

> 新增页面的导航链接统一在app/common/js/constants.js中的menuList变量中进行统一管理，属性`pin: true`表示将链接添加到系统首页的快捷入口

> app/biz目录中每个文件夹对应一个前端页面, 文件夹中app.vue(页面模板和逻辑), index.js(入口文件, 配置页面标题)

> 推荐使用工具: 编辑器插件 vue-for-idea(vue语法) Chrome浏览器插件(vue.js devtools)
