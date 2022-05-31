import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{
    JTextField dateText;
    JTextField secondDateText;
    JButton button;
    JTextArea startLabel;
    int yForButt = 160;
    int yForText = 0;
    JButton countButton;
    JTextField countAP;
    JTextField countFirstYear;
    JTextField countSecondYear;
    JTextField countTR;
    JTextArea paramField;
    MyFrame() {
        ImageIcon icon = new ImageIcon("logo.png");

        JTextField absoluteGrowth = new JTextField("Абсолютный прирост");
        absoluteGrowth.setBounds(590,15,130,50);
        absoluteGrowth.setFont(new Font("Consoles", Font.PLAIN,12));
        absoluteGrowth.setHorizontalAlignment(JTextField.CENTER);
        absoluteGrowth.setEditable(false);

        JTextField growthRate = new JTextField("Темп роста. %");
        growthRate.setBounds(770,15,130,50);
        growthRate.setFont(new Font("Consoles", Font.PLAIN,15));
        growthRate.setHorizontalAlignment(JTextField.CENTER);
        growthRate.setEditable(false);

        JLabel date = new JLabel();
        date.setBounds(225,5,400,10);
        date.setFont(new Font("Consoles", Font.BOLD,10));
        date.setText("Первый год для сравнения           Второй год для сравнения");

        startLabel = new JTextArea();
        startLabel.setText("Введите первый год затем наж-\nмите кнопку для создания первой  строки (второй год будет записан   автоматически)");
        startLabel.setLineWrap(true);
        startLabel.setBounds(5,5,210,70);
        startLabel.setFont(new Font("Consoles", Font.BOLD,12));
        startLabel.setEditable(false);

        dateText = new JTextField();
        dateText.setBounds(230,15,130,50);
        dateText.setFont(new Font("Consoles", Font.PLAIN,25));
        dateText.setHorizontalAlignment(JTextField.CENTER);

        secondDateText = new JTextField();
        secondDateText.setBounds(410,15,130,50);
        secondDateText.setFont(new Font("Consoles", Font.PLAIN,25));
        secondDateText.setHorizontalAlignment(JTextField.CENTER);
        secondDateText.setEditable(false);

        button = new JButton();
        button.setBounds(85,80,30,30);
        button.setFocusable(false);
        button.addActionListener(this);

        countButton = new JButton("Рассчитать");
        countButton.setBounds(770,yForButt,130,30);
        countButton.setFocusable(false);
        countButton.addActionListener(this);
        countButton.setVisible(false);


        this.setTitle("Расчет абсолютного прироста и темпа роста");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(300,75,970,700);
        this.add(startLabel);
        this.add(date);
        this.add(absoluteGrowth);
        this.add(growthRate);
        this.add(secondDateText);
        this.add(button);
        this.add(dateText);
        this.add(countButton);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            try {
                int d = Integer.parseInt(dateText.getText());
                if (d > 2022 || d < 0) {
                    JOptionPane.showMessageDialog(null, "Введите в поле *Первый год для сравнения* число от 1 до 2022!");
                } else {
                    secondDateText.setText(String.valueOf(d + 1));
                }
                startLabel.setVisible(false);
                dateText.setEditable(false);

                yForText += 80;

                button.setBounds(70,yForButt,45,30);
                button.setText("+");

                countButton.setBounds(770,yForButt,130,30);
                countButton.setVisible(true);

                JLabel param = new JLabel("Параметры для сравнения");
                param.setBounds(15,50,169,30);
                this.add(param);

                paramField = new JTextArea();
                paramField.setBounds(15,yForText,160,70);
                paramField.setFont(new Font("Consoles", Font.PLAIN,15));
                this.add(paramField);

                countFirstYear = new JTextField();
                countFirstYear.setBounds(230,yForText,130,70);
                countFirstYear.setFont(new Font("Consoles", Font.PLAIN,20));
                countFirstYear.setHorizontalAlignment(JTextField.CENTER);
                this.add(countFirstYear);

                countSecondYear = new JTextField();
                countSecondYear.setBounds(410,yForText,130,70);
                countSecondYear.setFont(new Font("Consoles", Font.PLAIN,20));
                countSecondYear.setHorizontalAlignment(JTextField.CENTER);
                this.add(countSecondYear);

                countAP = new JTextField();
                countAP.setBounds(590,yForText,130,70);
                countAP.setFont(new Font("Consoles", Font.PLAIN,20));
                countAP.setHorizontalAlignment(JTextField.CENTER);
                countAP.setEditable(false);
                this.add(countAP);

                countTR = new JTextField();
                countTR.setBounds(770,yForText,130,70);
                countTR.setFont(new Font("Consoles", Font.PLAIN,20));
                countTR.setHorizontalAlignment(JTextField.CENTER);
                countTR.setEditable(false);
                this.add(countTR);

                button.setEnabled(false);
                yForButt += 80;


            } catch (NumberFormatException exception) {
                dateText.setText("");
                JOptionPane.showMessageDialog(null, "Введите в поле *Первый год для сравнения* число от 1 до 2022!");
            }
        }
        if(e.getSource() == countButton){
            try{
                double i = Double.parseDouble(countFirstYear.getText());
                double i1 = Double.parseDouble(countSecondYear.getText());

                countAP.setText(String.format("%.2f", i1 - i));
                countTR.setText(String.format("%.2f",i1 / i * 100 ));

                countFirstYear.setEditable(false);
                countSecondYear.setEditable(false);

                button.setEnabled(true);

            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(null, "В поля данных введите число!");
            }
        }
    }
}
