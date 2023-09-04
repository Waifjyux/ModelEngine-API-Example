package de.waifjyux.custommobhandler;

import com.ticxo.modelengine.api.ModelEngineAPI;
import de.waifjyux.custommobhandler.commands.Spawn;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main plugin;
    public static ModelEngineAPI api;
    @Override
    public void onEnable() {
        plugin = this;

        getCommand("spawn").setExecutor(new Spawn());

    }

    public static Main getPlugin() {
        return plugin;
    }
}
