package fr.entasia.factools.listeners;

import fr.entasia.apis.nbt.NBTComponent;
import fr.entasia.apis.other.ItemBuilder;
import fr.entasia.factools.Main;
import fr.entasia.factools.Utils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class QualityCraftingListeners implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack craftOutput = e.getInventory().getResult();
        ItemStack craftResult = e.getRecipe().getResult();
        if (craftOutput == null) return;
        if (craftOutput.getType().toString().endsWith("SWORD")){
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack hey = new ItemBuilder(craftOutput).fakeEnchant().build();
                    AttributeModifier mod = new AttributeModifier(UUID.randomUUID(), "spood", 500.0, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
                    ItemMeta meta = hey.getItemMeta();
                    meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, mod);
                    hey.setItemMeta(meta);

                    e.getView().setCursor(hey);
                }
            }.runTaskLater(Main.main, 1);

        }
    }
}
