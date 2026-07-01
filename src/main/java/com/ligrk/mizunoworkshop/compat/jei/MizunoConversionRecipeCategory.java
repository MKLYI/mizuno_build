package com.ligrk.mizunoworkshop.compat.jei;

import com.ligrk.mizunoworkshop.MizunoWorkshop;
import com.ligrk.mizunoworkshop.registry.ModBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MizunoConversionRecipeCategory implements IRecipeCategory<MizunoConversionJeiRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(MizunoWorkshop.MOD_ID, "mizuno_workshop");
    public static final RecipeType<MizunoConversionJeiRecipe> TYPE = new RecipeType<>(UID, MizunoConversionJeiRecipe.class);

    private final IDrawableStatic background;
    private final IDrawable icon;

    public MizunoConversionRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(MizunoWorkshop.MOD_ID, "textures/gui/container/mizuno_workshop.png");
        this.background = guiHelper.createDrawable(texture, 8, 17, 160, 54);
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.MIZUNO_WORKSHOP.get()));
    }

    @Override
    public RecipeType<MizunoConversionJeiRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.mizuno_build.mizuno_workshop");
    }

    @Override
    public int getWidth() {
        return background.getWidth();
    }

    @Override
    public int getHeight() {
        return background.getHeight();
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MizunoConversionJeiRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 12, 17).addItemStack(recipe.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 136, 17).addItemStack(recipe.output());
    }

    @Override
    public void draw(MizunoConversionJeiRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics);
    }
}
