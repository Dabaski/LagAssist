package org.alvindimas05.lagassist.safety;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.alvindimas05.lagassist.Main;
import org.bukkit.Bukkit;

public class SafetyManager {

	public static boolean enabled = false;
	public static boolean crash_debug = false;
	
	public static void Enabler(boolean reload) {
		enabled = Main.config.getBoolean("safety-manager.enabled");
		crash_debug = Main.config.getBoolean("safety-manager.anti-crasher.settings.debug");
		if (!enabled) {
			return;
		}

		if (!reload) {
			
			try {
				long bytes = new File(".").getCanonicalFile().getUsableSpace();

				if (Main.config.getLong("safety-manager.no-space.startup-space") > bytes) {
					Bukkit.getLogger().warning("NOT ENOUGH MEMORY TO START SERVER. SHUTTING DOWN.");
					Bukkit.getServer().shutdown();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

//			SafetyAnticrash.startTask();
			startTask();
		}
		
		
		Bukkit.getLogger().info("    §e[§a✔§e] §fSafety Manager.");
	}

	public static void startTask() {
		Bukkit.getAsyncScheduler().runAtFixedRate(Main.p, (task) -> {

				long bytes;
				try {
					bytes = new File(".").getCanonicalFile().getUsableSpace();
					if (Main.config.getLong("safety-manager.no-space.shutdown-space") < bytes) {
						return;
					}

					Bukkit.getGlobalRegionScheduler().execute(Main.p, () -> {
							Bukkit.getLogger().warning(
									"Server doesn't have enough memory to keep running. Shutting down server!");
							Bukkit.getServer().shutdown();
					});
				} catch (IOException e) {
					e.printStackTrace();
				}

		}, 60L * 50L, 1L * 50L, TimeUnit.MILLISECONDS);
	}

}
