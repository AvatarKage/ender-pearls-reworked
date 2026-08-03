execute \
    if score @s ender_pearls_reworked.is_sneaking matches 1.. \
    run function ender_pearls_reworked:teleportation/cancel

execute \
    unless entity @s[nbt={RootVehicle:{}}] \
    run function ender_pearls_reworked:teleportation/cancel

execute \
    if entity @s[nbt={RootVehicle:{Entity:{inGround:1b}}}] \
    run function ender_pearls_reworked:teleportation/cancel
