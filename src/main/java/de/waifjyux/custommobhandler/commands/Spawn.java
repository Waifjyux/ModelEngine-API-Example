package de.waifjyux.custommobhandler.commands;

import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.animation.AnimationHandler;
import com.ticxo.modelengine.api.entity.BaseEntity;
import com.ticxo.modelengine.api.generator.model.ModelBlueprint;
import com.ticxo.modelengine.api.model.ActiveModel;
import com.ticxo.modelengine.api.model.ModeledEntity;
import de.waifjyux.custommobhandler.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        Pig ent = (Pig) p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.PIG);
        ent.setSilent(true);
        ent.clearLootTable();


        ModelBlueprint blueprint = ModelEngineAPI.getBlueprint("beast");

        ModeledEntity modeledEntity = ModelEngineAPI.createModeledEntity(ent);
        modeledEntity.setBaseEntityVisible(false);
        ActiveModel activeModel = ModelEngineAPI.createActiveModel(blueprint);
        modeledEntity.addModel(activeModel, true);

        AnimationHandler animationHandler = activeModel.getAnimationHandler();

        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                animationHandler.forceStopAllAnimations();
                animationHandler.playAnimation("slam",0,0,1,true);
                animationHandler.playAnimation("walk",0,0,1,true);

            }
        },40);

        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                animationHandler.forceStopAllAnimations();
                animationHandler.playAnimation("dash",0,0,1,true);
                animationHandler.playAnimation("walk",0,0,1,true);
            }
        },20*6);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {

                ent.getPathfinder().moveTo(p.getLocation());


            }
        },0,5);

        return false;
    }
}
