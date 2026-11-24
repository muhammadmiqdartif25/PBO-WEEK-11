package com.miqdar.pos.ui;

import com.miqdar.pos.model.Pesanan;
import com.miqdar.pos.model.Produk;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelProduk extends JPanel {
    private List<Produk> daftar;
    private Pesanan pesanan;
    private PanelKeranjang panelKeranjang;
    private JTable tabel;
    private JLabel labelDipilih = new JLabel("Dipilih: -");
    private JSpinner spinnerQty = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));

    public PanelProduk(List<Produk> daftar, Pesanan pesanan, PanelKeranjang panelKeranjang) {
        this.daftar = daftar;
        this.pesanan = pesanan;
        this.panelKeranjang = panelKeranjang;
        setBorder(BorderFactory.createTitledBorder("Produk"));
        setLayout(new BorderLayout(5, 5));

        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nama Produk", "Harga (Rp)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Produk p : daftar) {
            model.addRow(new Object[]{p.getId(), p.getNama(), p.getHarga()});
        }
        tabel = new JTable(model);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabel.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                perbaruiDipilih();
            }
        });
        add(new JScrollPane(tabel), BorderLayout.CENTER);

        JPanel bawah = new JPanel();
        bawah.setLayout(new BoxLayout(bawah, BoxLayout.Y_AXIS));

        JPanel info = new JPanel(new FlowLayout(FlowLayout.LEFT));
        info.add(labelDipilih);

        JPanel baris = new JPanel(new FlowLayout(FlowLayout.LEFT));
        baris.add(new JLabel("Qty:"));
        baris.add(spinnerQty);
        JButton tombolAdd = new JButton("Add To Cart");
        tombolAdd.addActionListener(e -> tambahKeKeranjang());
        baris.add(tombolAdd);

        bawah.add(info);
        bawah.add(baris);
        add(bawah, BorderLayout.SOUTH);
    }

    private void perbaruiDipilih() {
        Produk p = getProdukTerpilih();
        if (p == null) {
            labelDipilih.setText("Dipilih: -");
        } else {
            labelDipilih.setText("Dipilih: " + p.getId() + " - " + p.getNama()
                    + " (" + Pesanan.rupiah(p.getHarga()) + ")");
        }
    }

    private Produk getProdukTerpilih() {
        int row = tabel.getSelectedRow();
        if (row < 0) {
            return null;
        }
        return daftar.get(tabel.convertRowIndexToModel(row));
    }

    private void tambahKeKeranjang() {
        Produk p = getProdukTerpilih();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Pilih produk dulu");
            return;
        }
        int qty = (Integer) spinnerQty.getValue();
        pesanan.tambahPesanan(p, qty);
        panelKeranjang.refresh();
    }
}
