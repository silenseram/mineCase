package com.writer.mineCases.pojo;

import net.minecraft.item.ItemStack;


public class CaseItem {

    private ItemStack item;
    private int rarity;

    public CaseItem(ItemStack item, int rarity) {
        this.item = item;
        this.rarity = rarity;
    }

    public ItemStack getItemStack() {
        return item;
    }

    public int getRarity() {
        return rarity;
    }
}
