import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JTextArea log = new JTextArea();
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("Ivan Belenko");
    private final JPasswordField tfPassword = new JPasswordField("12345678");
    private final JButton btnLogin = new JButton("Login");
    private final JButton btnSend = new JButton("Send");
    private final JTextField tfMessage = new JTextField();

    ClientGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("My Chat");

        JPanel panelTop = new JPanel(new GridLayout(2, 3));
        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        btnSend.addActionListener(e -> sendMessage());
        tfMessage.addActionListener(e -> sendMessage());

        loadChatHistory();
        setVisible(true);
    }

    private void sendMessage() {
        String message = tfMessage.getText();
        log.append("Dima: " + message + "\n");
        tfMessage.setText("");

        try {
            FileWriter fileWriter = new FileWriter("chat_log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Dima: " + message);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void loadChatHistory() {
        try {
            File file = new File("chat_log.txt");
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    log.append(line + "\n");
                }
                bufferedReader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
