package ml.govnoed.Stats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Main extends JavaPlugin implements Listener{
	
	List<Inventory> invs = new ArrayList<Inventory>();
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("stats")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("You cannot do this from console!");
				return true;
			}
			Player player = (Player) sender;
			player.openInventory(createInv(player));
		}
		return false;

	}
	
	public Inventory createInv(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, "Your stats:");
		// kills and damage
		inv.setItem(0, createItem(player, new ItemStack(Material.CREEPER_HEAD), "creeper_kills"));
		inv.setItem(1, createItem(player, new ItemStack(Material.ZOMBIE_HEAD), "zombie_kills"));
		inv.setItem(2, createItem(player, new ItemStack(Material.BONE), "skeleton_kills"));
		inv.setItem(3, createItem(player, new ItemStack(Material.ENDER_PEARL), "endermen_kills"));
		inv.setItem(4, createItem(player, new ItemStack(Material.SLIME_BALL), "slime_kills"));
		inv.setItem(5, createItem(player, new ItemStack(Material.GHAST_TEAR), "ghast_kills"));
		inv.setItem(6, createItem(player, new ItemStack(Material.IRON_SWORD), "mob_kills"));
		inv.setItem(7, createItem(player, new ItemStack(Material.SHIELD), "damage_taken"));
		inv.setItem(8, createItem(player, new ItemStack(Material.DIAMOND_SWORD), "damage_dealt"));
		// ores and stone
		inv.setItem(10, createItem(player, new ItemStack(Material.IRON_ORE), "iron"));
		inv.setItem(9, createItem(player, new ItemStack(Material.COAL_ORE), "coal"));
		inv.setItem(11, createItem(player, new ItemStack(Material.REDSTONE_ORE), "redstone"));
		inv.setItem(12, createItem(player, new ItemStack(Material.LAPIS_ORE), "lazurit"));
		inv.setItem(13, createItem(player, new ItemStack(Material.NETHER_QUARTZ_ORE), "quartz"));
		inv.setItem(14, createItem(player, new ItemStack(Material.GOLD_ORE), "gold"));
		inv.setItem(15, createItem(player, new ItemStack(Material.DIAMOND_ORE), "diamonds"));
		inv.setItem(16, createItem(player, new ItemStack(Material.ANCIENT_DEBRIS), "debris"));
		inv.setItem(17, createItem(player, new ItemStack(Material.STONE), "stone_mined"));
		// other
		inv.setItem(27, createItem(player, new ItemStack(Material.TORCH), "torches_crafted"));
		inv.setItem(29, createItem(player, new ItemStack(Material.CHEST), "open_chest"));
		inv.setItem(36, createItem(player, new ItemStack(Material.GRASS_BLOCK), "run"));
		inv.setItem(28, createItem(player, new ItemStack(Material.CAKE), "cake_crafted"));
		inv.setItem(34, createItem(player, new ItemStack(Material.RED_BED), "sleep"));
		inv.setItem(37, createItem(player, new ItemStack(Material.MINECART), "vagonetka_cm"));
		inv.setItem(45, createItem(player, new ItemStack(Material.BEDROCK), "sekretitempickup"));
		inv.setItem(40, createItem(player, new ItemStack(Material.NETHERRACK), "netherrack"));
		inv.setItem(33, createItem(player, new ItemStack(Material.WITHER_ROSE), "deaths"));
		inv.setItem(38, createItem(player, new ItemStack(Material.FIREWORK_ROCKET), "jump"));
		inv.setItem(32, createItem(player, new ItemStack(Material.TNT), "tnt_crafted"));
		inv.setItem(41, createItem(player, new ItemStack(Material.FISHING_ROD), "fish_caught"));
		inv.setItem(46, createItem(player, new ItemStack(Material.BEDROCK), "sekret_crafted"));
		inv.setItem(39, createItem(player, new ItemStack(Material.DIRT), "dirt"));
		inv.setItem(44, createItem(player, new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON), "item_drops"));
		inv.setItem(42, createItem(player, new ItemStack(Material.OAK_BOAT), "boat"));
		inv.setItem(43, createItem(player, new ItemStack(Material.WHEAT), "animals_sex"));
		inv.setItem(30, createItem(player, new ItemStack(Material.EMERALD), "trade"));
		inv.setItem(31, createItem(player, new ItemStack(Material.ENCHANTING_TABLE), "enchant"));
		inv.setItem(35, createItem(player, new ItemStack(Material.LEATHER_BOOTS), "walk"));
		

		
		invs.add(inv);
		
		System.out.print("Inventories list size is " + invs.size());
		
		return inv;
		
	}
	
	public ItemStack createItem(Player player, ItemStack item, String objName) {
		
		ItemMeta meta = item.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		
		Scoreboard board = player.getScoreboard();
		Objective obj = board.getObjective(objName);
		@SuppressWarnings("deprecation")
		Score score = obj.getScore(player.getName());
		lore.add(String.valueOf(score.getScore()));
		meta.setLore(lore);
		meta.setDisplayName(objName);
		item.setItemMeta(meta);
		
		return item;
	}
	
	@EventHandler()
	public void onClick(InventoryClickEvent event) {
		if (!invs.contains(event.getInventory()))
			return;
		
		event.setCancelled(true);
		return;
	}
	
	
	
	

}


















