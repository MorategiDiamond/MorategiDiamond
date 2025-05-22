import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Quiztest extends JFrame implements ActionListener {
    JLabel label;
    JRadioButton radioButtons[] = new JRadioButton[23];
    JButton btnNext, BtnResult;
    ButtonGroup bg;
    int count = 0, current = 0;

    Quiztest(String s) {
        super(s);
        label = new JLabel();
        add(label);
        bg = new ButtonGroup();

        for (int i = 0; i < 23; i++) {
            radioButtons[i] = new JRadioButton("Option " + (i + 1));
            add(radioButtons[i]);
            bg.add(radioButtons[i]);
        }

        btnNext = new JButton("Next");
        btnNext.addActionListener(this);
        add(btnNext);

        BtnResult = new JButton("Result");
        BtnResult.addActionListener(this);
        BtnResult.setEnabled(false);
        add(BtnResult);

        SetData();
        label.setBounds(30, 40, 450, 20);

        for (int i = 0, y = 80; i < 4; i++, y += 30) {
            radioButtons[i].setBounds(50, y, 200, 20);
        }

        btnNext.setBounds(100, 240, 100, 30);
        BtnResult.setBounds(270, 240, 100, 30);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //getContentPane().setBackground(Color.RED);
        

    }

    void SetData() {
        bg.clearSelection(); // Clear previous selection
        // Set the text for the question and options
        if (current == 0) {
            label.setText("Q1: Which is the official language for Android Development?");
            radioButtons[0].setText("Java");
            radioButtons[1].setText("Kotlin");
            radioButtons[2].setText("C++");
            radioButtons[3].setText("JavaScript");
        }
        if (current == 1) {
            label.setText("Q2: What is the default value of a boolean variable in Java?");
            radioButtons[0].setText("true");
            radioButtons[1].setText("false");
            radioButtons[2].setText("0");
            radioButtons[3].setText("1");
        }
        if (current == 2) {
            label.setText("Q3: Which keyword is used to prevent inheritance in Java?");
            radioButtons[0].setText("private");
            radioButtons[1].setText("static");
            radioButtons[2].setText("final");
            radioButtons[3].setText("abstract");
        }
        if (current == 3) {
            label.setText("Q4: Which of these is not a Java keyword?");
            radioButtons[0].setText("switch");
            radioButtons[1].setText("synchronized");
            radioButtons[2].setText("volatile");
            radioButtons[3].setText("unsigned");
        }
        if (current == 4) {
            label.setText("Q5: Which class is the superclass of all classes in Java?");
            radioButtons[0].setText("Object");
            radioButtons[1].setText("Class");
            radioButtons[2].setText("System");
            radioButtons[3].setText("Exception");
        }
            if (current == 5) {
                label.setText("Q6: Which class is the superclass of all classes in Java?");
                radioButtons[0].setText("Object");
                radioButtons[1].setText("Class");
                radioButtons[2].setText("System");
                radioButtons[3].setText("Exception");
            }
            if (current == 6) {
                label.setText("Q6: Which method is called to free the memory of an object in Java?");
                radioButtons[0].setText("finalize()");
                radioButtons[1].setText("dispose()");
                radioButtons[2].setText("delete()");
                radioButtons[3].setText("free()");
            }
            if (current == 7) {
                label.setText("Q7: Which package contains the Random class in Java?");
                radioButtons[0].setText("java.lang");
                radioButtons[1].setText("java.util");
                radioButtons[2].setText("java.io");
                radioButtons[3].setText("java.net");
            }
            if (current == 8) {
                label.setText("Q8: Which method is called to free the memory of an object in Java?");
                radioButtons[0].setText("finalize()");
                radioButtons[1].setText("dispose()");
                radioButtons[2].setText("delete()");
                radioButtons[3].setText("free()");
            }
            if (current == 9) {
                label.setText("Q10: Which of these are reserved words in Java?");
                radioButtons[0].setText("goto");
                radioButtons[1].setText("const");
                radioButtons[2].setText("both");
                radioButtons[3].setText("none");
            }
            if (current == 10) {
                label.setText("Q11: Which interface does java.util.HashSet implement?");
                radioButtons[0].setText("List");
                radioButtons[1].setText("Set");
                radioButtons[2].setText("Map");
                radioButtons[3].setText("Collection");
            }
            if (current == 11) {
                label.setText("Q1: Which is the official language for Android Development?");
                radioButtons[0].setText("Java");
                radioButtons[1].setText("Kotlin");
                radioButtons[2].setText("C++");
                radioButtons[3].setText("JavaScript");
            }
            if (current == 12) {
                label.setText("Q12: What is the default value of a reference variable in Java?");
                radioButtons[0].setText("0");
                radioButtons[1].setText("null");
                radioButtons[2].setText("undefined");
                radioButtons[3].setText("empty");
            }
            if (current == 13) {
                label.setText("Q13: Which of the following is not an access modifier in Java?");
                radioButtons[0].setText("public");
                radioButtons[1].setText("protected");
                radioButtons[2].setText("private");
                radioButtons[3].setText("internal");
            }
            if (current == 14) {
                label.setText("Q14: Which keyword is used to inherit a class in Java?");
                radioButtons[0].setText("this");
                radioButtons[1].setText("super");
                radioButtons[2].setText("extends");
                radioButtons[3].setText("implements");
            }
            if (current == 15) {
                label.setText("Q16: What is the use of the keyword 'synchronized' in Java?");
                radioButtons[0].setText("Serialization");
                radioButtons[1].setText("Multithreading");
                radioButtons[2].setText("Inheritance");
                radioButtons[3].setText("Encapsulation");
            }
            if (current == 16) {
                label.setText("Q17: Which of the following is not a wrapper class in Java?");
                radioButtons[0].setText("Byte");
                radioButtons[1].setText("Integer");
                radioButtons[2].setText("Character");
                radioButtons[3].setText("int");
            }
            if (current == 17) {
                label.setText("Q18: Which method is used to compare two strings in Java?");
                radioButtons[0].setText("compareTo()");
                radioButtons[1].setText("equals()");
                radioButtons[2].setText("== operator");
                radioButtons[3].setText("hashCode()");
            }
            if (current == 18) {
                label.setText("Q19: Which of the following is not a feature of Java?");
                radioButtons[0].setText("Object-oriented");
                radioButtons[1].setText("Platform-independent");
                radioButtons[2].setText("Low-level programming");
                radioButtons[3].setText("Secure");
            }
            if (current == 19) {
                label.setText("Q20: Which collection class allows you to grow or shrink its size and provides indexed access to its elements, but whose methods are not synchronized?");
                radioButtons[0].setText("java.util.HashSet");
                radioButtons[1].setText("java.util.LinkedHashSet");
                radioButtons[2].setText("java.util.ArrayList");
                radioButtons[3].setText("java.util.LinkedList");
            }
            if (current == 20) {
                label.setText("Q21: Which method must be implemented by all threads in Java?");
                radioButtons[0].setText("wait()");
                radioButtons[1].setText("start()");
                radioButtons[2].setText("run()");
                radioButtons[3].setText("stop()");
            }
            if (current == 21) {
                label.setText("Q22: What is the default value of a char variable in Java?");
                radioButtons[0].setText("'\\u0000'");
                radioButtons[1].setText("'0'");
                radioButtons[2].setText("' '");
                radioButtons[3].setText("null");
            }
            if (current == 22) {
                label.setText("Q23: Which operator is used to create an instance of a class in Java?");
                radioButtons[0].setText("new");
                radioButtons[1].setText("create");
                radioButtons[2].setText("instance");
                radioButtons[3].setText("init");
            }
            if (current == 23) {
                label.setText("Q24: Which keyword is used to define a constant variable in Java?");
                radioButtons[0].setText("const");
                radioButtons[1].setText("final");
                radioButtons[2].setText("static");
                radioButtons[3].setText("constant");
            }
            if (current == 24) {
                label.setText("Q25: Which method is used to convert a string to a number in Java?");
                radioButtons[0].setText("parseInt()");
                radioButtons[1].setText("toString()");
                radioButtons[2].setText("valueOf()");
                radioButtons[3].setText("toNumber()");
            }
        }
    

    boolean CheckAnswer() {
        if (current == 0) {
            return (radioButtons[1].isSelected());
        }
        if (current == 1) {
            return (radioButtons[1].isSelected());
        }
        if (current == 2) {
            return (radioButtons[2].isSelected());
        }
        if (current == 3) {
            return (radioButtons[3].isSelected());
        }
        if (current == 4) {
            return (radioButtons[0].isSelected());
        }
    if (current == 5) {
        return (radioButtons[0].isSelected());
    }
    if (current == 6) {
        return (radioButtons[0].isSelected());
    }
    if (current == 7) {
        return (radioButtons[0].isSelected());
    }
    if (current == 8) {
        return (radioButtons[0].isSelected());
    }
    if (current == 9) {
        return (radioButtons[0].isSelected());
    }
    if (current == 10) {
        return (radioButtons[0].isSelected());
    }
    if (current == 11) {
        return (radioButtons[0].isSelected());
    }
    if (current == 12) {
        return (radioButtons[0].isSelected());
    }
    if (current == 13) {
        return (radioButtons[0].isSelected());
    }
    if (current == 14) {
        return (radioButtons[0].isSelected());
    }
    if (current == 15) {
        return (radioButtons[0].isSelected());
    }
    if (current == 16) {
        return (radioButtons[0].isSelected());
    }
    if (current == 17) {
        return (radioButtons[0].isSelected());
    }
    if (current == 18) {
        return (radioButtons[0].isSelected());
    }
    if (current == 19) {
        return (radioButtons[0].isSelected());
    }
    if (current == 20) {
        return (radioButtons[0].isSelected());
    }
    if (current == 21) {
        return (radioButtons[0].isSelected());
    }
    if (current == 22) {
        return (radioButtons[0].isSelected());
    }
    if (current == 23) {
        return (radioButtons[0].isSelected());
    }
    if (current == 24) {
        return (radioButtons[0].isSelected());
    }
  
    
        
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            if (CheckAnswer()) {
                count++;
            }
            current++;
            if (current < 25) {
                SetData();
            } else {
                btnNext.setEnabled(false);
                BtnResult.setEnabled(true);
            }
        }
        if (e.getSource() == BtnResult) {
            if (CheckAnswer()) {
                count++;
            }
            JOptionPane.showMessageDialog(this, "Correct answers = " + count);
            System.exit(0);
        }
    }

    public static void main(String s[]) {
        new Quiztest("Quiz Test Prepared by Muhali A.J");
    }
}
