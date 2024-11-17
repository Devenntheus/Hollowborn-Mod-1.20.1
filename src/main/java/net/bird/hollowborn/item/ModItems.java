package net.bird.hollowborn.item;

import net.bird.hollowborn.HollowbornMod;
import net.bird.hollowborn.item.custom.NecroticSwordOfDoom;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CALADBOLG = registerItem("caladbolg",
            new SwordItem(ModToolMaterial.DOOM, -7777701, 3f, new FabricItemSettings()));

    public static final Item HOLLOWBORN_SWORD_OF_DOOM = registerItem("hollowborn_sword_of_doom",
            new NecroticSwordOfDoom(ModToolMaterial.DOOM, -7777001, 3f, new FabricItemSettings()));

    public static final Item NECROTIC_SWORD_AURA = registerItem("necrotic_sword_aura", new Item(new FabricItemSettings()));
    public static final Item NECROTIC_SWORD_BLADE = registerItem("necrotic_sword_blade", new Item(new FabricItemSettings()));
    public static final Item NECROTIC_SWORD_HILT = registerItem("necrotic_sword_hilt", new Item(new FabricItemSettings()));
    public static final Item DOOM_HEART = registerItem("doom_heart", new Item(new FabricItemSettings()));
    public static final Item DOOM_DRAGON_SKULL = registerItem("doom_dragon_skull", new Item(new FabricItemSettings()));
    public static final Item VOID_AURA = registerItem("void_aura", new Item(new FabricItemSettings()));


    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(HollowbornMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        HollowbornMod.LOGGER.info("Registering Mod Items for " + HollowbornMod.MOD_ID);
    }
}
