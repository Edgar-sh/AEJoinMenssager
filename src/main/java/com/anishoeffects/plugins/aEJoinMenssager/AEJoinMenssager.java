package com.anishoeffects.plugins.aEJoinMenssager;

import com.anishoeffects.plugins.aEJoinMenssager.listener.JoinListener;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;


public final class AEJoinMenssager extends JavaPlugin {

    private Map<String, String> messages;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Verificando se diretorio (do plugin) já existe, caso não, cria o diretorio.
        if (!getDataFolder().exists()) {
            getLogger().info("Plugin folder not found, creating...");
            getDataFolder().mkdirs();
        } else {
            getLogger().info("Plugin folder already exists!");
        }
        // Copiando o "messages.json" para a pasta plugins(se ele não existir)
        File messageFile = new File(getDataFolder(), "messages.json");
        if (!messageFile.exists()){
            saveResource("messages.json", false);
        }
        // Carregando mensagens do arquivo para a memória
        loadMessages();

        getLogger().info("Plugin AEJoinMessage started successfully!");

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);

    }

    private void loadMessages() {
        File messageFile = new File(getDataFolder(), "messages.json");
        try (FileReader reader = new FileReader(messageFile)) {
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            this.messages = new Gson().fromJson(reader, type);
        } catch (IOException e) {
            getLogger().severe("Falha ao carregar messages.json");
            this.messages = Collections.emptyMap(); // Evitar erros se o arquivo fahar.
        }


    }
    public String getMessage(String key) {
        return messages.getOrDefault(key, "");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin AEJoinMessage disabled successfully!");
    }
}
