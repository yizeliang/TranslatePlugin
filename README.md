# MyMvpHelper

## 说明

只为学习使用

## 1 常用API

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


```java
//根据情况选择是否显示 菜单 (不会用,暂时没有使用)
 public static String getFileExtension(DataContext dataContext) {
        VirtualFile file = DataKeys.VIRTUAL_FILE.getData(dataContext);
        return file == null ? null : file.getExtension();
    }
    
  @Override
    public void update(AnActionEvent event) {
        //在Action显示之前,根据选中文件扩展名判定是否显示此Action
        String extension = getFileExtension(event.getDataContext());
        this.getTemplatePresentation().setEnabled(extension != null && "jar".equals(extension));
    }

```

```java
//修改完目录结构后,刷新目录结构
  // 第一个参数:是否异步
  //第二个参数:是否递归
  //第三个参数:完成后的回调
  //不建议获取项目的baseDir.使用的时候可以获取生成的当前目录,类型为 VirtualFile
  getEventProject(e).getBaseDir().refresh(true, true, new Runnable() {
                @Override
                public void run() {
                    MessagesCenter.showMessage("目录刷新成功", "创建成功");
                }
            });

```


```java
//悬浮框的展示
 JBPopupFactory factory = JBPopupFactory.getInstance();
                factory.createHtmlTextBalloonBuilder(result, null, 
                new JBColor(new Color(186, 238, 186),
                 new Color(73, 117, 73)), null)
                        .setFadeoutTime(5000)
                        .createBalloon()
                        .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
```


```java
//替换内容
private void changeSelectText(String text) {
    Project  mProject = e.getData(PlatformDataKeys.PROJECT);
    Document document = mEditor.getDocument();
    SelectionModel selectionModel = mEditor.getSelectionModel();

    final int start = selectionModel.getSelectionStart();
    final int end = selectionModel.getSelectionEnd();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            document.replaceString(start, end, text);
        }
    };
    WriteCommandAction.runWriteCommandAction(mProject, runnable);
    selectionModel.removeSelection();
}

```

## 2 action groups

- GenerateGroup

![1.png](https://ooo.0o0.ooo/2017/04/25/58feabb91e31d.png)

- EditorMenu

![2.png](https://ooo.0o0.ooo/2017/04/25/58feac0db7986.png)

其他:
具体的每个group-id的含义可参看：
http://keithlea.com/idea-actions/




## 参考

http://blog.csdn.net/lmj623565791/article/details/51548272#t0

https://github.com/githubwing/MVPHelper

http://www.jianshu.com/p/f017097e4b26

