package net.bird.hollowborn.datagen;

import net.bird.hollowborn.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CALADBOLG, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HOLLOWBORN_SWORD_OF_DOOM, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NECROTIC_SWORD_AURA, Models.GENERATED);
        itemModelGenerator.register(ModItems.NECROTIC_SWORD_BLADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.NECROTIC_SWORD_HILT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DOOM_HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.DOOM_DRAGON_SKULL, Models.GENERATED);
        itemModelGenerator.register(ModItems.VOID_AURA, Models.GENERATED);
    }
}
