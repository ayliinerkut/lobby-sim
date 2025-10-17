package Lobis;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

public class LobbyManager {
    // Mevcut oyuncuların listesi
    private List<String> currentPlayers;
    // Mevcut odaların listesi
    private List<String> availableRooms;
    
    // GUI List bileşenlerini güncellemek için modeller
    private DefaultListModel<String> playerListModel;
    private DefaultListModel<String> roomListModel;

    public LobbyManager(DefaultListModel<String> playerModel, DefaultListModel<String> roomModel) {
        this.currentPlayers = new ArrayList<>();
        this.availableRooms = new ArrayList<>();
        this.playerListModel = playerModel;
        this.roomListModel = roomModel;
        
        // Başlangıç odaları ekleyelim
        availableRooms.add("Oda 1 (Boş)");
        availableRooms.add("Oda 2 (1/4)");
        roomListModel.addElement("Oda 1 (Boş)");
        roomListModel.addElement("Oda 2 (1/4)");
    }

    // Oyuncu lobiye katıldığında
    public boolean joinLobby(String playerName) {
        if (playerName == null || playerName.trim().isEmpty() || currentPlayers.contains(playerName)) {
            return false; // Geçersiz isim veya zaten lobide
        }
        currentPlayers.add(playerName);
        playerListModel.addElement(playerName);
        return true;
    }

    // Oyuncu lobiden ayrıldığında
    public boolean leaveLobby(String playerName) {
        if (currentPlayers.contains(playerName)) {
            currentPlayers.remove(playerName);
            playerListModel.removeElement(playerName);
            return true;
        }
        return false;
    }

    // Yeni oda oluşturma (basit bir simülasyon)
    public void createRoom(String roomName) {
        if (!availableRooms.contains(roomName)) {
            availableRooms.add(roomName);
            roomListModel.addElement(roomName);
        }
    }
    
    // Mevcut oyuncu sayısını döndürür
    public int getPlayerCount() {
        return currentPlayers.size();
    }
}