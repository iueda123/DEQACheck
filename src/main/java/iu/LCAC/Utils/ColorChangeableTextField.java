package iu.LCAC.Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ColorChangeableTextField extends JTextField {

    private String defaultValue = "";
    private boolean updated = false;

    public ColorChangeableTextField(int columns) {
        super(columns);
        setupTextField();
    }

    public ColorChangeableTextField(String text) {
        super(text);
        setupTextField();
    }

    private void setupTextField(){

        defaultValue = this.getText();

        // テキストフィールドのドキュメントにリスナーを追加
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkUpdate();
                changeColor();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkUpdate();
                changeColor();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // 属性変更時 (プレーンテキストではあまり使われない)
                checkUpdate();
                changeColor();
            }

            private void changeColor() {

                if (ColorChangeableTextField.this.getText().equals(ColorChangeableTextField.this.defaultValue)) {
                    ColorChangeableTextField.this.resetBackgroundColor();
                } else {
                    ColorChangeableTextField.this.setBackground(Color.PINK);
                }
            }

            private void checkUpdate(){
                if (ColorChangeableTextField.this.getText().equals(ColorChangeableTextField.this.defaultValue)) {
                    ColorChangeableTextField.this.updated = false;
                } else {
                    ColorChangeableTextField.this.updated =true;
                }
            }


        });
    }

    public void updateDefaultValue() {
        ColorChangeableTextField.this.defaultValue = ColorChangeableTextField.this.getText();
        ColorChangeableTextField.this.updated = false;
    }

    public void resetBackgroundColor(){
        // UIManager から L&F 標準の背景色を取得
        Color defaultBg = UIManager.getColor("TextField.background");
        this.setBackground(defaultBg);
    }

    public boolean isUpdated(){
        return updated;
    }

}