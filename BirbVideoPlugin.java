
import org.bukkit.plugin.java.JavaPlugin;

public class BirbVideoPlugin extends JavaPlugin {

    public BirbVideoPlugin() {
	super();
    }

    @Override
    public void onEnable() {
	getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
    
};
