package views;

import controller.*;
import dao.UserDAO;
import model.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.ProductPanel;

public class LoginForm extends JFrame {

    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMessage;

    private UserDAO userDAO;
    
    public static String username;

    // Constructor
    public LoginForm() {
        userDAO = new UserDAO();  // Tạo đối tượng UserDAO để truy vấn cơ sở dữ liệu

        // Thiết lập giao diện người dùng (Swing)
        setTitle("Login Form");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Đặt cửa sổ vào giữa màn hình

        // Tạo các thành phần giao diện
        JLabel lblUserName = new JLabel("Username:");
        lblUserName.setBounds(20, 20, 80, 25);
        
        txtUserName = new JTextField();
        txtUserName.setBounds(100, 20, 160, 25);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(20, 60, 80, 25);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 60, 160, 25);
        
        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 100, 80, 25);
        
        lblMessage = new JLabel();
        lblMessage.setBounds(20, 130, 250, 25);
        
        // Thêm các thành phần vào JFrame
        add(lblUserName);
        add(txtUserName);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(lblMessage);
        
        setLayout(null);  // Sử dụng layout null để đặt các thành phần tự do trong JFrame

        // Xử lý sự kiện khi người dùng nhấn nút "Login"
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = txtUserName.getText();
                String password = new String(txtPassword.getPassword());

                // Kiểm tra đăng nhập
                User user = userDAO.getUserByUserName(userName);

                if (user != null && user.getPassword().equals(password)) {
                    if (user.isStatus()) {
                        lblMessage.setText("Đăng nhập thành công!");
                        // Có thể mở cửa sổ khác sau khi đăng nhập thành công
                        new MainJFrame().setVisible(true);
                        LoginForm.username = userName;
                        dispose();
                    } else {
                        lblMessage.setText("Tài khoản của bạn đã bị vô hiệu hóa.");
                    }
                } else {
                    lblMessage.setText("Sai tên đăng nhập hoặc mật khẩu.");
                }
            }
        });
    }

   // Phương thức main để chạy ứng dụng
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new LoginForm().setVisible(true);  // Hiển thị form đăng nhập
//            }
//        });
//    }
}