package com.ligrk.mizunoworkshop.compat.jei;

import com.ligrk.mizunoworkshop.MizunoWorkshop;
import com.ligrk.mizunoworkshop.registry.ModBlocks;
import java.util.List;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class MizunoBuildJeiPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_UID = ResourceLocation.fromNamespaceAndPath(MizunoWorkshop.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new MizunoConversionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<MizunoConversionJeiRecipe> recipes = ModBlocks.convertedBlocks().stream()
                .map(block -> new MizunoConversionJeiRecipe(new ItemStack(block.input()), new ItemStack(block.output().get())))
                .toList();
        registration.addRecipes(MizunoConversionRecipeCategory.TYPE, recipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MIZUNO_WORKSHOP.get()), MizunoConversionRecipeCategory.TYPE);
    }
}
