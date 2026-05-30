package com.avatarkage.enderpearlsreworked.tags;

import com.avatarkage.enderpearlsreworked.EnderPearlsReworkedMod;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModEntityTypeTags {

    public static final TagKey<EntityType<?>> PEARLS = TagKey.of(
            RegistryKeys.ENTITY_TYPE,
            Identifier.of(EnderPearlsReworkedMod.MOD_ID, "pearls")
    );
}
