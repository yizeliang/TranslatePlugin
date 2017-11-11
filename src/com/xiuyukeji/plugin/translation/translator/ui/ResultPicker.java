package com.xiuyukeji.plugin.translation.translator.ui;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.xiuyukeji.plugin.translation.translator.utils.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultPicker extends JDialog {
    private Editor editor;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList jList;
    private DefaultListModel model;
    String result;

    public ResultPicker(Editor editor, String result) {
        this.editor = editor;
        this.result = result;
        setContentPane(contentPane);
        setModal(true);
        setTitle("picker");
        //设置大小
        setMinimumSize(new Dimension(700, 300));
        //居中
        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width / 2; // 获取屏幕的宽
        int screenHeight = screenSize.height / 2; // 获取屏幕的高
        int height = this.getHeight();
        int width = this.getWidth();
        setLocation(screenWidth - width / 2, screenHeight - height / 2);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        initData();
    }


    private void initData() {
        model = new DefaultListModel();//只有默认的模型有添加/删除方法
        StringUtil.setResultType(model, result);
        jList.setModel(model);
    }

    private void onOK() {
        String result = model.get(jList.getSelectedIndex()).toString();
        StringUtil.changeSelectText(editor, result);
        dispose();
    }

    private void onCancel() {
        dispose();
    }

//    public static void main(String[] args) {
//        ResultPicker dialog = new ResultPicker(event, "my name", new Callback() {
//            @Override
//            public void picker(String pickerString) {
//                System.out.println(pickerString);
//            }
//        });
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
