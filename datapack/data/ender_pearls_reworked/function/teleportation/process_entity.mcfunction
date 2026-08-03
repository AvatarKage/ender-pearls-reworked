tag @s add ender_pearls_reworked_has_mount

data modify storage ender_pearls_reworked:owner CurrentOwner set from entity @s Owner

function ender_pearls_reworked:teleportation/begin with storage ender_pearls_reworked:owner
