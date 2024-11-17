function hollowborn:cool

scoreboard players remove @s cool 1
scoreboard players remove @s sw14 1

execute if entity @s[nbt={SelectedItem:{tag:{hollowbornsword:1}}},scores={asneak=1}] run summon minecraft:interaction ^ ^1 ^0.5 {Tags:["detact"]}
execute if entity @s[scores={sw14=1}] run tag @s remove sw14
#execute if entity @s[nbt={Inventory:[{Slot:-106b,tag:{hollowbornsword:1}}]}] run function hollowborn:reload1
#execute if entity @s[nbt={Inventory:[{Slot:-106b,tag:{hollowbornsword:1}}]}] run function hollowborn:reload
execute if entity @s[nbt={SelectedItem:{tag:{sw14:1}}},scores={sk=1..}] unless score @s cool matches 1.. run function hollowborn:sword14
execute if entity @s[scores={sk=1}] run kill @e[type=interaction,distance=..2]

scoreboard players reset @s sk
scoreboard players reset @s asneak
scoreboard players reset @s playerHit