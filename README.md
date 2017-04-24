# MyMvpHelper

## 说明

只为学习使用


```java

//获取正在编辑的文件
PsiJavaFile javaFile = (PsiJavaFile) e.getData(CommonDataKeys.PSI_FILE);

javaFile.getPackageName()//包名

javaFile.getName()//文件名 qqq.java

//获取编辑的文件,可以拿到绝对路径和名称
VirtualFile currentFile = DataKeys.VIRTUAL_FILE.getData(AnActionEvent.getDataContext());

//获取编辑对象
 _editor = e.getData(PlatformDataKeys.EDITOR);
 //获取正在编辑的文本内容
 _content = _editor.getDocument().getText();

```









## 参考

http://blog.csdn.net/lmj623565791/article/details/51548272#t0

https://github.com/githubwing/MVPHelper

