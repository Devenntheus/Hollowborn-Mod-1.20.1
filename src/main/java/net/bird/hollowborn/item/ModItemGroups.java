package net.bird.hollowborn.item;

import net.bird.hollowborn.HollowbornMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup HOLLOWBORN_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(HollowbornMod.MOD_ID, "hollowborn"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.hollowborn"))
                    .icon(() -> new ItemStack(ModItems.VOID_AURA)).entries((displayContext, entries) -> {
                        entries.add(ModItems.HOLLOWBORN_SWORD_OF_DOOM);
                        entries.add(ModItems.CALADBOLG);
                        entries.add(ModItems.NECROTIC_SWORD_AURA);
                        entries.add(ModItems.NECROTIC_SWORD_BLADE);
                        entries.add(ModItems.NECROTIC_SWORD_HILT);
                        entries.add(ModItems.DOOM_HEART);
                        entries.add(ModItems.DOOM_DRAGON_SKULL);
                        entries.add(ModItems.VOID_AURA);
                    }).build());

    public static void registerItemGroups() {
        HollowbornMod.LOGGER.info("Registering Hollowborn Item Group for " + HollowbornMod.MOD_ID);
    }
}
