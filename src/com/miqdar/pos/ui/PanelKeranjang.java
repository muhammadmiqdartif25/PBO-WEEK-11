package com.miqdar.pos.ui;

import com.miqdar.pos.model.ItemPesanan;
import com.miqdar.pos.model.Pesanan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterException;

public class PanelKeranjang extends JPanel {
    private Pesanan pesanan;
    private DefaultTableModel model;
    private JLabel labelTotal = new JLabel("Total: Rp0,00");
    private JLabel labelPoints = new JLabel("Points: 0");
    private JTextArea areaStruk = new JTextArea(12, 30);

    public PanelKeranjang(Pesanan pesanan) {
        this.pesanan = pesanan;
        setBorder(BorderFactory.createTitledBorder("Keranjang"));
        setLayout(new BorderLayout(5, 5));

        model = new DefaultTableModel(new Object[]{"ID", "Nama Produk", "Qty", "Harga", "Subtotal"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);

        JPanel tengah = new JPanel(new BorderLayout(5, 5));

        JPanel info = new JPanel(new GridLayout(2, 1));
        labelTotal.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        info.add(labelTotal);
        info.add(labelPoints);

        JPanel tombol = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton tombolCheckout = new JButton("Checkout");
        tombolCheckout.addActionListener(e -> checkout());
        JButton tombolCetak = new JButton("Cetak");
        tombolCetak.addActionListener(e -> cetak());
        tombol.add(tombolCheckout);
        tombol.add(tombolCetak);

        JPanel atas = new JPanel(new BorderLayout(5, 5));
        atas.add(info, BorderLayout.NORTH);
        atas.add(tombol, BorderLayout.SOUTH);
        tengah.add(atas, BorderLayout.NORTH);

        areaStruk.setEditable(false);
        areaStruk.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JPanel panelStruk = new JPanel(new BorderLayout());
        panelStruk.setBorder(BorderFactory.createTitledBorder("Struk:"));
        panelStruk.add(new JScrollPane(areaStruk), BorderLayout.CENTER);
        tengah.add(panelStruk, BorderLayout.CENTER);

        add(tengah, BorderLayout.SOUTH);
        refresh();
    }

    public void refresh() {
        model.setRowCount(0);
        for (ItemPesanan i : pesanan.getItem()) {
            model.addRow(new Object[]{
                    i.getProduk().getId(),
                    i.getProduk().getNama(),
                    i.getQty(),
                    i.getProduk().getHarga(),
                    i.getSubtotal()
            });
        }
        labelTotal.setText("Total: " + Pesanan.rupiah(pesanan.getTotal()));
        labelPoints.setText("Points: " + pesanan.getPoin());
    }

    private void checkout() {
        if (pesanan.isKosong()) {
            JOptionPane.showMessageDialog(this, "Keranjang kosong");
            return;
        }
        tulisStruk();
        pesanan.kosongkan();
        refresh();
    }

    private void tulisStruk() {
        String garis = "==============================";
        areaStruk.setText("");
        areaStruk.append("***** POIN OFF-SALES *****\n");
        areaStruk.append("Toko: Demo Toko\n");
        areaStruk.append(garis + "\n");
        areaStruk.append("ID   Nama            Qty  Subtotal\n");
        areaStruk.append("------------------------------\n");
        for (ItemPesanan i : pesanan.getItem()) {
            areaStruk.append(String.format("%-4s %-15s %-4d %s\n",
                    i.getProduk().getId(), i.getProduk().getNama(),
                    i.getQty(), Pesanan.rupiah(i.getSubtotal())));
        }
        areaStruk.append("------------------------------\n");
        areaStruk.append("TOTAL:  " + Pesanan.rupiah(pesanan.getTotal()) + "\n");
        areaStruk.append("POINTS DIDAPAT: " + pesanan.getPoin() + " (1 point per Rp 1000)\n");
        areaStruk.append(garis + "\n");
        areaStruk.append("Terima kasih! Silakan kunjungi kembali.");
    }

    private void cetak() {
        if (areaStruk.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada struk");
            return;
        }
        try {
            areaStruk.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak: " + ex.getMessage());
        }
    }
}
