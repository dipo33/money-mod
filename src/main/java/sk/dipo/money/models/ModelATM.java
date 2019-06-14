package sk.dipo.money.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelATM extends ModelBase {

	ModelRenderer Spodok;
	ModelRenderer Vrch;
	ModelRenderer Zadok;
	ModelRenderer Vlavo;
	ModelRenderer Vpravo;
	ModelRenderer Stena;
	ModelRenderer Displej;
	ModelRenderer Hore;
	ModelRenderer Dole;

	public ModelATM() {
		textureWidth = 128;
		textureHeight = 32;

		Spodok = new ModelRenderer(this, 0, 0);
		Spodok.addBox(0F, 0F, 0F, 16, 1, 16);
		Spodok.setRotationPoint(-8F, 23F, -8F);
		Spodok.setTextureSize(128, 32);
		Spodok.mirror = true;
		setRotation(Spodok, 0F, 0F, 0F);
		Vrch = new ModelRenderer(this, 0, 64);
		Vrch.addBox(0F, 0F, 0F, 16, 1, 16);
		Vrch.setRotationPoint(-8F, 8F, -8F);
		Vrch.setTextureSize(128, 32);
		Vrch.mirror = true;
		setRotation(Vrch, 0F, 0F, 0F);
		Zadok = new ModelRenderer(this, 94, 17);
		Zadok.addBox(0F, 0F, 0F, 16, 14, 1);
		Zadok.setRotationPoint(-8F, 9F, 7F);
		Zadok.setTextureSize(128, 32);
		Zadok.mirror = true;
		setRotation(Zadok, 0F, 0F, 0F);
		Vlavo = new ModelRenderer(this, 30, 3);
		Vlavo.addBox(0F, 0F, 0F, 1, 14, 15);
		Vlavo.setRotationPoint(-8F, 9F, -8F);
		Vlavo.setTextureSize(128, 32);
		Vlavo.mirror = true;
		setRotation(Vlavo, 0F, 0F, 0F);
		Vpravo = new ModelRenderer(this, 62, 3);
		Vpravo.addBox(0F, 0F, 0F, 1, 14, 15);
		Vpravo.setRotationPoint(7F, 9F, -8F);
		Vpravo.setTextureSize(128, 32);
		Vpravo.mirror = true;
		setRotation(Vpravo, 0F, 0F, 0F);
		Stena = new ModelRenderer(this, 0, 5);
		Stena.addBox(0F, 0F, 0F, 5, 14, 13);
		Stena.setRotationPoint(2F, 9F, -6F);
		Stena.setTextureSize(128, 32);
		Stena.mirror = true;
		setRotation(Stena, 0F, 0F, 0F);
		Displej = new ModelRenderer(this, 49, 1);
		Displej.addBox(0F, 0F, 0F, 9, 6, 3);
		Displej.setRotationPoint(-7F, 13F, -2F);
		Displej.setTextureSize(128, 32);
		Displej.mirror = true;
		setRotation(Displej, 0F, 0F, 0F);
		Hore = new ModelRenderer(this, 114, 4);
		Hore.addBox(0F, 0F, 0F, 9, 6, 1);
		Hore.setRotationPoint(-7F, 9F, -6F);
		Hore.setTextureSize(128, 32);
		Hore.mirror = true;
		setRotation(Hore, 0.7853982F, 0F, 0F);
		Dole = new ModelRenderer(this, 2, 3);
		Dole.addBox(0F, 0F, 0F, 9, 7, 1);
		Dole.setRotationPoint(-7F, 17F, -2F);
		Dole.setTextureSize(128, 32);
		Dole.mirror = true;
		setRotation(Dole, -0.3926991F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Spodok.render(f5);
		Vrch.render(f5);
		Zadok.render(f5);
		Vlavo.render(f5);
		Vpravo.render(f5);
		Stena.render(f5);
		Displej.render(f5);
		Hore.render(f5);
		Dole.render(f5);
	}

	public void renderModel(float f) {
		Spodok.render(f);
		Vrch.render(f);
		Zadok.render(f);
		Vlavo.render(f);
		Vpravo.render(f);
		Stena.render(f);
		Displej.render(f);
		Hore.render(f);
		Dole.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}