package com.derpyzombie.titanshiftersmod.mobs.render;

import com.derpyzombie.titanshiftersmod.mobs.entity.ErenEntity;
import com.derpyzombie.titanshiftersmod.mobs.model.ErenModel;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ErenRender extends LivingRenderer<ErenEntity, ErenModel> {

	public ErenRender(EntityRendererManager rendererManager, ErenModel entityModel, float shadowSize) {
		super(rendererManager, entityModel, shadowSize);
	}

	@Override
	public ResourceLocation getTextureLocation(ErenEntity erenEntity) {
		return new ResourceLocation("titanshiftersmod", "textures/entity/erenyeager.png");
	}
	
	public static class RenderFactory implements IRenderFactory<ErenEntity> {
	    public EntityRenderer<? super ErenEntity> createRenderFor(EntityRendererManager manager) {
	      return (EntityRenderer<? super ErenEntity>)new ErenRender(manager, new ErenModel(0.0F, false), 0.5F);
	    }
	 }
	
	

}
