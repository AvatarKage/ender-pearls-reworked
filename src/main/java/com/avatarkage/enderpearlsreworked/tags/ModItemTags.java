package com.avatarkage.enderpearlsreworked.tags;

import com.avatarkage.enderpearlsreworked.EnderPearlsReworkedMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {

    public static final TagKey<Item> PEARLS = TagKey.of(
            RegistryKeys.ITEM,
            Identifier.of(EnderPearlsReworkedMod.MOD_ID, "pearls")
    );
}
