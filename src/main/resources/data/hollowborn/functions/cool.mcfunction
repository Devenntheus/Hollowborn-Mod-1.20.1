execute if entity @s[scores={cool=60}] unless entity @a[tag=notip] run title @s actionbar ["",{"text":"CoolDown: 3s","bold":true,"color":"red"}]
execute if entity @s[scores={cool=40}] unless entity @a[tag=notip] run title @s actionbar ["",{"text":"CoolDown: 2s","bold":true,"color":"red"}]
execute if entity @s[scores={cool=20}] unless entity @a[tag=notip] run title @s actionbar ["",{"text":"CoolDown: 1s","bold":true,"color":"red"}]
execute if entity @s[scores={cool=0}] unless entity @a[tag=notip] run playsound entity.arrow.hit_player voice @a ~ ~ ~ 2 2
execute if entity @s[scores={cool=0}] unless entity @a[tag=notip] run title @s actionbar ["",{"text":"Skill Ready!","bold":true,"color":"green"}]