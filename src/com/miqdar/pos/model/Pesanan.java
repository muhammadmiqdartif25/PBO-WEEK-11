package com.miqdar.pos.model;

import java.text.*;
import java.util.*;

public class Pesanan {
    private Map<String, ItemPesanan> item = new LinkedHashMap<>();

    public void tambahPesanan(Produk produk, int qty){
        ItemPesanan existing = item.get(produk.getId());
        if (existing != null) {
            existing.tambahQty(qty);
        } else {
            item.put(produk.getId(), new ItemPesanan(produk, qty));
        }
    }

    public Collection<ItemPesanan> getItem(){
        return item.values();
    }

    public int getTotal(){
        int total = 0;
        for (ItemPesanan i : item.values()) {
            total += i.getSubtotal();
        }
        return total;
    }

    public int getPoin(){
        return getTotal() / 1000;
    }

    public boolean isKosong(){
        return item.isEmpty();
    }

    public void kosongkan(){
        item.clear();
    }

    public static String rupiah(int nilai){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("id", "ID"));
        DecimalFormat formatter = new DecimalFormat("'Rp' #,##0", symbols);
        return formatter.format(nilai);
    }
}
