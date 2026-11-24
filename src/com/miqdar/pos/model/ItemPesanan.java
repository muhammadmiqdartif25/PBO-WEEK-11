package com.miqdar.pos.model;

public class ItemPesanan {
    private Produk produk;
    private int qty;

    public ItemPesanan(Produk produk, int qty){
        this.produk = produk;
        this.qty = qty;
    }

    public void tambahQty(int qty){
        this.qty += qty;
    }

    public int getQty(){
        return qty;
    }

    public Produk getProduk(){
        return produk;
    }

    public int getSubtotal(){
        return qty * produk.getHarga();
    }
}
