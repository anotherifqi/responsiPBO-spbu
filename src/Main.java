public class Main {
    public static void main(String[] args) {
        System.out.println("===== SISTEM MANAJEMEN OPERASIONAL SPBU =====");
        System.out.println("[Sistem] SPBU: " + PompaBahanBakar.NAMA_SPBU);
        System.out.println("[Sistem] Kapasitas maksimal tangki: " + PompaBahanBakar.STOK_MAKSIMAL + " liter");

        // Penyiapan unit pompa Pertalite
        PompaPertalite pertalite = new PompaPertalite("P-01", 500);
        System.out.println("[Sistem] Unit P-01 siap melayani Pertalite.");
        System.out.println("[Pompa P-01] Kode unit: " + pertalite.getKodePompa());
        System.out.println("[Pompa P-01] Stok awal: " + pertalite.getStokLiter() + " liter");

        // Estimasi transaksi dan ringkasan unit
        double total10Liter = pertalite.hitungTotal(10);
        System.out.println("[Pompa P-01] Estimasi biaya 10 liter: Rp " + total10Liter);
        pertalite.tampilkanInfo();

        // Registrasi unit cadangan tanpa stok awal
        PompaPertalite pertaliteKosong = new PompaPertalite("P-02");
        System.out.println("[Sistem] Unit P-02 terdaftar dengan stok awal " + pertaliteKosong.getStokLiter() + " liter.");

        // Penyesuaian harga dan penambahan stok
        pertalite.setHargaPerLiter(10500);
        System.out.println("[Sistem] Harga Pertalite diperbarui menjadi Rp " + pertalite.getHargaPerLiter() + " per liter.");
        pertalite.isiStok(20);

        // Penyiapan unit Solar
        PompaBahanBakar solar = new PompaSolar("P-03", 1000);
        System.out.println("[Sistem] Unit P-03 siap melayani Solar.");
        pertalite.tampilkanInfo();
        solar.tampilkanInfo();

        // Operasional penyaluran bahan bakar
        System.out.println("[Sistem] Memproses pengisian Pertalite...");
        pertalite.salurkan(10);
        System.out.println("[Sistem] Memproses pengisian Solar...");
        solar.salurkan(20);
        solar.salurkan(30);

        // Validasi stok saat volume permintaan berubah
        System.out.println("[Sistem] Memproses pengisian Pertalite dalam volume besar...");
        pertalite.salurkan(100);
        System.out.println("[Pompa P-01] Stok setelah pengisian: " + pertalite.getStokLiter() + " liter.");
        System.out.println("[Sistem] Memproses permintaan pengisian melebihi stok...");
        pertalite.salurkan(10000);
        System.out.println("[Pompa P-01] Stok tidak berubah: " + pertalite.getStokLiter() + " liter.");

        // Pembayaran Pertalite melalui layanan pembayaran
        System.out.println("[Sistem] Memproses pembayaran via layanan digital...");
        Pembayaran transaksi = new PompaPertalite("P-04", 400);
        transaksi.bayar(20, 250000);
        double kembalian = transaksi.hitungKembalian(20, 250000);
        System.out.println("[Kasir] Kembalian pelanggan: Rp " + kembalian);

        // Pembayaran Solar melalui layanan pembayaran yang sama
        System.out.println("[Sistem] Memproses pembayaran Solar...");
        Pembayaran transaksiSolar = new PompaSolar("P-05", 200);
        transaksiSolar.bayar(30, 300000);
        System.out.println("[Kasir] Kembalian Solar: Rp " + transaksiSolar.hitungKembalian(30, 300000));

        // Pencetakan struk digital pelanggan
        System.out.println("[Sistem] Mencetak struk digital pelanggan...");
        LayananDigital layanan = (LayananDigital) transaksi;
        layanan.cetakStruk();
        System.out.println("[Sistem] Poin pelanggan tercatat: " + layanan.getPoin());

        System.out.println("===== OPERASIONAL SPBU SELESAI =====");
    }
}
