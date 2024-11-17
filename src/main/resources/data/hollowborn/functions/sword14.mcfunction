summon area_effect_cloud ^ ^1 ^5 {Tags:[skill,sw14],Duration:20,PersistenceRequired:1,CustomName:"\"\"",Silent:1,NoAI:1,Invisible:1b,NoBasePlate:1b,NoGravity:1b}
summon area_effect_cloud ^5 ^1 ^5 {Tags:[skill,sw14],Duration:20,PersistenceRequired:1,CustomName:"\"\"",Silent:1,NoAI:1,Invisible:1b,NoBasePlate:1b,NoGravity:1b}
summon area_effect_cloud ^-5 ^1 ^5 {Tags:[skill,sw14],Duration:20,PersistenceRequired:1,CustomName:"\"\"",Silent:1,NoAI:1,Invisible:1b,NoBasePlate:1b,NoGravity:1b}

effect give @s minecraft:instant_health 1 6
effect give @s minecraft:regeneration 10 6
effect give @s minecraft:absorption 10 6

tag @s add sw14
scoreboard players set @s sw14 40
scoreboard players set @s cool 60