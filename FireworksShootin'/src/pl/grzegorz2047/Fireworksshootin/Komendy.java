package pl.grzegorz2047.Fireworksshootin;
 
import java.util.ArrayList;
import java.util.List;
 
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Komendy extends JavaPlugin {
	int IleSpawnuje = 0;
	 int ilosc=0;
    @Override
	public void onEnable() {
    	saveDefaultConfig();

        
	}

 /*   protected void add(File f, int x,int y,int z){
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(f, true));
            write.newLine();
                write.write(x);
            write.flush();
            write.close();
        } catch (FileNotFoundException e) {
            this.getServer().broadcastMessage(ChatColor.RED + "ForceChat failed to access it's log file. Reason: Does not exist!");
            e.printStackTrace();
        } catch(IOException e) {
            System.out.print("Unable to access file. Does the file exist? File:" + f.getName());
            e.printStackTrace();
        }
    }
*/
    
    
    
    
    
    
    
    
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) { // If not player, return.
            return false;
        }
        
        final Player player = (Player) sender; // Cast command sender to player

        if (commandLabel.equalsIgnoreCase("fw")){
        	
        	if(args.length>2 && args.length <4){
        		if(args[0].equalsIgnoreCase("start") && player.hasPermission("fw.start")|| player.isOp() ){
        			player.sendMessage("Firing fireworks started");
        			IleSpawnuje++;
        			int Odstep = Integer.parseInt(args[1]);
                    final int a = (int) player.getLocation().getX();
                    final int y = (int) player.getLocation().getY();
                    final int z = (int) player.getLocation().getZ();
                    
                    this.getConfig().set("x", a);
                    this.getConfig().set("y", y);
                    this.getConfig().set("z", z);
                    this.saveConfig();
        			if(Odstep <1)
        			{
        				player.sendMessage("Odstep czasowy nie moze byc ujemny lub zerowy !");
        				return false;
        			}
        			int czas = (int) (Odstep*10L);//czas do configu.
        			ilosc = Integer.parseInt(args[2]);//Ile ma wypuszczac na raz fajerwerkow.
        			  Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
       
        				  public void run(){

                     List<Color> c = new ArrayList<Color>();
                    c.add(Color.PURPLE);
                    c.add(Color.AQUA);
                    c.add(Color.BLACK);
                    c.add(Color.BLUE);
                    c.add(Color.FUCHSIA);
                    c.add(Color.GRAY);
                    c.add(Color.GREEN);
                    c.add(Color.LIME);
                    c.add(Color.MAROON);
                    c.add(Color.NAVY);
                    c.add(Color.OLIVE);
                    c.add(Color.ORANGE);
                    c.add(Color.PURPLE);
                    c.add(Color.RED);
                    c.add(Color.SILVER);
                    c.add(Color.TEAL);
                    c.add(Color.WHITE);
                    c.add(Color.YELLOW);
                    
                     
                     
                     
                     
                    for(int h=0;h<=ilosc;h++){
                     for(int x=0;x<=ilosc;x++){
                         int power = (int)(Math.random()*2)+1;
                    	 int type = (int)(Math.random()*5)+1;
                         Type losuj = Type.STAR;
                         if (type == 1) losuj = Type.BALL;
                         if (type == 2) losuj = Type.BALL_LARGE;
                         if (type == 3) losuj = Type.BURST;
                         if (type == 4) losuj = Type.CREEPER;
                         if (type == 5) losuj = Type.STAR;
                    	 Firework fireworks = (Firework) player.getWorld().spawnEntity(new Location(player.getWorld(), a+x, y,z+h), EntityType.FIREWORK); //  Spawn firework
                    	 FireworkMeta fireworkmeta = fireworks.getFireworkMeta();
                    	 
                    	 for(int n=0;n<=ilosc;n++){
                    		 int randomcolor = (int)(Math.random()*18);
                    		 power = (int)(Math.random()*3)+1;
                    		 type = (int)(Math.random()*5)+1;
                    	 FireworkEffect e = FireworkEffect.builder().flicker(false).withColor(c.get(randomcolor)).withFade(c).with(losuj).trail(false).build();
                    	 fireworkmeta.addEffect(e);
                         fireworkmeta.setPower(power);
                         fireworks.setFireworkMeta(fireworkmeta);
                    	 }
                     }
                     }
              	 
              	 }
              	 },0, czas);
        		
        	}
        	}
    		
        		
        	else if(args.length==1){
        		/*if(args[0].equalsIgnoreCase("stop") && player.hasPermission("fw.stop")|| player.isOp()){
        			this.getServer().getScheduler().cancelTasks(this);
        			player.sendMessage("location Firing firework stopped. " + IleSpawnuje + " remain");
        			if(IleSpawnuje <=0){
        				
        				IleSpawnuje=0;
        				return false;
        			}
        			else if(IleSpawnuje >0) {IleSpawnuje--;}
        			return true;
        		}
        		*/
        		
        		if(args[0].equalsIgnoreCase("stopall") && player.hasPermission("fw.stopall")|| player.isOp()){
        			
        			for(int w=ilosc;w>=0;w--){
        				this.getServer().getScheduler().cancelTasks(this);
        			player.sendMessage("location Firing firework all stopped.");
        			if(IleSpawnuje <=0){
        				
        				IleSpawnuje=0;
        				return false;
        			}
        			else if(IleSpawnuje >0) {IleSpawnuje--;}
        			}
        			return true;
        			
        		}
        		
        		
        		
        	}
        	else
        	{
        		player.sendMessage("Too few/many arguments");
        		return false;
        	}
 
        }
		return false;
	}
}
/*
TODO:
Do wyboru wygl¹d spawnu square point line losowe miejsca
pomijanie argumentu czasu wtedy defaultowy czas
Oddzielna komenda do randomu salwami(jeden typ) 



*/