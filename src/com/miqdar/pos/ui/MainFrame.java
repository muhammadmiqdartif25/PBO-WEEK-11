package com.miqdar.pos.ui;

import com.miqdar.pos.model.Pesanan;
import com.miqdar.pos.model.Produk;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("POIN Off-Sales - Java Swing");

        List<Produk> daftar = new ArrayList<>();
        daftar.add(new Produk("P001", "Air Mineral 600ml", 3000));
        daftar.add(new Produk("P002", "Kopi Sachet", 5000));
        daftar.add(new Produk("P003", "Roti Isi", 8000));
        daftar.add(new Produk("P004", "Snack Keripik", 6000));
        daftar.add(new Produk("P005", "Minuman Botol", 12000));

        Pesanan pesanan = new Pesanan();
        PanelKeranjang panelKeranjang = new PanelKeranjang(pesanan);
        PanelProduk panelProduk = new PanelProduk(daftar, pesanan, panelKeranjang);

        setLayout(new GridLayout(1, 2, 10, 10));
        add(panelProduk);
        add(panelKeranjang);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem itemKeluar = new JMenuItem("Keluar");
        itemKeluar.addActionListener(e -> System.exit(0));
        menuFile.add(itemKeluar);
        menuBar.add(menuFile);

        JMenu menuHelp = new JMenu("Help");
        JMenuItem itemTentang = new JMenuItem("Tentang");
        itemTentang.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "POIN Off-Sales - Java Swing"));
        menuHelp.add(itemTentang);
        menuBar.add(menuHelp);

        setJMenuBar(menuBar);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }
}
