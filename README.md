# POIN Off-Sales (POS Sederhana)

Aplikasi kasir (Point of Sale) sederhana berbasis Java Swing. Dibuat untuk Praktikum PBO Week 11 (Graphical User Interface).

| | |
|---|---|
| Nama | Muhammad Miqdar Salim |
| NIM | 251511050 |
| Kelas | 2B |

## Fitur

- Daftar produk (5 produk tetap) dalam tabel.
- Add To Cart dengan jumlah (Qty). Produk yang sama ditambahkan ke baris yang sama.
- Total belanja dan poin (1 poin per Rp1.000) diperbarui otomatis.
- Checkout menampilkan struk dan mengosongkan keranjang.
- Cetak struk lewat dialog print Java.

## Struktur Project

```
src/com/miqdar/pos/
├── Main.java
├── model/
│   ├── Produk.java        data produk (id, nama, harga)
│   ├── ItemPesanan.java   satu baris keranjang (produk, qty, subtotal)
│   └── Pesanan.java       keranjang: total, poin, format rupiah
└── ui/
    ├── MainFrame.java     jendela utama, menu, data produk
    ├── PanelProduk.java   tabel produk, qty, tombol Add To Cart
    └── PanelKeranjang.java tabel keranjang, total, Checkout, Cetak, struk
```

## Cara Menjalankan

1. Buka folder project di IntelliJ IDEA.
2. Jalankan class `com.miqdar.pos.Main`.

Atau lewat terminal (dari folder project, butuh JDK):

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out com.miqdar.pos.Main
```

## Cara Pakai

1. Pilih produk di tabel kiri, atur Qty, lalu klik **Add To Cart**.
2. Klik **Checkout** untuk melihat struk dan mendapatkan poin.
3. Klik **Cetak** untuk mencetak struk.
