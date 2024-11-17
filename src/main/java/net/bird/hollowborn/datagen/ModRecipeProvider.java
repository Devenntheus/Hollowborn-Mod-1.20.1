package net.bird.hollowborn.datagen;

import net.bird.hollowborn.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HOLLOWBORN_SWORD_OF_DOOM, 1)
                .pattern(" 23")
                .pattern("456")
                .pattern("78 ")
                .input('2', ModItems.NECROTIC_SWORD_AURA)
                .input('3', ModItems.NECROTIC_SWORD_BLADE)
                .input('4', ModItems.DOOM_DRAGON_SKULL)
                .input('5', ModItems.NECROTIC_SWORD_HILT)
                .input('6', ModItems.VOID_AURA)
                .input('7', ModItems.CALADBOLG)
                .input('8', ModItems.DOOM_HEART)
                .criterion(hasItem(ModItems.NECROTIC_SWORD_AURA), conditionsFromItem(ModItems.HOLLOWBORN_SWORD_OF_DOOM))
                .criterion(hasItem(ModItems.NECROTIC_SWORD_BLADE), conditionsFromItem(ModItems.HOLLOWBORN_SWORD_OF_DOOM))
                .criterion(hasItem(ModItems.DOOM_DRAGON_SKULL), conditionsFromItem(ModItems.HOLLOWBORN_SWORD_OF_DOOM))
                .criterion(hasItem(ModItems.NECROTIC_SWORD_HILT), conditionsFromItem(ModItems.HOLLOWBORN_SWORD_OF_DOOM))
                .criterion(hasItem(ModItems.VOID_AURA), conditionsFromItem(ModItems.HOLLOWBORN_SWORD_OF_DOOM))
                .criterion(hasItem(ModItems.CALADBOLG), conditionsFromItem(ModItems.HOLLOWBORN_SWORD_OF_DOOM))
                .criterion(hasItem(ModItems.DOOM_HEART), conditionsFromItem(ModItems.HOLLOWBORN_SWORD_OF_DOOM))
                .offerTo(consumer, new Identifier(getRecipeName(ModItems.HOLLOWBORN_SWORD_OF_DOOM)));
    }
}
