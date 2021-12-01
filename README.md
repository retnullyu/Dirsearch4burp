# 	burp加载dirsearch
## 项目介绍
项目改自[sqlmap4burp++](https://github.com/c0ny1/sqlmap4burp-plus-plus),其实本质都是一样，调用python启动本地[dirsearch](https://github.com/maurosoria/dirsearch),sqlmap读取request数据改为--raw即可

## 插件编译

```
mvn clean package
```

## 参考项目
* https://github.com/blueroutecn/Burpsuite4Extender
* https://github.com/difcareer/sqlmap4burp
* https://github.com/c0ny1/sqlmap4burp-plus-plus
