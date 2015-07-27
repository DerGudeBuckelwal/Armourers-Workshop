
package riskyken.armourersWorkshop.client.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.model.ClientModelCache;
import riskyken.armourersWorkshop.client.model.equipmet.IEquipmentModel;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.EquipmentNBTHelper;

/**
 * Helps render item stacks.
 * 
 * @author RiskyKen
 *
 */

@SideOnly(Side.CLIENT)
public final class ItemStackRenderHelper {

    public static void renderItemAsArmourModel(ItemStack stack) {
        if (EquipmentNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinPointer = EquipmentNBTHelper.getSkinPointerFromStack(stack);
            renderItemModelFromSkinPointer(skinPointer);
        }
    }
    
    public static void renderItemModelFromSkinPointer(SkinPointer skinPointer) {
        int skinId = skinPointer.getSkinId();
        ISkinType skinType = skinPointer.getSkinType();
        
        IEquipmentModel targetModel = EquipmentModelRenderer.INSTANCE.getModelForEquipmentType(skinType);
        if (targetModel == null) {
            renderSkinWithoutHelper(skinPointer);
            return;
        }
        
        Skin data = ClientModelCache.INSTANCE.getEquipmentItemData(skinId);
        if (data == null) {
            return;
        }
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        
        
        
        if (skinType == SkinTypeRegistry.skinHead) {
            GL11.glTranslatef(0F, 0.2F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinChest) {
            GL11.glTranslatef(0F, -0.35F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinLegs) {
            GL11.glTranslatef(0F, -1.2F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinSkirt) {
            GL11.glTranslatef(0F, -1.0F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinFeet) {
            GL11.glTranslatef(0F, -1.2F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinSword) {
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinBow) {
            targetModel.render(null, null, data);
        }
        
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public static void renderSkinWithoutHelper(SkinPointer skinPointer) {
        Skin skin = ClientModelCache.INSTANCE.getEquipmentItemData(skinPointer.skinId);
        if (skin == null) {
            return;
        }
        skin.onUsed();
        for (int i = 0; i < skin.getParts().size(); i++) {
            SkinPart skinPart = skin.getParts().get(i);
            EquipmentPartRenderer.INSTANCE.renderPart(skinPart, 0.0625F);
        }
    }
}
