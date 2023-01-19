package pl.junkiewiczdamian.frame;

import javax.swing.*;

public class SnakeMapField extends JLabel {
    private FieldType fieldType;

    public SnakeMapField(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public FieldType getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }
}
