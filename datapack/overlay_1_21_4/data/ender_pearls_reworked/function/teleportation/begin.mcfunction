$tag @a[nbt={UUID:$(CurrentOwner)},limit=1] add ender_pearls_reworked_is_teleporting

$execute \
    as @a[nbt={UUID:$(CurrentOwner)},limit=1,gamemode=survival] \
    run scoreboard players set @s ender_pearls_reworked.previous_gamemode 0

$execute \
    as @a[nbt={UUID:$(CurrentOwner)},limit=1,gamemode=creative] \
    run scoreboard players set @s ender_pearls_reworked.previous_gamemode 1

$execute \
    as @a[nbt={UUID:$(CurrentOwner)},limit=1,gamemode=adventure] \
    run scoreboard players set @s ender_pearls_reworked.previous_gamemode 2

$execute \
    as @a[nbt={UUID:$(CurrentOwner)},limit=1] \
    run gamemode spectator

$attribute \
    @a[nbt={UUID:$(CurrentOwner)},limit=1] \
    minecraft:scale modifier add ender_pearls_reworked:teleportation -0.8 add_value

$scoreboard players set @a[nbt={UUID:$(CurrentOwner)},limit=1] ender_pearls_reworked.is_sneaking 0

$ride @a[nbt={UUID:$(CurrentOwner)},limit=1] mount @s
