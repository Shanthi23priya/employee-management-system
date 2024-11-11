package EMSSS;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

class Employee {
    String id, name, department, joiningDate, gender, contact, salary, email, address;

    public Employee(String id, String name, String department, String joiningDate, String gender,
                    String contact, String salary, String email, String address) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.joiningDate = joiningDate;
        this.gender = gender;
        this.contact = contact;
        this.salary = salary;
        this.email = email;
        this.address = address;
    }
}

public class EmployeeManagement {
    private JFrame frmEmployeeManagementSystem;
    private JTextField nameTextField;
    private JTextField departmentTextField;
    private JTextField contactTextField;
    private JTextField emailTextField;
    private JTextField idTextField;
    private JTextField joinDateTextField;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Employee> employees;
    private JTextField salaryTextField;
    private JTextField deletetextfield;
    private JTextField searchTextField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EmployeeManagement window = new EmployeeManagement();
                window.frmEmployeeManagementSystem.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EmployeeManagement() {
        employees = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frmEmployeeManagementSystem = new JFrame();
        frmEmployeeManagementSystem.setTitle("Employee Management System by ");
        frmEmployeeManagementSystem.setBounds(100, 100, 1200, 600); // Increased width
        frmEmployeeManagementSystem.setResizable(false);
        frmEmployeeManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Department");
        model.addColumn("Joining Date");
        model.addColumn("Gender");
        model.addColumn("Contact");
        model.addColumn("Salary");
        model.addColumn("E-mail");
        model.addColumn("Address");

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(222, 221, 218));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(new JLabel("Employee ID"), gbc);
        gbc.gridx = 1; idTextField = new JTextField(15); inputPanel.add(idTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(new JLabel("Employee Name"), gbc);
        gbc.gridx = 1; nameTextField = new JTextField(15); inputPanel.add(nameTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(new JLabel("Department"), gbc);
        gbc.gridx = 1; departmentTextField = new JTextField(15); inputPanel.add(departmentTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; inputPanel.add(new JLabel("Joining Date"), gbc);
        gbc.gridx = 1; joinDateTextField = new JTextField(15); inputPanel.add(joinDateTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; inputPanel.add(new JLabel("Gender"), gbc);
        gbc.gridx = 1; JComboBox<String> genderComboBox = new JComboBox<>();
        genderComboBox.addItem("Male");
        genderComboBox.addItem("Female");
        genderComboBox.addItem("Other");
        inputPanel.add(genderComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 5; inputPanel.add(new JLabel("Contact"), gbc);
        gbc.gridx = 1; contactTextField = new JTextField(15); inputPanel.add(contactTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 6; inputPanel.add(new JLabel("E-mail"), gbc);
        gbc.gridx = 1; emailTextField = new JTextField(15); inputPanel.add(emailTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 7; inputPanel.add(new JLabel("Salary"), gbc);
        gbc.gridx = 1; salaryTextField = new JTextField(15); inputPanel.add(salaryTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 8; inputPanel.add(new JLabel("Address"), gbc);
        gbc.gridx = 1; JTextArea addressTextArea = new JTextArea(3, 15);
        inputPanel.add(new JScrollPane(addressTextArea), gbc);

        // Create button panel
        JPanel buttonPanel = new JPanel();

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            Employee employee = new Employee(
                    idTextField.getText(),
                    nameTextField.getText(),
                    departmentTextField.getText(),
                    joinDateTextField.getText(),
                    genderComboBox.getSelectedItem().toString(),
                    contactTextField.getText(),
                    salaryTextField.getText(),
                    emailTextField.getText(),
                    addressTextArea.getText()
            );
            employees.add(employee);
            loadData();
            JOptionPane.showMessageDialog(inputPanel, "Employee successfully inserted", "Inserted", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(insertButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            String id = idTextField.getText();
            for (Employee emp : employees) {
                if (emp.id.equals(id)) {
                    emp.name = nameTextField.getText();
                    emp.department = departmentTextField.getText();
                    emp.joiningDate = joinDateTextField.getText();
                    emp.gender = genderComboBox.getSelectedItem().toString();
                    emp.contact = contactTextField.getText();
                    emp.salary = salaryTextField.getText();
                    emp.email = emailTextField.getText();
                    emp.address = addressTextArea.getText();
                    loadData();
                    JOptionPane.showMessageDialog(inputPanel, "Employee successfully updated", "Updated", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(inputPanel, "Employee ID not found", "ERROR", JOptionPane.ERROR_MESSAGE);
        });
        buttonPanel.add(updateButton);

        // Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            idTextField.setText("");
            nameTextField.setText("");
            departmentTextField.setText("");
            joinDateTextField.setText("");
            contactTextField.setText("");
            salaryTextField.setText("");
            emailTextField.setText("");
            addressTextArea.setText("");
            genderComboBox.setSelectedIndex(0); // Reset gender to first option
        });
        buttonPanel.add(resetButton);

        // Add the button panel to the input panel
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2; inputPanel.add(buttonPanel, gbc);

        frmEmployeeManagementSystem.getContentPane().add(inputPanel, BorderLayout.WEST);

        JPanel outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        outputPanel.setLayout(new BorderLayout());
        outputPanel.setBackground(new Color(22, 221, 218));

        table = new JTable(model);
        table.setBackground(new Color(222, 221, 218));
        outputPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        deletetextfield = new JTextField(15);
        actionPanel.add(deletetextfield);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            String id = deletetextfield.getText();
            employees.removeIf(emp -> emp.id.equals(id));
            loadData();
            JOptionPane.showMessageDialog(outputPanel, "Employee deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);
        });
        actionPanel.add(deleteButton);

        searchTextField = new JTextField(15);
        actionPanel.add(searchTextField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String id = searchTextField.getText();
            for (Employee emp : employees) {
                if (emp.id.equals(id)) {
                    idTextField.setText(emp.id);
                    nameTextField.setText(emp.name);
                    departmentTextField.setText(emp.department);
                    joinDateTextField.setText(emp.joiningDate);
                    contactTextField.setText(emp.contact);
                    salaryTextField.setText(emp.salary);
                    emailTextField.setText(emp.email);
                    addressTextArea.setText(emp.address);
                    return;
                }
            }
            JOptionPane.showMessageDialog(outputPanel, "Employee ID not found", "ERROR", JOptionPane.ERROR_MESSAGE);
        });
        actionPanel.add(searchButton);

        outputPanel.add(actionPanel, BorderLayout.SOUTH);
        frmEmployeeManagementSystem.getContentPane().add(outputPanel, BorderLayout.CENTER);
    }

    private void loadData() {
        model.setRowCount(0);
        for (Employee emp : employees) {
            model.addRow(new Object[]{
                    emp.id, emp.name, emp.department, emp.joiningDate, emp.gender, emp.contact, emp.salary, emp.email, emp.address
            });
        }
    }
}
