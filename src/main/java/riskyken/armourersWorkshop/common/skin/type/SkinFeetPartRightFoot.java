package riskyken.armourersWorkshop.common.skin.type;

import java.awt.Point;

import org.lwjgl.opengl.GL11;

import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartTypeTextured;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.model.armourer.ModelLegs;
import riskyken.armourersWorkshop.common.skin.Point3D;
import riskyken.armourersWorkshop.common.skin.Rectangle3D;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkinFeetPartRightFoot extends AbstractSkinPartTypeBase implements ISkinPartTypeTextured {
    
    public SkinFeetPartRightFoot(ISkinType baseType) {
        super(baseType);
        this.buildingSpace = new Rectangle3D(-3, -13, -6, 7, 5, 10);
        this.guideSpace = new Rectangle3D(-2, -12, -2, 4, 12, 4);
        this.offset = new Point3D(-6, 0, 0);
    }
    
    @Override
    public String getPartName() {
        return "rightFoot";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderBuildingGuide(float scale, boolean showSkinOverlay, boolean showHelper) {
        GL11.glTranslated(0, this.buildingSpace.getY() * scale, 0);
        GL11.glTranslated(0, -this.guideSpace.getY() * scale, 0);
        ModelLegs.MODEL.renderRightLeg(scale);
        GL11.glTranslated(0, this.guideSpace.getY() * scale, 0);
        GL11.glTranslated(0, -this.buildingSpace.getY() * scale, 0);
    }
    
    @Override
    public Point getTextureLocation() {
        return new Point(0, 16);
    }

    @Override
    public boolean isTextureMirrored() {
        return false;
    }

    @Override
    public IPoint3D getTextureModelSize() {
        return new Point3D(4, 12, 4);
    }
}