package Lobis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LobbySimulator extends JFrame {

    private LobbyManager lobbyManager;
    private JTextField nameField;
    private JButton joinButton;
    private JButton leaveButton;
    private JLabel statusLabel;
    
    private DefaultListModel<String> playerListModel;
    private DefaultListModel<String> roomListModel;

    public LobbySimulator() {
        // JFrame ayarları
        setTitle("Java Lobi Simülatörü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // List Modelleri ve LobbyManager'ı başlatma
        playerListModel = new DefaultListModel<>();
        roomListModel = new DefaultListModel<>();
        lobbyManager = new LobbyManager(playerListModel, roomListModel);

        // --- Üst Panel (Giriş) ---
        JPanel topPanel = new JPanel();
        nameField = new JTextField(15);
        joinButton = new JButton("Lobiye Katıl");
        leaveButton = new JButton("Lobiden Ayrıl");
        leaveButton.setEnabled(false); // Başlangıçta ayrılma pasif
        statusLabel = new JLabel("Durum: Bağlantı kesildi.");

        topPanel.add(new JLabel("Kullanıcı Adı:"));
        topPanel.add(nameField);
        topPanel.add(joinButton);
        topPanel.add(leaveButton);
        add(topPanel, BorderLayout.NORTH);

        // --- Merkez Panel (Listeler) ---
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));

        // Oyuncu Listesi
        JList<String> playerList = new JList<>(playerListModel);
        JScrollPane playerScrollPane = new JScrollPane(playerList);
        playerScrollPane.setPreferredSize(new Dimension(250, 250));
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.add(new JLabel("Mevcut Oyuncular:"), BorderLayout.NORTH);
        playerPanel.add(playerScrollPane, BorderLayout.CENTER);
        centerPanel.add(playerPanel);

        // Oda Listesi
        JList<String> roomList = new JList<>(roomListModel);
        JScrollPane roomScrollPane = new JScrollPane(roomList);
        roomScrollPane.setPreferredSize(new Dimension(250, 250));
        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.add(new JLabel("Oda Listesi:"), BorderLayout.NORTH);
        
        JButton createRoomButton = new JButton("Oda Oluştur");
        roomPanel.add(createRoomButton, BorderLayout.SOUTH);
        roomPanel.add(roomScrollPane, BorderLayout.CENTER);
        centerPanel.add(roomPanel);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // --- Alt Panel (Durum) ---
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(statusLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- Olay Dinleyicileri (Event Listeners) ---

        // Katıl Butonu
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (lobbyManager.joinLobby(name)) {
                    statusLabel.setText("Durum: Lobiye katıldınız: " + name);
                    joinButton.setEnabled(false);
                    leaveButton.setEnabled(true);
                    nameField.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(LobbySimulator.this, 
                        "Kullanıcı adı geçersiz veya zaten kullanılıyor.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ayrıl Butonu
        leaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (lobbyManager.leaveLobby(name)) {
                    statusLabel.setText("Durum: Lobiden ayrıldınız.");
                    joinButton.setEnabled(true);
                    leaveButton.setEnabled(false);
                    nameField.setEnabled(true);
                }
            }
        });
        
        // Oda Oluştur Butonu
        createRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if (joinButton.isEnabled()) {
                     JOptionPane.showMessageDialog(LobbySimulator.this, 
                        "Önce lobiye katılmalısınız.", "Hata", JOptionPane.WARNING_MESSAGE);
                     return;
                 }
                 
                 String newRoomName = JOptionPane.showInputDialog(LobbySimulator.this, 
                     "Yeni oda adı girin (örn: 'Oda 3 (1/4)'):", "Oda Oluştur", JOptionPane.PLAIN_MESSAGE);
                 
                 if (newRoomName != null && !newRoomName.trim().isEmpty()) {
                     lobbyManager.createRoom(newRoomName.trim());
                 }
            }
        });

        // Pencereyi görünür yap
        setLocationRelativeTo(null); // Ekranın ortasında göster
        setVisible(true);
    }

    public static void main(String[] args) {
        // GUI kodunun Event Dispatch Thread (EDT) üzerinde çalışmasını sağlar
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LobbySimulator();
            }
        });
    }
}