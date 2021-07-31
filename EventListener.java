import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.*;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import java.util.Random;

public class EventListener implements Listener {
    private Random random = new Random();

    
    @EventHandler
    public void onDeathEvent(EntityDeathEvent event) {

	if(event.getEntity().getKiller() == null || !(event.getEntity().getKiller() instanceof Player)) {
	    return;
	}

	if(event.getEntity() instanceof Enderman) {
	    ArrayList<Integer> indexesToRemoveInIncreasingOrder = new ArrayList<Integer>();

	    int largestPearlDrop = 0;
	    
	    //first, if there is an ender pearl drop, remove it
	    for(int i = 0; i < event.getDrops().size(); ++i) {
		if(event.getDrops().get(i).getType() == Material.ENDER_PEARL) {
		    indexesToRemoveInIncreasingOrder.add(i);

		    if(event.getDrops().get(i).getAmount() > largestPearlDrop) {
			largestPearlDrop = event.getDrops().get(i).getAmount();
		    }
		}
	    }

	    while(indexesToRemoveInIncreasingOrder.size() > 0) {
		int indexToRemove = indexesToRemoveInIncreasingOrder.get(0);
		event.getDrops().remove(indexToRemove);
		indexesToRemoveInIncreasingOrder.remove(0);
		for(int i = 0; i < indexesToRemoveInIncreasingOrder.size(); ++i) {
		    indexesToRemoveInIncreasingOrder.set(i, indexesToRemoveInIncreasingOrder.get(i) + 1);
		}
	    }
	    
	    if(random.nextInt(100) < 66) {
		event.getDrops().add(new ItemStack(Material.ENDER_PEARL, (largestPearlDrop > 0) ? largestPearlDrop : 1));
	    }
	}else if(event.getEntity() instanceof Blaze) {
            ArrayList<Integer> indexesToRemoveInIncreasingOrder = new ArrayList<Integer>();

            int largestRodDrop = 0;

            //first, if there is a blaze rod drop, remove it                                                                                                                                             
            for(int i = 0; i < event.getDrops().size(); ++i) {
                if(event.getDrops().get(i).getType() == Material.BLAZE_ROD) {
                    indexesToRemoveInIncreasingOrder.add(i);

                    if(event.getDrops().get(i).getAmount() > largestRodDrop) {
                        largestRodDrop = event.getDrops().get(i).getAmount();
                    }
                }
            }

            while(indexesToRemoveInIncreasingOrder.size() > 0) {
                int indexToRemove = indexesToRemoveInIncreasingOrder.get(0);
                event.getDrops().remove(indexToRemove);
                indexesToRemoveInIncreasingOrder.remove(0);
                for(int i = 0; i < indexesToRemoveInIncreasingOrder.size(); ++i) {
                    indexesToRemoveInIncreasingOrder.set(i, indexesToRemoveInIncreasingOrder.get(i) + 1);
                }
            }
	    
            if(random.nextInt(100) < 66) {
                event.getDrops().add(new ItemStack(Material.BLAZE_ROD, (largestRodDrop > 0) ? largestRodDrop : 1));
            }
        }
    }

    @EventHandler
    public void barterEvent(PiglinBarterEvent event) {
	int ePearlDropCount = 0; 

	for(int i = 0; i < event.getOutcome().size(); ++i) {
	    if(event.getOutcome().get(i).getType() == Material.ENDER_PEARL) {
		ePearlDropCount = event.getOutcome().get(i).getAmount();
	    }
	}
	
	if(ePearlDropCount == 0 && random.nextInt(20) == 0) {
	    switch(random.nextInt(3)) {
	    case 0:
		ePearlDropCount = 2;
		break;
	    case 1:
		ePearlDropCount = 3;
		break;
	    case 2:
		ePearlDropCount = 4;
		break;
	    }
	    event.getOutcome().clear();
	    event.getOutcome().add(new ItemStack(Material.ENDER_PEARL, ePearlDropCount));
	}
    }
};
