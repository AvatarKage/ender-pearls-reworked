ride @s dismount

execute \
    store result score @s ender_pearls_reworked.summon_endermite \
    run random value 1..20

execute \
    if score @s ender_pearls_reworked.summon_endermite matches 1 \
    at @s \
    if block ~ ~ ~ #minecraft:air \
    run summon minecraft:endermite ~ ~ ~ {PlayerSpawned:1b}

execute \
    if score @s ender_pearls_reworked.summon_endermite matches 1 \
    at @s \
    unless block ~ ~ ~ #minecraft:air \
    run summon minecraft:endermite ~ ~1 ~ {PlayerSpawned:1b}

execute \
    if score @s ender_pearls_reworked.previous_gamemode matches 0 \
    run gamemode survival @s

execute \
    if score @s ender_pearls_reworked.previous_gamemode matches 1 \
    run gamemode creative @s

execute \
    if score @s ender_pearls_reworked.previous_gamemode matches 2 \
    run gamemode adventure @s

attribute @s minecraft:scale modifier remove ender_pearls_reworked:teleportation

data modify storage ender_pearls_reworked:owner PlayerUUID set from entity @s UUID

function ender_pearls_reworked:teleportation/kill_entity with storage ender_pearls_reworked:owner

execute \
    at @s \
    unless block ~ ~ ~ #minecraft:air \
    run tp @s ~ ~1 ~

tag @s remove ender_pearls_reworked_is_teleporting

scoreboard players set @s ender_pearls_reworked.is_sneaking 0
