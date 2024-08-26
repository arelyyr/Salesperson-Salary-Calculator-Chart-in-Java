import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaryCalculatorGUI extends JFrame {
    private final JTextField salesAmountField;
    private final JTextArea resultsArea;
    private final SalaryCalculator calculator = new SalaryCalculator();

    SalaryCalculatorGUI() {
        setTitle("Sales Salary Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        add(inputPanel, BorderLayout.NORTH);

        JLabel salesAmountLabel = new JLabel("Enter sales amount:");
        inputPanel.add(salesAmountLabel);

        salesAmountField = new JTextField(15);
        inputPanel.add(salesAmountField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        inputPanel.add(submitButton);

        JButton finalizeButton = new JButton("Finalize");
        finalizeButton.addActionListener(new FinalizeButtonListener());
        inputPanel.add(finalizeButton);

        // Text area for results
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        printSalaryTable();
    }

    private void printSalaryTable() {
        resultsArea.setText(calculator.getSalaryTable());
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double salesAmount = Double.parseDouble(salesAmountField.getText());
                if (salesAmount >= 0) {
                    double totalSalary = calculator.calculateTotalSalary(salesAmount);
                    calculator.updateSalaryRanges(totalSalary);
                    printSalaryTable();
                    salesAmountField.setText("");
                } else if (salesAmount == -1) {
                    finalizeResults();
                } else {
                    JOptionPane.showMessageDialog(SalaryCalculatorGUI.this, "Please enter a valid number.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(SalaryCalculatorGUI.this, "Invalid input. Please enter a valid number.");
            }
        }
    }

    private class FinalizeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            finalizeResults();
        }
    }

    private void finalizeResults() {
        printSalaryTable();
        JOptionPane.showMessageDialog(this, calculator.getRandomApplause());
        salesAmountField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SalaryCalculatorGUI().setVisible(true));
    }
}
