# 📊 Oyun İstatistik Dashboard API'si (GameStatsApi)

Bu proje, bir oyunun temel performans göstergelerini (KPI) ve oyuncu etkileşim trendlerini sağlamak için C# ve ASP.NET Core kullanılarak geliştirilmiş minimalist bir arka plan (Backend) servisidir.

Ön yüz (Frontend) uygulamaları, Dashbord'ları veya analitik araçları bu API'yi kullanarak veri çekebilir.

## 🚀 Başlangıç

Bu API'yi yerel makinenizde çalıştırmak ve test etmek için aşağıdaki adımları izleyin.

### Ön Koşullar

Bu projeyi çalıştırmak için bilgisayarınızda şunların kurulu olması gerekir:

* [.NET 8.0 SDK](https://dotnet.microsoft.com/download) veya daha yeni bir sürümü.
* [Visual Studio Code (VS Code)](https://code.visualstudio.com/) veya [Visual Studio].

### Kurulum Adımları

1.  **Projeyi Klonlayın:**
    ```bash
    git clone [PROJE_GITHUB_ADRESİNİZİ_BURAYA_YAZIN]
    cd GameStatsApi
    ```

2.  **Bağımlılıkları Geri Yükleyin:**
    ```bash
    dotnet restore
    ```

3.  **Projeyi Çalıştırın:**
    ```bash
    dotnet run
    ```
    Sunucu başarıyla başladığında, terminalde API'nin çalıştığı adresi (`https://localhost:XXXX`) göreceksiniz.

---

## 💻 API Uç Noktaları (Endpoints)

API, istatistik verilerini `https://localhost:[Port]/Stats/...` yolu üzerinden sunar.

| Metot | Yol (Path) | Açıklama |
| :--- | :--- | :--- |
| `GET` | `/Stats/kpis` | Güncel **Kilit Tekil Metrikleri** (DAU, ARPPU gibi son gün değerleri) döner. |
| `GET` | `/Stats/daily-trend` | Son 7 güne ait **Günlük Aktif Kullanıcı (DAU)** trend verilerini döner. |
| `GET` | `/Stats/retention` | Oyuncu **Tutma Oranlarını** (Day 1, Day 7, Day 30) döner. |

### Test Etme

Sunucunuz çalışırken (genellikle port `7001` veya `5001`), tarayıcınızda veya Postman/Insomnia gibi bir araçta aşağıdaki adresi ziyaret ederek test edebilirsiniz:
