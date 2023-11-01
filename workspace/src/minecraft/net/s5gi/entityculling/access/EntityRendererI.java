package net.s5gi.entityculling.access;

import net.minecraft.entity.Entity;

public interface EntityRendererI<T extends Entity> {

    boolean shadowShouldShowName(T entity);

    void shadowRenderNameTag(T entity, double p_renderName_2_, double offsetX, double offsetY);

}