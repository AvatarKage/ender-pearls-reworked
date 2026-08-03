execute \
    as @e[type=#ender_pearls_reworked:pearls,tag=!ender_pearls_reworked_has_mount] \
    at @s \
    run function ender_pearls_reworked:teleportation/process_entity

execute \
    as @a[tag=ender_pearls_reworked_is_teleporting] \
    run function ender_pearls_reworked:teleportation/check
