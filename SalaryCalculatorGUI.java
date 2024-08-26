import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaryCalculatorGUI extends JFrame {
    private final JTextField salesAmountField;
    private final JTextField baseSalaryField;
    private final JTextField commissionPercentField;
    private final JTextField commissionEarnedField;
    private final JTextField totalSalaryField;
    private final JTextArea resultsArea;
    private final SalaryCalculator calculator = new SalaryCalculator();

    SalaryCalculatorGUI() {
        setTitle("Sales Salary Calculator");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font font = new Font("Times New Roman", Font.PLAIN, 14);

        // Panel for calculator section
        JPanel calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new GridBagLayout());
        calculatorPanel.setBackground(Color.LIGHT_GRAY);
        calculatorPanel.setBorder(BorderFactory.createTitledBorder("Salary Calculator"));
        add(calculatorPanel, BorderLayout.WEST);

           // Add a label above the table
        JLabel calculatorLabel = new JLabel("Enter Sales Amount for Salesperson");
        calculatorLabel.setFont(font);
        calculatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculatorPanel.add(calculatorLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;


        // Base salary label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        calculatorPanel.add(new JLabel("Base Salary:"), gbc);

        baseSalaryField = new JTextField("$200", 10);
        baseSalaryField.setEditable(false);
        baseSalaryField.setFont(font);
        gbc.gridx = 1;
        calculatorPanel.add(baseSalaryField, gbc);

        // Sales amount label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        calculatorPanel.add(new JLabel("Sales Amount:"), gbc);

        salesAmountField = new JTextField(10);
        salesAmountField.setFont(font);
        gbc.gridx = 1;
        calculatorPanel.add(salesAmountField, gbc);

        // Commission percent label and field
        gbc.gridx = 0;
        gbc.gridy = 3;
        calculatorPanel.add(new JLabel("Commission Percent:"), gbc);

        commissionPercentField = new JTextField("9%", 10);
        commissionPercentField.setEditable(false);
        commissionPercentField.setFont(font);
        gbc.gridx = 1;
        calculatorPanel.add(commissionPercentField, gbc);

        // Commission earned label and field
        gbc.gridx = 0;
        gbc.gridy = 4;
        calculatorPanel.add(new JLabel("Commission Earned:"), gbc);

        commissionEarnedField = new JTextField("$0.00", 10);
        commissionEarnedField.setEditable(false);
        commissionEarnedField.setFont(font);
        gbc.gridx = 1;
        calculatorPanel.add(commissionEarnedField, gbc);

        // Total salary label and field
        gbc.gridx = 0;
        gbc.gridy = 5;
        calculatorPanel.add(new JLabel("Total Salary:"), gbc);

        totalSalaryField = new JTextField("$0.00", 10);
        totalSalaryField.setEditable(false);
        totalSalaryField.setFont(font);
        gbc.gridx = 1;
        calculatorPanel.add(totalSalaryField, gbc);

        // Buttons for actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        JButton submitButton = new JButton("SUBMIT");
        submitButton.setFont(font);
        submitButton.addActionListener(new SubmitButtonListener());
        buttonPanel.add(submitButton);

        JButton finalizeButton = new JButton("FINALIZE");
        finalizeButton.setFont(font);
        finalizeButton.addActionListener(new FinalizeButtonListener());
        buttonPanel.add(finalizeButton);

        // Panel for range table section
        JPanel rangeTablePanel = new JPanel();
        rangeTablePanel.setLayout(new BoxLayout(rangeTablePanel, BoxLayout.Y_AXIS));
        rangeTablePanel.setBackground(Color.WHITE);
        rangeTablePanel.setBorder(BorderFactory.createTitledBorder("Salary Range Table"));
        add(rangeTablePanel, BorderLayout.CENTER);

        // Add a label above the table
        JLabel tableLabel = new JLabel("Salary Range List Displaying # of Salespeople.");
        tableLabel.setFont(font);
        tableLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rangeTablePanel.add(tableLabel);

        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setFont(font);
        resultsArea.setPreferredSize(new Dimension(200, 300));
        rangeTablePanel.add(new JScrollPane(resultsArea));

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
                    double commissionEarned = calculator.calculateTotalSalary(salesAmount) - 200; // Calculate commission earned
                    commissionEarnedField.setText(String.format("$%.2f", commissionEarned));
                    double totalSalary = calculator.calculateTotalSalary(salesAmount);
                    totalSalaryField.setText(String.format("$%.2f", totalSalary));
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
